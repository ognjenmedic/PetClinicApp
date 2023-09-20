package com.petclinic.controller;

import com.petclinic.entity.Visit;
import com.petclinic.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitController {

    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping
    public List<Visit> getAllVisits() {
        return visitService.findAllVisits();
    }

    @GetMapping("/{id}")
    public Visit getVisitById(@PathVariable Long id) {
        return visitService.findVisitById(id).orElse(null);
    }

    @PostMapping
    public Visit createVisit(@RequestBody Visit visit) {
        return visitService.saveVisit(visit);
    }

    @PutMapping("/{id}")
    public Visit updateVisit(@PathVariable Long id, @RequestBody Visit visit) {
        // Business logic for updating can be added here
        return visitService.saveVisit(visit);
    }

    @DeleteMapping("/{id}")
    public void deleteVisit(@PathVariable Long id) {
        Visit visit = visitService.findVisitById(id).orElse(null);
        if (visit != null) {
            visitService.deleteVisit(visit);
        }
    }
}
