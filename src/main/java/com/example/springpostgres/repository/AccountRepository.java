package com.example.springpostgres.repository;

import com.example.springpostgres.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "select * from Accounts", nativeQuery = true)
    List<Account> findAll();

    @Query(value = "select * from Accounts where Accounts.name = :name", nativeQuery = true)
    List<Account> findByName(@Param("name") String name); //el nombre puede estar repetido por eso devolvemos una lista

    //@Query("select a from Accounts a where a.username = :username")
    @Query(value = "select * from Accounts where Accounts.username = :username", nativeQuery = true)
    Optional<Account> findByUsername(@Param("username") String username);

    @Query(value = "select * from Accounts where Accounts.email = :email", nativeQuery = true)
    Optional<Account> findByEmail(@Param("email") String email);
}
