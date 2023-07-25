package com.dogs.mappers;

import com.dogs.dto.TagDto;
import com.dogs.entities.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagMapper implements Mapper<Tag, TagDto> {
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
}
