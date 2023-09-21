package com.petclinic.controller;

import com.petclinic.entity.Owner;
import com.petclinic.entity.Pet;
import com.petclinic.entity.Visit;
import com.petclinic.service.OwnerService;
import com.petclinic.service.PetService;
import com.petclinic.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.text.SimpleDateFormat;


@Controller
@RequestMapping("/visits")
public class VisitController {

    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
       
    }
    
    @Autowired
    private OwnerService ownerService;

    @Autowired
    private PetService petService;

    @GetMapping
    public List<Visit> getAllVisits() {
        return visitService.findAllVisits();
    }

    @GetMapping("/{id}")
    public Visit getVisitById(@PathVariable Long id) {
        return visitService.findVisitById(id).orElse(null);
    }

    @PostMapping
    public Visit createVisit(@RequestParam String date, @RequestParam String description, @RequestParam String petName, @RequestParam String ownerName) {
        System.out.println("Entered createVisit");
        
        Visit visit = new Visit();
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date parsedDate = formatter.parse(date);
            visit.setDate(parsedDate);
        } catch (Exception e) {
            System.out.println("Date Parsing Error: " + e.getMessage());
            e.printStackTrace();
        }
        
        visit.setDescription(description);
        
        Optional<Pet> petOptional = petService.findPetByName(petName);
        Optional<Owner> ownerOptional = ownerService.findOwnerByName(ownerName);

        if (petOptional.isPresent() && ownerOptional.isPresent()) {
            System.out.println("Pet and Owner are present");
            
            Pet pet = petOptional.get();
            Owner owner = ownerOptional.get();
            
            visit.setPet(pet);
            
            return visitService.saveVisit(visit);
        } else {
            System.out.println("Pet or Owner not found");
            return null; 
        }
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
    
    @GetMapping("/detailed/{id}")
    public Visit getVisitByIdWithPetAndOwner(@PathVariable Long id) {
        return visitService.getVisitByIdWithPetAndOwner(id);
    }
    
    @GetMapping("/visitsDetails")
    public String getAllVisitsWithDetails(Model model) {
        List<Visit> visits = visitService.findAllVisitsWithDetails();  
        model.addAttribute("visits", visits);
        return "visitsDetails";  
    }


}
