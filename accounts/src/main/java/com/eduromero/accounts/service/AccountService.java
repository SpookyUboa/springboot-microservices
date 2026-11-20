package com.eduromero.accounts.service;

import com.eduromero.accounts.dto.CustomerDTO;

public interface AccountService {

    /**
     * @param customerDTO - CustomerDTO Object
     */
    void createAccount(CustomerDTO customerDTO);

    /**
     * @param phoneNumber - Customer's phone number
     * @return Customer's data
     */
    CustomerDTO getAccount(String phoneNumber);

    /**
     * @param customerDTO - Customer's data
     * @return boolean indicating operation result
     */

    boolean updateAccount(CustomerDTO customerDTO);

    /**
     * @param phoneNumber - Customer's phone number
     * @return boolean indicating operation result
     */

    boolean deleteAccount(String phoneNumber);

}
