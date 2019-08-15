package com.zoo.web;

import com.zoo.domain.Animal;
import com.zoo.domain.Staff;
import com.zoo.repository.AnimalRepository;
import com.zoo.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rest/animals")
public class AnimalRestController {

    private AnimalRepository aniamalRepository;
    private StaffRepository staffRepository;
    private final int PAGE_SIZE = 5;

    public AnimalRestController(AnimalRepository aniamalRepository, StaffRepository staffRepository) {
        this.aniamalRepository = aniamalRepository;
        this.staffRepository = staffRepository;
    }

    @GetMapping
    public List<Animal> getAllAnimals() {
        List<Animal> animals = aniamalRepository.findAll();
        return animals;
    }

    @GetMapping("/page/{pageNumber}")
    public Page<Animal> getPage(@PathVariable String pageNumber) {
        PageRequest request =
                PageRequest.of((Integer.parseInt(pageNumber) - 1), PAGE_SIZE, Sort.Direction.ASC, "name");
        Page<Animal> page = aniamalRepository.findAll(request);
        return page;
    }

    @PostMapping("/{staffId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addAnimal(@RequestBody Animal animal, @PathVariable Long staffId) {

        Staff responsiblePerson = staffRepository.findById(staffId)
                .orElseThrow(() -> new DataAccessResourceFailureException("Can not find staff id: " + staffId));

        animal.setResponsiblePerson(responsiblePerson);
        return aniamalRepository.save(animal).getId();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getOne(@PathVariable Long id) {
        return aniamalRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Animal animal = aniamalRepository.findById(id)
                .orElseThrow(() -> new DataAccessResourceFailureException("Can not find animal id: " + id));
        aniamalRepository.delete(animal);
    }

}
