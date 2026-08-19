package com.security.bank.jwt;

import javax.servlet.*;
import java.io.IOException;
import javax.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    private final JwtAuthenticationHelper helper;
    private final UserDetailsService service;

    @Override
    protected void doFilterInternal
    (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException
    {
        String requestHeader = request.getHeader("Authorization");

		String username =null;
		String token =null;
		if(requestHeader!=null && requestHeader.startsWith("Bearer"))
		{
			token = requestHeader.substring(7);
			
			username= helper.getUsernameFromToken(token);
			
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
			{
				UserDetails userDetails = service.loadUserByUsername(username);
				
				if(!helper.isTokenExpired(token))
				{
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(token, null,userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
				
			}
		}
		filterChain.doFilter(request, response);
    }

    // private void authenticate(HttpServletRequest request, UserDetails details)
    // {
    //     UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken
    //     (details, null, details.getAuthorities());
    //     authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    //     SecurityContextHolder.getContext().setAuthentication(authToken);
    // }
}
/**
 * String requestHeader = request.getHeader("Authorization");

		String username =null;
		String token =null;
		if(requestHeader!=null && requestHeader.startsWith("Bearer"))
		{
			token = requestHeader.substring(7);
			
			username= jwtHelper.getUsernameFromToken(token);
			
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
			{
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				
				if(!jwtHelper.isTokenExpired(token))
				{
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(token, null,userDetails.getAuthorities());
					usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
				
			}
		}
		filterChain.doFilter(request, response);
 */