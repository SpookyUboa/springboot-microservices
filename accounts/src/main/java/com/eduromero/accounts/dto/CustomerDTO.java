package com.eduromero.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema holding victim's personal details"
)
public class CustomerDTO {

    @Schema(
            description = "Name of the victim"
    )
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 5, max = 30, message = "Customer name length must be between 5 and 30 characters")
    private String name;

    @Schema(
            description = "Email address belonging to the victim",
            example = "john_doe@mee.com"
    )
    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Email address has incorrect format")
    private String email;

    @Schema(
            description = "Phone number belonging to the victim"
    )
    @NotEmpty(message = "Phone number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{9})", message = "Phone number must be 9 digits long")
    private String phoneNumber;

    @Schema(
            description = "Victim's account details"
    )
    @Valid
    private AccountDTO accountDTO;

}
