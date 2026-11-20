package com.eduromero.accounts.mapper;

import com.eduromero.accounts.dto.AccountDTO;
import com.eduromero.accounts.entity.Account;

public class AccountMapper {

    public static AccountDTO mapToAccountDTO(Account account, AccountDTO accountDto) {
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setBranchAddress(account.getBranchAddress());
        return accountDto;
    }


    public static Account mapToAccount(AccountDTO accountDto, Account account) {
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountType(accountDto.getAccountType());
        account.setBranchAddress(accountDto.getBranchAddress());
        return account;
    }

}
