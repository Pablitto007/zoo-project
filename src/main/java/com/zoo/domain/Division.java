package com.zoo.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * @author Paweł (Pablitto007)
 */
@Entity
@Table(name="Divisions")
public class Division {

	@Id
	@SequenceGenerator(name="divisionSeqGen",  sequenceName="SEQ_DIVISONS")//from RDBMS
	@GeneratedValue(generator="divisionSeqGen")
	private Integer id;
	
	@Column(name = "UUID_number")
	private UUID uuid = UUID.randomUUID();
	
	private String name;
	
	@OneToMany(mappedBy="division", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Staff> staffSet = new HashSet<>();
	
	protected Division(){}
	

	public Division(String name) {
		this.name = name;
	}
	
	public void addStaff (Staff staff){
			staff.setDivision(this);
			staffSet.add(staff);
	}
	
	public void onDelete(){
		staffSet.forEach(e -> e.setDivision(null));
		this.staffSet.removeAll(staffSet);
	}
	
	public Set<Staff> getStaffSet() {
		return staffSet;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getUuid() {
		return uuid;
	}
}
