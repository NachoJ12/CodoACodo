package com.cac.minibank.service;

import com.cac.minibank.dto.request.AccountRequestDTO;
import com.cac.minibank.dto.response.AccountResponseDTO;
import com.cac.minibank.model.Account;
import com.cac.minibank.model.Currency;
import com.cac.minibank.model.Customer;
import com.cac.minibank.mapper.AccountMapper;
import com.cac.minibank.repository.AccountRepository;
import com.cac.minibank.repository.CurrencyRepository;
import com.cac.minibank.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final CurrencyRepository currencyRepository;
    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository, CustomerRepository customerRepository, CurrencyRepository currencyRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.currencyRepository = currencyRepository;
        this.accountMapper = accountMapper;
    }

    public AccountResponseDTO getAccountById(Long id){
        Account account = accountRepository.getAccountByAccountId(id);

        AccountResponseDTO accountResponseDTO = accountMapper.apply(account);

        return accountResponseDTO;
    }

    public List<AccountResponseDTO> getAccountsByHolderSurname(String surname){
        List<Account> accounts = accountRepository.getAccountsByAccountHolder_Surname(surname);

        List<AccountResponseDTO> accountsResponseDTO = accounts
                        .stream()
                        .map(account -> accountMapper.apply(account))
                .collect(Collectors.toList());

        return accountsResponseDTO;
    }

    public void createAccount(AccountRequestDTO accountRequestDTO){
        Random random = new Random();

        Customer customer = customerRepository.findByCustomerId(accountRequestDTO.getCustomerId());

        Account account = new Account();

        account.setNumber(random.nextLong(9999999));
        account.setCreationDate(LocalDate.now());
        account.setInitialBalance(BigDecimal.ZERO);
        account.setCurrentBalance(BigDecimal.ZERO);
        account.setAgreedOverdraft(BigDecimal.ZERO);
        account.setAccountHolder(customer);

        if(accountRequestDTO.getCurrencyId() != -1){
            Currency currency = currencyRepository.findByCurrencyId(accountRequestDTO.getCurrencyId());
            account.setCurrency(currency);
        } else {
            account.setCurrency(null);
        }

        Account savedAccount = accountRepository.save(account);

        List<Customer> jointAccountHolders  = new ArrayList<>();
        accountRequestDTO.getJointAccountHoldersId()
                .stream()
                .forEach(jointAccountId  -> {
                            Customer jointAccountHolder  = customerRepository.findByCustomerId(jointAccountId);
                            jointAccountHolders.add(jointAccountHolder);
                            jointAccountHolder.getJointAccounts().add(savedAccount); // Establecer la referencia de la cuenta en el cotitular
                        }
                );
        // Actualizar los cotitulares en la cuenta
        savedAccount.setJointAccountHolders(jointAccountHolders);
        accountRepository.save(savedAccount);

    }

    public void updateBalance(Long id, BigDecimal balance) {
        var account = accountRepository.getAccountByAccountId(id);
        account.setCurrentBalance(balance);
        accountRepository.save(account);
    }

}