package com.petsalone.controller;

import com.petsalone.PetsApplication;
import com.petsalone.model.PetEntity;
import com.petsalone.model.PetTypeEntity;
import com.petsalone.service.PetsService;
import com.petsalone.web.PetsController;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@SpringBootTest
/*@AutoConfigureMockMvc
@ContextConfiguration(classes = {PetsApplication.class})
@WebMvcTest*/
//@WebMvcTest(PetsController.class)
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

	@WithMockUser("spring")
	@Test
	public void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
		List<PetEntity> pets = new ArrayList<>();
		pets.add(new PetEntity("Max", LocalDateTime.now().minusDays(6), new PetTypeEntity("Cat")));
		pets.add(new PetEntity("Fluffy", LocalDateTime.now().minusDays(10), new PetTypeEntity("Dog")));
		pets.add(new PetEntity("Snowball", LocalDateTime.now().minusDays(2), new PetTypeEntity("Bird")));
		Mockito.when(petsService.getAllMissingPets()).thenReturn(pets);

		mvc.perform(get("/get"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.hasSize(3)))
				.andExpect(jsonPath("$[0].name", Matchers.is("Snowball")));


	}


}
