package com.eduromero.accounts.service;

import com.eduromero.accounts.dto.CustomerDTO;
import com.eduromero.accounts.entity.Account;

public interface IAccountService {

    /**
     * @param customerDTO - CustomerDTO Object
     */
    void createAccount(CustomerDTO customerDTO);

    /**
     * @param mobileNumber - Input mobile number
     * @return Account details based on given phone number
     */
    CustomerDTO fetchAccount(String mobileNumber);

    /**
     * @param customerDTO - CustomerDTO Object
     * @return boolean indicatin whether the update of Account details is successful or not
     */
    boolean updateAccount(CustomerDTO customerDTO);

    /**
     * @param mobileNumber - Input mobile number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    boolean deleteAccount(String mobileNumber);

}
