package com.dogs.services;

import com.dogs.dto.TagDto;
import com.dogs.mappers.TagMapper;
import com.dogs.repositories.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagService {
    private final TagMapper tagMapper;
    private final TagRepository tagRepository;

    public List<TagDto> findAll() {
        return tagRepository.findAll()
                .stream()
                .map(tagMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public TagDto findById(Long id) {
        return tagRepository.findById(id)
                .map(tagMapper::mapToDto)
                .orElseThrow(() -> new EntityNotFoundException("Pet with id: " + id + " not found"));
    }
}
