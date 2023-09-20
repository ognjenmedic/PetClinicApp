package com.petclinic.controller;

import com.petclinic.entity.Pet;
import com.petclinic.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
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
    public Pet createPet(@RequestParam String name, @RequestParam String type) {
        Pet pet = new Pet();
        pet.setName(name);
        pet.setType(type);
        return petService.savePet(pet);
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
