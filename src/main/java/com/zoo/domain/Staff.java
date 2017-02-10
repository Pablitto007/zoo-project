package com.zoo.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Paweł (Pablitto007)
 */
@Entity
public class Staff {

	@Id
	@SequenceGenerator(name = "staffSeqGen", sequenceName = "SEQ_STAFF") // from DBMS																		
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

	@JsonIgnore
	@OneToMany(mappedBy = "supervisor", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Staff> inferiors = new HashSet<>();

	private String specialization;
	

	@ManyToOne
	@JoinColumn(name = "division_id")
	private Division division;

	@JsonIgnore
	@OneToMany(mappedBy = "responsiblePerson", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Animal> animals = new HashSet<>();

	protected Staff() {
	}

	public Staff(String name, String surname, char gender, Staff supervisor, String specialization)

	{
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.supervisor = supervisor;
		this.specialization = specialization;
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
				+ ", supervisor=" + supervisor + ", specialization=" + specialization
				+ ", division=" + division + "]";
	}

	public void addAnimal(Animal animal) {
		animal.setResponsiblePerson(this);
		this.animals.add(animal);
	}
	
	public void addInferior(Staff inferior){
		inferior.setSupervisor(this);
		this.inferiors.add(inferior);
	}

	public void onDelete() {
		animals.forEach(e -> e.setResponsiblePerson(null));
		inferiors.forEach(i -> i.setSupervisor(null));
	}

	public void setDivision(Division division) {
		this.division = division;
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

	public Staff getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Staff supervisor) {
		this.supervisor = supervisor;
	}

	public Division getDivision() {
		return division;
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
