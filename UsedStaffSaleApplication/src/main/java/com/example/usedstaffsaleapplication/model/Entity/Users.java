package com.example.usedstaffsaleapplication.model.Entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.List;
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Users {

    private String name;

    private String surname;

    private String email;

    private long phoneNumber;
}
