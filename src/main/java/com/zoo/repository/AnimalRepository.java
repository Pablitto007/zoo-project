package com.zoo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zoo.domain.Animal;
import com.zoo.domain.Staff;

public interface AnimalRepository extends BaseRepository<Animal, Long> {

	public Set<Animal> findBySpiecesIgnoreCase(String spiece);

	public Set<Animal> findByResponsiblePerson(Staff responsiblePerson);

	@Query(
			value ="SELECT * FROM Animals  WHERE EXTRACT(year from BIRTH_DATE) = :birthYear"
			, nativeQuery=true
			)
	public Set<Animal> findByBirthYear(@Param("birthYear") String birthYear);
}