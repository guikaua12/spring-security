package me.approximations.springsecurity.repositories;

import me.approximations.springsecurity.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
