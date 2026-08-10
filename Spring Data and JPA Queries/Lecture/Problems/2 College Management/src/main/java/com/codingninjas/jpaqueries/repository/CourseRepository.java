package com.codingninjas.jpaqueries.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.codingninjas.jpaqueries.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>
{
	Optional<Course> findByName(String course);
	
	/*
	 * Write a JPQL Query which returns the List of courses_name by the student id'
	*/
	@Query
	(
		"select c.name from Course c join c.students s where s.id = ?1"
	)
	List<String> getCoursesOfStudent(int studentId);
}