package com.petclinic.repository;

import com.petclinic.entity.Visit;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    @Query("SELECT v FROM Visit v JOIN FETCH v.pet p JOIN FETCH p.owner WHERE v.id = :id")
    Optional<Visit> findByIdWithPetAndOwner(@Param("id") Long id);
    
    @Query("SELECT v FROM Visit v JOIN FETCH v.pet p JOIN FETCH p.owner")
    List<Visit> findAllWithPetAndOwner();

}
