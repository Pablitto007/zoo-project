package com.zoo;

import java.util.List;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.zoo.domain.Animal;
import com.zoo.domain.Staff;
import com.zoo.repository.AnimalRepository;
import com.zoo.repository.StaffRepository;
import com.zoo.util.SampleDataGenerator;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class ZooProjectApplication 
{
	
	@Autowired
	AnimalRepository animalRepository;
	
	@Autowired
	StaffRepository staffRepository;

	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(50);
		executor.setThreadNamePrefix("Async-");
		executor.initialize();
		return executor;
	}

	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
        	
        	/**
        	 * Sample data generated via SampleDataGenerator class. 
        	 * Each person is responsible for 2 distinct animals at the beginning.
        	 */
        	List<Animal> animals = SampleDataGenerator.getAnimals(40);
        	List<Staff> staff = SampleDataGenerator.getStaff(20);
        	
        	for (int i = 0; i < staff.size(); i++){
        		Staff toPersist = staff.get(i);
        		
        		for (int y = i*2;  y <= animals.size();){
        		List<Animal> sublist = animals.subList(y, y+2);	
        		Animal [] toAdd = new Animal [sublist.size()];
        		toPersist.addAnimal(sublist.toArray(toAdd));
        		staffRepository.save(toPersist);
        		break;
        		}
        	}
        };
    }
	public static void main(String[] args) {
		SpringApplication.run(ZooProjectApplication.class, args);
	}
}
