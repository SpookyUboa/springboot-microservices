package com.eduromero.accounts.controller;

import com.eduromero.accounts.constants.Constants;
import com.eduromero.accounts.dto.CustomerDTO;
import com.eduromero.accounts.dto.ResponseDTO;
import com.eduromero.accounts.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = (MediaType.APPLICATION_JSON_VALUE))
@AllArgsConstructor
public class AccountController {

    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(Constants.STATUS_201, Constants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDTO> fetchAccount(@RequestParam String phoneNumber) {
        CustomerDTO customerDTO = accountService.getAccount(phoneNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateAccount(@RequestBody CustomerDTO customerDTO) {
        boolean isUpdated = accountService.updateAccount(customerDTO);
        if(isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(Constants.STATUS_200, Constants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(Constants.STATUS_500, Constants.MESSAGE_500));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteAccount(@RequestParam String phoneNumber) {
        boolean isDeleted = accountService.deleteAccount(phoneNumber);
        if(isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(Constants.STATUS_200, Constants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(Constants.STATUS_500, Constants.MESSAGE_500));
        }
    }

}
