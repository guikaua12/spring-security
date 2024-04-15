package me.approximations.springsecurity.security.providers;

import me.approximations.springsecurity.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Value("${auth.key}")
    private String authKey;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final CustomAuthentication customAuthentication = (CustomAuthentication) authentication;

        if (!authKey.equals(customAuthentication.getKey())) {
            throw new BadCredentialsException("Invalid key.");
        }

        authentication.setAuthenticated(true);

        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.equals(authentication);
    }
}
