package com.security.bank.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.security.bank.dto.AdminDto;
import com.security.bank.entity.*;
import com.security.bank.service.AdminService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController
{
    private final AdminService service;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public void register(@RequestBody AdminDto dto)
    {
        service.register(dto);
    }

    @GetMapping("/getAllUser")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers()
    {
        return service.getAllUsers();
    }

    @GetMapping("/getUserByName/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public User getUserByName(@PathVariable String username)
    {
        return service.getUserByName(username);
    }

    @DeleteMapping("/deleteUser/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long userId)
    {
        service.deleteUser(userId);
    }

    @PutMapping("/account/deactivate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ADMIN')")
    public String deactivateAccount(@RequestParam Long userId, @RequestParam Long accountId)
    {
        return service.deactivateUser(userId, accountId);
    }

    @PutMapping("/account/activate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasRole('ADMIN')")
    public String activateAccount(@RequestParam Long userId, @RequestParam Long accountId)
    {
        return service.activateAccount(userId, accountId);
    }

    @GetMapping("/account/getActiveAccountsList")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Account> getAllActiveAccounts()
    {
        return service.getAllActiveAccounts();
    }

    @GetMapping("/account/getInActiveAccountsList")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Account> getAllInactiveAccounts()
    {
        return service.getAllInactiveAccounts();
    }

    @GetMapping("/accountList/ByAccountType/{accType}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Account> getAccountsByType(@PathVariable AccountType accType)
    {
        return service.getAllAccountsOfType(accType);
    }

    @GetMapping("/accountList/ByBranchType/{branchType}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Account> getAccountsByBranchType(@PathVariable BranchType branchType)
    {
        return service.getAllAccountsOfBranchType(branchType);
    }
}