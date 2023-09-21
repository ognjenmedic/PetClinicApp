package com.petclinic.controller;

import com.petclinic.entity.Owner;
import com.petclinic.entity.Pet;
import com.petclinic.service.OwnerService;
import com.petclinic.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;
    private final OwnerService ownerService;  

    @Autowired
    public PetController(PetService petService, OwnerService ownerService) {  
        this.petService = petService;
        this.ownerService = ownerService;  
    }

    @GetMapping
    public String getAllPets(Model model) {
        List<Pet> petList = petService.findAllPets();
        model.addAttribute("pets", petList);
        return "pets";
    }

    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable Long id) {
        return petService.findPetById(id).orElse(null);
    }

    @PostMapping
    public String createPet(@RequestParam String name, @RequestParam String type, @RequestParam Long ownerId, Model model, RedirectAttributes redirectAttributes) {
        Pet pet = new Pet();
        pet.setName(name);
        pet.setType(type);

        Optional<Owner> ownerOptional = ownerService.findOwnerById(ownerId);
        if (ownerOptional.isPresent()) {
            Owner owner = ownerOptional.get();
            pet.setOwner(owner);
            Pet savedPet = petService.savePet(pet);

            model.addAttribute("owner", owner);
            model.addAttribute("pet", savedPet);

            return "summary"; 
        } else {
            return "redirect:/"; 
        }
    }


    @PutMapping("/{id}")
    public Pet updatePet(@PathVariable Long id, @RequestBody Pet pet) {
        return petService.savePet(pet);
    }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Long id) {
        Pet pet = petService.findPetById(id).orElse(null);
        if (pet != null) {
            petService.deletePet(pet);
        }
    }
}
