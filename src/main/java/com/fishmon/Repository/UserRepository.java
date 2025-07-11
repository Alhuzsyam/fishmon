package com.fishmon.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fishmon.Model.Dao.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}