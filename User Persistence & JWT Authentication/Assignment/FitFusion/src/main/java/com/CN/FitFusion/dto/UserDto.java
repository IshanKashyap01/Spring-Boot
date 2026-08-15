package com.CN.FitFusion.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    private String email;
    private String password;
    private int age;
    private String gender;
    private Long contactNo;
    private String userType;
}