package com.codingNinjas.taxEase.repository;

import com.codingNinjas.taxEase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUsername(String email);
}
