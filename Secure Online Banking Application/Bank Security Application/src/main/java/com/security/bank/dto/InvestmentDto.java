package com.security.bank.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentDto
{
    private String investmentType;
    private double amount;
    private String duration;
}