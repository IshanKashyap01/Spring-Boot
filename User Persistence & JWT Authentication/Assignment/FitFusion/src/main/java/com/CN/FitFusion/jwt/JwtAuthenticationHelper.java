package com.CN.FitFusion.jwt;

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
    private final UserDetailsService service;

    public String generateToken(String username)
    {
        // get user details
        UserDetails details = this.service.loadUserByUsername(username);
        // prepare a hashmap to contain the claims about the user
        HashMap<String, Object> claims = new HashMap<>();
        // call the Jwts factory to build a JWT String
        return Jwts.builder()
        // give it a map to put the claims in, which will be used to create a JSON string
        .setClaims(claims)
        .setSubject(details.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + JWT_VALIDITY))
        // sign the JWT with the secret using a signing algorithm
        .signWith(
            new SecretKeySpec(SECRET.getBytes(), SignatureAlgorithm.HS512.getJcaName()),
            SignatureAlgorithm.HS512
        )
        // build the JWT and serialize it to a compact URL-safe string
        .compact();
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
    
    public Claims getClaimsFromToken(String token)
    {
        return Jwts.parserBuilder()
        .setSigningKey(SECRET.getBytes())
        .build()
        .parseClaimsJws(token)
        .getBody();
    }
    
    public String getUsernameFromToken(String token)
    {
        return getClaimsFromToken(token).getSubject();
    }
    
    public boolean validateToken(String token, UserDetails details)
    {
        String username = getUsernameFromToken(token);
        return username != null && username.equals(details.getUsername());
    }

    public boolean isTokenExpired(String token)
    {
        return getClaimsFromToken(token).getExpiration().before(new Date());
    }

    public boolean isUserAlreadyAuthenticated()
    {
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }
}