package com.dogs.controllers;

import com.dogs.dto.PetDto;
import com.dogs.entities.Pet;
import com.dogs.repositories.PetRepository;
import com.dogs.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pets")
public class PetController {
    private final PetService petService;

    @GetMapping
    public List<PetDto> getAllPets() {
        return petService.findAll();
    }

    @GetMapping("/{id}")
    public PetDto getPetById(@PathVariable Long id) {
        return petService.findById(id);
    }
}

