package com.cac.minibank.service;

import com.cac.minibank.dto.request.movement.DepositAndWithdrawalRequestDTO;
import com.cac.minibank.dto.request.movement.OutgoingTransferRequestDTO;
import com.cac.minibank.dto.response.MovementResponseDTO;
import com.cac.minibank.exceptions.AccountException;
import com.cac.minibank.exceptions.BadRequestException;
import com.cac.minibank.exceptions.MessageError;
import com.cac.minibank.exceptions.ResourceNotFoundException;
import com.cac.minibank.mapper.MovementMapper;
import com.cac.minibank.model.Account;
import com.cac.minibank.model.movement.*;
import com.cac.minibank.repository.AccountRepository;
import com.cac.minibank.repository.MovementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public MovementResponseDTO findById(Long id) throws ResourceNotFoundException {
        Movement movement = movementRepository.findByMovementId(id);

        if(movement == null){
            throw new ResourceNotFoundException(MessageError.MOVEMENT_ID_NOT_FOUND);
        }

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

    public void deposit(DepositAndWithdrawalRequestDTO depositAndWithdrawalRequestDTO) throws BadRequestException, ResourceNotFoundException {

        if(depositAndWithdrawalRequestDTO.getAmount().compareTo(BigDecimal.ZERO) < 0){
            throw new BadRequestException(MessageError.NEGATIVE_NUMBER_NOT_ALLOWED);
        }

        Account account = accountRepository.getAccountByAccountId(depositAndWithdrawalRequestDTO.getAccountId());

        if(account == null){
            throw new ResourceNotFoundException(MessageError.ACCOUNT_ID_NOT_FOUND);
        }

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

    }


    public void withdrawal(DepositAndWithdrawalRequestDTO depositAndWithdrawalRequestDTO) throws ResourceNotFoundException, BadRequestException, AccountException {
        Account account = accountRepository.getAccountByAccountId(depositAndWithdrawalRequestDTO.getAccountId());
        if(account == null){
            throw new ResourceNotFoundException(MessageError.ACCOUNT_ID_NOT_FOUND);
        }

        BigDecimal currentBalance = account.getCurrentBalance();

        BigDecimal amountToWithdrawal = depositAndWithdrawalRequestDTO.getAmount();

        if(amountToWithdrawal.compareTo(BigDecimal.ZERO) < 0){
            throw new BadRequestException(MessageError.NEGATIVE_NUMBER_NOT_ALLOWED);
        }
        if(currentBalance.compareTo(amountToWithdrawal) < 0){
            throw new AccountException(MessageError.ACCOUNT_NOT_HAVE_FUNDS);
        }

        DepositAndWithdrawal depositAndWithdrawal = new DepositAndWithdrawal();

        depositAndWithdrawal.setRealizationDateTime(LocalDateTime.now());
        depositAndWithdrawal.setAmount(depositAndWithdrawalRequestDTO.getAmount().negate());
        depositAndWithdrawal.setDescription(depositAndWithdrawalRequestDTO.getDescription());
        depositAndWithdrawal.setCashier(depositAndWithdrawalRequestDTO.getCashier());
        depositAndWithdrawal.setAccount(account);

        BigDecimal newBalance = currentBalance.subtract(amountToWithdrawal);
        accountService.updateBalance(depositAndWithdrawalRequestDTO.getAccountId(), newBalance);

        movementRepository.save(depositAndWithdrawal);

    }

    @Transactional(rollbackFor = Exception.class)
    public void transfer(OutgoingTransferRequestDTO outgoingTransferRequestDTO) throws ResourceNotFoundException, BadRequestException, AccountException {
        Account originAccount = accountRepository.getAccountByAccountId(outgoingTransferRequestDTO.getAccountId());
        Account destinationAccount = accountRepository.getAccountByAccountId(outgoingTransferRequestDTO.getDestinationAccountId());

        if(originAccount == null || destinationAccount == null){
            throw new ResourceNotFoundException(MessageError.ACCOUNT_ID_NOT_FOUND);
        }

        BigDecimal currentBalance = originAccount.getCurrentBalance();
        BigDecimal amountToTransfer = outgoingTransferRequestDTO.getAmount();

        if(amountToTransfer.compareTo(BigDecimal.ZERO) < 0){
            throw new BadRequestException(MessageError.NEGATIVE_NUMBER_NOT_ALLOWED);
        }
        if(currentBalance.compareTo(amountToTransfer) < 0){
            throw new AccountException(MessageError.ACCOUNT_NOT_HAVE_FUNDS);
        }

        OutgoingTransfer outgoingTransfer = new OutgoingTransfer();
        outgoingTransfer.setRealizationDateTime(LocalDateTime.now());
        outgoingTransfer.setAmount(amountToTransfer.negate());
        outgoingTransfer.setDescription(outgoingTransferRequestDTO.getDescription());
        outgoingTransfer.setAccount(originAccount);
        outgoingTransfer.setDestinationAccount(destinationAccount);

        IncomingTransfer incomingTransfer = new IncomingTransfer();
        incomingTransfer.setRealizationDateTime(LocalDateTime.now());
        incomingTransfer.setAmount(amountToTransfer);
        incomingTransfer.setDescription(outgoingTransferRequestDTO.getDescription());
        incomingTransfer.setAccount(destinationAccount);
        incomingTransfer.setSourceAccount(originAccount);

        BigDecimal newBalance = currentBalance.subtract(amountToTransfer);

        accountService.updateBalance(originAccount.getAccountId(), newBalance);
        accountService.updateBalance(destinationAccount.getAccountId(), amountToTransfer);

        movementRepository.save(outgoingTransfer);
        movementRepository.save(incomingTransfer);

    }

    public List<MovementResponseDTO> getMovementsByAccountId(Long accountId){
        List<Movement> movements = movementRepository.findMovementsByAccountAccountId(accountId);

        List<MovementResponseDTO> movementsResponseDTOs = movements
                .stream().map(movementMapper::toDto)
                .collect(Collectors.toList());

        return movementsResponseDTOs;
    }

}
