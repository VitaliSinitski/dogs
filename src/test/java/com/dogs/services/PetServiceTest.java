package com.dogs.services;

import com.dogs.dto.OwnerDto;
import com.dogs.dto.PetDto;
import com.dogs.entities.Owner;
import com.dogs.entities.Pet;
import com.dogs.mappers.PetMapper;
import com.dogs.repositories.PetRepository;
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
class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private PetMapper petMapper;

    @InjectMocks
    private PetService petService;

    @Test
    void testFindAll() {
        // Given
        List<Pet> petList = List.of(new Pet(1L, "Fluffy", new Owner(1L, "John Doe")),
                new Pet(2L, "Buddy", new Owner(2L, "Jane Smith")));

        when(petRepository.findAll()).thenReturn(petList);

        PetDto petDto1 = new PetDto(1L, "Fluffy", new OwnerDto(1L, "John Doe"));
        PetDto petDto2 = new PetDto(2L, "Buddy", new OwnerDto(2L, "Jane Smith"));

        when(petMapper.mapToDto(petList.get(0))).thenReturn(petDto1);
        when(petMapper.mapToDto(petList.get(1))).thenReturn(petDto2);

        // When
        List<PetDto> result = petService.findAll();

        // Then
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(2);
        assertThat(result).containsExactlyInAnyOrder(petDto1, petDto2);
    }

    @Test
    void testFindAllWhenNoPetsFound() {
        // Given
        when(petRepository.findAll()).thenReturn(Collections.emptyList());

        // When
        List<PetDto> result = petService.findAll();

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    void testFindById() {
        // Given
        Long petId = 1L;
        Pet pet = new Pet(petId, "Fluffy", new Owner(1L, "John Doe"));
        when(petRepository.findById(petId)).thenReturn(Optional.of(pet));

        PetDto petDto = new PetDto(petId, "Fluffy", new OwnerDto(1L, "John Doe"));
        when(petMapper.mapToDto(pet)).thenReturn(petDto);

        // When
        PetDto result = petService.findById(petId);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(petId);
        assertThat(result.getName()).isEqualTo("Fluffy");
        assertThat(result.getOwner()).isNotNull();
        assertThat(result.getOwner().getId()).isEqualTo(1L);
        assertThat(result.getOwner().getName()).isEqualTo("John Doe");
    }

    @Test
    void testFindByIdWhenPetNotFound() {
        // Given
        Long nonExistentPetId = 999L;
        when(petRepository.findById(nonExistentPetId)).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> petService.findById(nonExistentPetId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Pet with id: " + nonExistentPetId + " not found");
    }
}