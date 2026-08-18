package com.CN.Gym.jwt;

import java.util.Date;
import java.util.HashMap;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationHelper
{
    private static final String SECRET = "ThisIsMySecretTokenForSigningJwTs_MakeThis_AtLeast_64_Characters_Long!@#1234567890";
    private static final int JWT_VALIDITY = 3_600_000;
    private final UserDetailsService userDetailsService;

    public String generateToken(String username)
    {
        UserDetails details = this.userDetailsService.loadUserByUsername(username);
        HashMap<String, Object> claims = new HashMap<>();
        return Jwts.builder()
        .setClaims(claims)
        .setSubject(details.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + JWT_VALIDITY))
        .signWith
        (
            new SecretKeySpec(SECRET.getBytes(), SignatureAlgorithm.HS512.getJcaName()),
            SignatureAlgorithm.HS512
        )
        .compact();
    }

    public String getUsernameFromToken(String token)
    {
        Claims payload = getClaimsFromToken(token);
        return payload.getSubject();
    }

    public boolean validateToken(String token, UserDetails details)
    {
        String username = getUsernameFromToken(token);
        return username != null && username.equals(details.getUsername()) && !isTokenExpired(token);
    }

    public Claims getClaimsFromToken(String token)
    {
        return Jwts.parserBuilder()
        .setSigningKey(SECRET.getBytes())
        .build()
        .parseClaimsJws(token)
        .getBody();
    }

    public boolean isUserAlreadyLoggedIn()
    {
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }

    public boolean isTokenExpired(String token)
    {
        Claims payload = getClaimsFromToken(token);
        return payload.getExpiration().before(new Date());
    }

    public String getToken(HttpServletRequest request)
    {
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer "))
        {
            return header.substring(7);
        }
        return null;
    }
}