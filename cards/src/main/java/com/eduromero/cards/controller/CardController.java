package com.eduromero.cards.controller;

import com.eduromero.cards.constants.Constants;
import com.eduromero.cards.dto.CardDTO;
import com.eduromero.cards.dto.ErrorResponseDTO;
import com.eduromero.cards.dto.ResponseDTO;
import com.eduromero.cards.service.CardService;
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
        name = "MEE - Cards REST APIs",
        description = "CRUD REST APIs in Most Evil Enterprises for creation, updating, fetching and deletion of victim contracts"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class CardController {

    private CardService cardService;

    @Operation(
            summary = "Create Card REST API",
            description = "API for creation of new victim contract"
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
    public ResponseEntity<ResponseDTO> createCard(@Valid @RequestParam
                                                      @Pattern(regexp="(^$|[0-9]{9})",message = "Phone number must be 9 digits long")
                                                      String phoneNumber) {
        cardService.createCard(phoneNumber);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ResponseDTO(Constants.STATUS_201, Constants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch card REST API",
            description = "API for inspection of victim's contract details"
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
    })
    @GetMapping("/fetch")
    public ResponseEntity<CardDTO> fetchCardDetails(@RequestParam
                                                               @Pattern(regexp="(^$|[0-9]{9})",message = "Phone number must be 9 digits long")
                                                               String phoneNumber) {
        CardDTO cardDTO = cardService.fetchCard(phoneNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardDTO);
    }

    @Operation(
            summary = "Update card REST API",
            description = "API for renewal of victim's contract"
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
        })
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateCardDetails(@Valid @RequestBody CardDTO cardDTO) {
        boolean isUpdated = cardService.updateCard(cardDTO);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(Constants.STATUS_200, Constants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(Constants.STATUS_417, Constants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete card REST API",
            description = "API to nullify victim's contract"
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
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteCardDetails(@RequestParam
                                                                @Pattern(regexp="(^$|[0-9]{9})",message = "Phone number must be 9 digits long")
                                                                String phoneNumber) {
        boolean isDeleted = cardService.deleteCard(phoneNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(Constants.STATUS_200, Constants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(Constants.STATUS_417, Constants.MESSAGE_417_DELETE));
        }
    }

}
