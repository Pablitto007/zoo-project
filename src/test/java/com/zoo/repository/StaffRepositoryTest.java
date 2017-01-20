package com.zoo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.zoo.domain.Animal;
import com.zoo.domain.Division;
import com.zoo.domain.Staff;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql({ "classpath:test/schema.sql", "classpath:test/data.sql" })
@Transactional
public class StaffRepositoryTest {

	// @ClassRule
	// public static final TestResources res = new TestResources();

	@Autowired
	StaffRepository staffRepository;
	
	@Autowired
	DivisionRepository divisionRepository;

//	@Test
//	public void findBySupervisorTest() {
//		Division division = new Division("ADMINISTRATION");
//		Staff newStaff = new Staff("Dominik", "De Vitto", 'M', null, null, division);
//		Set<Staff> staff = staffRepository.findBySupervisor(newStaff);
//		// List<Staff> staff = staffRepository.findAll();
//		// assertThat(staff.get(0).getName().equals(TestResources.STAFF[4].getName()));
//		assertThat(staff.size() == 1);
//	}
	@Test
	public void divisionSaveTest(){
		long before = divisionRepository.count();
		Division division = new Division("ADMINISTRATION");
		divisionRepository.save(division);
		long after = divisionRepository.count();
		assertThat(before == after-1);
	}
	

}
