package com.security.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.security.bank.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>
{
    Role findByRoleName(String roleName);
}