package com.cac.minibank.controller;

import com.cac.minibank.dto.request.movement.DepositAndWithdrawalRequestDTO;
import com.cac.minibank.dto.response.MovementResponseDTO;
import com.cac.minibank.service.MovementService;
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
    public MovementResponseDTO getMovementById(@PathVariable Long id){
        return movementService.findById(id);
    }

    @GetMapping("/getAll")
    public List<MovementResponseDTO> getAllMovements(){
        return movementService.findAll();
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody DepositAndWithdrawalRequestDTO depositAndWithdrawalRequestDTO){
        movementService.deposit(depositAndWithdrawalRequestDTO);

        return ResponseEntity.ok("Deposit succesful");
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<String> withdrawal(@RequestBody DepositAndWithdrawalRequestDTO depositAndWithdrawalRequestDTO){
        movementService.withdrawal(depositAndWithdrawalRequestDTO);

        return ResponseEntity.ok("Withdrawal succesful");
    }

}
