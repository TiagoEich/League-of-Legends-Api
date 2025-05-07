package com.lol.champs_info.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegionValidatorTest {

    private RegionValidator validator;

    @BeforeEach
    void setUp() {
        validator = new RegionValidator();
    }

    @Test
    public void testValidateRegionWithValidRegions() {
        assertDoesNotThrow(() -> validator.validateRegion("Bandle City"));
        assertDoesNotThrow(() -> validator.validateRegion("Bilgewater"));
        assertDoesNotThrow(() -> validator.validateRegion("Demacia"));
        assertDoesNotThrow(() -> validator.validateRegion("Freljord"));
        assertDoesNotThrow(() -> validator.validateRegion("Ionia"));
        assertDoesNotThrow(() -> validator.validateRegion("Ixtal"));
        assertDoesNotThrow(() -> validator.validateRegion("Noxus"));
        assertDoesNotThrow(() -> validator.validateRegion("Piltover"));
        assertDoesNotThrow(() -> validator.validateRegion("Runeterra"));
        assertDoesNotThrow(() -> validator.validateRegion("Shadow Isles"));
        assertDoesNotThrow(() -> validator.validateRegion("Shurima"));
        assertDoesNotThrow(() -> validator.validateRegion("Targon"));
        assertDoesNotThrow(() -> validator.validateRegion("The Void"));
        assertDoesNotThrow(() -> validator.validateRegion("Zaun"));
        assertDoesNotThrow(() -> validator.validateRegion("ionia"));
        assertDoesNotThrow(() -> validator.validateRegion("DEMACIA"));
        assertDoesNotThrow(() -> validator.validateRegion("shAdow islEs"));
    }

    @Test
    public void testValidateRegionWithNullRegion() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validateRegion(null)
        );

        assertEquals("Region is required", exception.getMessage());
    }

    @Test
    public void testValidateRegionWithEmptyRegion() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validateRegion("  ")
        );

        assertEquals("Region is required", exception.getMessage());
    }

    @Test
    public void testValidateRegionWithInvalidRegion() {
        String invalidRegion = "Mordor";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validateRegion(invalidRegion)
        );

        assertTrue(exception.getMessage().contains("Invalid region"));
        assertTrue(exception.getMessage().contains(invalidRegion));
        assertTrue(exception.getMessage().contains("Valid regions are"));
    }

    @Test
    public void testValidRegionsConstant() {
        assertEquals(14, RegionValidator.VALID_REGIONS.size());
        assertTrue(RegionValidator.VALID_REGIONS.contains("Bandle City"));
        assertTrue(RegionValidator.VALID_REGIONS.contains("Bilgewater"));
        assertTrue(RegionValidator.VALID_REGIONS.contains("Demacia"));
        assertTrue(RegionValidator.VALID_REGIONS.contains("Freljord"));
        assertTrue(RegionValidator.VALID_REGIONS.contains("Ionia"));
        assertTrue(RegionValidator.VALID_REGIONS.contains("Ixtal"));
        assertTrue(RegionValidator.VALID_REGIONS.contains("Noxus"));
        assertTrue(RegionValidator.VALID_REGIONS.contains("Piltover"));
        assertTrue(RegionValidator.VALID_REGIONS.contains("Runeterra"));
        assertTrue(RegionValidator.VALID_REGIONS.contains("Shadow Isles"));
        assertTrue(RegionValidator.VALID_REGIONS.contains("Shurima"));
        assertTrue(RegionValidator.VALID_REGIONS.contains("Targon"));
        assertTrue(RegionValidator.VALID_REGIONS.contains("The Void"));
        assertTrue(RegionValidator.VALID_REGIONS.contains("Zaun"));
    }
}