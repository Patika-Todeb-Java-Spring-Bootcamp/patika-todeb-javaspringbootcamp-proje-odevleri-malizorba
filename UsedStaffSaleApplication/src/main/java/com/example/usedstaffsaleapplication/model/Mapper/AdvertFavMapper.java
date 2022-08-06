package com.example.usedstaffsaleapplication.model.Mapper;

import com.example.usedstaffsaleapplication.model.DTO.FavoriteAdvertDTO;
import com.example.usedstaffsaleapplication.model.Entity.FavoriteAdvert;
import com.example.usedstaffsaleapplication.service.AdvertService;
import com.example.usedstaffsaleapplication.service.StandardUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdvertFavMapper {

    private final AdvertService advertService;


    private final StandardUserService standartUserService;;

    public FavoriteAdvertDTO toDTO(FavoriteAdvert favoriteAdvert){
        FavoriteAdvertDTO favoriteAdvertDTO = new FavoriteAdvertDTO();
        favoriteAdvertDTO.setStandard_user_id(favoriteAdvert.getStandartUsers().getId());
        favoriteAdvertDTO.setAdvert_id(favoriteAdvert.getAdvert().getId());

        return favoriteAdvertDTO;
    }

    public FavoriteAdvert toEntity(FavoriteAdvertDTO favoriteAdvertDTO){
        FavoriteAdvert favoriteAdvert = new FavoriteAdvert();
        favoriteAdvert.setStandartUsers(standartUserService.getById(favoriteAdvertDTO.getStandard_user_id()));
        favoriteAdvert.setAdvert(advertService.getByid(favoriteAdvertDTO.getAdvert_id()));

        return favoriteAdvert;
    }
}
