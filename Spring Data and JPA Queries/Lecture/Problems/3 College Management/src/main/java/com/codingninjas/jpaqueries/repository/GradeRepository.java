package com.codingninjas.jpaqueries.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.codingninjas.jpaqueries.entities.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer>
{
	/*
	   Write a Native Query to fetch the average of grades of a given
	   Student.
	*/
	@Query
	(
		nativeQuery = true,
		value = "select avg(g.marks) from grade g where g.student_id = ?1"
	)
	public double getAverageGradesOfStudent(int studentId);
}