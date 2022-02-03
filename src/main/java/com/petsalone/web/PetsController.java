package com.petsalone.web;

import com.petsalone.model.PetEntity;
import com.petsalone.service.PetsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class PetsController {

    private final PetsService petsService;

    public PetsController(PetsService petsService) {
        this.petsService = petsService;
    }

    @GetMapping("/getAllMissingPets")
    public String getAllMissingPets(Model model) {
        model.addAttribute("pets", petsService.getAllMissingPets());
        return "/index";
    }

    @GetMapping("/addmissingpet")
    public String addMissingPet(PetEntity missingPet, BindingResult result, Model model) {
        model.addAttribute("petEntity", new PetEntity());
        return "add-missing-pet";
    }

    @PostMapping("/addmissingpet")
   // @PreAuthorize("hasRole('USER')")
    public String addMissingPetPost(@ModelAttribute("petEntity") @Valid PetEntity petEntity, BindingResult result) {
     //   authentication.getPrincipal();
        if (result.hasErrors()) {
            return "add-missing-pet";
        }
        petsService.addMissingPet(petEntity);
        return "redirect:/";
    }


}
