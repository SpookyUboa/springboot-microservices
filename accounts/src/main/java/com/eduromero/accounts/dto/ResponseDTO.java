package com.eduromero.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
@Data
@AllArgsConstructor
public class ResponseDTO {

    @Schema(
            description = "Response status code",
            example = "200"
    )
    private String statusCode;

    @Schema(
            description = "Response status message",
            example = "Requeest processed successfully"
    )
    private String statusMessage;
}
