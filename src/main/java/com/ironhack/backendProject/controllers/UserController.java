package com.ironhack.backendProject.controllers;

import com.ironhack.backendProject.models.*;
import com.ironhack.backendProject.models.User;
import com.ironhack.backendProject.repositories.RoleRepository;
import com.ironhack.backendProject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.annotation.*;
import org.springframework.security.core.userdetails.*;
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

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user = userRepository.save(user);
        Role role = roleRepository.save(new Role("USER", user));

    }

    @PostMapping("/create-admin-user")
    public void createAdminUser(@RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user = userRepository.save(user);
        Role role1 = roleRepository.save(new Role("ACCOUNTHOLDER", user));
       Role role2 = roleRepository.save(new Role("ADMIN", user));
      Role role3 = roleRepository.save(new Role("THIRDPARTY", user));

    }

  /*  @GetMapping("user-area")
    public String showUserArea() {
        return "Estás en el area solo para usuarios";
    }

    @GetMapping("user-admin-area")
    public String showAdminArea() {
        return "Estás en el area solo para administradores";
    }
    @GetMapping("user-contributor-area")
    public String showContributorArea() {
        return "Estás en el area solo para contributors";
    }*/


}
