package com.zoo.repository;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.zoo.domain.Animal;
import com.zoo.domain.Staff;


@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class AnimalRepositoryTest {
	
	@Autowired
	AnimalRepository animalRepository;
	
	@Autowired
	StaffRepository staffRepository;
	
	@Autowired
	DivisionRepository divisionRepository;
	
//	 @ClassRule
//		 public static final TestResources res = new TestResources();

	@Test
	public void animalsRelationshipTest(){
		assertEquals(animalRepository.count(), 3L);
		
		Set<Animal> animals = animalRepository.findBySpiecesIgnoreCase("LioN");
		assertEquals(animals.size(), 2);
		Set<Animal> empty = animalRepository.findBySpiecesIgnoreCase("xxxx");
		assertEquals(empty.size(),  0);
		Animal animalFromDB = animals.iterator().next();
		
		Set<Staff> staffSet = staffRepository.findBySpecializationIgnoreCase("MaMMals");
		assertEquals(staffSet.size(), 1);
		Staff staffFromDB = staffSet.iterator().next(); 
		
		List<Animal> staffsAnimals = staffFromDB.getAnimals().stream()
				.sorted(Comparator.comparing(Animal::getName)).collect(Collectors.toList());//{Anatol, Opera}
		String animalName = staffsAnimals.iterator().next().getName();
		String staffSurname = animalFromDB.getResponsiblePerson().getSurname();
	
		//Check if relations between entities have been established by JPA
		assertEquals(staffSurname,"Abranow");
		assertEquals(animalName, "Anatol");
		
		staffFromDB.onDelete(); //deletes all covered animals
		staffRepository.delete(staffFromDB);
		assertEquals(staffRepository.count(), 1L);
		
		Set<Animal> animalsAfter = animalRepository.findBySpiecesIgnoreCase("LioN");
		Animal animalFromDBAfter = animalsAfter.iterator().next();
		assertTrue( animalFromDBAfter.getResponsiblePerson() == null);
	}
	
	@Test
	public void findByResponsiblePersonTest(){
		Staff staff = staffRepository.findOne(2L).get(); //2		
		Set<Animal> animals = animalRepository.findByResponsiblePerson(staff);
		assertTrue(animals.size() == 1);
		Animal animalFromDB = animals.iterator().next();
		assertEquals(animalFromDB.getBirthDate(), LocalDate.of(2010, Month.JANUARY, 1));
	
	}
	@Before
		public void populateDB(){
		//1
//		Division mammals =new Division("MAMMALS");
		Staff Al = new Staff("Al" , "Abranow" , 'f', null, "Mammals");
		Animal Anatol = new Animal("Anatol" , "lion", 'M', 
				LocalDate.of(2013, Month.JANUARY, 1), LocalDate.of(2015, Month.JANUARY, 1));
		Animal Opera = new Animal("Opera" , "lion", 'F', 
				LocalDate.of(2008, Month.NOVEMBER, 11), null);
//		divisionRepository.save(mammals);
//		Al.setDivision(mammals);
		Al.addAnimal(Anatol);
		Al.addAnimal(Opera);
		staffRepository.save(Al);
		
		//2
//		Division reptiles = new Division("REPTILES");
		Animal turtle = new Animal("Donald" , "turtle", 'M', 
			LocalDate.of(2010, Month.JANUARY, 1), LocalDate.of(2010, Month.JANUARY, 1));
		Staff Cezar = new Staff("Cezar" , "Cejrus" , 'M', null, "Reptiles");
		

//		divisionRepository.save(reptiles);
//		reptiles.addStaff(Cezar);
//		Cezar.setDivision(reptiles);
		Cezar.addAnimal(turtle);
		staffRepository.save(Cezar);

	}

}
