package com.eduromero.accounts.service;

import com.eduromero.accounts.dto.CustomerDetailsDTO;

public interface ICustomerService {

    /**
     *
     *
     * @param mobileNumber - Input mobile number
     * @return Customer Details based on a given mobileNumber
     *
     */
    CustomerDetailsDTO fetchCustomerDetails(String mobileNumber, String correlationId);

}
