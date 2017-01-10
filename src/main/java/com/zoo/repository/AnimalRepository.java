package com.zoo.repository;

import java.util.List;

import com.zoo.domain.Animal;

public interface AnimalRepository extends BaseRepository<Animal, Long> {
	
	public List<Animal> findByNameIgnoreCase(String name);

}
