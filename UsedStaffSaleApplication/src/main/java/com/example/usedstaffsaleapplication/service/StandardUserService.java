package com.example.usedstaffsaleapplication.service;

import com.example.usedstaffsaleapplication.Exception.EntityNotFoundException;
import com.example.usedstaffsaleapplication.model.DTO.StandardUserDto;

import com.example.usedstaffsaleapplication.model.Entity.Advert;
import com.example.usedstaffsaleapplication.model.Entity.StandartUsers;

import com.example.usedstaffsaleapplication.model.Mapper.StandardUserMapper;
import com.example.usedstaffsaleapplication.repository.AdvertRepository;
import com.example.usedstaffsaleapplication.repository.StandardUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StandardUserService  {

    private final StandardUserRepository standardUserRepository;

    private final AdvertRepository advertRepository;
    private final AdvertService advertService;


    public StandartUsers getById(Long id){
        Optional<StandartUsers> ById=standardUserRepository.findById(id);
        return ById.orElseThrow(()-> new EntityNotFoundException("There is no user with this id"));
    }


    public StandartUsers getByName(String name){
        Optional<StandartUsers>getname=standardUserRepository.findStandartUsersByName(name);
        return getname.orElseThrow(()-> new EntityNotFoundException("Advert not found"));
    }


    public List<StandartUsers> getAllstandartUsers(){
        List<StandartUsers>getall=standardUserRepository.findAll();
        return getall;
    }



  /*  public StandartUsers getByContainAdvert(Advert advert){
        Optional<StandartUsers>getContain=standardUserRepository.findStandartUsersByAdvertListContains(advert);
        return getContain.orElseThrow(()-> new EntityNotFoundException("Advert not found"));

    }*/

   /* public StandartUsers getByContainofAdvertatFavList(Advert advert){
        Optional<StandartUsers>getByContainofAdvertFavList=standardUserRepository.findByAdversoffavContains(advert);
        return getByContainofAdvertFavList.orElseThrow(()-> new EntityNotFoundException("there is no fav list that is not contain advert that entered"));
    }*/


    public StandartUsers create (StandardUserDto standardUserDto){
        StandartUsers standartUsers = StandardUserMapper.toEntity(standardUserDto);
        return standardUserRepository.save(standartUsers);
    }

    public void delete(Long id){
        getById(id);
        standardUserRepository.deleteById(id);

    }


    public StandartUsers update(String email,StandardUserDto standardUserDto){
       Optional<StandartUsers> standartUsersByEmail=standardUserRepository.findStandartUsersByEmail(email);
     if (!standartUsersByEmail.isPresent())
        throw new EntityNotFoundException("There is no user to update");
        StandartUsers updated = standartUsersByEmail.get();
        if (!StringUtils.isEmpty(standardUserDto.getEmail())) {
            updated.setEmail(standardUserDto.getEmail());
        }
        if (!StringUtils.isEmpty(standardUserDto.getName())) {
            updated.setName(standardUserDto.getName());
        }
        if (!StringUtils.isEmpty(standardUserDto.getSurname())) {
            updated.setSurname(standardUserDto.getSurname());
        }
        if (!StringUtils.isEmpty(standardUserDto.getPhoneNumber())) {
            updated.setPhoneNumber(standardUserDto.getPhoneNumber());
        }
        return standardUserRepository.save(updated);

    }

    public StandartUsers addFavoriteAdvertList(Long id, Advert advert) {
        StandartUsers standartUsers = getById(id);
        Advert addAvert= advertService.getByid(advert.getId());
        List<Advert> adverts=standartUsers.getAdvertList();
        adverts.add(addAvert);
        return standardUserRepository.save(standartUsers);

    }

    public StandartUsers addAdvert (Long id,Advert advert){
        StandartUsers standartUsers=getById(id);
        Advert advert1=advertService.getByid(advert.getId());
        List<Advert> advertList=standartUsers.getAdvertList();
        advertList.add(advert1);
        return standardUserRepository.save(standartUsers);
    }

}
