package com.security.bank.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.security.bank.dto.UserDto;
import com.security.bank.entity.Role;
import com.security.bank.entity.User;
import com.security.bank.repository.RoleRepository;
import com.security.bank.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    public void registerUser(UserDto userDto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(userDto.getPassword());
        Role role = new Role();
        role.setRoleName("ROLE_CUSTOMER");
        User saveUser = new User();
        saveUser.setName(userDto.getName());
        saveUser.setPassword(encodedPassword);
        saveUser.setUsername(userDto.getUsername());
        saveUser.setIdentityProof(userDto.getIdentityProof());
        saveUser.setNumber(userDto.getNumber());
        saveUser.setRoles(role);
        saveUser.setAddress(userDto.getAddress());
        userRepository.save(saveUser);
    }

    public User getUserById(Long id)
    {
        return userRepository.findById(id).get();
    }
}