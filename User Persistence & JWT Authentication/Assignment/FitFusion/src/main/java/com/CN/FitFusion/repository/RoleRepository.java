package com.CN.FitFusion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CN.FitFusion.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>
{
    Optional<Role> findByRoleName(String name);
}