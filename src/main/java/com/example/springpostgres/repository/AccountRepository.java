package com.example.springpostgres.repository;

import com.example.springpostgres.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    //@Query("select a from Accounts a where a.username = :username")
    @Query(value = "select * from Accounts where Accounts.username = :username", nativeQuery = true)
    Optional<Account> findByUsername(@Param("username") String username);

    @Query(value = "select * from Accounts where Accounts.email = :email", nativeQuery = true)
    Optional<Account> findByEmail(@Param("email") String email);
}
