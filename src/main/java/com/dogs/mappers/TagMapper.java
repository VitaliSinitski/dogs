package com.dogs.mappers;

import com.dogs.dto.TagDto;
import com.dogs.entities.Tag;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class TagMapper implements Mapper<Tag, TagDto> {
    private static final TagMapper INSTANCE = new TagMapper();
    @Override
    public TagDto mapToDto(Tag entity) {
        return TagDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public Tag mapToEntity(TagDto dto) {
        return Tag.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    public Set<TagDto> mapSetToDto(Set<Tag> entitySet) {
        if (entitySet == null || entitySet.isEmpty()) {
            return Collections.emptySet();
        }
        return entitySet
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toSet());
    }

    public static TagMapper getInstance() {
        return INSTANCE;
    }
}
