package com.eduromero.accounts.dto;

import com.eduromero.accounts.entity.Account;
import lombok.Data;

@Data
public class CustomerDTO {

    private String name;
    private String email;
    private String phoneNumber;
    private AccountDTO accountDTO;

}
