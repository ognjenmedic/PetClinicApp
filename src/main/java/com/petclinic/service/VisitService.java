package com.petclinic.service;

import com.petclinic.entity.Visit;
import com.petclinic.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitService {

    private final VisitRepository visitRepository;

    @Autowired
    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public List<Visit> findAllVisits() {
        return visitRepository.findAll();
    }

    public Optional<Visit> findVisitById(Long id) {
        return visitRepository.findById(id);
    }

    public Visit saveVisit(Visit visit) {
        return visitRepository.save(visit);
    }

    public void deleteVisit(Visit visit) {
        visitRepository.delete(visit);
    }

    // Additional business logic can be added here
}
