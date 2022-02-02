package com.petsalone.service;

import com.petsalone.model.PetEntity;
import java.util.List;

public interface PetsService {

    List<PetEntity> getAllMissingPets();

    void addMissingPet(PetEntity missingPet);
}