package com.eduromero.loans.service;

import com.eduromero.loans.dto.LoanDTO;

public interface ILoanService {

    /**
     *
     * @param mobileNumber - Mobile Number of the Customer
     */
    void createLoan(String mobileNumber);

    /**
     *
     * @param mobileNumber - Input mobile Number
     *  @return Loan Details based on a given mobileNumber
     */
    LoanDTO fetchLoan(String mobileNumber);

    /**
     *
     * @param loanDTO - LoanDTO Object
     * @return boolean indicating if the update of card details is successful or not
     */
    boolean updateLoan(LoanDTO loanDTO);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of loan details is successful or not
     */
    boolean deleteLoan(String mobileNumber);

}
