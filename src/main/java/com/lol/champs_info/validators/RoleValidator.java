package com.lol.champs_info.validators;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleValidator {
    private static final List<String> VALID_ROLES = List.of("TOP","JUNGLE", "MID", "ADC", "SUPPORT");

    public void validation (String role) {
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("Role is required!");
        }
        if (!VALID_ROLES.contains(role.toUpperCase())) {
            throw new IllegalArgumentException("Invalid role: '"+role+"'. Valid roles are: '"+VALID_ROLES+"'");
        }
    }
}
