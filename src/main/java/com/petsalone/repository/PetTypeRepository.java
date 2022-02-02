package com.petsalone.repository;

import com.petsalone.model.PetTypeEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetTypeRepository extends JpaRepository<PetTypeEntity, Long> {

    @Cacheable(value = "petType")
    PetTypeEntity findByType(String type);

}