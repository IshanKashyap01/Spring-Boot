package com.security.bank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.security.bank.dto.CardDto;
import com.security.bank.entity.Card;
import com.security.bank.service.CardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
public class UserCardController
{
    private final CardService service;

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/block")
    public String blockCard(@RequestParam Long accountNumber, @RequestParam Long cardNumber)
    {
        service.blockCard(accountNumber, cardNumber);
        return "Card Blocked Successfully";
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/apply/new")
    public String applyForCard(@RequestParam Long accountNumber, @RequestBody CardDto dto)
    {
        return service.applyForCard(accountNumber, dto);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/setting")
    public void updateLimitAndPin(@RequestBody Card card, @RequestParam Long cardNumber)
    {
        service.updateCardLimitAndPin(card, cardNumber);
    }
}