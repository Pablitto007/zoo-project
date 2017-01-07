package com.zoo.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean //ensures that Spring Data JPA doesn’t try to create an implementation for our base repository interface
public interface BaseRepository <T, ID extends Serializable>  extends Repository<T, ID>{
	
	void delete(T deleted);
	 
    List<T> findAll();
     
    Optional<T> findOne(ID id);
 
    T save(T persisted);

}
