package me.approximations.springsecurity.config;

import lombok.RequiredArgsConstructor;
import me.approximations.springsecurity.security.filters.KeyAuthenticationFilter;
import me.approximations.springsecurity.security.managers.CustomAuthenticationManager;
import me.approximations.springsecurity.security.providers.KeyAuthenticationProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
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
                .authorizeHttpRequests(authorize ->
                                authorize.requestMatchers("/demo1").permitAll()
//                                .requestMatchers("/demo2").hasRole("MEMBER")
//                                .requestMatchers("/demo3").hasRole("ADMIN")
                                        .anyRequest().authenticated()

                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        final InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

        final UserDetails admin = User.withUsername("admin")
                .password("12345")
                .roles("ADMIN")
                .build();

        final UserDetails member = User.withUsername("member")
                .password("12345")
                .roles("MEMBER")
                .build();

        userDetailsManager.createUser(admin);
        userDetailsManager.createUser(member);

        return userDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
