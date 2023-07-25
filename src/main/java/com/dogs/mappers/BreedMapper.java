package com.dogs.mappers;

import com.dogs.dto.BreedDto;
import com.dogs.entities.Breed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BreedMapper implements Mapper<Breed, BreedDto> {
    private final PetMapper petMapper;
    @Override
    public BreedDto mapToDto(Breed entity) {
        return BreedDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .pet(Optional.ofNullable(entity.getPet())
                        .map(petMapper::mapToDto).orElse(null))
                .build();
    }

    @Override
    public Breed mapToEntity(BreedDto dto) {
        return Breed.builder()
                .id(dto.getId())
                .name(dto.getName())
                .pet(Optional.ofNullable(dto.getPet())
                        .map(petMapper::mapToEntity).orElse(null))
                .build();
    }
}
