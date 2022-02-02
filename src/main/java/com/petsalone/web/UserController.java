package com.petsalone.web;

import com.petsalone.service.PetsService;
import com.petsalone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class UserController {

    private final UserService userService;
    private final PetsService petsService;

    public UserController(PetsService petsService, UserService userService) {
        this.petsService = petsService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (userService.isUserAuthenticated()) {
            return "redirect:/add-missing-pet";
        }

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping({"/"})
    public String index(Model model) {
        model.addAttribute("pets", petsService.getAllMissingPets());
        return "index";
    }

    @GetMapping({"/welcome"})
    public String welcome(Model model) {
        if (userService.isUserAuthenticated()) {
            return "redirect:/login";
        }
        return "welcome";
    }
}
