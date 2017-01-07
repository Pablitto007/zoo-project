package com.zoo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.zoo.domain.Animal;
import com.zoo.repository.AnimalRepository;


@SpringBootApplication
public class ZooProjectApplication {
	
	private static final Logger log = LoggerFactory.getLogger(ZooProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ZooProjectApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(AnimalRepository repository) {
		return (args) -> {
			// save a couple of animals
			repository.save(new Animal("Bruno", "Lion", 'M'));


			// fetch all animals
			log.info("Animals found with findAll():");
			log.info("-------------------------------");
			for (Animal animal : repository.findAll()) {
				log.info(animal.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Animal customer = repository.findOne(1L);
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			
			log.info("");
		};
	}
}
