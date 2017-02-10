package com.zoo;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@SpringBootApplication
public class ZooProjectApplication //implements CommandLineRunner 
{
	
//	@Bean
//	public Jackson2ObjectMapperBuilder jacksonBuilder() {
//		Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
//		b.featuresToDisable(SerializationFeature.);
//		b.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
//		b.build();
//		b.serializerByType(Staff.class,  
//		return b;
//	}

	private static final Logger log = LoggerFactory.getLogger(ZooProjectApplication.class);
	
	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
            	if(beanName.equalsIgnoreCase("Jackson2ObjectMapperBuilder")){
                log.info(beanName);
                log.info("-----------------");
            	}
            }

        };
    }

//
//	@Autowired
//	StaffRepository staffRepository;
//
//	@Autowired
//	AnimalRepository animalRepository;

	public static void main(String[] args) {
		SpringApplication.run(ZooProjectApplication.class, args);
	}


//	@Override
//	public void run(String... arg0) throws Exception {
		// fetch an individual animal by ID
//		log.info("Animal count =" + animalRepository.count());
//		Animal animal = animalRepository.findOne(27L).get();
//		animal.setResponsiblePerson(null);
//		animalRepository.delete(animal);
//
//		log.info("Delete animal id =23");
//		log.info("Animal count =" + animalRepository.count());
//		log.info("--------------------------------");
//		log.info("");

//		log.info("Staff count =" + staffRepository.count());
//		Staff staff = staffRepository.findOne(29L).get();
//		staff.setDivision(null);
//		staff.onDelete();
//		staffRepository.delete(staff);
//		log.info("Delete staff id =28");
//		log.info("Staff count =" + staffRepository.count());
//		log.info("--------------------------------");
//		log.info("");
//
//		// fetch all animals
//		log.info("Staff found with findAll():");
//		log.info("-------------------------------");
//		for (Staff an : staffRepository.findAll()) {
//			log.info(an.toString());
//		}
//		log.info("");
//
//		log.info("");
//	};
	
	// @Bean
		// public CommandLineRunner demo(DivisionRepository divisionRepository,
		// AnimalRepository animalRepository,
		// StaffRepository staffRepository, EntityManager em) {
		// return (args) -> {
		// save a couple of staff
		// Division mammals =new Division("MAMMALS");
		// Animal lion = new Animal("Anatol" , "lion", 'M',
		// LocalDate.of(2013, Month.JANUARY, 1), LocalDate.of(2015,
		// Month.JANUARY, 1));
		// Staff Al = new Staff("Santa" , "Abranow" , 'f', null, "Mammals");
		//
		//
		// divisionRepository.save(mammals);
		// Al.setDivision(mammals);
		// Al.addAnimal(lion);

		// fetch an individual animal by ID
		// log.info("Animal count =" + animalRepository.count());
		// Animal animal = animalRepository.findOne(23L).get();
		// animal.onDelete();
		//
		//
		// log.info("Delete animal id =23");
		// log.info("Animal count =" + animalRepository.count());
		// log.info("--------------------------------");
		// log.info("");
		//
		// log.info("Staff count =" + staffRepository.count());
		// Staff staff = staffRepository.findOne(23L).get();
		// staff.onDelete();
		// staffRepository.delete(staff);
		// log.info("Delete staff id =23");
		// log.info("Staff count =" + staffRepository.count());
		// log.info("--------------------------------");
		// log.info("");

		// fetch all animals
		// log.info("Animals found with findAll():");
		// log.info("-------------------------------");
		// for (Animal an : animalRepository.findAll()) {
		// log.info(an.toString());
		// }
		// log.info("");
		//
		// log.info("");
		// };
		// }

}
