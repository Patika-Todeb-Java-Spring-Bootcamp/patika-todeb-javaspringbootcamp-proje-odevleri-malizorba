package com.example.usedstaffsaleapplication.model.Mapper;

import com.example.usedstaffsaleapplication.model.Entity.Advert;
import com.example.usedstaffsaleapplication.model.DTO.AdvertDTO;

public class AdvertMapper {

    public static AdvertDTO toDTO(Advert advert){
        AdvertDTO advertDTO=new AdvertDTO();


       // advertDTO.setAdvertCategoryList(advert.getAdvertCategoryList());
        //advertDTO.setAdvertTypeList(advert.getAdvertTypeList());
        //advertDTO.setKeywords(advert.getKeywords());
        //advertDTO.setCountofReview(advert.getCountofReview());
        advertDTO.setLocation(advert.getLocation());
        advertDTO.setPrice(advert.getPrice());
        //advertDTO.setLanguageOptionsList(advert.getLanguageOptionsList());
        advertDTO.setTitle(advert.getTitle());


        return advertDTO;
    }
    public static Advert toEntity(AdvertDTO advertDTO){
        Advert advert=new Advert();


        //advert.setAdvertCategoryList(advertDTO.getAdvertCategoryList());
        //advert.setAdvertTypeList(advertDTO.getAdvertTypeList());
        //advert.setKeywords(advertDTO.getKeywords());
        //advert.setCountofReview(advertDTO.getCountofReview());
        advert.setLocation(advertDTO.getLocation());
        advert.setPrice(advertDTO.getPrice());
        //advert.setLanguageOptionsList(advertDTO.getLanguageOptionsList());
        advert.setTitle(advertDTO.getTitle());

        return advert;
    }
}
