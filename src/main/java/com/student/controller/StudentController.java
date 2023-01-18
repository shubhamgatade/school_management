package com.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.student.entity.Student;
import com.student.exception.StudentNotFoundException;
import com.student.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/create")
	public Student create(@RequestBody Student student) {

		Student newStudent = studentService.create(student);
		if (newStudent.getId() > 0) {
			return newStudent;
		}
		throw new RuntimeException("Error! Student not created");
	}

	@GetMapping("/get/{id}")
	public Student getStudent(@PathVariable("id") Integer id) throws StudentNotFoundException {

		Student student = studentService.getStudentDetailsById(id);
		return student;
	}

	@DeleteMapping("/delete")
	public String deleteByRP(@RequestParam("id") Integer id) throws StudentNotFoundException {

		String response = studentService.deleteStudent(id);
		return response;
	}

	@PutMapping("/update")
	public Student updateStudent(@RequestParam("id") Integer id, @RequestBody Student student)
			throws StudentNotFoundException {

		Student newStudent = studentService.updateStudentDetailsById(id, student);
		return newStudent;
	}
}