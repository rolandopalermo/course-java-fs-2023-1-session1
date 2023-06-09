package com.rolandopalermo.sample.repositories.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "instructors")
public class Instructor {

	@Id
	@Column(name = "id", unique= true, updatable=false, nullable=false)
	private String id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="registration_date")
	private LocalDateTime registrationDate;
	
	@Column(name="dni")
	private String dni;
}
