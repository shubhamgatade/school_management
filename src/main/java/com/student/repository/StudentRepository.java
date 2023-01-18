package com.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.student.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

	@Query(value = "select * from student_details sd where sd.id = :id", nativeQuery = true)
	Student getById(@Param("id") Integer id);
	
	@Query(value = "select * from student_details", nativeQuery = true)
	List<Student> getAllStudentDetails();
	
	@Query(value = "select sd.* from teacher_student_details tsd inner join student_details sd "
			+ "on tsd.student_id =sd.id where tsd.teacher_id = :id", nativeQuery = true)
	List<Student> getStudentsByTeacherId(@Param("id") Integer id);
}
