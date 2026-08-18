package com.CN.Gym.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse
{
    private String jwtToken;
}