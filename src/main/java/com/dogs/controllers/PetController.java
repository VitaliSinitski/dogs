package com.dogs.controllers;

import com.dogs.dto.PetDto;
import com.dogs.dto.TagDto;
import com.dogs.entities.Pet;
import com.dogs.entities.Tag;
import com.dogs.repositories.PetRepository;
import com.dogs.services.PetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/pets")
public class PetController {
    private final PetService petService;
    private final PetRepository petRepository;

    @GetMapping
    public List<PetDto> getAllPets() {
        return petService.findAll();
    }

    @GetMapping("/{id}")
    public PetDto getPetById(@PathVariable Long id) {
        return petService.findById(id);
    }

    @GetMapping("/lazy/{id}")
    public Set<TagDto> getLazyTagsByPetId(@PathVariable Long id) {
        PetDto pet = petService.findById(id);
        System.out.println("pet: " + pet);
        Set<TagDto> tags = pet.getTags();
        System.out.println("tag: " + tags);
        System.out.println(tags.size());
        return tags;
    }

//    @GetMapping("/lazy/{id}")
//    public Set<Tag> getLazyTagsByPetId(@PathVariable Long id) {
//        Pet pet = petRepository.findById(id).orElse(null);
//        System.out.println("pet: " + pet);
//        Set<Tag> tags = pet.getTags();
//
//        System.out.println("tag: " + tags);
//        System.out.println(tags.size());
//        return tags;
//    }
}

