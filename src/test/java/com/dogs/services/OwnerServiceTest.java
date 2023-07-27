package com.dogs.services;

import com.dogs.dto.OwnerDto;
import com.dogs.entities.Owner;
import com.dogs.mappers.OwnerMapper;
import com.dogs.repositories.OwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private OwnerMapper ownerMapper;

    @InjectMocks
    private OwnerService ownerService;

    @Test
    void testFindAll() {
        // Given
        List<Owner> ownerList = List.of(new Owner(1L, "John Doe"), new Owner(2L, "Jane Smith"));
        when(ownerRepository.findAll()).thenReturn(ownerList);

        OwnerDto ownerDto1 = new OwnerDto(1L, "John Doe");
        OwnerDto ownerDto2 = new OwnerDto(2L, "Jane Smith");
        when(ownerMapper.mapToDto(ownerList.get(0))).thenReturn(ownerDto1);
        when(ownerMapper.mapToDto(ownerList.get(1))).thenReturn(ownerDto2);

        // When
        List<OwnerDto> result = ownerService.findAll();

        // Then
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(2);
        assertThat(result).containsExactlyInAnyOrder(ownerDto1, ownerDto2);
    }

    @Test
    void testFindAllWhenNoOwnerFound() {
        // Given
        when(ownerRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        List<OwnerDto> result = ownerService.findAll();

        // Then
        assertThat(result).isEmpty();
    }


    @Test
    void testFindById() {
        // Given
        Long ownerId = 1L;
        Owner owner = new Owner(ownerId, "John Doe");
        when(ownerRepository.findById(ownerId)).thenReturn(Optional.of(owner));

        OwnerDto ownerDto = new OwnerDto(ownerId, "John Doe");
        when(ownerMapper.mapToDto(owner)).thenReturn(ownerDto);

        // When
        OwnerDto result = ownerService.findById(ownerId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(ownerId);
        assertThat(result.getName()).isEqualTo("John Doe");
    }

    @Test
    void testFindByIdWhenOwnerNotFound() {
        // Given
        Long nonExistentOwnerId = 999L;
        when(ownerRepository.findById(nonExistentOwnerId)).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> ownerService.findById(nonExistentOwnerId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Owner with id: " + nonExistentOwnerId + " not found");
    }
}