package com.dtb.implementation;

import com.dtb.dto.CreateAccountRequest;
import com.dtb.dto.UpdateAccountRequest;
import com.dtb.entities.Account;
import com.dtb.repository.AccountRepository;
import com.dtb.services.abstracts.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createAccount(CreateAccountRequest request) {
        Account account = new Account();
        account.setProfileId(request.getProfileId());
        account.setBalance(request.getInitialBalance());
        account.setActive(true);
        return accountRepository.save(account);
    }

    @Override
    public BigDecimal getBalance(Long accountId) {
        Account account = findById(accountId);
        return account.getBalance();
    }



    @Override
    public Account activateAccount(Long accountId) {
        Account account = findById(accountId);
        account.setActive(true);
        return accountRepository.save(account);
    }

    @Override
    public Account deactivateAccount(Long accountId) {
        Account account = findById(accountId);
        account.setActive(false);
        return accountRepository.save(account);
    }

    private Account findById(Long accountId) {
        return accountRepository.findByProfileId(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}

