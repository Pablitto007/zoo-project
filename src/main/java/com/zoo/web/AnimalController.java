package com.zoo.web;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zoo.domain.Animal;
import com.zoo.repository.AnimalRepository;

@Controller
@RequestMapping("/animals")
public class AnimalController {
	
	@Autowired
	AnimalRepository aniamalRepository; 
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String getAllAnimals(Model model){
		Set<Animal> animals = aniamalRepository.findAll();
		model.addAttribute("animals", animals);
		return "animals";
	}
	
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public Animal getOne(@PathVariable String id){
//		Animal animal = aniamalRepository.findOne(Long.parseLong(id)).get();
////		model.addAttribute("animals", animals);
//		return animal;
//	}

}
