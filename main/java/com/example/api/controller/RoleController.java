package com.example.api.controller;

import com.example.api.model.enums.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN')")
public class RoleController {

    @GetMapping("")
    public Role[] list() {
        return Role.ROLES;
    }

}
