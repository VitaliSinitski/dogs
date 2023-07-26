package com.dogs.services;

import com.dogs.dto.TagDto;

import java.util.List;

public interface TagService {
    List<TagDto> findAll();
    TagDto findById(Long id);
}
