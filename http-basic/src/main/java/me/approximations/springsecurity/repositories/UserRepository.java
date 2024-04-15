package me.approximations.springsecurity.repositories;

import me.approximations.springsecurity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.authorities WHERE u.username = :username")
    Optional<User> findByUsername(String username);
}
