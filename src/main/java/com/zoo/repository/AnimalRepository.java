package com.zoo.repository;

import java.util.Set;

import com.zoo.domain.Animal;
import com.zoo.domain.Staff;

public interface AnimalRepository extends BaseRepository<Animal, Long> {
	
	public Set<Animal> findBySpiecesIgnoreCase(String spiece);
	
	public Set<Animal> findByResponsiblePerson(Staff responsiblePerson);

}
