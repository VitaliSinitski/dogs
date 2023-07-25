package com.dogs.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class PetDto {
    Long id;
    String name;
    BreedDto breed;
    OwnerDto owner;
}
