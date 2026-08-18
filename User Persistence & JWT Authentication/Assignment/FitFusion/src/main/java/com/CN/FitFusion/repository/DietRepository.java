package com.CN.FitFusion.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.CN.FitFusion.model.Diet;

public interface DietRepository extends JpaRepository<Diet, Long>
{
    List<Diet> findAllByUserId(Long userId);
}