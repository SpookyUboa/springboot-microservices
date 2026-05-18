package com.eduromero.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
        name = "Account",
        description = "Details of the customer's account"
)
@Data
public class AccountDTO {

    @Schema(
            description = "Account number of the customer",
            example = "9988776655"
    )
    @NotEmpty(message="Account number cannot be null or empty")
    @Pattern(regexp="^$|[0-9]{10}", message="Account number must be 10 digits long")
    private Long accountNumber;

    @Schema(
            description = "Account type of the customer",
            example = "Sales"
    )
    @NotEmpty(message="Account type cannot be null or empty")
    private String accountType;

    @Schema(
            description = "Customer's branch address",
            example = "Somewhere I guess"
    )
    @NotEmpty(message="Branch address cannot be null or empty ")
    private String branchAddress;
}
