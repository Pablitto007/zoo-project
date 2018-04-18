package com.zoo.web;

import com.zoo.domain.Animal;
import com.zoo.util.AnimalCSVService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("csv/animals")
public class AnimalCSVController {

    private AnimalCSVService animalCSVService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AnimalCSVController.class);

    @Autowired
    public AnimalCSVController(AnimalCSVService animalCSVService) {
        this.animalCSVService = animalCSVService;

    }

    @GetMapping("/all")
    public void getAnimals(HttpServletResponse response) throws ExecutionException, InterruptedException {
        //response.setHeader("Transfer-Encoding", "chunked");
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"animals.csv\"");
        List<Animal> list = animalCSVService.getAnimalsChunk().get();
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();

            for (Animal animal : list) {
                outputStream.write(animal.toString().getBytes());
            }
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            LOGGER.debug("IOexception {}", e.getMessage());
        }
    }
}