package com.security.bank.controller;

import lombok.RequiredArgsConstructor;
import com.security.bank.dto.AccountDto;
import com.security.bank.dto.KycDto;
import com.security.bank.dto.NomineeDto;
import com.security.bank.entity.Account;
import com.security.bank.entity.Nominee;
import com.security.bank.entity.User;
import com.security.bank.service.AccountService;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class UserAccountController
{
    private final AccountService service;

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/create/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccountForUser(@RequestBody AccountDto dto, @PathVariable Long userId)
    {
        service.createAccountForUser(dto, userId);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/all/{userId}")
    public List<Account> getAllUserAccounts(@PathVariable Long userId)
    {
        return service.getAllUserAccounts(userId);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/balance")
    public double getAccountBalance(@RequestParam Long accountNumber)
    {
        return service.getAccountBalance(accountNumber);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/nominee")
    public Nominee getAccountNominee(@RequestParam Long accountNumber)
    {
        return service.getAccountNominee(accountNumber);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/updateNominee/{accountId}")
    public void updateNominee(@RequestBody NomineeDto dto, @PathVariable Long accountId)
    {
        service.updateNominee(dto, accountId);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/getKycDetails")
    public User getKycDetails(@RequestParam Long accountNumber)
    {
        User user = service.getAccountUser(accountNumber);
        user.setAccountList(null);
        user.setInvestmentList(null);
        return user;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/updateKyc/{accountId}")
    public User updateKyc(@RequestBody KycDto dto, @PathVariable Long accountId)
    {
        User user = service.updateKyc(dto, accountId);
        user.setAccountList(null);
        user.setInvestmentList(null);
        return user;
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/getAccount/summary")
    public Account getAccountSummary(@RequestParam Long accountNumber)
    {
        Account account = service.getAccountByAccountNumber(accountNumber);
        account.setUser(null);
        return account;
    }
}