package com.cac.minibank.service;

import com.cac.minibank.dto.request.movement.DepositAndWithdrawalRequestDTO;
import com.cac.minibank.dto.response.MovementResponseDTO;
import com.cac.minibank.mapper.MovementMapper;
import com.cac.minibank.model.Account;
import com.cac.minibank.model.movement.*;
import com.cac.minibank.repository.AccountRepository;
import com.cac.minibank.repository.MovementRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovementService {
    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;

    private final AccountService accountService;
    private final MovementMapper movementMapper;

    public MovementService(MovementRepository movementRepository, AccountRepository accountRepository, AccountService accountService, MovementMapper movementMapper) {
        this.movementRepository = movementRepository;
        this.accountRepository = accountRepository;
        this.accountService = accountService;
        this.movementMapper = movementMapper;
    }

    public MovementResponseDTO findById(Long id){
        Movement movement = movementRepository.findByMovementId(id);

        MovementResponseDTO movementResponseDTO = movementMapper.toDto(movement);

        return movementResponseDTO;
    }

    public List<MovementResponseDTO> findAll(){
        List<Movement> movements = movementRepository.findAll();

        List<MovementResponseDTO> movementResponseDTOs = movements
                .stream().map(movementMapper::toDto
                ).collect(Collectors.toList());

        return movementResponseDTOs;
    }

    public void deposit(DepositAndWithdrawalRequestDTO depositAndWithdrawalRequestDTO){

        if(depositAndWithdrawalRequestDTO.getAmount().compareTo(BigDecimal.ZERO) >= 0){
            Account account = accountRepository.getAccountByAccountId(depositAndWithdrawalRequestDTO.getAccountId());

            DepositAndWithdrawal depositAndWithdrawal = new DepositAndWithdrawal();

            depositAndWithdrawal.setRealizationDateTime(LocalDateTime.now());
            depositAndWithdrawal.setAmount(depositAndWithdrawalRequestDTO.getAmount());
            depositAndWithdrawal.setDescription(depositAndWithdrawalRequestDTO.getDescription());
            depositAndWithdrawal.setCashier(depositAndWithdrawalRequestDTO.getCashier());
            depositAndWithdrawal.setAccount(account);

            BigDecimal currentBalance = account.getCurrentBalance();
            BigDecimal newBalance = currentBalance.add(depositAndWithdrawalRequestDTO.getAmount());
            accountService.updateBalance(depositAndWithdrawalRequestDTO.getAccountId(), newBalance);

            movementRepository.save(depositAndWithdrawal);
        } else {
            throw new RuntimeException("No puede ingresar un número negativo");
        }

    }


    public void withdrawal(DepositAndWithdrawalRequestDTO depositAndWithdrawalRequestDTO){
        Account account = accountRepository.getAccountByAccountId(depositAndWithdrawalRequestDTO.getAccountId());
        BigDecimal currentBalance = account.getCurrentBalance();

        BigDecimal amountToWithdrawal = depositAndWithdrawalRequestDTO.getAmount();

        if(currentBalance.compareTo(amountToWithdrawal) >= 0 &&
                depositAndWithdrawalRequestDTO.getAmount().compareTo(BigDecimal.ZERO) >= 0){
            DepositAndWithdrawal depositAndWithdrawal = new DepositAndWithdrawal();

            depositAndWithdrawal.setRealizationDateTime(LocalDateTime.now());
            depositAndWithdrawal.setAmount(depositAndWithdrawalRequestDTO.getAmount().negate());
            depositAndWithdrawal.setDescription(depositAndWithdrawalRequestDTO.getDescription());
            depositAndWithdrawal.setCashier(depositAndWithdrawalRequestDTO.getCashier());
            depositAndWithdrawal.setAccount(account);

            BigDecimal newBalance = currentBalance.subtract(amountToWithdrawal);
            accountService.updateBalance(depositAndWithdrawalRequestDTO.getAccountId(), newBalance);

            movementRepository.save(depositAndWithdrawal);
        } else {
            throw new RuntimeException("No dispone saldo suficiente o ingreso numero menor a 0");
        }

    }


}
