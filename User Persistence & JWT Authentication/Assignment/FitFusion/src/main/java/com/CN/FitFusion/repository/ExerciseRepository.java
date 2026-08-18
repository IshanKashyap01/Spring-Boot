package com.CN.FitFusion.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.CN.FitFusion.model.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long>
{
    List<Exercise> findAllByUserId(Long userId);
}