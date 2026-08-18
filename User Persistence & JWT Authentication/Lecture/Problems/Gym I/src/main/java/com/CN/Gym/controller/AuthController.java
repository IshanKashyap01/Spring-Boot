package com.CN.Gym.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.CN.Gym.dto.*;
import com.CN.Gym.service.AuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController
{
    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request)
    {
        return new ResponseEntity<>(service.login(request), HttpStatus.OK);
    }
}