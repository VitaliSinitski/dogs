package com.dogs.controllers;

import com.dogs.dto.BreedDto;
import com.dogs.dto.OwnerDto;
import com.dogs.services.BreedService;
import com.dogs.services.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping
    public List<OwnerDto> getAllOwners() {
        return ownerService.findAll();
    }

    @GetMapping("/{id}")
    public OwnerDto getOwnerById(@PathVariable Long id) {
        return ownerService.findById(id);
    }
}

