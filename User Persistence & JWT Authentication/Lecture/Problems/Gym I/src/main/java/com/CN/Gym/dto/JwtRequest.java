package com.CN.Gym.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest
{
    private String username;
    private String password;
}