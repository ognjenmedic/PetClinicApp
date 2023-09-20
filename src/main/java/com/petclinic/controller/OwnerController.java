package com.petclinic.controller;

import com.petclinic.entity.Owner;
import com.petclinic.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public String getAllOwners(Model model) {
        List<Owner> ownerList = ownerService.findAllOwners();
        model.addAttribute("owners", ownerList);
        return "owners";  
    }

    @GetMapping("/{id}")
    public Owner getOwnerById(@PathVariable Long id) {
        return ownerService.findOwnerById(id).orElse(null);
    }

    @PostMapping
    public Owner createOwner(@RequestParam String name, @RequestParam String email, @RequestParam String phoneNumber) {
        Owner owner = new Owner();
        owner.setName(name);
        owner.setEmail(email);
        owner.setPhoneNumber(phoneNumber);
        return ownerService.saveOwner(owner);
    }


    @PutMapping("/{id}")
    public Owner updateOwner(@PathVariable Long id, @RequestBody Owner owner) {
        // Business logic for updating can be added here
        return ownerService.saveOwner(owner);
    }

    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable Long id) {
        Owner owner = ownerService.findOwnerById(id).orElse(null);
        if (owner != null) {
            ownerService.deleteOwner(owner);
        }
    }
}
