package com.CN.FitFusion.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.CN.FitFusion.dto.UserDto;
import com.CN.FitFusion.exception.UserNotFoundException;
import com.CN.FitFusion.model.Role;
import com.CN.FitFusion.model.User;
import com.CN.FitFusion.repository.RoleRepository;
import com.CN.FitFusion.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public User getUserById(long id)
    {
        return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("user not found"));
    }

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    @Transactional
    public void updateUser(UserDto dto, long id)
    {
        User user = getUserById(id);
        user.setAge(dto.getAge());
        user.setContactNo(dto.getContactNo());
        user.setEmail(dto.getEmail());
        user.setGender(dto.getGender());
        user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
    }

    public void deleteUser(long id)
    {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    @Transactional
    public void createUser(UserDto dto)
    {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        user.setAge(dto.getAge());
        user.setContactNo(dto.getContactNo());
        user.setGender(dto.getGender());
        if(dto.getUserType() == null)
        {
            dto.setUserType("customer");
        }
        user.getRoles().add(getRole(dto.getUserType()));
        userRepository.save(user);
    }

    @Transactional
    private Role getRole(String userType)
    {
        String roleName;
        switch(userType.toLowerCase())
        {
            case "admin" -> roleName = "ROLE_ADMIN";
            case "trainer" -> roleName = "ROLE_TRAINER";
            default -> roleName = "ROLE_CUSTOMER";
        }
        return roleRepository.findByRoleName(roleName)
        .orElseGet(() -> roleRepository.save(new Role(null, roleName)));
    }
}