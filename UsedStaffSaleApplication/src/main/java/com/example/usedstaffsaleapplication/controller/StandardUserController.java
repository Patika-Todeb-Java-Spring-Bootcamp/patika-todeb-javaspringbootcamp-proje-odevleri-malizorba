package com.example.usedstaffsaleapplication.controller;

import com.example.usedstaffsaleapplication.model.DTO.StandardUserDto;
import com.example.usedstaffsaleapplication.model.Entity.Advert;
import com.example.usedstaffsaleapplication.model.Entity.StandartUsers;
import com.example.usedstaffsaleapplication.service.StandardUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/StandartUser")
public class StandardUserController {

    @Autowired
    StandardUserService standardUserService;


    @GetMapping({"{id}"})
    public ResponseEntity geyById(@PathVariable ("id") Long id){
        StandartUsers Byid=standardUserService.getById(id);
       return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @GetMapping("{name}")
    public ResponseEntity getByName(@PathVariable("name") String name){
        StandartUsers Byname=standardUserService.getByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(name);

    }

    @GetMapping("/all")
    public ResponseEntity getAllstandardusers (){
        List<StandartUsers> AllStandardUsers=standardUserService.getAllstandartUsers();
        return ResponseEntity.status(HttpStatus.OK).body(AllStandardUsers);
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody StandardUserDto standardUser) {
        StandartUsers standartUsers = standardUserService.create(standardUser);
        if (standartUsers == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Advert cold not be created");
        }
        return ResponseEntity.status(HttpStatus.OK).body(standartUsers);
    }

    @PutMapping("/{email}")
    public ResponseEntity update(
            @PathVariable String email,
            @RequestBody StandardUserDto standardUserDto){
        StandartUsers update=standardUserService.update(email, standardUserDto);
        return ResponseEntity.status(HttpStatus.OK).body(update);

    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam(name = "id")Long id){
        standardUserService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Standard User was deleted");


    }

    @PutMapping("/addfav/users/{id}")
    public ResponseEntity addFavoriteList(
            @PathVariable Long id,
            @RequestBody Advert advert) {
        StandartUsers standartUsers=standardUserService.addFavoriteAdvertList(id, advert);
        return ResponseEntity.status(HttpStatus.OK).body(standartUsers);
    }
    @PutMapping("/add/advert/users/{id}")
    public ResponseEntity addAdvert(
            @PathVariable Long id,
            @RequestBody Advert advert){
        standardUserService.addAdvert(id,advert);
        return ResponseEntity.status(HttpStatus.OK).body("Advert that you want add your advert list");
    }







}
