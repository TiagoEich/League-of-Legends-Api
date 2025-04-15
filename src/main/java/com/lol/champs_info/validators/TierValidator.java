package com.lol.champs_info.validators;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TierValidator {
    private static final List<String> VALID_TIERS = List.of("S+", "S", "A", "B", "C", "D");

    public void validate(String tier) {
        if (tier == null || tier.trim().isEmpty()) {
            throw new IllegalArgumentException("Tier is required!");
        }

        if (!VALID_TIERS.contains(tier.toUpperCase())) {
            throw new IllegalArgumentException("Invalid tier '"+tier+"'. Valid tiers are: '"+VALID_TIERS+"'");
        }
    }
}
