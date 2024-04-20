package me.approximations.springsecurity.security.providers;

import lombok.RequiredArgsConstructor;
import me.approximations.springsecurity.security.authentication.KeyAuthentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@RequiredArgsConstructor
public class KeyAuthenticationProvider implements AuthenticationProvider {
    private final String authKey;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        KeyAuthentication keyAuthentication = (KeyAuthentication) authentication;

        keyAuthentication.setAuthenticated(authKey.equals(keyAuthentication.getHeaderKey()));

        if (!keyAuthentication.isAuthenticated()) {
            throw new BadCredentialsException("Invalid key.");
        }

        return keyAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return KeyAuthentication.class.equals(authentication);
    }
}
