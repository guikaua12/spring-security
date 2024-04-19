package me.approximations.springsecurity.config;

import lombok.RequiredArgsConstructor;
import me.approximations.springsecurity.security.filters.KeyAuthenticationFilter;
import me.approximations.springsecurity.security.managers.CustomAuthenticationManager;
import me.approximations.springsecurity.security.providers.KeyAuthenticationProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Value("${auth.key}")
    private String authKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        final KeyAuthenticationProvider keyAuthenticationProvider = new KeyAuthenticationProvider(authKey);
        final CustomAuthenticationManager customAuthenticationManager = new CustomAuthenticationManager(keyAuthenticationProvider);

        return http
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(new KeyAuthenticationFilter(customAuthenticationManager), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .build();
    }
}
