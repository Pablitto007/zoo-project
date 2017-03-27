package com.zoo.repository;

import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean //ensures that Spring Data JPA doesn’t try to create an implementation for base repository interface
public interface BaseRepository <T, ID extends Serializable>  extends Repository<T, ID>{
	
	void delete(T toDelete);
	 
	Set<T> findAll();
     
    Optional<T> findOne(ID id);
 
    T save(T toPersist);
    
    long count();
    
    Set<T> findByNameIgnoreCase(String name);

}
