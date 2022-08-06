package com.example.usedstaffsaleapplication.repository;

import com.example.usedstaffsaleapplication.model.Entity.FavoriteAdvert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdvertFavRepository extends JpaRepository<FavoriteAdvert,Long> {

    Optional<FavoriteAdvert>findFavoriteAdvertByAdvert_Id(Long id);
    Optional<FavoriteAdvert>findFavoriteAdvertByStandartUsers_Id(Long id);


}
