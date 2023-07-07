package com.cac.minibank.controller;

import com.cac.minibank.dto.request.AccountRequestDTO;
import com.cac.minibank.dto.response.AccountResponseDTO;
import com.cac.minibank.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable Long id){
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @GetMapping("/getByHolderSurname/{surname}")
    public ResponseEntity<List<AccountResponseDTO>> getAccountsByHolderSurname(@PathVariable String surname){
        return ResponseEntity.ok(accountService.getAccountsByHolderSurname(surname));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody AccountRequestDTO accountRequestDTO){
        accountService.createAccount(accountRequestDTO);

        return ResponseEntity.ok("Account created");

    }

}
