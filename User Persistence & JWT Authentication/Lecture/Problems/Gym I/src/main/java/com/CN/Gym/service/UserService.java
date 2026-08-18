package com.CN.Gym.service;

import com.CN.Gym.dto.UserRequest;
import com.CN.Gym.dto.WorkoutDto;
import com.CN.Gym.exception.UserNotFoundException;
import com.CN.Gym.model.Role;
import com.CN.Gym.model.User;
import com.CN.Gym.model.Workout;
import com.CN.Gym.repository.UserRepository;
import com.CN.Gym.repository.WorkoutRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
/*
This is the service class for User, you need to complete the class by doing the following:
a. Use appropriate annotations.
b. Complete the methods given below.
c. Autowire the necessary dependencies.
*/
@Service
public class UserService
{    
    private final UserRepository userRepository;

    private final WorkoutRepository workoutRepository;

    public UserService(UserRepository userRepository, WorkoutRepository workoutRepository)
    {
        this.userRepository = userRepository;
        this.workoutRepository = workoutRepository;
    }

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    public void createUser(UserRequest dto) 
    {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(dto.getPassword());
        User user = new User(null, dto.getEmail(), encodedPassword, dto.getAge(), dto.getGender(), null, null, new ArrayList<>());
        // User user = User.builder().email(userRequest.getEmail()).age(userRequest.getAge())
        //         .gender(userRequest.getGender()).password(encodedPassword)
        //         .build();
        Role role = new Role();
        Set<Role> roles = new HashSet<>();
        if(dto.getUserType() != null)
        {
            if(dto.getUserType().equalsIgnoreCase("TRAINER"))
            {
                role.setRoleName("ROLE_TRAINER");
                roles.add(role);
                user.setRoles(roles);
            } 
            else if(dto.getUserType().equalsIgnoreCase("ADMIN"))
            {
                role.setRoleName("ROLE_ADMIN");
                roles.add(role);
                user.setRoles(roles);
            } 
            else
            {
                role.setRoleName("ROLE_CUSTOMER");
                roles.add(role);
                user.setRoles(roles);
            }
        }
        else
        {
            role.setRoleName("ROLE_CUSTOMER");
            roles.add(role);
            user.setRoles(roles);
        }
        userRepository.save(user);
    }

    public User getUserById(Long id)
    {
        return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("user not found"));
    }

    @Transactional
    public void updateUser(UserRequest dto, Long id)
    {
        User user = getUserById(id);
        user.setAge(dto.getAge());
        user.setEmail(dto.getEmail());
        user.setGender(dto.getGender());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(dto.getPassword()));
    }

    public void deleteUser(Long id)
    {
        getUserById(id);
        userRepository.deleteById(id);        
    }

    @Transactional
    public void addWorkout(WorkoutDto dto, Long userId)
    {
        User user = getUserById(userId);
        Workout workout = new Workout
        (null, dto.getWorkoutName(), dto.getDescription(), dto.getDifficultyLevel(), dto.getDuration(), user);
        workoutRepository.save(workout);
        user.getWorkouts().add(workout);
    }
}
