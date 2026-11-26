package com.eduromero.loans.controller;

import com.eduromero.loans.constants.Constants;
import com.eduromero.loans.dto.ErrorResponseDTO;
import com.eduromero.loans.dto.LoanDTO;
import com.eduromero.loans.dto.ResponseDTO;
import com.eduromero.loans.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "MEE - Loans REST APIs",
        description = "CRUD REST APIs in Most Evil Enterprises for creation, updating, fetching and deletion of victim's faustian deals"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class LoanController {

    private LoanService loanService;

    @Operation(
            summary = "Create loan REST API",
            description = "API for insertion of new faustian deal data"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL_SERVER_ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createLoan(@RequestParam
                                                  @Pattern(regexp = "(^$|[0-9]{9})", message = "Phone number must be 9 digits long")
                                                  String phoneNumber) {
        loanService.createLoan(phoneNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(Constants.STATUS_201, Constants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch loan REST API",
            description = "API for fetching victim's faustian deal details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL_SERVER_ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @GetMapping("/fetch")
    public ResponseEntity<LoanDTO> fetchLoanDetails(@RequestParam
                                                     @Pattern(regexp = "(^$|[0-9]{9})", message = "Phone number must be 9 digits long")
                                                     String phoneNumber) {
        LoanDTO loanDTO = loanService.fetchLoan(phoneNumber);
        return ResponseEntity.status(HttpStatus.OK).body(loanDTO);
    }

    @Operation(
            summary = "Update loan REST API",
            description = "API fpr updating faustian deal details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL_SERVER_ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateLoanDetails(@Valid @RequestBody LoanDTO loanDTO) {
        boolean isUpdated = loanService.updateLoan(loanDTO);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(Constants.STATUS_200, Constants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(Constants.STATUS_417, Constants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete loan REST API",
            description = "API for cancellation of faustian deal"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL_SERVER_ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDTO.class)
                    )
            )
    }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteLoanDetails(@RequestParam
                                                         @Pattern(regexp = "(^$|[0-9]{9})", message = "Phone number must be 9 digits long")
                                                         String phoneNumber) {
        boolean isDeleted = loanService.deleteLoan(phoneNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(Constants.STATUS_200, Constants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(Constants.STATUS_417, Constants.MESSAGE_417_DELETE));
        }
    }

}
