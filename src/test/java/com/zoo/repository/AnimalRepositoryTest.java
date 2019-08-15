package com.zoo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import com.zoo.domain.Animal;
import com.zoo.domain.Staff;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class AnimalRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	AnimalRepository animalRepository;

	@Autowired
	StaffRepository staffRepository;

	@BeforeTransaction
	public void setupData() throws Exception {
		
		executeSqlScript("classpath:test/schema.sql", false);
		executeSqlScript("classpath:test/data.sql", false);
	}

	@Test
	public void animalsRelationshipTest() {
		assertEquals(animalRepository.count(), 3L); // all animals

		Set<Animal> lions = animalRepository.findBySpiecesIgnoreCase("LioN");
		assertEquals(lions.size(), 2);

		Set<Animal> shouldBeEmpty = animalRepository.findBySpiecesIgnoreCase("xxxx");
		assertEquals(shouldBeEmpty.size(), 0);

		Animal lionOne = lions.iterator().next();

		Set<Staff> mammalsStaff = staffRepository.findBySpecializationIgnoreCase("MaMMals");
		assertEquals(mammalsStaff.size(), 1);
		Staff staffFromDB = mammalsStaff.iterator().next();

		List<Animal> staffsAnimals = staffFromDB.getAnimals().stream().sorted(Comparator.comparing(Animal::getName))
				.collect(Collectors.toList());// {Anatol, Opera}
		String animalName = staffsAnimals.iterator().next().getName();
		String staffSurname = lionOne.getResponsiblePerson().getSurname();

		// Check if relations between entities have been established by JPA
		assertEquals(staffSurname, "Abranow");
		assertEquals(animalName, "Anatol");
	}
	
	@Test
	public void getOnePositive(){
		Animal one = animalRepository.findById(1L)
				.orElseThrow(() -> new DataAccessResourceFailureException("id 1L not found"));
		assertEquals(one.getName(), "Anatol");
		assertEquals(one.getBirthDate(), LocalDate.of(2013, Month.JANUARY, 1));
	}
	

	@Test(expected = DataAccessResourceFailureException.class)
	public void getOneNegative(){
		Animal one = animalRepository.findById(10L)
				.orElseThrow(() -> new DataAccessResourceFailureException("id 10L not found"));
	}

	@Test
	public void findByResponsiblePersonTest() {
		Staff staff = staffRepository.findByNameIgnoreCase("Cezar")
				.iterator().next(); // Cezar
																					
		Set<Animal> animals = animalRepository.findByResponsiblePerson(staff);
		assertTrue(animals.size() == 1);
		Animal animalFromDB = animals.iterator().next();
		assertEquals(animalFromDB.getBirthDate(), LocalDate.of(2010, Month.JANUARY, 1));
		assertEquals(animalFromDB.getName(), "Donald");
	}

	@Test
	public void deleteTets() {
		assertEquals(staffRepository.count(), 3L); 
		Staff staffToDelete = staffRepository.findByNameIgnoreCase("AL").iterator().next();

		staffToDelete.onDelete(); // deletes all covered animals
		staffRepository.delete(staffToDelete);
		assertEquals(staffRepository.count(), 2L); 

		Set<Animal> lions = animalRepository.findBySpiecesIgnoreCase("LioN");
		Animal lionOne = lions.iterator().next();
		assertTrue(lionOne.getResponsiblePerson() == null); //responsible person has been deleted
	}

	@Test
	public void findByBirthYearTest() {
		Set<Animal> bornIn2008 = animalRepository.findByBirthYear("2008");
		assertEquals(bornIn2008.size(), 1);
		assertEquals(bornIn2008.iterator().next().getName(), "Opera");
	}
}
