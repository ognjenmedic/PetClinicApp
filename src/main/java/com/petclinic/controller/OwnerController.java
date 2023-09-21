package com.petclinic.controller;

import com.petclinic.entity.Owner;
import com.petclinic.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

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
    public String createOwner(@RequestParam String name, @RequestParam String email, @RequestParam String phoneNumber, RedirectAttributes redirectAttributes) {
        Owner owner = new Owner();
        owner.setName(name);
        owner.setEmail(email);
        owner.setPhoneNumber(phoneNumber);
        Owner savedOwner = ownerService.saveOwner(owner);
        return "redirect:/owners/" + savedOwner.getId() + "/details";
    }

    @GetMapping("/{id}/details")
    public String ownerDetails(@PathVariable Long id, Model model) {
        Optional<Owner> ownerOpt = ownerService.findOwnerById(id);
        if (ownerOpt.isPresent()) {
            model.addAttribute("owner", ownerOpt.get());
            return "ownerDetails";  
        } else {
            return "redirect:/";  
        }
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
