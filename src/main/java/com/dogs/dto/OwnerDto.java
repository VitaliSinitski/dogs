package com.dogs.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class OwnerDto {
    Long id;
    String name;
}
