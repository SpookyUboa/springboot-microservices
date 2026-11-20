package com.eduromero.accounts.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AccountDTO {

    private Long accountNumber;

    private String accountType;

    private String branchAddress;

}
