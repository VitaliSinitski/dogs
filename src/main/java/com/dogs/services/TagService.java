package com.dogs.services;

import com.dogs.dao.TagDao;
import com.dogs.dao.TagDaoImpl;
import com.dogs.dto.TagDto;
import com.dogs.mappers.TagMapper;
import com.dogs.repositories.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TagService {
    private final TagMapper tagMapper;
    private final TagDao tagDao;

    public List<TagDto> findAll() {
        return tagDao.findAll()
                .stream()
                .map(tagMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public TagDto findById(Long id) {
        return tagDao.findById(id)
                .map(tagMapper::mapToDto)
                .orElseThrow(() -> new EntityNotFoundException("Pet with id: " + id + " not found"));
    }
}
