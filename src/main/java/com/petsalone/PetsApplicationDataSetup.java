package com.petsalone;


import com.petsalone.model.PetEntity;
import com.petsalone.model.PetTypeEntity;
import com.petsalone.model.Role;
import com.petsalone.model.User;
import com.petsalone.repository.PetRepository;
import com.petsalone.repository.PetTypeRepository;
import com.petsalone.repository.RoleRepository;
import com.petsalone.repository.UserRepository;
import com.petsalone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class PetsApplicationDataSetup {

    @Autowired
    private UserService userService;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetTypeRepository petTypeRepository;

    @Autowired
    private RoleRepository roleRepository;


    @PostConstruct
    public void setupSeedData() {
        // 1 = Cat, 2 = Dog, 3 = Hamster, 4 = Bird, 5 = Rabbit, 6 = Fish, 7 = Lizard, 8 = Horse, 9 = Gerbil, 10 = Tortoise
        petTypeRepository.saveAll(
                List.of(new PetTypeEntity("Cat"),
                        new PetTypeEntity("Dog"),
                        new PetTypeEntity("Hamster"),
                        new PetTypeEntity("Bird"),
                        new PetTypeEntity("Rabbit"),
                        new PetTypeEntity("Fish"),
                        new PetTypeEntity("Lizard"),
                        new PetTypeEntity("Horse"),
                        new PetTypeEntity("Gerbil"),
                        new PetTypeEntity("Tortoise")));

        petRepository.save(new PetEntity("Max", LocalDateTime.now().minusDays(6), petTypeRepository.findByType("Dog")));
        petRepository.save(new PetEntity("Fluffy", LocalDateTime.now().minusDays(10), petTypeRepository.findByType("Cat")));
        petRepository.save(new PetEntity("Snowball", LocalDateTime.now().minusDays(2), petTypeRepository.findByType("Bird")));
        User user = new User();
        user.setUsername("admin");
        user.setPassword("test");
        /*user.setUsername("elmyraduff");
        user.setPassword("MontanaMax!!");*/
        user.setRoles(Set.of(roleRepository.save(new Role("USER"))));
        userService.save(user);
    }

}