package com.example.usedstaffsaleapplication.model.Mapper;

import com.example.usedstaffsaleapplication.model.DTO.AdvertCommentDTO;
import com.example.usedstaffsaleapplication.model.Entity.AdvertComment;
import com.example.usedstaffsaleapplication.service.AdvertService;
import com.example.usedstaffsaleapplication.service.StandardUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdvertCommentsMapper {

    @Autowired
    private AdvertService advertService;

    @Autowired
    private StandardUserService standartUserService;;

    public AdvertCommentDTO toDTO(AdvertComment advertComment){
        AdvertCommentDTO advertCommentDTO = new AdvertCommentDTO();
        advertCommentDTO.setStandard_user_id(advertComment.getStandartUsers().getId());
        advertCommentDTO.setAdvert(advertComment.getAdvert().getId());
        advertCommentDTO.setComments(advertComment.getComment());
        return advertCommentDTO;
    }

    public AdvertComment toEntity(AdvertCommentDTO advertCommentDTO){
        AdvertComment advertComment = new AdvertComment();
        advertComment.setStandartUsers(standartUserService.getById(advertCommentDTO.getStandard_user_id()));
        advertComment.setAdvert(advertService.getByid(advertCommentDTO.getAdvert()));
        advertComment.setComment(advertCommentDTO.getComments());
        return advertComment;
    }
}