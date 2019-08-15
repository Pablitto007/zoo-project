package com.zoo.web;

import com.zoo.domain.Staff;
import com.zoo.repository.StaffRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/staff")
public class StaffRestController {

    private StaffRepository staffRepository;
    private final int PAGE_SIZE = 5;
    private static final Logger LOGGER = LoggerFactory.getLogger(StaffRestController.class);

    @Autowired
    public StaffRestController(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @GetMapping
    public List<Staff> getAll() {
        return staffRepository.findAll();
    }

    @GetMapping("/page/{pageNumber}")
    public Page<Staff> getPage(@PathVariable String pageNumber) {
        PageRequest request = PageRequest.of((Integer.parseInt(pageNumber) - 1), PAGE_SIZE,
                Sort.Direction.ASC, "surname", "name");
        Page<Staff> page = staffRepository.findAll(request);
        return page;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Staff> getOne(@PathVariable Long id) {
        return staffRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody Staff toUpdate) {
        LOGGER.info("received staff to update {} ", toUpdate.getName());
        Staff staff = staffRepository.save(toUpdate);
        LOGGER.info("updated staff {} ", staff.getName());
    }
}
