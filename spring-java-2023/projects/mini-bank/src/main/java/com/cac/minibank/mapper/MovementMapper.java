package com.cac.minibank.mapper;

import com.cac.minibank.dto.response.MovementResponseDTO;
import com.cac.minibank.model.movement.*;
import org.springframework.stereotype.Component;

@Component
public class MovementMapper {

    public MovementResponseDTO toDto(Movement movement){
        MovementResponseDTO movementResponseDTO = new MovementResponseDTO();
        movementResponseDTO.setMovementId(movement.getMovementId());
        movementResponseDTO.setRealizationDateTime(movement.getRealizationDateTime());
        movementResponseDTO.setAmount(movement.getAmount());
        movementResponseDTO.setDescription(movement.getDescription());
        movementResponseDTO.setAccount(movement.getAccount().getAccountId());

        if (movement instanceof DepositAndWithdrawal) {
            DepositAndWithdrawal depositAndWithdrawal = (DepositAndWithdrawal) movement;
            movementResponseDTO.setCashier(depositAndWithdrawal.getCashier());
        }

        if (movement instanceof OutgoingTransfer) {
            OutgoingTransfer outgoingTransfer = (OutgoingTransfer) movement;
            movementResponseDTO.setDestinationAccount(outgoingTransfer.getDestinationAccount().getAccountId());
        }

        if (movement instanceof IncomingTransfer) {
            IncomingTransfer incomingTransfer = (IncomingTransfer) movement;
            movementResponseDTO.setSourceAccount(incomingTransfer.getSourceAccount().getAccountId());
        }

        if (movement instanceof CurrencyExchange) {
            CurrencyExchange currencyExchange = (CurrencyExchange) movement;
            movementResponseDTO.setComission(currencyExchange.getComission());
            movementResponseDTO.setExchangeRate(currencyExchange.getExchangeRate());
        }

        return movementResponseDTO;
    }


}
