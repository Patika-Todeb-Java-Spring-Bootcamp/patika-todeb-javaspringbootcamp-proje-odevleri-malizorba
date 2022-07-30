package com.example.usedstaffsaleapplication.repository;

import com.example.usedstaffsaleapplication.model.Entity.Advert;
import com.example.usedstaffsaleapplication.model.Entity.StandartUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
@Repository
public interface StandardUserRepository extends JpaRepository<StandartUsers,Long> {

    Optional<StandartUsers> findStandartUsersByName (String name);
    Optional<StandartUsers> findStandartUsersByEmail (String email);

    //Optional<StandartUsers> findStandartUsersByAdvertListContains ()





}
