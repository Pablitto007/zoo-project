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
@RequestMapping("/rest/animals")
public class AnimalRestController {
	
	@Autowired
	AnimalRepository aniamalRepository; 
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public Set<Animal> getAllAnimals(){
		Set<Animal> animals = aniamalRepository.findAll();
//		model.addAttribute("animals", animals);
		return animals;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Animal getOne(@PathVariable String id){
		Animal animal = aniamalRepository.findOne(Long.parseLong(id)).get();
//		model.addAttribute("animals", animals);
		return animal;
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String id){
		Animal animal = aniamalRepository.findOne(Long.parseLong(id)).get();
		aniamalRepository.delete(animal);
//		System.out.println(id);
	}

}
