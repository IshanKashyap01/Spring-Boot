package com.codingNinjas.taxEase.controller;

import com.codingNinjas.taxEase.dto.UserDto;
import com.codingNinjas.taxEase.model.User;
import com.codingNinjas.taxEase.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController
{
	private final UserService service;

	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getAllUsers()
	{
		return service.getAllUsers();		
	}

	@GetMapping("/{userid}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('NORMAL')")
	public User getUserById(@PathVariable Long userid)
	{
		return service.getUserById(userid);
	}

	@PostMapping("/signup")
	@ResponseStatus(HttpStatus.CREATED)
	public void addUser(@RequestBody UserDto userDto)
	{
		service.createUser(userDto);
	}

	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('NORMAL')")
	@ResponseStatus(HttpStatus.OK)
	public void updateUser(@PathVariable Long id,@RequestBody UserDto userDto)
	{
		service.updateUser(userDto, id);
	}
}