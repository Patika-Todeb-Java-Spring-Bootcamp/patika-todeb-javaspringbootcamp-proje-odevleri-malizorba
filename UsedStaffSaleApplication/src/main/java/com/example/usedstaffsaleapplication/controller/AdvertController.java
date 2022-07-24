package com.example.usedstaffsaleapplication.controller;

import com.example.usedstaffsaleapplication.model.Entity.Advert;
import com.example.usedstaffsaleapplication.model.DTO.AdvertDTO;
import com.example.usedstaffsaleapplication.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api/advert")
@RestController
public class AdvertController {
    @Autowired
    private AdvertService advertService;

    @GetMapping("/all")
    public ResponseEntity getAllAdverts(){
        List<Advert>Alladverts=advertService.getAllAdverts();
        return ResponseEntity.ok(Alladverts);
    }
    @GetMapping("/{id}")
    public ResponseEntity getAdvertbyId(@PathVariable("id") Long id){
        Advert byid;
        try {
             byid = advertService.getByid(id);
        }catch (RuntimeException exception){
            return ResponseEntity.status(404).body(id);
        }

        return ResponseEntity.status(200).body(id);
    }
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody AdvertDTO advert) {
        Advert repsAdvert = advertService.create(advert);
        if (repsAdvert == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Advert cold not be created");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(repsAdvert);

    }
   @DeleteMapping
    public ResponseEntity deleteAdvert (@RequestParam(name = "id")Long id){
       try{
           advertService.delete(id);
       }

        catch (RuntimeException exception){
           return ResponseEntity.status(404).body(id);
       }
        return ResponseEntity.status(HttpStatus.OK).body("Advert was deleted");

        }
        @PutMapping("/{title}")
    public ResponseEntity update(
            @PathVariable String title,
            @RequestBody AdvertDTO advertdto) {
            Advert update=advertService.update(title,advertdto);
        if (update == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Advert cold not be updated");
            }
        return ResponseEntity.status(HttpStatus.OK).body(update);

        }



    }




