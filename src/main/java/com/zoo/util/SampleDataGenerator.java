package com.zoo.util;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.zoo.domain.Animal;
import com.zoo.domain.Staff;

public class SampleDataGenerator {
	
	private static List<String> animalNames = Arrays.asList("Bambi", "Dominik", "Shiny", "Hope", "Opera", "Dino", "Fanta", "Zara", "Dory", "Julian", "Tricky");
	private static List<String> spieces = Arrays.asList("lion", "turtle", "elephant", "fish");
	private static List<Character> gender = Arrays.asList('F', 'M');
	private static List<String> staffNames = Arrays.asList("Al", "Rita", "Bob", "Jimi", "Helen", "Robert", "Zack", "Steve", "Marta");
	private static List<String> staffSurnames = Arrays.asList("Connor", "Levinsky", "Trump", "Morrison", "Zappa", "Anderson", "Derek");
	private static Random rand = new Random();
	
	public static List<Animal> getAnimals(int size){
		return IntStream.range(0, size)
				.mapToObj(e -> new Animal(getAnimalName(), getSpieces(), getGender(), getDate(), LocalDate.now()))
				.collect(Collectors.toList());			
	}
	
	public static List<Staff> getStaff(int size){
		return IntStream.range(0, size)
				.mapToObj(e -> new Staff(getStaffName(), getStaffSutname(), getGender(), null, null))
				.collect(Collectors.toList());
	}
	
	private static String getAnimalName(){
		return animalNames.get(rand.nextInt(animalNames.size()));
	}
	
	private static String getSpieces(){
		return spieces.get(rand.nextInt(spieces.size()));
	}
	private static char getGender(){
		return gender.get(rand.nextInt(gender.size()));
	}
	
	private static String getStaffName(){
		return staffNames.get(rand.nextInt(staffNames.size()));
	}
	
	private static String getStaffSutname(){
		return staffSurnames.get(rand.nextInt(staffSurnames.size()));
	}
	private static LocalDate getDate(){
		int [] years = IntStream.rangeClosed(1995, 2016).toArray();
		int [] months = IntStream.rangeClosed(1, 12).toArray();
		int [] days = IntStream.rangeClosed(1, 28).toArray(); // not 31 just for example
		
		return LocalDate.of(years[rand.nextInt(years.length)], 
				months[rand.nextInt(months.length)], days[rand.nextInt(days.length)]);
	}
}
