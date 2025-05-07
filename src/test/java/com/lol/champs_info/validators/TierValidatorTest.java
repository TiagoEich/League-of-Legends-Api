package com.lol.champs_info.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TierValidatorTest {

    private TierValidator validator;

    @BeforeEach
    void setUp() {
        validator = new TierValidator();
    }

    @Test
    public void testValidateWithValidTiers() {
        assertDoesNotThrow(() -> validator.validate("S+"));
        assertDoesNotThrow(() -> validator.validate("s+"));
        assertDoesNotThrow(() -> validator.validate("S+"));

        assertDoesNotThrow(() -> validator.validate("S"));
        assertDoesNotThrow(() -> validator.validate("s"));

        assertDoesNotThrow(() -> validator.validate("A"));
        assertDoesNotThrow(() -> validator.validate("a"));

        assertDoesNotThrow(() -> validator.validate("B"));
        assertDoesNotThrow(() -> validator.validate("b"));

        assertDoesNotThrow(() -> validator.validate("C"));
        assertDoesNotThrow(() -> validator.validate("c"));

        assertDoesNotThrow(() -> validator.validate("D"));
        assertDoesNotThrow(() -> validator.validate("d"));
    }

    @Test
    public void testValidateWithNullTier() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(null)
        );

        assertEquals("Tier is required!", exception.getMessage());
    }

    @Test
    public void testValidateWithEmptyTier() {
        // Should throw exception
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate("  ")
        );

        assertEquals("Tier is required!", exception.getMessage());
    }

    @Test
    public void testValidateWithInvalidTier() {
        String invalidTier = "F";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(invalidTier)
        );

        assertTrue(exception.getMessage().contains("Invalid tier"));
        assertTrue(exception.getMessage().contains(invalidTier));
        assertTrue(exception.getMessage().contains("Valid tiers are"));
        assertTrue(exception.getMessage().contains("S+"));
        assertTrue(exception.getMessage().contains("S"));
        assertTrue(exception.getMessage().contains("A"));
        assertTrue(exception.getMessage().contains("B"));
        assertTrue(exception.getMessage().contains("C"));
        assertTrue(exception.getMessage().contains("D"));
    }
}