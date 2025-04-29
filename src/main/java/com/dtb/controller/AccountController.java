package com.dtb.controller;


import com.dtb.dto.CreateAccountRequest;
import com.dtb.entities.Account;
import com.dtb.services.abstracts.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody CreateAccountRequest request) {
        return ResponseEntity.ok(accountService.createAccount(request));
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getBalance(id));
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Account> activateAccount(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.activateAccount(id));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Account> deactivateAccount(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.deactivateAccount(id));
    }
}
