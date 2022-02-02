package com.petsalone.web;

import com.petsalone.service.PetsService;
import com.petsalone.service.SecurityService;
import com.petsalone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class UserController {

    private UserService userService;
    private SecurityService securityService;
    private UserValidator userValidator;
    private PetsService petsService;

    public UserController(UserService userService, SecurityService securityService, UserValidator userValidator, PetsService petsService) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
        this.petsService = petsService;
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (securityService.isAuthenticated()) {
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
        if (securityService.isAuthenticated()) {
            return "redirect:/login";
        }
        return "welcome";
    }
}
