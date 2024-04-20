package me.approximations.springsecurity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoController {

    @GetMapping("/demo1")
    public String demo() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return String.format("Demo1, %s", authentication.getName());
    }

    @PreAuthorize("hasRole('ROLE_MEMBER')")
    @GetMapping("/demo2")
    public String demo2() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return String.format("Demo2, %s", authentication.getName());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/demo3")
    public String demo3() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return String.format("Demo3, %s", authentication.getName());
    }
}
