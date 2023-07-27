package com.dogs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BreedDto {
    private Long id;
    private String name;
}
