package com.CN.Gym.jwt;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import lombok.*;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    private final JwtAuthenticationHelper helper;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal
    (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
    throws ServletException, IOException
    {
        String token = this.helper.getToken(request);
        if(token != null)
        {
            String username = this.helper.getUsernameFromToken(token);
            if(username != null)
            {
                UserDetails details = this.userDetailsService.loadUserByUsername(username);
                if(this.helper.validateToken(token, details) && !this.helper.isUserAlreadyLoggedIn())
                {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken
                    (details, null, details.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}