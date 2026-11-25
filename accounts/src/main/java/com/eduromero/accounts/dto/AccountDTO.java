package com.eduromero.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Schema(
        name = "Account",
        description = "Schema holding victim's system data"
)
public class AccountDTO {

    @Schema(
            description = "Victim's prisoner code"
    )
    @NotEmpty(message = "Account number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits long")
    private String accountNumber;

    @Schema(
            description = "Victim's account type",
            example = "Dividend extraction"
    )
    @NotEmpty(message = "Account type cannot be null or empty")
    private String accountType;

    @Schema(
            description = "Victim's holding cell address"
    )
    @NotEmpty(message = "Branch address cannot be null or empty")
    private String branchAddress;

}
