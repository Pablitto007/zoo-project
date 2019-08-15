package com.zoo.web;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping
	public List<Division> getAll(){
		List<Division> division = divisionRepository.findAll();
		return division;
	}
	
	@GetMapping("/{id}")
	public Division getOne(@PathVariable String id){
		Division division = divisionRepository.findById(Long.parseLong(id))
				.orElseThrow(()-> new DataAccessResourceFailureException("Can not find division id: " + id)) ;
		return division;
	}
}
