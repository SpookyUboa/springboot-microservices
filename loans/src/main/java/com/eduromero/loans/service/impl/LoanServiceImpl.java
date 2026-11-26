package com.eduromero.loans.service.impl;

import com.eduromero.loans.constants.Constants;
import com.eduromero.loans.dto.LoanDTO;
import com.eduromero.loans.entity.Loan;
import com.eduromero.loans.exception.LoanAlreadyExistsException;
import com.eduromero.loans.exception.ResourceNotFoundException;
import com.eduromero.loans.mapper.LoanMapper;
import com.eduromero.loans.repository.LoanRepository;
import com.eduromero.loans.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService {

    private LoanRepository loanRepository;

    /**
     * @param phoneNumber - Victim's phone number
     */
    @Override
    public void createLoan(String phoneNumber) {
        Optional<Loan> optionalLoans= loanRepository.findByPhoneNumber(phoneNumber);
        if(optionalLoans.isPresent()){
            throw new LoanAlreadyExistsException("Loan already registered with given phoneNumber "+phoneNumber);
        }
        loanRepository.save(createNewLoan(phoneNumber));
    }

    private Loan createNewLoan(String phoneNumber) {
        Loan newLoan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setPhoneNumber(phoneNumber);
        newLoan.setLoanType(Constants.HOME_LOAN);
        newLoan.setTotalLoan(Constants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(Constants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    /**
     * @param phoneNumber - Victim's phone number
     *  @return Victim's faustian deal details
     */
    @Override
    public LoanDTO fetchLoan(String phoneNumber) {
        Loan loan = loanRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "phoneNumber", phoneNumber)
        );
        return LoanMapper.mapToLoanDTO(loan, new LoanDTO());
    }

    /**
     * @param loanDTO - LoansDTO Object
     * @return boolean indicating operation result
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
     * @param phoneNumber - Input Phone Number
     * @return boolean indicating operation result
     */
    @Override
    public boolean deleteLoan(String phoneNumber) {
        Loan loan = loanRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "phoneNumber", phoneNumber)
        );
        loanRepository.deleteById(loan.getLoanId());
        return true;
    }


}
