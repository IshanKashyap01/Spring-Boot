package com.security.bank.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDto
{
    private String cardHolderName;
    private String cardType;
    private Long pin;
}