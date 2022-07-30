package com.example.usedstaffsaleapplication.model.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
@Getter
@Setter
public class StandardUserDto {

    private String name;
    private String surname;
    private String email;
    private long phoneNumber;
}
