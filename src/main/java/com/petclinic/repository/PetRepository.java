package com.petclinic.repository;

import com.petclinic.entity.Pet;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    Optional<Pet> findByName(String name);
}
