package com.ironhack.backendProject.repositories.account;

import com.ironhack.backendProject.models.account.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
