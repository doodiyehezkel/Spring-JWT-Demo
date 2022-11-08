package com.dailycodebuffer.JWTDemo.repository;

import com.dailycodebuffer.JWTDemo.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Long> {
    VerificationToken findByToken(String token);
}
