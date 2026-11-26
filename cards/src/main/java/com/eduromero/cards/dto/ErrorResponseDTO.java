package com.eduromero.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "ErrorResponse",
        description = "Schema for error response information"
)
@Data
@AllArgsConstructor
public class ErrorResponseDTO {

    @Schema(
            description = "API path invoked by user"
    )
    private String apiPath;

    @Schema(
            description = "HTTP error code",
            example = "404"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "HTTP error message",
            example = "Internal server error"
    )
    private String errorMessage;

    @Schema(
            description = "Timestamp of error occurrence"
    )
    private LocalDateTime errorTime;

}
