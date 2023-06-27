package com.cac.minibank.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long customerId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;

    private String phone;
    private String email;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address") // Especifica el nombre de la columna en la tabla Customer
    private Address address;

    @OneToMany(mappedBy = "accountHolder", cascade = CascadeType.ALL)
    private List<Account> accounts;


    @ManyToMany(cascade = CascadeType.ALL)
    private List<Account> jointAccounts;

}
