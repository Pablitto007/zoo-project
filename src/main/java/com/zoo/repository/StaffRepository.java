package com.zoo.repository;

import java.util.Set;

import com.zoo.domain.Staff;

public interface StaffRepository extends BaseRepository<Staff, Long> {
	
	Set<Staff> findBySupervisor(Staff supervisor);

}
