package com.CN.FitFusion.service;

import org.springframework.security.authentication.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import com.CN.FitFusion.dto.*;
import com.CN.FitFusion.jwt.JwtAuthenticationHelper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService
{
    private final AuthenticationManager manager;
    private final JwtAuthenticationHelper helper;

    public JwtResponse login(JwtRequest request)
    {
        authenticate(request.getUsername(), request.getPassword());
        String token = this.helper.generateToken(request.getUsername());
        return JwtResponse.builder().jwtToken(token).build();
    }

    private void authenticate(String username, String password)
    {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        try
        {
            this.manager.authenticate(authToken);
        }
        catch(BadCredentialsException e)
        {
            throw new BadCredentialsException("invalid username or password");
        }
    }
}