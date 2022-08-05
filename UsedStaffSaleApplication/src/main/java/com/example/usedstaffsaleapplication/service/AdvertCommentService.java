package com.example.usedstaffsaleapplication.service;

import com.example.usedstaffsaleapplication.Exception.EntityNotFoundException;
import com.example.usedstaffsaleapplication.model.DTO.AdvertCommentDTO;
import com.example.usedstaffsaleapplication.model.Entity.AdvertComment;
import com.example.usedstaffsaleapplication.model.Mapper.AdvertCommentsMapper;
import com.example.usedstaffsaleapplication.repository.AdvertCommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdvertCommentService {

    private AdvertCommentsMapper advertCommentsMapper;


    private AdvertCommentRepository advertCommentRepository;

    public List<AdvertComment> getAllAdvertsComments(){
        List<AdvertComment> allAdvertComments = advertCommentRepository.findAll();
        return allAdvertComments;

            }

    public AdvertComment getById(Long id){
        Optional<AdvertComment> byId = advertCommentRepository.findById(id);
        return byId.orElseThrow(()->new EntityNotFoundException("advert comment"));
    }

    public AdvertComment getByUserId(Long id){
        Optional<AdvertComment> advertCommentByStandartUsersId = advertCommentRepository.findAdvertCommentByStandartUsersId(id);
        return advertCommentByStandartUsersId.orElseThrow(()->new EntityNotFoundException("Advert Comment"));
    }

    public AdvertComment getByAdvertId(Long id){
        Optional<AdvertComment> advertCommentByStandartAdvertId = advertCommentRepository.findAdvertCommentByAdvert_Id(id);
        return advertCommentByStandartAdvertId.orElseThrow(()->new EntityNotFoundException("Advert Comment"));
    }


    public AdvertComment create(AdvertCommentDTO advertCommentDTO){
        AdvertComment advertComment = advertCommentsMapper.toEntity(advertCommentDTO);
        AdvertComment advertComment1 = advertCommentRepository.save(advertComment);
        return advertComment1;
    }

    public void delete(Long id){
        getById(id);
        advertCommentRepository.deleteById(id);
    }
}
