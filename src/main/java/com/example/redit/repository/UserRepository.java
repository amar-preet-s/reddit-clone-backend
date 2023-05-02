package com.example.redit.repository;

import com.example.redit.dbos.UserDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDBO,Long> {
    Optional<UserDBO> findByUsername(String username);
}
