package com.zoo.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.zoo.domain.Animal;


public interface AnimalRepository extends CrudRepository<Animal, Long>{
	
	List<Animal> findByName(String name);

}
