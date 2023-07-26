package com.dogs.mappers;

import com.dogs.dto.OwnerDto;
import com.dogs.entities.Owner;
import lombok.RequiredArgsConstructor;

//@Component
@RequiredArgsConstructor
public class OwnerMapper implements Mapper<Owner, OwnerDto> {
//    private final PetMapper petMapper;
    @Override
    public OwnerDto mapToDto(Owner entity) {
        return OwnerDto.builder()
                .id(entity.getId())
                .name(entity.getName())
//                .pets(petMapper.mapSetToDto(entity.getPets()))
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
