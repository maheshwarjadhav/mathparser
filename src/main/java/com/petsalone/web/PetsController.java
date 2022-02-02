package com.petsalone.web;

import com.petsalone.model.PetEntity;
import com.petsalone.service.PetsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PetsController {

    private final PetsService petsService;

    public PetsController(PetsService petsService) {
        this.petsService = petsService;
    }

    @GetMapping("/getAllMissingPets")
    public String getAllMissingPets(Model model) {
        model.addAttribute("pets", petsService.getAllMissingPets());
        return "pets/index";
    }

    @GetMapping("/addmissingpet")
    public String addMissingPet(PetEntity missingPet, BindingResult result, Model model) {
        model.addAttribute("petEntity", new PetEntity());
        return "add-missing-pet";
    }

    @PostMapping("/addmissingpet")
    public String addMissingPetPost(PetEntity missingPet, BindingResult result, Model model, Authentication authentication) {
        authentication.getPrincipal();
        if (result.hasErrors()) {
            return "pets/add-missing-pet";
        }
        petsService.addMissingPet(missingPet);
        return "redirect:/";
    }


}
