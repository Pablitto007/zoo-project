package com.zoo.web;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.zoo.domain.Animal;
import com.zoo.repository.AnimalRepository;
import com.zoo.repository.StaffRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(AnimalRestController.class)
public class AnimalRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AnimalRepository animalRepositoryMock;

    @MockBean
    StaffRepository staffRepositoryMock;

    @Test
    public void findOneTest_Success() throws Exception {
        Animal animal = new Animal("Bruno", "lion", 'M', LocalDate.of(2010, Month.APRIL, 20), null);
        Optional<Animal> optional = Optional.of(animal);
        when(animalRepositoryMock.findById(1L)).thenReturn(optional);

        mockMvc.perform(get("/rest/animals/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name", is("Bruno")))
                .andExpect(jsonPath("$.spieces", is("lion")));

        verify(animalRepositoryMock, times(1)).findById(1L);
        verifyNoMoreInteractions(animalRepositoryMock);
    }

    @Test
    public void findOneTest_NotFound() throws Exception {
        when(animalRepositoryMock.findById(1L)).thenThrow(new DataAccessResourceFailureException(""));

        mockMvc.perform(get("/rest/animals/{id}", 1L))
                .andExpect(status().isNotFound());

        verify(animalRepositoryMock, times(1)).findById(1L);
        verifyNoMoreInteractions(animalRepositoryMock);
    }

}
