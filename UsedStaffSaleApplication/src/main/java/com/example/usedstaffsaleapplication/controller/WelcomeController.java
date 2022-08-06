package com.example.usedstaffsaleapplication.controller;

import com.example.usedstaffsaleapplication.model.Response.ResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping(name = "welcome",

            path = "/api/welcome"
    )
    public ResponseModel welcomeMessageApi(){
        String welcomeMessage="Welcome to Application";
        ResponseModel responseModel=new ResponseModel();
        responseModel.setWelcomeMessage(welcomeMessage);
        return responseModel;
    }
}
