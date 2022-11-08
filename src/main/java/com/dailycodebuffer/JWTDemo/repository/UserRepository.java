package com.dailycodebuffer.JWTDemo.repository;

import com.dailycodebuffer.JWTDemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {
}
