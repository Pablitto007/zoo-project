package com.zoo.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zoo.domain.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    /**
     * To avoid n+1 SELECT problem
     */
    @Override
    @Query(
            value = "SELECT s FROM Staff s LEFT JOIN FETCH s.animals")
    List<Staff> findAll();

    Set<Staff> findBySupervisor(Staff supervisor);

    Set<Staff> findBySpecializationIgnoreCase(String specialization);

    Set<Staff> findByNameIgnoreCase(String name);

}
