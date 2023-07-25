package com.dogs.mappers;

import com.dogs.dto.PetDto;
import com.dogs.entities.Pet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PetMapper implements Mapper<Pet , PetDto> {
    private final BreedMapper breedMapper;
    private final OwnerMapper ownerMapper;
    private final TagMapper tagMapper;
    @Override
    public PetDto mapToDto(Pet entity) {
        return PetDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .breed(Optional.ofNullable(entity.getBreed())
                        .map(breedMapper::mapToDto).orElse(null))
                .owner(Optional.ofNullable(entity.getOwner())
                        .map(ownerMapper::mapToDto).orElse(null))
                .tags(tagMapper.mapSetToDto(entity.getTags()))
                .build();
    }

    @Override
    public Pet mapToEntity(PetDto dto) {
        return Pet.builder()
                .id(dto.getId())
                .name(dto.getName())
                .breed(Optional.ofNullable(dto.getBreed())
                        .map(breedMapper::mapToEntity).orElse(null))
                .owner(Optional.ofNullable(dto.getOwner())
                        .map(ownerMapper::mapToEntity).orElse(null))
                .build();
    }

    public Set<PetDto> mapSetToDto(Set<Pet> entitySet) {
        if (entitySet == null || entitySet.isEmpty()) {
            return Collections.emptySet();
        }
        return entitySet
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toSet());
    }
}
