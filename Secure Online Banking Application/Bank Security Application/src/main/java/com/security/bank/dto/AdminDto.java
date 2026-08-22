package com.security.bank.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto
{
    private String name;
    private String username;
    private String password;
    private String address;
    private Long number;
    private String identityProof;
}