package com.dogs.services;

import com.dogs.dto.BreedDto;
import com.dogs.entities.Breed;
import com.dogs.mappers.BreedMapper;
import com.dogs.repositories.BreedRepository;
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
class BreedServiceTest {
    @Mock
    private BreedRepository breedRepository;

    @Mock
    private BreedMapper breedMapper;

    @InjectMocks
    private BreedService breedService;

    @Test
    void testFindAll() {
        // Given
        List<Breed> breedList = List.of(new Breed(1L, "Breed 1"), new Breed(2L, "Breed 2"));
        when(breedRepository.findAll()).thenReturn(breedList);

        BreedDto breedDto1 = new BreedDto(1L, "Breed 1");
        BreedDto breedDto2 = new BreedDto(2L, "Breed 2");
        when(breedMapper.mapToDto(breedList.get(0))).thenReturn(breedDto1);
        when(breedMapper.mapToDto(breedList.get(1))).thenReturn(breedDto2);

        // When
        List<BreedDto> result = breedService.findAll();

        // Then
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(2);
        assertThat(result).containsExactlyInAnyOrder(breedDto1, breedDto2);
    }

    @Test
    void testFindAllWhenNoBreedFound() {
        // Given
        when(breedRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        List<BreedDto> result = breedService.findAll();

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    void testFindById() {
        // Given
        Long breedId = 1L;
        Breed breed = new Breed(breedId, "Breed 1");
        when(breedRepository.findById(breedId)).thenReturn(Optional.of(breed));

        BreedDto breedDto = new BreedDto(breedId, "Breed 1");
        when(breedMapper.mapToDto(breed)).thenReturn(breedDto);

        // When
        BreedDto result = breedService.findById(breedId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(breedId);
        assertThat(result.getName()).isEqualTo("Breed 1");
    }

    @Test
    void testFindByIdWhenBreedNotFound() {
        // Given
        Long nonExistentBreedId = 999L;
        when(breedRepository.findById(nonExistentBreedId)).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> breedService.findById(nonExistentBreedId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Breed with id: " + nonExistentBreedId + " not found");
    }
}