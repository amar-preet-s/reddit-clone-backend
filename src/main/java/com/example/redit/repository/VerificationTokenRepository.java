package com.example.redit.repository;

import com.example.redit.dbos.VerificationTokenDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationTokenDBO,Long> {
    Optional<VerificationTokenDBO> findByToken(String Token);
}
