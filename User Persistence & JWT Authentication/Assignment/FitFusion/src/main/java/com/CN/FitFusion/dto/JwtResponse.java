package com.CN.FitFusion.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse
{
    private String jwtToken;
}