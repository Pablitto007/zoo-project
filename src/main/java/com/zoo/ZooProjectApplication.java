package com.zoo;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.zoo.domain.Animal;
import com.zoo.domain.Division;
import com.zoo.domain.Staff;
import com.zoo.repository.AnimalRepository;
import com.zoo.repository.DivisionRepository;
import com.zoo.repository.StaffRepository;


@SpringBootApplication
public class ZooProjectApplication {
	
	private static final Logger log = LoggerFactory.getLogger(ZooProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ZooProjectApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(DivisionRepository divisionRepository, 
			AnimalRepository animalRepository, StaffRepository staffRepository) {
		return (args) -> {
			// save a couple of staff
//			Division mammals =new Division("MAMMALS");
//			Animal lion = new Animal("Anatol" , "lion", 'M', 
//					LocalDate.of(2013, Month.JANUARY, 1), LocalDate.of(2015, Month.JANUARY, 1));
//			Staff Al = new Staff("Santa" , "Abranow" , 'f', null, "Mammals");
//
//			
//			divisionRepository.save(mammals);
//			Al.setDivision(mammals);
//			Al.addAnimal(lion);

			// fetch all animals
//			log.info("Animals found with findAll():");
//			log.info("-------------------------------");
//			for (Animal animal : animalRepository.findBySpiecesIgnoreCase("LiOn")) {
//				log.info(animal.toString());
//			}
//			log.info("");

			// fetch an individual customer by ID
//			Optional<Staff> optional =staffRepository.findOne(17L);
//			log.info("Customer found with findOne(1L):");
//			log.info("--------------------------------");
//			System.out.println(optional.get().equals(Al));
//			log.info("");

			
			log.info("");
		};
	}
}
