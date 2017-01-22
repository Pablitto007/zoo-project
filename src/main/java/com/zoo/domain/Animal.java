package com.zoo.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * 
 * @author Paweł (Pablitto007)
 */
@Entity
@Table(name="Animals")
public class Animal {

	@Id
	@SequenceGenerator(name="animalSeqGen",  sequenceName="SEQ_ANIMALS")//from RDBMS
	@GeneratedValue(generator="animalSeqGen")
	private Long id;
	
	@Column(name = "UUID_number")
	private UUID uuid = UUID.randomUUID();
	
	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String spieces;
	
	private char gender;
	
	@Column(name="birth_date", nullable=false)
	LocalDate birthDate;
	
	@Column(name="arrival_date")
	LocalDate arrivalDate;
	
	@ManyToOne
	@JoinColumn(name="responsible_person_id")
	private Staff responsiblePerson;
	
	
	protected Animal(){}

	public Animal(String name, String spieces, char gender,
			LocalDate birthDate, LocalDate arrivalDate) 
	 
	{
		this.name = name;
		this.spieces = spieces;
		this.gender = gender;
		this.birthDate = birthDate;
		this.arrivalDate = arrivalDate;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(uuid);	
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || !(obj instanceof Animal))
			return false;
		Animal other = (Animal) obj;
		return Objects.equals(this.getUuid(), other.getUuid());
	}
	
	@Override
	public String toString() {
		return "Animal [id=" + id + ", uuid=" + uuid + ", name=" + name + ", spieces=" + spieces + ", gender=" + gender
				+ ", birthDate=" + birthDate + ", arrivalDate=" + arrivalDate + ", responsiblePerson="
				+ responsiblePerson + "]";
	}

	public void setResponsiblePerson(Staff responsiblePerson) {
//		responsiblePerson.getAnimals().add(this);
		this.responsiblePerson = responsiblePerson;
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


	public UUID getUuid() {
		return uuid;
	}

	public Staff getResponsiblePerson() {
		return responsiblePerson;
	}	
}
