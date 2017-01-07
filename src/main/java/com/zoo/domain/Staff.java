package com.zoo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Staff {
	
	@Id
	@SequenceGenerator(name="staffSeqGen",  sequenceName="SEQ_STAFF")//from RDBMS
	@GeneratedValue(generator="staffSeqGen")
	private Long id;
	
	private String name;
	private String surname;
	private char gender;
	
	@Column(name="supervisor_id")
	private Long supervisorID;
	
	private String specialization; 
	
	@Column(name="division_id", nullable=false)
	private Long divisionID;
	
	protected Staff(){}

	public Staff(String name, String surname, char gender,
//			Long supervisorID, String specialization, 
	Long divisionID) 
	{
		this.name = name;
		this.surname = surname;
		this.gender = gender;
//		this.supervisorID = supervisorID;
//		this.specialization = specialization;
		this.divisionID = divisionID;
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", name=" + name + ", surname=" + surname + ", gender=" + gender + ", supervisorID="
				+ supervisorID + ", specialization=" + specialization + ", divisionID=" + divisionID + "]";
	}
	
	//getters

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public char getGender() {
		return gender;
	}

	public Long getSupervisorID() {
		return supervisorID;
	}

	public String getSpecialization() {
		return specialization;
	}

	public Long getDivisionID() {
		return divisionID;
	}
	
	
}
