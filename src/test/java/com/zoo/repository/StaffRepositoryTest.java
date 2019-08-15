package com.zoo.repository;

import static org.junit.Assert.assertEquals;

import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import com.zoo.domain.Division;
import com.zoo.domain.Staff;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class StaffRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	StaffRepository staffRepository;

	@Autowired
	DivisionRepository divisionRepository;
	
	@BeforeTransaction
	public void setupData() throws Exception {
		
		executeSqlScript("classpath:test/schema.sql", false);
		executeSqlScript("classpath:test/data.sql", false);
	}
	
	@Test
	public void staffRelationshipTest(){
		assertEquals(staffRepository.count(), 3L);
		
		Set<Division> divisions = divisionRepository.findByNameIgnoreCase("REPTILES");
		Division divisionFromDB = divisions.iterator().next();
		
		Set<Staff> staffSet = staffRepository.findBySpecializationIgnoreCase("rEPTILEs");
		assertEquals(staffSet.size(), 1);
		Staff staffFromDB = staffSet.iterator().next();
		
		String divisionName = staffFromDB.getDivision().getName();
		String staffName = divisionFromDB.getStaffSet().iterator().next().getName();
		
		assertEquals(staffName,"Cezar");
		assertEquals(divisionName, "REPTILES");
	}

	 @Test
	 public void findBySupervisorTest() {
		 Staff boss = staffRepository.findById(3L).get();
		 Set<Staff> inferiors = staffRepository.findBySupervisor(boss);
		 assertEquals(inferiors.size(), 1);
		 Staff inferior = inferiors.iterator().next();
		 assertEquals(inferior.getSupervisor(), boss); //by UUID
	 }
	 
	 @Test
		public void getOnePositive(){
			Staff one = staffRepository.findById(1L)
					.orElseThrow(() -> new DataAccessResourceFailureException("id 1L not found"));
			assertEquals(one.getName(), "Al");
			assertEquals(one.getSurname(), "Abranow");
		}
		
		@Test(expected = DataAccessResourceFailureException.class)
		public void getOneNegative(){
			Staff one = staffRepository.findById(10L) //id does not exist
					.orElseThrow(() -> new DataAccessResourceFailureException("id 10L not found"));
		}
}
