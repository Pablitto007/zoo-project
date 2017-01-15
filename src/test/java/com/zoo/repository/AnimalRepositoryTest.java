package com.zoo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.zoo.domain.Animal;


@RunWith(SpringRunner.class)
@DataJpaTest
@Sql({"classpath:test/schema.sql", "classpath:test/data.sql"})
//@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
public class AnimalRepositoryTest {
	
	
	@ClassRule
    public static final TestResources res = new TestResources();
	
	@Autowired
	AnimalRepository animalRepository;

	@Test
	public void countTest(){
		assertThat(animalRepository.count() ==4);
	}
	@Test
	public void findBySpiecesIgnoreCaseTest(){
		assertThat(animalRepository.findBySpiecesIgnoreCase("liON").size() == 2);
	}
	@Test
	public void findOneTest(){
		assertThat(animalRepository.findOne(2L).isPresent() == true);
		assertThat(animalRepository.findOne(1L).get().getName().equals(TestResources.ANIMALS[0].getName()));
		assertThat(animalRepository.findOne(2L).get().getName().equals(TestResources.ANIMALS[1].getName()));
	}
	@Test
	public void findAllTest(){
		List<Animal> animals = animalRepository.findAll();
		animals.sort(Comparator.comparing(Animal::getId));
		assertThat(animals.get(0).getName().equals(TestResources.ANIMALS[0].getName()) &&
				animals.get(0).getBirthDate().equals(TestResources.ANIMALS[0].getBirthDate()));
//		EmbeddedDatabaseConnection con = null;
//		assertThat(con.getType().toString().equals("H2"));
	}

}
