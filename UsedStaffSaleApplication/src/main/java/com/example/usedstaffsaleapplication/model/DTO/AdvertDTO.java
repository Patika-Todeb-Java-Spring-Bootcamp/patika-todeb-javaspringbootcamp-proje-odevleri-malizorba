package com.example.usedstaffsaleapplication.model.DTO;

import com.example.usedstaffsaleapplication.model.Entity.StandartUsers;
import com.example.usedstaffsaleapplication.model.Enums.AdvertCategory;
import com.example.usedstaffsaleapplication.model.Enums.LanguageOptions;
import com.example.usedstaffsaleapplication.model.Enums.SubCategories;
import com.example.usedstaffsaleapplication.model.Enums.UsingofStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class AdvertDTO {

    private String title;
    private String location;
    private Float price;
    private AdvertCategory advertCategory;
    private SubCategories subCategories;
    private  UsingofStatus usingofStatus;
    private LanguageOptions languageOptions;
    private String explain;


}
