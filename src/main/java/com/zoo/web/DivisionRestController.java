package com.zoo.web;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zoo.domain.Division;
import com.zoo.repository.DivisionRepository;

@RestController
@RequestMapping("/rest/division")
public class DivisionRestController {
	
	private DivisionRepository divisionRepository;
	
	@Autowired
	public DivisionRestController(DivisionRepository divisionRepository) {
		this.divisionRepository = divisionRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Set<Division> getAll(){
		Set<Division> division = divisionRepository.findAll();
		return division;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Division getOne(@PathVariable String id){
		Division division = divisionRepository.findOne(Long.parseLong(id))
				.orElseThrow(()-> new DataAccessResourceFailureException("Can not find division id: " + id)) ;
		return division;
	}
}
