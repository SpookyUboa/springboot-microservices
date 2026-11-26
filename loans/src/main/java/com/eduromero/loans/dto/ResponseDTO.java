package com.eduromero.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "Response",
        description = "Schema for successful response information"
)
@Data
@AllArgsConstructor
public class ResponseDTO {

    @Schema(
            description = "HTTP status code",
            example = "200"
    )
    private String statusCode;

    @Schema(
            description = "Server status message",
            example = "OK"
    )
    private String statusMessage;

}
