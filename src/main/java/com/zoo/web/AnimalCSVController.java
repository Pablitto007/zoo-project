package com.zoo.web;

import com.zoo.domain.Animal;
import com.zoo.util.AnimalCSVService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("csv/animals")
public class AnimalCSVController {

    private AnimalCSVService animalCSVService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AnimalCSVController.class);

    @Autowired
    public AnimalCSVController(AnimalCSVService animalCSVService) {
        this.animalCSVService = animalCSVService;
    }

    @GetMapping("/all")
    public CompletableFuture<Void> getAnimals(HttpServletResponse response) throws ExecutionException, InterruptedException {
        LOGGER.info("Received new csv request");
        //response.setHeader("Transfer-Encoding", "chunked");

        return animalCSVService.getAnimalsAsync(response);
    }
}