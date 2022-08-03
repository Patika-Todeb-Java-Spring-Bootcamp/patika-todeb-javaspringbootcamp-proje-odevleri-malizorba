package com.example.usedstaffsaleapplication.model.Mapper;

import com.example.usedstaffsaleapplication.model.Entity.Advert;
import com.example.usedstaffsaleapplication.model.DTO.AdvertDTO;

public class AdvertMapper {

    public static AdvertDTO toDTO(Advert advert){
        AdvertDTO advertDTO=new AdvertDTO();


       advertDTO.setLanguageOptions(advert.getLanguageOptions());
       advertDTO.setAdvertCategory(advert.getAdvertCategory());
       advertDTO.setSubCategories(advert.getSubCategories());
       advertDTO.setUsingofStatus(advert.getUsingofStatus());
       advertDTO.setLocation(advert.getLocation());
       advertDTO.setPrice(advert.getPrice());
       advertDTO.setTitle(advert.getTitle());
       advertDTO.setExplain(advert.getExplain());



        return advertDTO;
    }
    public static Advert toEntity(AdvertDTO advertDTO){
        Advert advert=new Advert();


        advert.setLanguageOptions(advertDTO.getLanguageOptions());
        advert.setAdvertCategory(advertDTO.getAdvertCategory());
        advert.setSubCategories(advertDTO.getSubCategories());
        advert.setUsingofStatus(advertDTO.getUsingofStatus());
        advert.setLocation(advertDTO.getLocation());
        advert.setPrice(advertDTO.getPrice());
        advert.setTitle(advertDTO.getTitle());
        advert.setExplain(advertDTO.getExplain());


        return advert;
    }
}
