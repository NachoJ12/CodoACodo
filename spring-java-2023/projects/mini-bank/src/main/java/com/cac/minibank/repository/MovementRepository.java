package com.cac.minibank.repository;

import com.cac.minibank.model.movement.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    List<Movement> findAll();

    Movement findByMovementId(Long id);


}
