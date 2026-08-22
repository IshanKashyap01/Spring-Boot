package Telecom.SubscriptionService.controller;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import Telecom.SubscriptionService.dto.AccountDto;
import Telecom.SubscriptionService.dto.ResponseMessage;
import Telecom.SubscriptionService.model.Account;
import Telecom.SubscriptionService.service.AccountService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController
{
    private final AccountService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAllAccounts()
    {
        return service.getAllAccounts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account getAccountById(@PathVariable Long id)
    {
        return service.getAccountById(id);
    }

    @GetMapping("/userId/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Account getAccountByUserId(@PathVariable Long userId)
    {
        return service.getAccountByUserId(userId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account updateAccount(@PathVariable Long id, @RequestBody AccountDto dto)
    {
        return service.updateAccount(id, dto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMessage createAccount(@RequestBody AccountDto dto)
    {
        return service.createAccount(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseMessage deleteAccount(@PathVariable Long id)
    {
        return service.deleteAccount(id);
    }
}