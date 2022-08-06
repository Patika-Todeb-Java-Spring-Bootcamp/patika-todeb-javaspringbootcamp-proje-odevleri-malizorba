package com.example.usedstaffsaleapplication.controller;

import com.example.usedstaffsaleapplication.model.DTO.FavoriteAdvertDTO;
import com.example.usedstaffsaleapplication.model.Entity.FavoriteAdvert;
import com.example.usedstaffsaleapplication.service.AdvertFavService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
public class AdvertFavController {

    private final AdvertFavService advertFavService;

    @GetMapping("/all")
    public ResponseEntity getAllAdvertComments() {
        return ResponseEntity.status(HttpStatus.OK).body(advertFavService.getAllFavAdvert());
    }

    @GetMapping("/byadvert/{id}")
    public ResponseEntity getByAdvertId(@PathVariable(name = "id") Long id) {
        FavoriteAdvert byAdvertId = advertFavService.getByAdvertId(id);
        return ResponseEntity.status(HttpStatus.OK).body(byAdvertId);
    }
    @GetMapping("/byuser/{id}")
    public ResponseEntity<String> getByFavUserId(@PathVariable(name = "id") Long id){
        FavoriteAdvert favoriteAdvertByUserId = advertFavService.getByFavUserId(id);
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody FavoriteAdvertDTO favoriteAdvertDTO){
        FavoriteAdvert favoriteAdvert=advertFavService.create(favoriteAdvertDTO);
        if (favoriteAdvert==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fav advert cannot be created");
        }
        return ResponseEntity.status(HttpStatus.OK).body(favoriteAdvert);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") Long id){
        advertFavService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Related favorite  deleted ");
    }
}
