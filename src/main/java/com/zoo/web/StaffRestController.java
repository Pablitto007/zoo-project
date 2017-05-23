package com.zoo.web;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zoo.domain.Staff;
import com.zoo.repository.StaffRepository;

@RestController
@RequestMapping("/rest/staff")
public class StaffRestController {
	
	private StaffRepository staffRepository; 
	private final int PAGE_SIZE = 5;
	
	@Autowired
	public StaffRestController(StaffRepository staffRepository) {
		this.staffRepository = staffRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Set<Staff> getAll(){
		Set<Staff> staff = staffRepository.findAll();
		return staff;
	}
	
	@RequestMapping(value = "/page/{pageNumber}",  method = RequestMethod.GET)
	public Page<Staff> getPage(@PathVariable String pageNumber){
		PageRequest request = new PageRequest((Integer.parseInt(pageNumber) -1), PAGE_SIZE, 
				Sort.Direction.ASC, "surname", "name");
		Page<Staff> page = staffRepository.findAll(request);
		return page;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Staff getOne(@PathVariable String id){
		Staff staff = staffRepository.findOne(Long.parseLong(id))
				.orElseThrow(()-> new DataAccessResourceFailureException("Can not find staff id: " +id));
		return staff;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void update(@RequestBody Staff toUpdate){
		staffRepository.save(toUpdate);	
	}
}
