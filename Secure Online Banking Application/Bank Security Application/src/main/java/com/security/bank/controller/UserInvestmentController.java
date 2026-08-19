package com.security.bank.controller;

import lombok.RequiredArgsConstructor;
import com.security.bank.dto.InvestmentDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/invest")
public class UserInvestmentController
{
    @PostMapping("/now")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String invest(@RequestParam Long accountId, @RequestBody InvestmentDto dto)
    {
        return "Investment successful";
    }
}