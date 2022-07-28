package com.example.usedstaffsaleapplication.model.DTO;

import com.example.usedstaffsaleapplication.model.Enums.Role;
import com.example.usedstaffsaleapplication.model.Enums.SubCategories;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {

    private Integer id;
    private String username;
    private String email;
    private List<Role> roles;

}