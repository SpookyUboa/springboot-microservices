package com.eduromero.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Schema(name = "Loan",
        description = "Schema holding victim's faustian deal data"
)
@Data
public class LoanDTO {

    @NotEmpty(message = "Phone Number can not be null or empty")
    @Pattern(regexp="(^$|[0-9]{9})",message = "Phone Number must be 9 digits long")
    @Schema(
            description = "Victim's phone number", example = "661662663"
    )
    private String phoneNumber;

    @NotEmpty(message = "Loan number can not be null or empty")
    @Pattern(regexp="(^$|[0-9]{12})",message = "Loan number must be 12 digits long")
    @Schema(
            description = "Victim's loan number", example = "548732457654"
    )
    private String loanNumber;

    @NotEmpty(message = "Loan type can not be null or empty")
    @Schema(
            description = "Victim's loan type", example = "Soul Loan"
    )
    private String loanType;

    @Positive(message = "Total loan amount should be greater than zero")
    @Schema(
            description = "Total loan amount", example = "100000"
    )
    private int totalLoan;

    @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
    @Schema(
            description = "Total loan amount paid", example = "1000"
    )
    private int amountPaid;

    @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
    @Schema(
            description = "Total outstanding amount against a loan", example = "99000"
    )
    private int outstandingAmount;

}
