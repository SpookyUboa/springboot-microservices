package com.eduromero.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Schema(name = "Cards",
        description = "Schema to hold victim's contract information"
)
@Data
public class CardDTO {

    @NotEmpty(message = "Phone pumber can not be null or empty")
    @Pattern(regexp="(^$|[0-9]{9})",message = "Phone number must be 9 digits long")
    @Schema(
            description = "Victim's phone number", example = "661662663"
    )
    private String phoneNumber;

    @NotEmpty(message = "Card number can not be null or empty")
    @Pattern(regexp="(^$|[0-9]{12})",message = "Card number must be 12 digits long")
    @Schema(
            description = "Victim's contract number", example = "100646930341"
    )
    private String cardNumber;

    @NotEmpty(message = "Card type can not be null or empty")
    @Schema(
            description = "Victim's contract type", example = "Enslavement contract"
    )
    private String cardType;

    @Positive(message = "Total card limit should be greater than zero")
    @Schema(
            description = "Total amount limit available against a card", example = "100000"
    )
    private int totalLimit;

    @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
    @Schema(
            description = "Total amount used by a victim", example = "1000"
    )
    private int amountUsed;

    @PositiveOrZero(message = "Total available amount should be equal or greater than zero")
    @Schema(
            description = "Total available amount against a card", example = "90000"
    )
    private int availableAmount;

}
