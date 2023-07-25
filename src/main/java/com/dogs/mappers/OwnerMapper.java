package com.dogs.mappers;

import com.dogs.dto.OwnerDto;
import com.dogs.entities.Owner;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper implements Mapper<Owner, OwnerDto> {
    @Override
    public OwnerDto mapToDto(Owner entity) {
        return OwnerDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public Owner mapToEntity(OwnerDto dto) {
        return Owner.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
