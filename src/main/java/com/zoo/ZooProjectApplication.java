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
import com.zoo.domain.Staff;
import com.zoo.repository.AnimalRepository;
import com.zoo.repository.StaffRepository;


@SpringBootApplication
public class ZooProjectApplication {
	
	private static final Logger log = LoggerFactory.getLogger(ZooProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ZooProjectApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(AnimalRepository repository, StaffRepository staffRepo) {
		return (args) -> {
			// save a couple of staff
//			Optional<Staff> opt = staffRepo.findOne(13L);
//			Staff staff = opt.get();
//			repository.save(new Animal("Kermit", "Frog", 'M', 
//					LocalDate.of(2013, Month.APRIL, 20), LocalDate.of(2013, Month.APRIL, 20),staff));


			// fetch all animals
			log.info("Animals found with findAll():");
			log.info("-------------------------------");
			for (Animal animal : repository.findBySpiecesIgnoreCase("LiOn")) {
				log.info(animal.toString());
			}
			log.info("");

			// fetch an individual customer by ID
//			Optional<Animal> optional = repository.findOne(1L);
//			log.info("Customer found with findOne(1L):");
//			log.info("--------------------------------");
//			log.info(optional.get().toString());
			log.info("");

			
			log.info("");
		};
	}
}
