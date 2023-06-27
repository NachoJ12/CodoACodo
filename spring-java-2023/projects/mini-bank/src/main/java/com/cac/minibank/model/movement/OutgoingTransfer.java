package com.cac.minibank.model.movement;

import com.cac.minibank.model.Account;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("outgoing_transfer")
public class OutgoingTransfer extends Movement{
    @ManyToOne
    private Account destinationAccount;
}
