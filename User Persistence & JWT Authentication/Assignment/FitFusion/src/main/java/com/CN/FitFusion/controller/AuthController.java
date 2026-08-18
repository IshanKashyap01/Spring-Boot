package com.CN.FitFusion.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.CN.FitFusion.dto.JwtRequest;
import com.CN.FitFusion.dto.JwtResponse;
import com.CN.FitFusion.service.AuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController
{
    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request)
    {
        return new ResponseEntity<>(service.login(request), HttpStatus.OK);
    }
}