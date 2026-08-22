package com.security.bank.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.security.bank.entity.User;

public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByUsername(String username);
}