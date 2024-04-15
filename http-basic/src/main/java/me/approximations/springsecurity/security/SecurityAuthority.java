package me.approximations.springsecurity.security;

import lombok.RequiredArgsConstructor;
import me.approximations.springsecurity.entities.Authority;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class SecurityAuthority implements GrantedAuthority {
    private final Authority authority;

    @Override
    public String getAuthority() {
        return authority.getName();
    }
}
