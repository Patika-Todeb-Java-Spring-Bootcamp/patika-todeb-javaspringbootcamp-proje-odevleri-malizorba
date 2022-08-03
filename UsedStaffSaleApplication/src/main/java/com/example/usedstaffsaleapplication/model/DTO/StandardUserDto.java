package com.example.usedstaffsaleapplication.model.DTO;

import com.example.usedstaffsaleapplication.model.Entity.Advert;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
public class StandardUserDto {

    private String name;
    private String surname;
    private String email;
    private long phoneNumber;
    private List<Advert> favoriteAdverts;
    private List<Advert> advertList;
}
