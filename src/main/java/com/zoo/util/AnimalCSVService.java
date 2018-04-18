package com.zoo.util;

import com.google.common.util.concurrent.Futures;
import com.zoo.domain.Animal;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AnimalCSVService {


    final int CHUNK_SIZE = 20;
    @Async
    public CompletableFuture<List<Animal>> getAnimalsChunk(){
            return CompletableFuture.completedFuture(generateAnimalChunk());
    }

    private List<Animal> generateAnimalChunk(){
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            return Collections.emptyList();
        }
        return SampleDataGenerator.getAnimals(CHUNK_SIZE);
    }
}
