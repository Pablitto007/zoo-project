package com.zoo.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Animals")
public class Animal {

	@Id
	@SequenceGenerator(name="animalSeqGen",  sequenceName="SEQ_ANIMALS")//from RDBMS
	@GeneratedValue(generator="animalSeqGen")
	private Long id;
	
	private String name;
	private String spieces;
	private char gender;
	
	@Column(name="father_id")
	private Long motherID;
	
	@Column(name="mother_id")
	private Long fatherID;
	
	@Column(name="birth_date", nullable=false)
	private Date birthDate;
	
	@Column(name="arrival_date")
	private Date arrivalDate;
	
	@ManyToOne
	@JoinColumn(name="responsible_person_id")
	private Staff responsiblePerson;
	
	
	protected Animal(){}

	public Animal(String name, String spieces, char gender,
//	, Long motherId, Long fatherId, Date birthDate,
//			Date arrivalDate, 
	Staff responsiblePerson) 
	{
		this.name = name;
		this.spieces = spieces;
		this.gender = gender;
//		this.motherId = motherId;
//		this.fatherId = fatherId;
//		this.birthDate = birthDate;
//		this.arrivalDate = arrivalDate;
		this.responsiblePerson = responsiblePerson;
	}
	
	@Override
	public String toString() {
		return "Animal [id=" + id + ", name=" + name + ", spieces=" + spieces + ", gender=" + gender + ", motherId="
				+ motherID + ", fatherId=" + fatherID + ", birthDate=" + birthDate + ", arrivalDate=" + arrivalDate
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

	public Long getMotherID() {
		return motherID;
	}

	public Long getFatherID() {
		return fatherID;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public Date getArrivalDate() {
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

	public void setMotherID(Long motherID) {
		this.motherID = motherID;
	}

	public void setFatherID(Long fatherID) {
		this.fatherID = fatherID;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public void setResponsiblePersonID(Staff responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}	
	
}
