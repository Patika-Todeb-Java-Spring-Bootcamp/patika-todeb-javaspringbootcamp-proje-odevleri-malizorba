package com.example.usedstaffsaleapplication.repository;

import com.example.usedstaffsaleapplication.model.Entity.AdvertComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdvertCommentRepository extends JpaRepository<AdvertComment,Long> {



    Optional<AdvertComment> findAdvertCommentByAdvert_Id(Long id);
    Optional<AdvertComment>findAdvertCommentByStandartUsersId(Long id);

}
