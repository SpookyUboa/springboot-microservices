package com.eduromero.accounts.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {

    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 5, max = 30, message = "Customer name length must be between 5 and 30 characters")
    private String name;

    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Email address has incorrect format")
    private String email;

    @NotEmpty(message = "Phone number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{9})", message = "Phone number must be 9 digits long")
    private String phoneNumber;

    @Valid
    private AccountDTO accountDTO;

}
