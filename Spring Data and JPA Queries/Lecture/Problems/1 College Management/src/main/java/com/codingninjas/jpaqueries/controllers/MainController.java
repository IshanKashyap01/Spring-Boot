package com.codingninjas.jpaqueries.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.codingninjas.jpaqueries.entities.*;
import com.codingninjas.jpaqueries.services.MainService;

@RestController
public class MainController {
	
	final MainService service;

	MainController(MainService service) {
		this.service = service;
	}
	
	@GetMapping("/student/{id}")
	public Student getStudentById(@PathVariable int id) {
		return service.getStudentById(id);
	}
	
	@PostMapping("/student")
	public void saveStudent(@RequestBody Student student) {
		System.out.println("student name is "+ student.getName());
		service.setStudent(student);
	}
	
	@PostMapping("/student/{id}/courses_marks")
	public void saveCoursesWithMarks(@RequestBody List<CourseMarks> courses,@PathVariable int id) {
		service.setCourses(courses,id);
	}
	
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		return service.getAllStudents();
	}
	
	/*
					Tasks:

	 1. Create a method with name getAllStudentsByCourse() which returns a list the Student.
	 2. The method getAllStudentsByCourse(@PathVariable String course) takes String course as input parameter.
	 2. You need to use GET mapping for getAllStudentsByCourse() method.
	 3. The path for mapping should be "/students/{course}" where course is a string.
	 4. Call the appropriate service method in order to get all students by the given course name.

	 */
	@GetMapping("/students/{course}")
	public List<Student> getAllStudentsByCourse(@PathVariable String course)
	{
		return service.getAllStudentsByCourse(course);
	}
}
