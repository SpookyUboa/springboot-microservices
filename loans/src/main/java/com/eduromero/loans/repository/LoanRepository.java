package com.eduromero.loans.repository;

import com.eduromero.loans.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    Optional<Loan> findByPhoneNumber(String phoneNumber);

    Optional<Loan> findByLoanNumber(String loanNumber);

}
