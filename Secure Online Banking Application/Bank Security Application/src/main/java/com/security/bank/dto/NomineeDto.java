package com.security.bank.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NomineeDto
{
    private String relation;
    private String name;
    private Long accountNumber;
    private String gender;
    private int age;
}