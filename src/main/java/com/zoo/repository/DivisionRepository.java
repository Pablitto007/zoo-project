package com.zoo.repository;

import org.springframework.transaction.annotation.Transactional;

import com.zoo.domain.Division;

@Transactional
public interface DivisionRepository extends BaseRepository<Division, Long> {

}
