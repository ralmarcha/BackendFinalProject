package com.ironhack.backendProject.controllers;

import com.ironhack.backendProject.models.user.User;
import com.ironhack.backendProject.models.user.Role;
import com.ironhack.backendProject.repositories.user.RoleRepository;
import com.ironhack.backendProject.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/create-account-holder")
    public void createUser(@RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user = userRepository.save(user);
        Role role = roleRepository.save(new Role("ACCOUNT_HOLDER", user));

    }
    @PostMapping("/create-admin")
    public void createAdminUser(@RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user = userRepository.save(user);
        Role role2 = roleRepository.save(new Role("ADMIN", user));
       }
       @PostMapping("/create-third-party")
    public void createThirdPArty(@RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user = userRepository.save(user);
        Role role3 = roleRepository.save(new Role("THIRD_PARTY", user));

    }
}
