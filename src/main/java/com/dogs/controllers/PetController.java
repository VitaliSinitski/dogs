package com.dogs.controllers;

import com.dogs.dto.PetDto;
import com.dogs.services.PetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
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

