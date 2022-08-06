package com.example.usedstaffsaleapplication.controller;

import com.example.usedstaffsaleapplication.model.DTO.AdvertCommentDTO;
import com.example.usedstaffsaleapplication.model.Entity.AdvertComment;
import com.example.usedstaffsaleapplication.service.AdvertCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/comment")
@RestController
@RequiredArgsConstructor
public class AdvertCommentController {


   private final AdvertCommentService advertCommentservice;

    @GetMapping("/all")
    public ResponseEntity getAllAdvertComments() {
        return ResponseEntity.status(HttpStatus.OK).body(advertCommentservice.getAllAdvertsComments());
    }

    @GetMapping("/byUser/{id}")
    public ResponseEntity getByUserId(@PathVariable(name = "id") Long id) {
        AdvertComment byUserId = advertCommentservice.getByUserId(id);
        return ResponseEntity.status(HttpStatus.OK).body(byUserId);
    }

    @GetMapping("/byAdvertId/{id}")
    public ResponseEntity getByAdvertId(@PathVariable(name = "id") Long id) {
        AdvertComment byAdvertId = advertCommentservice.getByAdvertId(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody AdvertCommentDTO advertCommentDTO){
        AdvertComment advertComment=advertCommentservice.create(advertCommentDTO);
        if (advertComment==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Comment could not be created");
                    }
        return ResponseEntity.status(HttpStatus.OK).body(advertComment);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") Long id){
        advertCommentservice.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Related Comment  deleted ");
    }
}
