package com.dogs.mappers;

import com.dogs.dto.BreedDto;
import com.dogs.entities.Breed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BreedMapper implements Mapper<Breed, BreedDto> {
    @Override
    public BreedDto mapToDto(Breed entity) {
        return BreedDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public Breed mapToEntity(BreedDto dto) {
        return Breed.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
}
