package com.dogs.repositories;

import com.dogs.dto.PetDto;
import com.dogs.entities.Owner;
import com.dogs.entities.Pet;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    @EntityGraph(attributePaths = {"tags", "breed"})
    Pet findPetById(long id);
    List<Pet> findPetsByOwner(Owner owner);
}
