package com.dogs.controllers;

import com.dogs.dto.PetDto;
import com.dogs.dto.OwnerDto;
import com.dogs.services.BreedService;
import com.dogs.services.OwnerService;
import com.dogs.services.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;
    private final PetService petService;

    @GetMapping
    public List<OwnerDto> getAllOwners() {
        return ownerService.findAll();
    }

    @GetMapping("/{id}")
    public OwnerDto getOwnerById(@PathVariable Long id) {
        return ownerService.findById(id);
    }

    @GetMapping("/lazy/{id}")
    public ResponseEntity<String> simulateLazyInitException(@PathVariable Long id) {
        OwnerDto owner = ownerService.findById(id);
        // will throw LazyInitException.
        petService.findPetsByOwner(owner);

        return ResponseEntity.ok("Исключение LazyInitException было воспроизведено.");
    }
}

