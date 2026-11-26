package com.eduromero.loans.service;

import com.eduromero.loans.dto.LoanDTO;

public interface LoanService {

    /**
     * @param phoneNumber - Victim's phone number
     */
    void createLoan(String phoneNumber);

    /**
     * @param phoneNumber - Victim's phone number
     *  @return Victim's faustian deal details
     */
    LoanDTO fetchLoan(String phoneNumber);

    /**
     * @param loanDTO - LoansDTO Object
     * @return boolean indicating operation result
     */
    boolean updateLoan(LoanDTO loanDTO);

    /**
     * @param phoneNumber - Input Phone Number
     * @return boolean indicating operation result
     */
    boolean deleteLoan(String phoneNumber);

}
