package com.dogs.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Set;

@Builder
@Value
public class OwnerDto {
    Long id;
    String name;
    Set<PetDto> pets;
}
