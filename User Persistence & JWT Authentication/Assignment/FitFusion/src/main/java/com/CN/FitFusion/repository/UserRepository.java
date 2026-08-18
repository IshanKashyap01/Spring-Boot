package com.CN.FitFusion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.CN.FitFusion.model.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByEmail(String email);
}