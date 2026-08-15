package com.CN.Gym.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.CN.Gym.exception.UserNotFoundException;
import com.CN.Gym.repository.UserRepository;

/*
1. Autowire the necessary dependencies and override the interface methods.
*/
@Service
public class CustomUserDetailService implements UserDetailsService
{
	private final UserRepository repository;

	public CustomUserDetailService(UserRepository repository)
	{
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		return repository.findByEmail(username)
		.orElseThrow(() -> new UserNotFoundException("user not found"));
	}
}