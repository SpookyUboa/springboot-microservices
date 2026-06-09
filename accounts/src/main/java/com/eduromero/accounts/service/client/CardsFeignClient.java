package com.eduromero.accounts.service.client;

import com.eduromero.accounts.dto.CardDTO;
import com.eduromero.accounts.dto.LoanDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<CardDTO> fetchCardDetails(
            @RequestHeader("evilAssBanking-correlation-id") String correlationId,
            @RequestParam String mobileNumber);

}
