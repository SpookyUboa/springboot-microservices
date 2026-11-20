package com.eduromero.accounts.service;

import com.eduromero.accounts.dto.CustomerDTO;

public interface AccountService {

    /**
     * @param customerDto - CustomerDTO Object
     */
    void createAccount(CustomerDTO customerDto);
}
