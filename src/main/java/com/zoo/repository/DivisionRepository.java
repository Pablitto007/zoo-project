package com.zoo.repository;

import com.zoo.domain.Division;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface DivisionRepository extends JpaRepository<Division, Long> {

    Set<Division> findByNameIgnoreCase(String name);

}
