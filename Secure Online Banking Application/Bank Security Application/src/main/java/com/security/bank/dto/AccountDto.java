package com.security.bank.dto;

import com.security.bank.entity.Nominee;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto
{
    private String accountType;
    private double balance;
    private String proof;
    private Nominee nominee;
}