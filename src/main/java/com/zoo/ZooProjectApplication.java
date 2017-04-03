package com.zoo;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.zoo.domain.Animal;
import com.zoo.domain.Staff;
import com.zoo.repository.AnimalRepository;
import com.zoo.repository.DivisionRepository;
import com.zoo.repository.StaffRepository;

@SpringBootApplication
public class ZooProjectApplication 
{
	
	@Autowired
	AnimalRepository animalRepository;
	
	@Autowired
	StaffRepository staffRepository;
	

	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
        	
         // 1
    		Staff Al = new Staff("Al" , "Abranow" , 'f', null, "Mammals");
    		Animal Anatol = new Animal("Anatol" , "lion", 'M', 
    				LocalDate.of(2013, Month.JANUARY, 1), LocalDate.of(2015, Month.JANUARY, 1));
    		Animal Opera = new Animal("Opera" , "lion", 'F', 
    				LocalDate.of(2008, Month.NOVEMBER, 11), null);
    		Al.addAnimal(new Animal [] {Anatol, Opera});
    		staffRepository.save(Al);	
    		//2
    		Staff Cezar = new Staff("Cezar" , "Cejrus" , 'M', null, "Reptiles");
    		Animal turtle = new Animal("Donald" , "turtle", 'M', 
    			LocalDate.of(2010, Month.JANUARY, 1), LocalDate.of(2010, Month.JANUARY, 1));
    		Cezar.addAnimal(turtle);
    		staffRepository.save(Cezar);
        };
    }
	public static void main(String[] args) {
		SpringApplication.run(ZooProjectApplication.class, args);
	}
}
