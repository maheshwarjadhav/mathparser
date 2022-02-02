package com.petsalone.repository;


import com.petsalone.model.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<PetEntity, Long> {

    List<PetEntity> findAllByOrderByMissingSinceDesc();

}
