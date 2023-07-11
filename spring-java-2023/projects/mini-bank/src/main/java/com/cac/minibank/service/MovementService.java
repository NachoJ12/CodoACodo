package com.cac.minibank.service;

import com.cac.minibank.dto.response.MovementResponseDTO;
import com.cac.minibank.mapper.MovementMapper;
import com.cac.minibank.model.movement.*;
import com.cac.minibank.repository.MovementRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovementService {
    private final MovementRepository movementRepository;
    private final MovementMapper movementMapper;

    public MovementService(MovementRepository movementRepository, MovementMapper movementMapper) {
        this.movementRepository = movementRepository;
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



}
