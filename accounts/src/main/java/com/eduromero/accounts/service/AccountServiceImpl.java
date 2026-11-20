package com.eduromero.accounts.service;

import com.eduromero.accounts.dto.CustomerDTO;
import com.eduromero.accounts.repository.AccountRepository;
import com.eduromero.accounts.repository.CustomerRepository;

public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    /**
     * @param customerDto - CustomerDTO Object
     */
    @Override
    public void createAccount(CustomerDTO customerDto) {

    }
}
