package com.CN.StoreFinder.jwt;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    @Autowired
    private JwtAuthenticationHelper helper;
    @Autowired
    private UserDetailsService service;

    @Override
    protected void doFilterInternal
    (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException
    {
        // check if there is a token, and it contains a username
        String token = this.helper.getToken(request);
        if(token != null)
        {
            String username = this.helper.getUsernameFromToken(token);
            if(username != null)
            {
                // match the username in the token against internal records and authenticate him
                UserDetails details = this.service.loadUserByUsername(username);
                if(this.helper.validateToken(token, details) && !this.helper.isUserAlreadyAuthenticated())
                {
                    authenticate(request, details);
                }
            }
        }
        // send the request/response down the filter chain, regardless of what happens here
        filterChain.doFilter(request, response);
    }

    private void authenticate(HttpServletRequest request, UserDetails details)
    {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken
        (details, null, details.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}