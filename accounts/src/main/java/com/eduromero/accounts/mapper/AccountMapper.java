package com.eduromero.accounts.mapper;

import com.eduromero.accounts.dto.AccountDTO;
import com.eduromero.accounts.entity.Account;

import java.time.LocalDateTime;

public class AccountMapper {

    public static AccountDTO mapToAccountDTO(Account account, AccountDTO accountDTO) {
        accountDTO. setAccountNumber(account.getAccountNumber());
        accountDTO.setAccountType(account.getAccountType());
        accountDTO.setBranchAddress(account.getBranchAddress());
        return accountDTO;
    }

    public static Account mapToAccount(AccountDTO accountDTO, Account account) {
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setAccountType(accountDTO.getAccountType());
        account.setBranchAddress(accountDTO.getBranchAddress());
        account.setCreatedAt(LocalDateTime.now());
        return account;
    }
}
