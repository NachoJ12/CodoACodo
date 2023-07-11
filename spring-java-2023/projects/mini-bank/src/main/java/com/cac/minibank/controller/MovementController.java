package com.cac.minibank.controller;

import com.cac.minibank.dto.response.MovementResponseDTO;
import com.cac.minibank.service.MovementService;
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


}
