package com.CN.Gym.repository;

import com.CN.Gym.model.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, Long>
{
}