package com.example.usedstaffsaleapplication.model.Mapper;

import com.example.usedstaffsaleapplication.model.DTO.AdvertCommentDTO;
import com.example.usedstaffsaleapplication.model.Entity.AdvertComment;
import com.example.usedstaffsaleapplication.service.AdvertService;
import com.example.usedstaffsaleapplication.service.StandardUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdvertCommentsMapper {


    private final AdvertService advertService;


    private final StandardUserService standartUserService;;

    public AdvertCommentDTO toDTO(AdvertComment advertComment){

        AdvertCommentDTO advertCommentDTO = new AdvertCommentDTO();

        advertCommentDTO.setStandartUsers(advertComment.getStandartUsers().getId());
        advertCommentDTO.setAdvert(advertComment.getAdvert().getId());
        advertCommentDTO.setComments(advertComment.getComment());
        return advertCommentDTO;
    }

    public AdvertComment toEntity(AdvertCommentDTO advertCommentDTO){
        AdvertComment advertComment = new AdvertComment();

        advertComment.setStandartUsers(standartUserService.getById(advertCommentDTO.getStandartUsers()));
        advertComment.setAdvert(advertService.getByid(advertCommentDTO.getAdvert()));
        advertComment.setComment(advertCommentDTO.getComments());
        return advertComment;
    }
}