package com.example.usedstaffsaleapplication;

import com.example.usedstaffsaleapplication.model.Entity.User;
import com.example.usedstaffsaleapplication.repository.UserRepository;
import com.example.usedstaffsaleapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SampleDataInitiliaser implements ApplicationRunner {

    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public void run(ApplicationArguments args) {


        // Creating a sample Admin USER
        User adminUser = new User("admin-user", "adminuser@mail.com", "pass1234");
        if (!userRepository.existsByUsername(adminUser.getUsername())) {
            userService.signup(adminUser,true);
        }

        // Creating a sample USER
        User sampleUser = new User("sample-user", "sampleuser@mail.com", "pass1234");
        if (!userRepository.existsByUsername(sampleUser.getUsername())) {
            userService.signup(sampleUser,false);
        }

    }

}