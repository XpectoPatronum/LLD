package org.example.realSystems.atm.repository;

import org.example.realSystems.atm.model.Account;

import java.util.HashMap;
import java.util.Map;

public class AccountRepository {
    Map<String, Account>accountMap = new HashMap<>();

    public void AddAccount(Account account) {
        accountMap.put(account.getAccountNumber(), account);
    }

    public Account getAccount(String accountNumber) {
        return accountMap.get(accountNumber);
    }
}
