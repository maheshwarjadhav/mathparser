package com.petsalone.service;

import com.petsalone.model.PetEntity;
import com.petsalone.model.PetTypeEntity;
import com.petsalone.repository.PetRepository;
import com.petsalone.repository.PetTypeRepository;
import com.petsalone.service.impl.PetsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PetsServiceTest {

    @InjectMocks
    PetsServiceImpl petsService;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @Test
    void testFindAllMissingPets() {
        List<PetEntity> pets = new ArrayList<>();
        pets.add(new PetEntity("Max", LocalDateTime.now().minusDays(6), new PetTypeEntity("Cat")));
        pets.add(new PetEntity("Fluffy", LocalDateTime.now().minusDays(10), new PetTypeEntity("Dog")));
        pets.add(new PetEntity("Snowball", LocalDateTime.now().minusDays(2), new PetTypeEntity("Bird")));

        when(petRepository.findAllByOrderByMissingSinceDesc()).thenReturn(pets);

        List<PetEntity> empList = petsService.getAllMissingPets();

        assertEquals(3, empList.size());
        verify(petRepository, times(1)).findAllByOrderByMissingSinceDesc();
    }

    @Test
    void testAddMissingPets() {
        PetTypeEntity petType = new PetTypeEntity("Cat");
        PetEntity pet = new PetEntity("Max", LocalDateTime.now().minusDays(6), petType);
        when(petTypeRepository.findByType("Cat")).thenReturn(petType);

        petsService.addMissingPet(pet);

        verify(petRepository, times(1)).save(pet);
    }
}
