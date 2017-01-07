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
	
	@ManyToOne()
	@JoinColumn(name="responsible_person_id", nullable=false)
	private Long responsiblePersonID;
	
	
	protected Animal(){}

	public Animal(String name, String spieces, char gender,
//	, Long motherId, Long fatherId, Date birthDate,
//			Date arrivalDate, 
	Long responsiblePersonID) 
	{
		this.name = name;
		this.spieces = spieces;
		this.gender = gender;
//		this.motherId = motherId;
//		this.fatherId = fatherId;
//		this.birthDate = birthDate;
//		this.arrivalDate = arrivalDate;
		this.responsiblePersonID = responsiblePersonID;
	}
	
	@Override
	public String toString() {
		return "Animal [id=" + id + ", name=" + name + ", spieces=" + spieces + ", gender=" + gender + ", motherId="
				+ motherID + ", fatherId=" + fatherID + ", birthDate=" + birthDate + ", arrivalDate=" + arrivalDate
				+ ", responsiblePerson=" + responsiblePersonID + "]";
	}	
}
