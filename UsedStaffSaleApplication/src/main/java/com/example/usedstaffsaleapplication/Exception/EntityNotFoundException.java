package com.example.usedstaffsaleapplication.Exception;

import lombok.Data;

@Data
public class EntityNotFoundException extends RuntimeException{

    private String details;

    public EntityNotFoundException(String EntityName) {

        super("Related "+EntityName+" Not found");
        details="Some special details";

    }
}
