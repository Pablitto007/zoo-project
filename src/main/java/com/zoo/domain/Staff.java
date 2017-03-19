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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	
	@JsonIgnore
	@Column(name = "uuid", nullable = false)
	private UUID uuid = UUID.randomUUID();

	private String name;
	private String surname;
	private char gender;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supervisor_id")
//	@JsonBackReference
	private Staff supervisor;
	
	@JsonIgnore
//	@JsonManagedReference
	@OneToMany(mappedBy = "supervisor", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Staff> inferiors = new HashSet<>();

	private String specialization;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "division_id")
	@JsonBackReference //to avoid Infinite Recursion with Jackson 
	private Division division;


	@JsonManagedReference //to avoid Infinite Recursion with Jackson 
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
		if (obj == null || this.getClass() != (obj.getClass()))
			return false;
		Staff other = (Staff) obj;
		return Objects.equals(this.getUuid(), other.getUuid());
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", uuid=" + uuid + ", name=" + name + ", surname=" + surname + ", gender=" + gender
				+ ", supervisor=" + supervisor + ", specialization=" + specialization + "]";
	}

	public void addAnimal(Animal...animals) {
		for (Animal animal : animals){
		animal.setResponsiblePerson(this);
		this.animals.add(animal);
		}
	}
	
	public void addInferior(Staff inferior){
		inferior.setSupervisor(this);
		this.inferiors.add(inferior);
	}

	public void onDelete() {
		animals.forEach(e -> e.setResponsiblePerson(null));
		inferiors.forEach(i -> i.setSupervisor(null));
	}
	
	public void removeAnimal(Animal animal){
		animals.remove(animal);
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
