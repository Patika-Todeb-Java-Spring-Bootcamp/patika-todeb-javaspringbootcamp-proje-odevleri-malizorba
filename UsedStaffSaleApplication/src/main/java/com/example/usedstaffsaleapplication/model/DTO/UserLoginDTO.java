package com.example.usedstaffsaleapplication.model.DTO;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UserLoginDTO {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

}