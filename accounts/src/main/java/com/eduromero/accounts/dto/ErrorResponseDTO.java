package com.eduromero.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
@Data @AllArgsConstructor
public class ErrorResponseDTO {

    @Schema(
            description = "API path of the call",
            example = "/api/create"
    )
    private String apiPath;

    @Schema(
            description = "Error code of the response",
            example = "500"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error description",
            example = "Internal server error"
    )
    private String errorMessage;

    @Schema(
            description = "Time at which exception was raised",
            example = "2026-05-07T21:29:52.5394845"
    )
    private LocalDateTime errorTime;
}
