package com.cac.minibank.model;

import com.cac.minibank.model.movement.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long accountId;
    @NotNull
    private Long number;
    @NotNull
    private LocalDate creationDate;
    @NotNull
    private BigDecimal initialBalance;
    @NotNull
    private BigDecimal currentBalance;
    @NotNull
    private BigDecimal agreedOverdraft;
    private LocalDate accountClosingDate;
    @ManyToOne
    @JoinColumn(name = "id_currency")
    private Currency currency; // REVISAR

    @NotNull
    @ManyToOne
    private Customer accountHolder; //fk

    @ManyToMany(mappedBy = "jointAccounts", cascade = CascadeType.ALL)
    private List<Customer> jointAccountHolders;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "account_id")
    private List<Movement> movements;




    public List<OutgoingTransfer> getOutgoingTransfers() {
        List<OutgoingTransfer> outgoingTransfers = new ArrayList<>();
        for (Movement movement : movements) {
            if (movement instanceof OutgoingTransfer) {
                outgoingTransfers.add((OutgoingTransfer) movement);
            }
        }
        return outgoingTransfers;
    }

    public List<IncomingTransfer> getIncomingTransfers() {
        List<IncomingTransfer> incomingTransfers = new ArrayList<>();
        for (Movement movement : movements) {
            if (movement instanceof IncomingTransfer) {
                incomingTransfers.add((IncomingTransfer) movement);
            }
        }
        return incomingTransfers;
    }

    public List<DepositAndWithdrawal> getDepositAndWithdrawals() {
        List<DepositAndWithdrawal> depositAndWithdrawals = new ArrayList<>();
        for (Movement movement : movements) {
            if (movement instanceof DepositAndWithdrawal) {
                depositAndWithdrawals.add((DepositAndWithdrawal) movement);
            }
        }
        return depositAndWithdrawals;
    }

    public List<CurrencyExchange> getCurrencyExchanges() {
        List<CurrencyExchange> currencyExchanges = new ArrayList<>();
        for (Movement movement : movements) {
            if (movement instanceof CurrencyExchange) {
                currencyExchanges.add((CurrencyExchange) movement);
            }
        }
        return currencyExchanges;
    }


}
