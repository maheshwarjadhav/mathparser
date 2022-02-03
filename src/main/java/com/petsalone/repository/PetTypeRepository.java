package com.petsalone.repository;

import com.petsalone.model.PetTypeEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetTypeRepository extends JpaRepository<PetTypeEntity, Long> {

    //static data cached for better performance
    @Cacheable(value = "petType")
    PetTypeEntity findByType(String type);

}