package com.petsalone.repository;

import com.petsalone.model.PetEntity;
import com.petsalone.model.PetTypeEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PetRepositoryTest {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private PetTypeRepository petTypeRepository;

    @Test
    public void test_Pet_Creation_Retrieval_Deletion() {
        PetTypeEntity cat = petTypeRepository.save(new PetTypeEntity("Cat"));
        PetEntity petEntity1 = new PetEntity("CuteCat1", LocalDateTime.now(), cat);
        PetEntity petEntity2 = new PetEntity("CuteCat2", LocalDateTime.now(), cat);
        petRepository.saveAll(List.of(petEntity1, petEntity2));

        List<PetEntity> pets = petRepository.findAll();
        Assertions.assertThat(pets.get(0)).extracting(PetEntity::getName).isEqualTo("CuteCat1");
        Assertions.assertThat(pets.get(1)).extracting(PetEntity::getName).isEqualTo("CuteCat2");
        petRepository.deleteAll();
        Assertions.assertThat(petRepository.findAll()).isEmpty();
    }

}
