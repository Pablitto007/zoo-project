package com.zoo.repository;

import com.zoo.domain.Staff;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface StaffRepository extends BaseRepository<Staff, Long> {
	
	/**
	 * To avoid n+1 SELECT problem 
	 */
	@Override
	@Query(
			value ="SELECT s FROM Staff s LEFT JOIN FETCH s.animals")
	Set<Staff> findAll();
	
	Set<Staff> findBySupervisor(Staff supervisor);
	
	Set<Staff> findBySpecializationIgnoreCase(String specialization);

}
