package com.ironhack.backendProject.repositories.account;

import com.ironhack.backendProject.models.account.Account;
import com.ironhack.backendProject.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByPrimaryOwner(User user);


}
