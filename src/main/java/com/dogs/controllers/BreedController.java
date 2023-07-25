package com.dogs.controllers;

import com.dogs.dto.BreedDto;
import com.dogs.entities.Breed;
import com.dogs.repositories.BreedRepository;
import com.dogs.services.BreedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/breeds")
public class BreedController {
    private final BreedService breedService;

    @GetMapping
    public List<BreedDto> getAllBreeds() {
        return breedService.findAll();
    }

    @GetMapping("/{id}")
    public BreedDto getBreedById(@PathVariable Long id) {
        return breedService.findById(id);
    }
}

