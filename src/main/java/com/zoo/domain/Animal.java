package com.zoo.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Animals")
public class Animal {

	@Id
	@SequenceGenerator(name="animalSeqGen",  sequenceName="SEQ_ANIMALS")//from RDBMS
	@GeneratedValue(generator="animalSeqGen")
	private Long id;
	
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String spieces;
	
	private char gender;
	
	@Column(name="birth_date", nullable=false)
	@Temporal(TemporalType.DATE)
	LocalDate birthDate;
	
	@Column(name="arrival_date")
	@Temporal(TemporalType.DATE)
	LocalDate arrivalDate;
	
	@ManyToOne
	@JoinColumn(name="responsible_person_id")
	private Staff responsiblePerson;
	
	
	protected Animal(){}

	public Animal(String name, String spieces, char gender,
			LocalDate birthDate, LocalDate arrivalDate, 
	Staff responsiblePerson) 
	{
		this.name = name;
		this.spieces = spieces;
		this.gender = gender;
		this.birthDate = birthDate;
		this.arrivalDate = arrivalDate;
		this.responsiblePerson = responsiblePerson;
	}
	
	@Override
	public String toString() {
		return "Animal [id=" + id + ", name=" + name + ", spieces=" + spieces + ", gender=" + gender 
				+  ", birthDate=" + birthDate + ", arrivalDate=" + arrivalDate
				+ ", responsiblePerson=" + responsiblePerson + "]";
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSpieces() {
		return spieces;
	}

	public char getGender() {
		return gender;
	}


	public LocalDate getBirthDate() {
		return birthDate;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public Staff getResponsiblePersonID() {
		return responsiblePerson;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSpieces(String spieces) {
		this.spieces = spieces;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}


	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public void setResponsiblePersonID(Staff responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}	
	
}
