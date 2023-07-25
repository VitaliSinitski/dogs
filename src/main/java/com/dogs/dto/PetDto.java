package com.dogs.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Builder
@Value
public class PetDto {
    Long id;
    String name;
    BreedDto breed;
    OwnerDto owner;
    Set<TagDto> tags;
}
