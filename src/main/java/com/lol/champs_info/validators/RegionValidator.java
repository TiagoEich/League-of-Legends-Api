package com.lol.champs_info.validators;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegionValidator {
    public static final List <String> VALID_REGIONS = List.of(
            "Bandle City",
            "Bilgewater", "Demacia", "Freljord", "Ionia",
            "Ixtal", "Noxus", "Piltover", "Runeterra",
            "Shadow Isles", "Shurima", "Targon", "The Void", "Zaun"
    );
    public void validateRegion (String region) {
        if (region== null || region.trim().isEmpty()) {
            throw new IllegalArgumentException("Region is required");
        }

        boolean isValid = VALID_REGIONS.stream()
                .anyMatch(validRegion -> validRegion.equalsIgnoreCase(region));
        if (!isValid) {
            throw new IllegalArgumentException("Invalid region: '" +region+ "'. Valid regions are: '"+ VALID_REGIONS + "'");
        }
    }
}
