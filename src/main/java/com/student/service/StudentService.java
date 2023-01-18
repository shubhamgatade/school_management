package com.student.service;

import java.util.List;

import com.student.entity.Student;
import com.student.exception.StudentNotFoundException;

public interface StudentService {

	Student create(Student student);
	
	Student getStudentDetailsById(Integer id) throws StudentNotFoundException;
		
	String deleteStudent(Integer rollNo) throws StudentNotFoundException;
	
	Student updateStudentDetailsById(Integer id, Student student) throws StudentNotFoundException;

	List<Student> getAllStudentDetails();

	List<Student> getStudentsByTeacherId(Integer teacherId);
}
