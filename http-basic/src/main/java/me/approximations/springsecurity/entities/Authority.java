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
@Table(name="authorities")
public class Authority {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private final Long id;

    private String name;

    @ManyToMany(mappedBy="authorities")
    private final Set<User> users = new HashSet<>();
}
