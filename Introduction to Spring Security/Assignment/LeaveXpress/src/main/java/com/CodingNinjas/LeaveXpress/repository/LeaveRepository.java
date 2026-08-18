package com.CodingNinjas.LeaveXpress.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.CodingNinjas.LeaveXpress.model.LeaveModel;

public interface LeaveRepository extends JpaRepository<LeaveModel, Long>
{
    /**
     * Finds all accepted leave requests
     * @return list of accepted leaves
     */
    List<LeaveModel> findAllByIsAcceptedTrue();
    /**
     * Finds all rejected leave requests
     * @return list of rejected leaves
     */
    List<LeaveModel> findAllByIsAcceptedFalse();
}