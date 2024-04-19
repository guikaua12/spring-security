package me.approximations.springsecurity.security.managers;

import lombok.RequiredArgsConstructor;
import me.approximations.springsecurity.security.providers.KeyAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {
    private final KeyAuthenticationProvider keyAuthenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (keyAuthenticationProvider.supports(authentication.getClass())) {
            authentication = keyAuthenticationProvider.authenticate(authentication);
        }

        return authentication;
    }
}
