package org.example.realSystems.atm.service;

import org.example.realSystems.atm.model.Account;
import org.example.realSystems.atm.repository.AccountRepository;

public class AccountService {
    AccountRepository accountRepository;
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void AddAccount(Account account) {
        accountRepository.AddAccount(account);
    }

    public Account getAccount(String accountNumber) {
        return accountRepository.getAccount(accountNumber);
    }
}
