package com.zoo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.zoo.domain.Staff;

@Transactional
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
