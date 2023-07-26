package com.dogs.services;

import com.dogs.dao.TagDao;
import com.dogs.dto.TagDto;
import com.dogs.mappers.TagMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagMapper tagMapper;
    private final TagDao tagDao;

    @Override
    public List<TagDto> findAll() {
        return tagDao.findAll()
                .stream()
                .map(tagMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TagDto findById(Long id) {
        return tagDao.findById(id)
                .map(tagMapper::mapToDto)
                .orElseThrow(() -> new EntityNotFoundException("Pet with id: " + id + " not found"));
    }
}
