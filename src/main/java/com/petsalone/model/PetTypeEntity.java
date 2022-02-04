package com.petsalone.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@JsonIgnoreProperties(value = {"id"})
public class PetTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "The PetType can't be null")
    private String type;

    public PetTypeEntity(String type) {
        this.type = type;
    }

    public PetTypeEntity() {
        super();
    }

    public void setType(String type) {
        this.type = type;
    }

}