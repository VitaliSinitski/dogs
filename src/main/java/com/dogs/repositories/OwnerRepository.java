package com.dogs.repositories;

import com.dogs.entities.Owner;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    @EntityGraph("ownerWithPets")
    Owner findOwnerById(long id);
}
