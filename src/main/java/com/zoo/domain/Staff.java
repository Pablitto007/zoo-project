package com.zoo.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * 
 * @author Paweł (Pablitto007)
 */
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
	private Long supervisor;
	
	private String specialization; 
	
	@ManyToOne
	@JoinColumn(name="division_id")
	private Division division;
	
	@OneToMany(mappedBy="responsiblePerson")
	private List<Animal> animalList;
	
	protected Staff(){}

	public Staff(String name, String surname, char gender,
//			Long supervisorID, String specialization, 
	Division divisionID) 
	{
		this.name = name;
		this.surname = surname;
		this.gender = gender;
//		this.supervisorID = supervisorID;
//		this.specialization = specialization;
		this.division = divisionID;
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", name=" + name + ", surname=" + surname + ", gender=" + gender + ", supervisorID="
				+ supervisor + ", specialization=" + specialization + ", divisionID=" + division + "]";
	}
	
	//getters and setters

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
		return supervisor;
	}

	public String getSpecialization() {
		return specialization;
	}

	public Division getDivisionID() {
		return division;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public void setSupervisorID(Long supervisorID) {
		this.supervisor = supervisorID;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public void setDivisionID(Division divisionID) {
		this.division = divisionID;
	}
	
	
}
