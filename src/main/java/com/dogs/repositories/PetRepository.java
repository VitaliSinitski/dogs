package com.dogs.repositories;

import com.dogs.entities.Pet;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
    @EntityGraph(attributePaths = {"tags", "breed"})
    Pet findPetById(long id);
}
