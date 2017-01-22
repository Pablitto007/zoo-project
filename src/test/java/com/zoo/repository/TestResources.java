//package com.zoo.repository;
//
//import java.time.LocalDate;
//import java.time.Month;
//
//import org.junit.rules.ExternalResource;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.zoo.domain.Animal;
//import com.zoo.domain.Division;
//import com.zoo.domain.Staff;
//
//public class TestResources extends ExternalResource {
//	
//	@Autowired
//	AnimalRepository animalRepository;
//	
//	@Autowired
//	StaffRepository staffRepository;
//	
//	@Autowired
//	DivisionRepository divisionRepository;
//	
////	static final Animal [] ANIMALS = new Animal[4];
////	static final Staff [] STAFF = new Staff[5];
////	static final Division [] DIVISIONS = new Division[6];
//	
//	@Override
//	protected void before() {
//		
////		DIVISIONS[0] = new Division("MAMMALS");
////		DIVISIONS[1] = new Division("BIRDS");
////		DIVISIONS[2] = new Division("REPTILES");
////		DIVISIONS[3] = new Division("ADMINISTRATION");
////		DIVISIONS[4] = new Division("SECURITY");
////		DIVISIONS[5] = new Division("CLEANING");
////		
////		STAFF[0] = new Staff("Al" , "Abranow" , 'f', null, "Mammals");
////		STAFF[1] = new Staff("Betty" , "Brunowska" , 'f', null, "Birds");
////		STAFF[2] = new Staff("Cezar" , "Cejrus" , 'M', null, "Reptiles");
////		STAFF[3] = new Staff("Dominik" , "De Vitto" , 'M', null, null);
////		STAFF[4] = new Staff("Eve" , "Evans" , 'F', STAFF[3], null);
//		
////		ANIMALS[0] = new Animal("Anatol" , "lion", 'M', 
////				LocalDate.of(2013, Month.JANUARY, 1), LocalDate.of(2015, Month.JANUARY, 1), STAFF[0]);
////		ANIMALS[1] = new Animal("Snaky" , "Cobra", 'M', 
////				LocalDate.of(2003, Month.OCTOBER, 10), null, STAFF[2]);
////		ANIMALS[2] = new Animal("Opera" , "lion", 'F', 
////				LocalDate.of(2008, Month.NOVEMBER, 11), null, STAFF[0]);
////		ANIMALS[3] = new Animal("Donald" , "turtle", 'M', 
////				LocalDate.of(2010, Month.JANUARY, 1), LocalDate.of(2010, Month.JANUARY, 1), STAFF[2]);
//		Division mammals =new Division("MAMMALS");
//		Staff Al = new Staff("Al" , "Abranow" , 'f', null, "Mammals");
//		Animal Anatol = new Animal("Anatol" , "lion", 'M', 
//				LocalDate.of(2013, Month.JANUARY, 1), LocalDate.of(2015, Month.JANUARY, 1));
//		Animal Opera = new Animal("Opera" , "lion", 'F', 
//				LocalDate.of(2008, Month.NOVEMBER, 11), null);
//		
//		
//		divisionRepository.save(mammals);
//		Al.setDivision(mammals);
//		Al.addAnimal(Anatol);
//		Al.addAnimal(Opera);
//		staffRepository.save(Al);
//		
//		//2
////		Division reptiles = new Division("REPTILES");
//		Animal turtle = new Animal("Donald" , "turtle", 'M', 
//			LocalDate.of(2010, Month.JANUARY, 1), LocalDate.of(2010, Month.JANUARY, 1));
//		Staff Cezar = new Staff("Cezar" , "Cejrus" , 'M', null, "Reptiles");
//		
//
////		divisionRepository.save(reptiles);
////		reptiles.addStaff(Cezar);
//		//Cezar.setDivision(reptiles);
//		Cezar.addAnimal(turtle);
//		staffRepository.save(Cezar);
////		turtle.setResponsiblePerson(Cezar);
////		animalRepository.save(turtle);	
//    }
//
//}
