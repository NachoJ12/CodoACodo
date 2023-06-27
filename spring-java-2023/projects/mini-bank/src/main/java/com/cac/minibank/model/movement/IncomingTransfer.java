package com.cac.minibank.model.movement;

import com.cac.minibank.model.Account;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("incoming_transfer")
public class IncomingTransfer extends Movement {
    @ManyToOne
    private Account sourceAccount;

}
