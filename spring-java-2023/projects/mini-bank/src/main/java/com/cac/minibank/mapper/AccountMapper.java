package com.cac.minibank.mapper;

import com.cac.minibank.dto.response.AccountResponseDTO;
import com.cac.minibank.dto.response.CurrencyResponseDTO;
import com.cac.minibank.model.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class AccountMapper implements Function<Account, AccountResponseDTO> {

    public AccountResponseDTO apply(Account account) {
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();

        accountResponseDTO.setAccountId(account.getAccountId());
        accountResponseDTO.setNumber(account.getNumber());
        accountResponseDTO.setCreationDate(account.getCreationDate());
        accountResponseDTO.setInitialBalance(account.getInitialBalance());
        accountResponseDTO.setCurrentBalance(account.getCurrentBalance());
        accountResponseDTO.setAgreedOverdraft(account.getAgreedOverdraft());
        accountResponseDTO.setAccountClosingDate(account.getAccountClosingDate());

        if(account.getCurrency() != null){
            CurrencyResponseDTO currencyResponseDTO = new CurrencyResponseDTO();
            currencyResponseDTO.setCode(account.getCurrency().getCode());
            currencyResponseDTO.setName(account.getCurrency().getName());

            accountResponseDTO.setCurrency(currencyResponseDTO);
        }

        List<String> jointAccountHolders = new ArrayList<>();

        account.getJointAccountHolders()
                .stream()
                .forEach(jointAccountHolder ->
                        jointAccountHolders.add(jointAccountHolder.getSurname() + ", " + jointAccountHolder.getName())
                );

        accountResponseDTO.setJointAccountHolders(jointAccountHolders);
        accountResponseDTO.setAccountHolderFullName(account.getAccountHolder().getSurname() + ", " + account.getAccountHolder().getName() );

        return accountResponseDTO;
    }

}