package com.cac.minibank.controller;

import com.cac.minibank.dto.request.movement.DepositAndWithdrawalRequestDTO;
import com.cac.minibank.dto.response.MovementResponseDTO;
import com.cac.minibank.exceptions.AccountException;
import com.cac.minibank.exceptions.BadRequestException;
import com.cac.minibank.exceptions.ResourceNotFoundException;
import com.cac.minibank.response.ApiResponseHandler;
import com.cac.minibank.service.MovementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movements")
public class MovementController {

    private final MovementService movementService;

    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }

    @GetMapping("/byId/{id}")
    public MovementResponseDTO getMovementById(@PathVariable Long id) throws ResourceNotFoundException {
        return movementService.findById(id);
    }

    @GetMapping("/getAll")
    public List<MovementResponseDTO> getAllMovements(){
        return movementService.findAll();
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@Valid @RequestBody DepositAndWithdrawalRequestDTO depositAndWithdrawalRequestDTO) {
        try {
            movementService.deposit(depositAndWithdrawalRequestDTO);
            return ApiResponseHandler.generateResponse("Deposit successful", HttpStatus.OK, null);
        } catch (BadRequestException ex) {
            return ApiResponseHandler.generateResponseError(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException ex) {
            return ApiResponseHandler.generateResponseError(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<?> withdrawal(@Valid @RequestBody DepositAndWithdrawalRequestDTO depositAndWithdrawalRequestDTO) {
        try {
            movementService.withdrawal(depositAndWithdrawalRequestDTO);
            return ApiResponseHandler.generateResponse("Withdrawal succesful", HttpStatus.OK, null);
        } catch (BadRequestException ex) {
            return ApiResponseHandler.generateResponseError(ex.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException ex) {
            return ApiResponseHandler.generateResponseError(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (AccountException ex){
            return ApiResponseHandler.generateResponseError(ex.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

}
