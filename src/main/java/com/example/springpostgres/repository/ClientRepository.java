package com.example.springpostgres.repository;

import com.example.springpostgres.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Override
    List<Client> findAll();

    @Override
    Optional<Client> findById(Long id);

}
