package com.eduromero.loans.service.impl;

import com.eduromero.loans.constants.LoanConstants;
import com.eduromero.loans.dto.LoanDTO;
import com.eduromero.loans.entity.Loan;
import com.eduromero.loans.exception.LoanAlreadyExistsException;
import com.eduromero.loans.exception.ResourceNotFoundException;
import com.eduromero.loans.mapper.LoanMapper;
import com.eduromero.loans.repository.LoanRepository;
import com.eduromero.loans.service.ILoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {

    private LoanRepository loanRepository;

    /**
     * @param mobileNumber - Mobile number of the customer
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan> optionalLoan = loanRepository.findByMobileNumber(mobileNumber);
        if(optionalLoan.isPresent()) {
            throw new LoanAlreadyExistsException("Loan with provided mobile number already registered");
        }
        loanRepository.save(createNewLoan(mobileNumber));
    }

    /**
     * @param mobileNumber - Mobile Number of the Customer
     * @return the new loan details
     */
    private Loan createNewLoan(String mobileNumber) {
        Loan newLoan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    /**
     *
     * @param mobileNumber - Input mobile Number
     * @return Loan Details based on a given mobileNumber
     */
    @Override
    public LoanDTO fetchLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        return LoanMapper.mapToLoanDTO(loan, new LoanDTO());
    }

    /**
     *
     * @param loanDTO - LoanDTO Object
     * @return boolean indicating if the update of loan details is successful or not
     */
    @Override
    public boolean updateLoan(LoanDTO loanDTO) {
        Loan loan = loanRepository.findByLoanNumber(loanDTO.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "LoanNumber", loanDTO.getLoanNumber()));
        LoanMapper.mapToLoan(loanDTO, loan);
        loanRepository.save(loan);
        return  true;
    }

    /**
     * @param mobileNumber - Input MobileNumber
     * @return boolean indicating if the delete of loan details is successful or not
     */
    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        loanRepository.deleteById(loan.getLoanId());
        return true;
    }

}
