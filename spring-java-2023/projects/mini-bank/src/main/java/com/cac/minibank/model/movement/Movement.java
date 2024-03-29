package com.cac.minibank.model.movement;

import com.cac.minibank.model.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
public abstract class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movementId;
    private LocalDateTime realizationDateTime;
    private BigDecimal amount;
    private String description;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


    public Movement() {

    }
}
