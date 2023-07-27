package com.dogs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDto {
    private Long id;
    private String name;
    private BreedDto breed;
    private OwnerDto owner;
    private Set<TagDto> tags;

    public PetDto(Long id, String name, OwnerDto owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }
}
