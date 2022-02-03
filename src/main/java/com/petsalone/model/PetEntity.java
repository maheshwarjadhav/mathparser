package com.petsalone.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class PetEntity extends AbstractEntity {

    // the name
    @NotBlank(message = "Name is mandatory")
    @Size(min = 20)
    private String name;
    // missing since
    @NotBlank(message = "Name is mandatory")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime missingSince;
    // type
    // 1 = Cat, 2 = Dog, 3 = Hamster, 4 = Bird, 5 = Rabbit, 6 = Fish, 7 = Lizard, 8 = Horse, 9 = Gerbil, 10 = Tortoise
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private PetTypeEntity petTypeEntity;

    public PetEntity(String name, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime missingSince, PetTypeEntity petTypeEntity) {
        super();
        this.name = name;
        this.missingSince = missingSince;
        this.petTypeEntity = petTypeEntity;
    }

    public PetEntity() {
        super();
    }

}