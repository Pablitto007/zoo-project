package com.zoo.domain;

import java.util.List;

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
	
	private String name;
	
	@OneToMany(mappedBy="division")
	private List<Staff> staffList;
	
	protected Division(){}

	public Division(Integer id, String name) {
		this.id = id;
		this.name = name;
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
	
	
}
