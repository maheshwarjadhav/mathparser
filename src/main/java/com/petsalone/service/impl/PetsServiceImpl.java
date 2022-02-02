package com.petsalone.service.impl;

import com.petsalone.model.PetEntity;
import com.petsalone.model.PetTypeEntity;
import com.petsalone.repository.PetRepository;
import com.petsalone.repository.PetTypeRepository;
import com.petsalone.service.PetsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetsServiceImpl implements PetsService {

    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public PetsServiceImpl(PetRepository petRepository, PetTypeRepository petTypeRepository) {
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    public List<PetEntity> getAllMissingPets() {
        return petRepository.findAllByOrderByMissingSinceDesc();
    }

    public void addMissingPet(PetEntity missingPet) {
        PetTypeEntity petTypeEntity = petTypeRepository.findByType(missingPet.getPetTypeEntity().getType());
        if (petTypeEntity == null) {
            petTypeEntity = petTypeRepository.save(new PetTypeEntity(missingPet.getPetTypeEntity().getType()));
        }
        missingPet.setPetTypeEntity(petTypeEntity);
        petRepository.save(missingPet);
    }

}