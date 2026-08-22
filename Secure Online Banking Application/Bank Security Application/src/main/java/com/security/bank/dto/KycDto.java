package com.security.bank.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KycDto
{
    private String name;
    private String address;
    private Long number;
    private String identityProof;
}