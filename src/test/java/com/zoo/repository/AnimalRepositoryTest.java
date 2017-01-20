package com.zoo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.zoo.domain.Animal;
import com.zoo.domain.Staff;


@RunWith(SpringRunner.class)
@DataJpaTest
//@Sql({"classpath:test/schema.sql"})//, "classpath:test/data.sql"})
@Transactional
public class AnimalRepositoryTest {
	
	
//	@ClassRule
//    public static final TestResources res = new TestResources();
	
	@Autowired
	AnimalRepository animalRepository;
	
	@Autowired
	StaffRepository staffRepository;

	@Test
	public void saveTest(){
		Animal animal = new Animal("Anatol" , "lion", 'M', 
				LocalDate.of(2013, Month.JANUARY, 1), LocalDate.of(2015, Month.JANUARY, 1));
		Staff staff = new Staff("Al" , "Abranow" , 'f', null, "Mammals");

		staffRepository.save(staff);
		animal.setResponsiblePerson(staff);
		animalRepository.save(animal);
		assertEquals(staffRepository.count(), 1L);
		assertEquals(animalRepository.count(), 1L);
		
		Set<Animal> animals = animalRepository.findBySpiecesIgnoreCase("LioN");
		Animal animalFromDB = animals.iterator().next();
		
		System.out.println(animalFromDB);
		
//		String staffName = animalFromDB.getResponsiblePerson().getSurname();
//	
//		assertEquals("Check if relations between etities established", staffName,
//			"Abranow");
	}
	
//	@Test
//	public void findBySpiecesIgnoreCaseTest(){
//		assertThat(animalRepository.findBySpiecesIgnoreCase("liON").size() == 2);
//	}
//	@Test
//	public void findOneTest(){
//		assertThat(animalRepository.findOne(2L).isPresent() == true);
//		assertThat(animalRepository.findOne(1L).get().getName().equals(TestResources.ANIMALS[0].getName()));
//		assertThat(animalRepository.findOne(2L).get().getName().equals(TestResources.ANIMALS[1].getName()));
//	}
	
//	@Test
//	public void findAllTest(){
//		Set<Animal> animals = new TreeSet<>(Comparator.comparing(Animal::getId));
//		animals = animalRepository.findAll();
//		assertThat(animals.size() == 4);
//		
//		Animal animal = animals.iterator().next();
//		assertThat(animal.getUuid().equals(TestResources.ANIMALS[0].getUuid()));
//				
////		assertThat(animal.getName().equals(TestResources.ANIMALS[0].getName()) &&
////				animal.getBirthDate().equals(TestResources.ANIMALS[0].getBirthDate()));
////		assertThat(!animal.getName().equals(TestResources.ANIMALS[1].getName()) &&
////				!animal.getBirthDate().equals(TestResources.ANIMALS[1].getBirthDate()));	
//	}
//	@Test
//	public void findByResponsiblePersonTest(){
//		Staff staff = staffRepository.findOne(2L).get(); //STAFF[2]
//		Set<Animal> animals = new TreeSet<>(Comparator.comparing(Animal::getId));
//		
//		animals = animalRepository.findByResponsiblePerson(staff);
//		assertThat(animals.size() == 2);
//		
//		Animal animal = animals.iterator().next();
//		assertThat(animal.getName().equals(TestResources.ANIMALS[1].getName()) &&
//				animal.getBirthDate().equals(TestResources.ANIMALS[1].getBirthDate()));	
//		
//	}

}
