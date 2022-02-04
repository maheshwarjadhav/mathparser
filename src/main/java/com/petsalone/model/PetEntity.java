package com.petsalone.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class PetEntity extends AbstractEntity {

    // the name
    @NotEmpty(message = "The PetName can't be null")
    private String name;

    // missing since
    @NotNull(message = "The Missing Since Date can't be null")
    @PastOrPresent(message = "The Missing Since Date cant bet in future")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime missingSince;

    // type
    // 1 = Cat, 2 = Dog, 3 = Hamster, 4 = Bird, 5 = Rabbit, 6 = Fish, 7 = Lizard, 8 = Horse, 9 = Gerbil, 10 = Tortoise
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull(message = "The Pet Type can't be null")
    @Valid
    private PetTypeEntity petTypeEntity;

    @ManyToOne
    private User reportedBy;

    public PetEntity(String name, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime missingSince, PetTypeEntity petTypeEntity) {
        super();
        this.name = name;
        this.missingSince = missingSince;
        this.petTypeEntity = petTypeEntity;
    }

    public PetEntity(String name, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime missingSince, PetTypeEntity petTypeEntity, User reportedBy) {
        super();
        this.name = name;
        this.missingSince = missingSince;
        this.petTypeEntity = petTypeEntity;
        this.reportedBy = reportedBy;
    }

    public PetEntity() {
        super();
    }

}