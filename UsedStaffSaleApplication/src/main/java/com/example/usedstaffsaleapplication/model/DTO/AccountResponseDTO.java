package com.example.usedstaffsaleapplication.model.DTO;

import com.example.usedstaffsaleapplication.model.Enums.Role;
import lombok.Data;

import java.util.List;

@Data
public class AccountResponseDTO {

    private Integer id;
    private String username;
    private String email;
    private List<Role> roles;

}