package com.dogs.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class OwnerDto {
    Long id;
    String name;
}
