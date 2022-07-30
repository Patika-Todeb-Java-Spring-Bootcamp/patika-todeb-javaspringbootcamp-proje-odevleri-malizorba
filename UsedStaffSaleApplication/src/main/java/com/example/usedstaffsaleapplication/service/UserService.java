package com.example.usedstaffsaleapplication.service;

import com.example.usedstaffsaleapplication.Exception.CustomJwtException;
import com.example.usedstaffsaleapplication.Exception.EntityNotFoundException;
import com.example.usedstaffsaleapplication.model.Entity.User;
import com.example.usedstaffsaleapplication.model.Enums.Role;
import com.example.usedstaffsaleapplication.model.Enums.SubCategories;
//import com.example.usedstaffsaleapplication.repository.RoleRepository;
import com.example.usedstaffsaleapplication.repository.UserRepository;
import com.example.usedstaffsaleapplication.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

   // private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;

    //    @PostConstruct
//    private void postConstruct() {
//        // Sample test admin user insert
//        User admin = new User();
//        admin.setUsername("admin-rmzn");
//        admin.setPassword("pass12345");
//        admin.setEmail("admin@email.com");
//        admin.setRoles(Collections.singletonList(roleRepository.getById(1)));
//        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
//        userRepository.save(admin);
//    }
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//            return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
            return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
        } catch (AuthenticationException e) {
            throw new CustomJwtException("Invalid username/password supplied", HttpStatus.BAD_REQUEST);
        }
    }

    public String signup(User user,boolean isAdmin) {
       /* if (!userRepository.existsByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            List<Role> relatedRole = new ArrayList<>();
            relatedRole.add(Role.PREMIUM_CLIENT);
            relatedRole.add(Role.PREMIUM_CLIENT);
            relatedRole.add(Role.ROLE_STANDARD_CLIENT);

            for (Role a : relatedRole) {
                if (a == Role.ROLE_ADMIN) {
                    user.setRoles(Collections.singletonList(Role.ROLE_ADMIN));


                } else if (a == Role.PREMIUM_CLIENT) {
                    userRepository.save(user);
                    user.setRoles(Collections.singletonList(Role.PREMIUM_CLIENT));

                } else if (a == Role.ROLE_STANDARD_CLIENT) {
                    userRepository.save(user);
                    user.setRoles(Collections.singletonList(Role.ROLE_STANDARD_CLIENT));

                }


            }
            return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());

        }

        else {
            throw new CustomJwtException("Username is already in use", HttpStatus.BAD_REQUEST);
        }*/
        // Optional<Role> relatedRole = RoleRepository.findByName(isAdmin ? "ROLE_ADMIN" : "ROLE_USER");


        // İF WE HAVE TWO ROLES WE CAN DO AS DOWN
        if (!userRepository.existsByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Role role = isAdmin ? Role.ROLE_ADMIN : Role.ROLE_STANDARD_CLIENT;
            user.setRoles(Collections.singletonList(role));
            userRepository.save(user);
            return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
        } else {
            throw new CustomJwtException("Username is already in use", HttpStatus.BAD_REQUEST);
        }
    }

    public void delete(String username) {
        User byUsername = userRepository.findByUsername(username);
        if (byUsername == null) {
            throw new EntityNotFoundException( "username : " + username);
        } else if (byUsername.getRoles().contains(Role.ROLE_ADMIN)) {
            throw new AccessDeniedException("No permission to delete user : " + username);
        }
        userRepository.deleteByUsername(username);
    }

    public User search(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CustomJwtException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }

    public User whoami(HttpServletRequest req) {
        return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    public String refresh(String username) {
        return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
    }

}