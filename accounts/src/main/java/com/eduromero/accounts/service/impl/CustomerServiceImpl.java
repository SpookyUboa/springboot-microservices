package com.eduromero.accounts.service.impl;

import com.eduromero.accounts.dto.AccountDTO;
import com.eduromero.accounts.dto.CardDTO;
import com.eduromero.accounts.dto.CustomerDetailsDTO;
import com.eduromero.accounts.dto.LoanDTO;
import com.eduromero.accounts.entity.Account;
import com.eduromero.accounts.entity.Customer;
import com.eduromero.accounts.exception.ResourceNotFoundException;
import com.eduromero.accounts.mapper.AccountMapper;
import com.eduromero.accounts.mapper.CustomerMapper;
import com.eduromero.accounts.repository.AccountRepository;
import com.eduromero.accounts.repository.CustomerRepository;
import com.eduromero.accounts.service.ICustomerService;
import com.eduromero.accounts.service.client.CardsFeignClient;
import com.eduromero.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     *
     *
     * @param mobileNumber - Input mobile number
     * @return Customer Details based on a given mobileNumber
     *
     */
    @Override
    public CustomerDetailsDTO fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDTO customerDetailsDTO = CustomerMapper.mapToCustomerDetailsDTO(customer, new CustomerDetailsDTO());
        customerDetailsDTO.setAccountDTO(AccountMapper.mapToAccountDTO(account, new AccountDTO()));

        ResponseEntity<LoanDTO> loanDTOResponseEntity = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        customerDetailsDTO.setLoanDTO(loanDTOResponseEntity.getBody());
        ResponseEntity<CardDTO> cardDTOResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        customerDetailsDTO.setCardDTO(cardDTOResponseEntity.getBody());

        return customerDetailsDTO;
    }
}


