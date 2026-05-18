package com.eduromero.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDTO {

    @Schema(
            description = "Name of the customer",
            example = "Edu Romero"
    )
    @Size(min=5, max=30, message="Name length must be between 5 and 30")
    @NotEmpty(message="Name cannot be null or empty")
    private String name;

    @Schema(
            description = "Email of the customer",
            example = "edu@romero.com"
    )
    @Email(message="Invalid email address format")
    @NotEmpty(message="Email address cannot be null or empty")
    private String email;


    @Schema(
            description = "Customer's phone number",
            example = "654321987"
    )
    @Pattern(regexp="^$|[0-9]{9}", message="Invalid phone number format")
    private String mobileNumber;

    @Schema(
            description = "Account details"
    )
    private AccountDTO accountDTO;
}
