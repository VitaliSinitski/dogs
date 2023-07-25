package com.dogs.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class BreedDto {
    Long id;
    String name;
}
