package com.example.usedstaffsaleapplication.model.Mapper;

import com.example.usedstaffsaleapplication.model.DTO.StandardUserDto;
import com.example.usedstaffsaleapplication.model.Entity.StandartUsers;

public class StandardUserMapper {

    public static StandardUserDto dto(StandartUsers standartUsers){
        StandardUserDto standardUserDto=new StandardUserDto();

        standardUserDto.setEmail(standartUsers.getEmail());
        standardUserDto.setName(standartUsers.getName());
        standardUserDto.setPhoneNumber(standartUsers.getPhoneNumber());
        standardUserDto.setSurname(standartUsers.getSurname());

        return standardUserDto;
    }

    public static StandartUsers toEntity(StandardUserDto standardUserDto){
        StandartUsers standartUsers=new StandartUsers();

        standartUsers.setSurname(standardUserDto.getSurname());
        standartUsers.setEmail(standardUserDto.getEmail());
        standartUsers.setName(standardUserDto.getName());
        standartUsers.setPhoneNumber(standardUserDto.getPhoneNumber());

        return standartUsers;

    }
}