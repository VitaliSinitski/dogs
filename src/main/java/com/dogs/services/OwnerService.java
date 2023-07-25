package com.dogs.services;

import com.dogs.dto.OwnerDto;
import com.dogs.mappers.OwnerMapper;
import com.dogs.repositories.OwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OwnerService {
    private final OwnerMapper ownerMapper;
    private final OwnerRepository ownerRepository;

    public List<OwnerDto> findAll() {
        return ownerRepository.findAll()
                .stream()
                .map(ownerMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public OwnerDto findById(Long id) {
        return ownerRepository.findById(id)
                .map(ownerMapper::mapToDto)
                .orElseThrow(() -> new EntityNotFoundException("Owner with id: " + id + " not found"));
    }
}
