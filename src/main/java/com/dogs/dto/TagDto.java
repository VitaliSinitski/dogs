package com.dogs.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class TagDto {
    Long id;
    String name;
}
