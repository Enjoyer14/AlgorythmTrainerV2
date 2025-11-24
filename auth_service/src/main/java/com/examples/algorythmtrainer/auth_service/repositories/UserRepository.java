package com.examples.algorythmtrainer.auth_service.repositories;

import com.examples.algorythmtrainer.auth_service.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserById(Integer id);
    Optional<User> findUserByLogin(String login);
    boolean existsByEmail(String email);
}
