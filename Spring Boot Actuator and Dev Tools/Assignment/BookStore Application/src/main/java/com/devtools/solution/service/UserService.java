package com.devtools.solution.service;

import org.springframework.stereotype.Service;
import com.devtools.solution.entity.User;
import com.devtools.solution.repo.UserRepoInterface;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepoInterface repository;

    public User getUserById(Integer id)
    {
        return repository.findById(id).get();
    }

    public void saveUser(User user)
    {
        repository.save(user);
    }

    public void deleteUser(Integer id)
    {
        User user = getUserById(id);
        repository.delete(user);
    }
}