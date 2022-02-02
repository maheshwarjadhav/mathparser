package com.petsalone.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User extends AbstractEntity {

    private String username;

    private String password;

    private String email;

    @Transient
    private String passwordConfirm;

    @ManyToMany
    private Set<Role> roles;

}
