package com.eduromero.accounts.service;

import com.eduromero.accounts.constants.Constants;
import com.eduromero.accounts.dto.AccountDTO;
import com.eduromero.accounts.dto.CustomerDTO;
import com.eduromero.accounts.entity.Account;
import com.eduromero.accounts.entity.Customer;
import com.eduromero.accounts.exception.CustomerAlreadyExistsException;
import com.eduromero.accounts.exception.ResourceNotFoundException;
import com.eduromero.accounts.mapper.AccountMapper;
import com.eduromero.accounts.mapper.CustomerMapper;
import com.eduromero.accounts.repository.AccountRepository;
import com.eduromero.accounts.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    /**
     * @param customerDTO - CustomerDTO Object
     */
    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByPhoneNumber(customerDTO.getPhoneNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException(String.format("Customer with phone number %s already exists", customerDTO.getPhoneNumber()));
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    private Account createNewAccount(Customer customer) {
        Account account = new Account();
        account.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        account.setAccountNumber(String.valueOf(randomAccNumber));
        account.setAccountType(Constants.SAVINGS);
        account.setBranchAddress(Constants.ADDRESS);
        return account;
    }

    @Override
    public CustomerDTO getAccount(String phoneNumber) {
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "Phone NUmber", phoneNumber)
        );
        Account account = accountRepository.findByCustomerId(customer.getCustomerId().toString()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "Phone NUmber", phoneNumber)
        );

        CustomerDTO customerDTO = CustomerMapper.mapToCustomerDTO(customer, new CustomerDTO());
        customerDTO.setAccountDTO(AccountMapper.mapToAccountDTO(account, new AccountDTO()));

        return customerDTO;
    }

    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated = false;
        AccountDTO accountDTO = customerDTO.getAccountDTO();
        if(accountDTO != null) {
            Account account = accountRepository.findById(Long.parseLong(accountDTO.getAccountNumber())).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountDTO.getAccountNumber().toString())
            );
            AccountMapper.mapToAccount(accountDTO, account);
            account = accountRepository.save(account);

            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "Customer ID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDTO, customer);
            customerRepository.save(customer);

            isUpdated = true;
        }

        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String phoneNumber) {
        Customer customer = customerRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "Phone NUmber", phoneNumber)
        );

        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
}
