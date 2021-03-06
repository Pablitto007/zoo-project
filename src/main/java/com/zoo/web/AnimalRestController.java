package com.zoo.web;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zoo.domain.Animal;
import com.zoo.domain.Staff;
import com.zoo.repository.AnimalRepository;
import com.zoo.repository.StaffRepository;


@RestController
@RequestMapping("/rest/animals")
public class AnimalRestController {

	private AnimalRepository aniamalRepository;
	private StaffRepository staffRepository; 
	private final int PAGE_SIZE = 5;
	
	@Autowired
	public AnimalRestController(AnimalRepository aniamalRepository, StaffRepository staffRepository) {
		this.aniamalRepository = aniamalRepository;
		this.staffRepository = staffRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Set<Animal> getAllAnimals() {
		Set<Animal> animals = aniamalRepository.findAll();
		return animals;
	}
	
	@RequestMapping(value = "/page/{pageNumber}",  method = RequestMethod.GET)
	public Page<Animal> getPage(@PathVariable String pageNumber){
		PageRequest request = 
				new PageRequest((Integer.parseInt(pageNumber) -1), PAGE_SIZE, Sort.Direction.ASC, "name");
		Page<Animal> page = aniamalRepository.findAll(request);
		return page;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public void addAnimal(@RequestBody Animal animal, @PathVariable String id) {
		
		Staff responsiblePerson = staffRepository.findOne(Long.parseLong(id))
				.orElseThrow(()-> new DataAccessResourceFailureException("Can not find staff id: " + id)) ;
		
		animal.setResponsiblePerson(responsiblePerson);
		aniamalRepository.save(animal);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Animal getOne(@PathVariable String id) {
		Animal animal = aniamalRepository.findOne(Long.parseLong(id))
				.orElseThrow(()-> new DataAccessResourceFailureException("Can not find animal id: " + id)) ;
		return animal;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String id) {
		Animal animal = aniamalRepository.findOne(Long.parseLong(id))
				.orElseThrow(()-> new DataAccessResourceFailureException("Can not find animal id: " + id)) ;
		aniamalRepository.delete(animal);
	}

}
