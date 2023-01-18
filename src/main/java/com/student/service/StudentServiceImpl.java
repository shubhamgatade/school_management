package com.student.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.student.entity.Student;
import com.student.exception.StudentNotFoundException;
import com.student.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student create(Student student) {

		if (Objects.nonNull(student)) {
			student = studentRepository.save(student);
			return student;
		}
		return null;
	}

	@Override
	public Student getStudentDetailsById(Integer rollNo) throws StudentNotFoundException {

		Optional<Student> studentDetails = studentRepository.findById(rollNo);
		if (studentDetails.isPresent()) {

			Student student = studentDetails.get();
			return student;
		}
		throw new StudentNotFoundException("No Student Found");
	}

	@Override
	public List<Student> getAllStudentDetails() {

		return studentRepository.getAllStudentDetails();
	}

	@Override
	public List<Student> getStudentsByTeacherId(Integer teacherId) {

		return studentRepository.getStudentsByTeacherId(teacherId);
	}

	@Override
	public String deleteStudent(Integer rollNo) throws StudentNotFoundException {

		Optional<Student> studentDetails = studentRepository.findById(rollNo);
		if (studentDetails.isPresent()) {
			Student student = studentDetails.get();
			student.setStatus(false);
			studentRepository.save(student);
		}
		throw new StudentNotFoundException("No Student Found");
	}

	@Transactional
	@Override
	public Student updateStudentDetailsById(Integer rollNo, Student student) throws StudentNotFoundException {

		if (rollNo != null && rollNo > 0) {
			Student existing = getById(rollNo);

			if (Objects.nonNull(existing)) {
				if (!StringUtils.isEmpty(student.getName())) {
					existing.setName(student.getName());
				}
				if (student.getAge() != null) {
					existing.setAge(student.getAge());
				}
				studentRepository.save(existing);
				return existing;
			}
			throw new StudentNotFoundException("No Student Found");
		}
		throw new StudentNotFoundException("Invalid Student ID");
	}

	private Student getById(Integer rollNo) throws StudentNotFoundException {

		Optional<Student> findById = studentRepository.findById(rollNo);
		if (findById.isPresent()) {
			return findById.get();
		}
		throw new StudentNotFoundException("Student Not Found");
	}
}