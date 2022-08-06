package com.example.usedstaffsaleapplication.service;

import com.example.usedstaffsaleapplication.Exception.EntityNotFoundException;
import com.example.usedstaffsaleapplication.model.DTO.FavoriteAdvertDTO;
import com.example.usedstaffsaleapplication.model.Entity.FavoriteAdvert;
import com.example.usedstaffsaleapplication.model.Mapper.AdvertFavMapper;
import com.example.usedstaffsaleapplication.repository.AdvertFavRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdvertFavService {
    private AdvertFavMapper advertFavMapper;
    private AdvertFavRepository advertFavRepository;

    public List<FavoriteAdvert> getAllFavAdvert(){
        List<FavoriteAdvert>allFavAdvert=advertFavRepository.findAll();
        return allFavAdvert;
    }

    public FavoriteAdvert getByAdvertId(Long id){
        Optional<FavoriteAdvert> favoriteAdvertbyId = advertFavRepository.findFavoriteAdvertByAdvert_Id(id);
        return favoriteAdvertbyId.orElseThrow(()->new EntityNotFoundException("there is no favorite advert"));
    }

    public FavoriteAdvert getByFavUserId(Long id){
        Optional<FavoriteAdvert> favoriteAdvertByUserId = advertFavRepository.findFavoriteAdvertByStandartUsers_Id(id);
        return favoriteAdvertByUserId.orElseThrow(()->new EntityNotFoundException("there is no favorite advert"));
    }


    public FavoriteAdvert create(FavoriteAdvertDTO favoriteAdvertDTO){
        FavoriteAdvert favoriteAdvert = advertFavMapper.toEntity(favoriteAdvertDTO);
        return  advertFavRepository.save(favoriteAdvert);

    }

    public void delete(Long id){
        getByAdvertId(id);
        advertFavRepository.deleteById(id);
    }

}
