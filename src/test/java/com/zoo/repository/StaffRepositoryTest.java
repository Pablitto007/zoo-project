package com.zoo.repository;

import static org.junit.Assert.assertEquals;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.zoo.domain.Division;
import com.zoo.domain.Staff;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class StaffRepositoryTest {

	// @ClassRule
	// public static final TestResources res = new TestResources();

	@Autowired
	StaffRepository staffRepository;

	@Autowired
	DivisionRepository divisionRepository;
	
	@Test
	public void staffRelationshipTest(){
		assertEquals(staffRepository.count(), 3L);
		
		Set<Division> divisions = divisionRepository.findByName("REPTILES");
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
		 Staff boss = staffRepository.findOne(3L).get();
		 Set<Staff> inferiors = staffRepository.findBySupervisor(boss);
		 assertEquals(inferiors.size(), 1);
		 Staff inferior = inferiors.iterator().next();
		 assertEquals(inferior.getSupervisor(), boss); //by UUID
	 }

	@Before
	public void populateDB() {
		// 1
		Division mammals = new Division("MAMMALS");
		Staff Al = new Staff("Al", "Abranow", 'f', null, "Mammals");
		mammals.addStaff(Al);
		divisionRepository.save(mammals);
		// 2
		Division reptiles = new Division("REPTILES");
		Staff Cezar = new Staff("Cezar", "Cejrus", 'M', null, "Reptiles");
		reptiles.addStaff(Cezar);
		divisionRepository.save(reptiles);
		//3
		Staff newStaff = new Staff("Dominik", "De Vitto", 'M', null, null);
		newStaff.addInferior(Cezar);
		staffRepository.save(newStaff);
	}
}
