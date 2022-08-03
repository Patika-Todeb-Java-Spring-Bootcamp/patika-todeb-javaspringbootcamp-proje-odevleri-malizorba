package com.example.usedstaffsaleapplication.controller;

import com.example.usedstaffsaleapplication.model.DTO.AccountDataDTO;
import com.example.usedstaffsaleapplication.model.DTO.AccountLoginDTO;
import com.example.usedstaffsaleapplication.model.Entity.Account;
import com.example.usedstaffsaleapplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private AccountService accountService;

   // @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_STANDARD_CLIENT')")
    @GetMapping("/all")
    public List<Account> getAllUsers() {
        return accountService.getAll();
    }

    @PostMapping("/signin")
    public String login(@Valid @RequestBody AccountLoginDTO accountLoginDTO) {
        return accountService.signin(accountLoginDTO.getUsername(), accountLoginDTO.getPassword());
    }

    @PostMapping("/signup")

    public String signup(@RequestBody @Valid AccountDataDTO accountDataDTO) {
        Account account = new Account();
        account.setUsername(accountDataDTO.getUsername());
        account.setEmail(accountDataDTO.getEmail());
        account.setPassword(accountDataDTO.getPassword());
//        return userService.signup(modelMapper.map(user, User.class));
        return accountService.signup(account,false);//, false
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping(value = "/delete/{username}")
    public String delete(@PathVariable String username) {
        accountService.delete(username);
        return username;
    }

//    @GetMapping(value = "/me")
//    public UserResponseDTO whoami(HttpServletRequest req) {
//
//        return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
//    }

}