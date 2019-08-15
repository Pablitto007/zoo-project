package com.zoo.util;

import com.zoo.domain.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
public class AnimalCSVService {

    private final int CHUNK_SIZE = 20;
    private static final Logger LOGGER = LoggerFactory.getLogger(AnimalCSVService.class);
    private final Executor executor;

    public AnimalCSVService(@Qualifier("asyncExecutor") Executor executor) {
        this.executor = executor;
    }

    public CompletableFuture<Void> getAnimalsAsync(HttpServletResponse response) {

        LOGGER.debug(Thread.currentThread().getName());

        return CompletableFuture.supplyAsync(() -> generateAnimalChunk(), executor)
                .thenAccept(list -> {
                    LOGGER.debug("Animals list size {} ", list.size());
                    response.setContentType("text/csv");
                    response.setHeader("Content-Disposition", "attachment; filename=\"animals.csv\"");
                    try (OutputStream outputStream = response.getOutputStream()) {

                        for (Animal animal : list) {
                            outputStream.write(animal.toString().getBytes());
                        }
                        outputStream.flush();

                    } catch (IOException e) {
                        LOGGER.error("getAnimals() fail with exception {}", e.getMessage());
                    }
                });
    }

    private List<Animal> generateAnimalChunk() {
        try {
            LOGGER.debug(Thread.currentThread().getName());
            LOGGER.debug("Heavy computation started");
            Thread.sleep(3000);
            LOGGER.debug("Heavy computation ended");
        } catch (InterruptedException e) {
            return Collections.emptyList();
        }
        return SampleDataGenerator.getAnimals(CHUNK_SIZE);
    }
}
