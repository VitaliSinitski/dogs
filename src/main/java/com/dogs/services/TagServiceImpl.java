package com.dogs.services;

import com.dogs.dao.TagDao;
import com.dogs.dao.TagDaoImpl;
import com.dogs.dto.TagDto;
import com.dogs.mappers.TagMapper;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class TagServiceImpl implements TagService {
    private static final TagServiceImpl INSTANCE = new TagServiceImpl();
    private final TagMapper tagMapper = TagMapper.getInstance();
    private final TagDao tagDao = TagDaoImpl.getInstance();

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

    public static TagServiceImpl getInstance() {
        return INSTANCE;
    }
}
