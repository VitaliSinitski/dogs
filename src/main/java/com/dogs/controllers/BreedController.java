package com.dogs.controllers;

import com.dogs.entities.Breed;
import com.dogs.repositories.BreedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/breed")
public class BreedController {
    private final BreedRepository breedRepository;

    @GetMapping
    public List<Breed> getAllBreeds() {
        return breedRepository.findAll();
    }

//    @GetMapping("/{id}")
//    public Pet getPetById(@PathVariable Long id) {
//        return breedRepository.findById(id);
//    }
}

