package com.student.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.student.utility.DateTimeHelper;

import lombok.Data;

@Data
@Entity
@Table(name = "student_details")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String gender;
	private Integer age;
	private String email;
	private Double attendance;
	private Boolean status;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date createdOn;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date updatedOn;
	
	@PrePersist
    public void prePersist() {

        createdOn = DateTimeHelper.getCurrentDateTime();
        status = true;
    }

    @PreUpdate
    public void postUpdate() {

        updatedOn = DateTimeHelper.getCurrentDateTime();
        status = status == null || status;
    }
}
