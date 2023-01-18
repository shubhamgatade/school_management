package com.student.exception;

public class StudentNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;//Ignore

	public StudentNotFoundException(String message) {

		super(message);
	}
}
