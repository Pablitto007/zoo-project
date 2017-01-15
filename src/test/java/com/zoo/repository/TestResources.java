package com.zoo.repository;

import java.time.LocalDate;
import java.time.Month;

import org.junit.rules.ExternalResource;

import com.zoo.domain.Animal;
import com.zoo.domain.Division;
import com.zoo.domain.Staff;

public class TestResources extends ExternalResource {
	
	static final Animal [] ANIMALS = new Animal[4];
	static final Staff [] STAFF = new Staff[5];
	static final Division [] DIVISIONS = new Division[6];
	
	@Override
	protected void before() {
		
		DIVISIONS[0] = new Division("MAMMALS");
		DIVISIONS[1] = new Division("BIRDS");
		DIVISIONS[2] = new Division("REPTILES");
		DIVISIONS[3] = new Division("ADMINISTRATION");
		DIVISIONS[4] = new Division("SECURITY");
		DIVISIONS[5] = new Division("CLEANING");
		
		STAFF[0] = new Staff("Al" , "Abranow" , 'f', "Mammals", DIVISIONS[0]);
		STAFF[1] = new Staff("Betty" , "Brunowska" , 'f', "Birds", DIVISIONS[1]);
		STAFF[2] = new Staff("Cezar" , "Cejrus" , 'M', "Reptiles", DIVISIONS[2]);
		STAFF[3] = new Staff("Dominik" , "De Vitto" , 'M', null, DIVISIONS[3]);
		STAFF[4] = new Staff("Eve" , "Evans" , 'F', null, DIVISIONS[3]);
		
		ANIMALS[0] = new Animal("Anatol" , "lion", 'M', 
				LocalDate.of(2013, Month.JANUARY, 1), LocalDate.of(2015, Month.JANUARY, 1), STAFF[0]);
		ANIMALS[1] = new Animal("Snaky" , "Cobra", 'M', 
				LocalDate.of(2003, Month.OCTOBER, 10), null, STAFF[2]);
		ANIMALS[2] = new Animal("Opera" , "lion", 'F', 
				LocalDate.of(2008, Month.NOVEMBER, 11), null, STAFF[0]);
		ANIMALS[3] = new Animal("Donald" , "turtle", 'M', 
				LocalDate.of(2010, Month.JANUARY, 1), LocalDate.of(2010, Month.JANUARY, 1), STAFF[2]);
		
    }

}
