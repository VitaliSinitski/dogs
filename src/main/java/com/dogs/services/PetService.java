package com.dogs.services;

import com.dogs.dto.PetDto;
import com.dogs.mappers.PetMapper;
import com.dogs.repositories.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PetService {
    private final PetMapper petMapper;
    private final PetRepository petRepository;

    public List<PetDto> findAll() {
        return petRepository.findAll()
                .stream()
                .map(petMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public PetDto findById(Long id) {
        return petRepository.findById(id)
                .map(petMapper::mapToDto)
                .orElseThrow(() -> new EntityNotFoundException("Pet with id: " + id + " not found"));
    }
}
