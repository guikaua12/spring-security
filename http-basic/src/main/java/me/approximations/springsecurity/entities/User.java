package me.approximations.springsecurity.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@AllArgsConstructor
@NoArgsConstructor(force=true)
@Entity
@Table(name="users")
public class User {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private final Long id;

    private final String username;
    private final String password;

    @ManyToMany
    @JoinTable(name="user_authorities", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="authority_id"))
    private final Set<Authority> authorities = new HashSet<>();
}
