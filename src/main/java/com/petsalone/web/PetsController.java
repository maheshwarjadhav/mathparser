package com.petsalone.web;

import com.petsalone.model.PetEntity;
import com.petsalone.repository.UserRepository;
import com.petsalone.service.PetsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
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
    private final UserRepository userRepository;

    public PetsController(PetsService petsService, UserRepository userRepository) {
        this.petsService = petsService;
        this.userRepository = userRepository;
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
    public String addMissingPetPost(@Valid PetEntity missingPet, BindingResult result, Model model, Authentication authentication) {
        if (result.hasErrors()) {
            return "add-missing-pet";
        }
        User user = (User) authentication.getPrincipal();
        missingPet.setReportedBy(userRepository.findByUsername(user.getUsername()));
        petsService.addMissingPet(missingPet);
        return "redirect:/";
    }


}
