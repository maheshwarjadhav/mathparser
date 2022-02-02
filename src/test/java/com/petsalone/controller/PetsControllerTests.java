package com.petsalone.controller;

import com.petsalone.model.PetEntity;
import com.petsalone.model.PetTypeEntity;
import com.petsalone.service.PetsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PetsControllerTests {
    @MockBean
    private PetsService petsService;
    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void given_AnonymousUser_OnGetAllMissingPetService_shouldSucceedWith200() throws Exception {
        List<PetEntity> pets = new ArrayList<>();
        pets.add(new PetEntity("Max", LocalDateTime.now().minusDays(6), new PetTypeEntity("Cat")));
        Mockito.when(petsService.getAllMissingPets()).thenReturn(pets);
        mvc.perform(get("/getAllMissingPets")).andExpect(status().isOk());
    }

    @Test
    public void given_AnonymousUser_OnPostMissingPetService_shouldFailWith3xxRedirection() throws Exception {
        List<PetEntity> pets = new ArrayList<>();
        pets.add(new PetEntity("Max", LocalDateTime.now().minusDays(6), new PetTypeEntity("Cat")));
        Mockito.when(petsService.getAllMissingPets()).thenReturn(pets);
        mvc.perform(get("/addmissingpet")).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser("test_user")
    public void given_AuthUser_OnGetAllMissingPetService_shouldFailWith3xxRedirection() throws Exception {
        List<PetEntity> pets = new ArrayList<>();
        pets.add(new PetEntity("Max", LocalDateTime.now().minusDays(6), new PetTypeEntity("Cat")));
        Mockito.when(petsService.getAllMissingPets()).thenReturn(pets);
        mvc.perform(get("/addmissingpet")).andExpect(status().isOk());
    }

}
