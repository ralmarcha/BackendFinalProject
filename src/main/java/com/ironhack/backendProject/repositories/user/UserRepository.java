package com.ironhack.backendProject.repositories.user;

import com.ironhack.backendProject.models.user.ThirdParty;
import com.ironhack.backendProject.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);

}