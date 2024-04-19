package me.approximations.springsecurity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoController {
    private final FilterChainProxy filterChainProxy;

    @GetMapping("/demo")
    public String demo() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return String.format("Hello, %s", authentication.getName());
    }
}
