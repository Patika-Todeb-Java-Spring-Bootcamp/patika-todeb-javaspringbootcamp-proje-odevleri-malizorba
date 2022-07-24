package com.example.usedstaffsaleapplication.model.DTO;

import com.example.usedstaffsaleapplication.model.AdvertCategory;
import com.example.usedstaffsaleapplication.model.AdvertType;
import com.example.usedstaffsaleapplication.model.LanguageOptions;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AdvertDTO {

    private String title;
    private String location;
    private Float price;
    private List<AdvertCategory> advertCategoryList;
    private List<AdvertType> advertTypeList;
    private List<LanguageOptions> languageOptionsList;

}
