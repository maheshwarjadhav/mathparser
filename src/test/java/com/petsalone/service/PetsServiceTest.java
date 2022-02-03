package com.petsalone.service;

import com.petsalone.model.PetEntity;
import com.petsalone.model.PetTypeEntity;
import com.petsalone.service.impl.PetsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PetsServiceTest {

    @Autowired
    PetsServiceImpl petsService;

    @Test
    void test_Add_And_Find_MissingPets() {
        List<PetEntity> pets = petsService.getAllMissingPets();
        addMissingPet("Test", "Cat");
        assertEquals(pets.size() + 1, petsService.getAllMissingPets().size());
    }

    void addMissingPet(String petName, String petType) {
        PetTypeEntity petTypeEntity = new PetTypeEntity(petType);
        PetEntity pet = new PetEntity(petName, LocalDateTime.now().minusDays(6), petTypeEntity);
        petsService.addMissingPet(pet);
    }
}
