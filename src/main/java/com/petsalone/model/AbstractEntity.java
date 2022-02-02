package com.petsalone.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@JsonIgnoreProperties(value = {"id"})
public abstract class AbstractEntity {

    // the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id = null;

    public Long getId() {
        return id;
    }
}
