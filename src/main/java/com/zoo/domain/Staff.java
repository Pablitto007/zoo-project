package com.zoo.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
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
	@SequenceGenerator(name = "staffSeqGen", sequenceName = "SEQ_STAFF") // from
																			// RDBMS
	@GeneratedValue(generator = "staffSeqGen")
	private Long id;

	@Column(name = "UUID_number", nullable = false)
	private UUID uuid = UUID.randomUUID();

	private String name;
	private String surname;
	private char gender;

	@ManyToOne
	@JoinColumn(name = "supervisor_id")
	private Staff supervisor;

	@OneToMany(mappedBy = "supervisor", cascade = CascadeType.PERSIST)
	private Set<Staff> inferiors = new HashSet<>();

	private String specialization;

	@ManyToOne
	@JoinColumn(name = "division_id")
	private Division division;

	@OneToMany(mappedBy = "responsiblePerson")
	private Set<Animal> animals = new HashSet<>();

	protected Staff() {
	}

	public Staff(String name, String surname, char gender, Staff supervisor, String specialization) // Division
																									// divisionID)
	{
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.supervisor = supervisor;
		this.specialization = specialization;
		// this.division = divisionID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuid);

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || !(obj instanceof Staff))
			return false;
		Staff other = (Staff) obj;
		return Objects.equals(this.getUuid(), other.getUuid());
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", uuid=" + uuid + ", name=" + name + ", surname=" + surname + ", gender=" + gender
				+ ", supervisor=" + supervisor + ", inferiors=" + inferiors + ", specialization=" + specialization
				+ ", division=" + division + ", animalList=" + animals + "]";
	}

	public void setAnimals(Animal animal) {
//		for (Animal animal : animals) {
			this.animals.add(animal);
//		}
	}

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

	public Staff getSupervisorID() {
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

	public void setSupervisorID(Staff supervisor) {
		this.supervisor = supervisor;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public void setDivisionID(Division divisionID) {
		this.division = divisionID;
	}

	public Staff getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Staff supervisor) {
		this.supervisor = supervisor;
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public Set<Animal> getAnimals() {
		return animals;
	}

	public Set<Staff> getInferiors() {
		return inferiors;
	}

	public void setInferiors(Set<Staff> inferiors) {
		this.inferiors = inferiors;
	}

	public UUID getUuid() {
		return uuid;
	}

}
