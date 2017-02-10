package com.zoo.web;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zoo.domain.Animal;
import com.zoo.repository.AnimalRepository;

@RestController
@RequestMapping("/staff")
public class StaffController {
	@Autowired
	AnimalRepository staffRepository; 
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Set<Animal> getAllAnimals(){
		Set<Animal> animals = staffRepository.findAll();
//		model.addAttribute("animals", animals);
		return animals;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Animal getOne(@PathVariable String id){
		Animal animal = staffRepository.findOne(Long.parseLong(id)).get();
//		model.addAttribute("animals", animals);
		return animal;
	}

}
