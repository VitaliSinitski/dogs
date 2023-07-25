package com.dogs.services;

import com.dogs.dto.BreedDto;
import com.dogs.entities.Pet;
import com.dogs.mappers.BreedMapper;
import com.dogs.repositories.BreedRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BreedService {
    private final BreedRepository breedRepository;
    private final BreedMapper breedMapper;

    public List<BreedDto> findAll() {
        return breedRepository.findAll()
                .stream()
                .map(breedMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public BreedDto findById(Long id) {
        return breedRepository.findById(id)
                .map(breedMapper::mapToDto)
                .orElseThrow(() -> new EntityNotFoundException("Breed with id: " + id + " not found"));
    }
}
