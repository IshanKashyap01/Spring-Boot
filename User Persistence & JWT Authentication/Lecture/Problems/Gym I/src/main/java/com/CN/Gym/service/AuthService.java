package com.CN.Gym.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import com.CN.Gym.dto.JwtRequest;
import com.CN.Gym.dto.JwtResponse;
import com.CN.Gym.jwt.JwtAuthenticationHelper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService
{
    private final AuthenticationManager authManager;
    private final JwtAuthenticationHelper helper;

    public JwtResponse login(JwtRequest request)
    {
        authenticate(request.getUsername(), request.getPassword());
        String token = helper.generateToken(request.getUsername());
        return JwtResponse.builder().jwtToken(token).build();
    }

    private void authenticate(String username, String password)
    {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        try
        {
            authManager.authenticate(authToken);
        }
        catch(BadCredentialsException e)
        {
            throw new BadCredentialsException("invalid username or password");
        }
    }
}