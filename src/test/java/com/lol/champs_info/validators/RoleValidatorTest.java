package com.lol.champs_info.validators;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoleValidatorTest {

    private RoleValidator validator;

    @BeforeEach
    void setUp() {
        validator = new RoleValidator();
    }

    @Test
    public void testValidationWithValidRoles() {
        // Test all valid roles with different cases
        assertDoesNotThrow(() -> validator.validation("TOP"));
        assertDoesNotThrow(() -> validator.validation("top"));
        assertDoesNotThrow(() -> validator.validation("Top"));

        assertDoesNotThrow(() -> validator.validation("JUNGLE"));
        assertDoesNotThrow(() -> validator.validation("jungle"));
        assertDoesNotThrow(() -> validator.validation("Jungle"));

        assertDoesNotThrow(() -> validator.validation("MID"));
        assertDoesNotThrow(() -> validator.validation("mid"));
        assertDoesNotThrow(() -> validator.validation("Mid"));

        assertDoesNotThrow(() -> validator.validation("ADC"));
        assertDoesNotThrow(() -> validator.validation("adc"));
        assertDoesNotThrow(() -> validator.validation("Adc"));

        assertDoesNotThrow(() -> validator.validation("SUPPORT"));
        assertDoesNotThrow(() -> validator.validation("support"));
        assertDoesNotThrow(() -> validator.validation("Support"));
    }

    @Test
    public void testValidationWithNullRole() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validation(null)
        );

        assertEquals("Role is required!", exception.getMessage());
    }

    @Test
    public void testValidationWithEmptyRole() {
        // Should throw exception
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validation("  ")
        );

        assertEquals("Role is required!", exception.getMessage());
    }

    @Test
    public void testValidationWithInvalidRole() {
        String invalidRole = "Carry";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validation(invalidRole)
        );

        assertTrue(exception.getMessage().contains("Invalid role"));
        assertTrue(exception.getMessage().contains(invalidRole));
        assertTrue(exception.getMessage().contains("Valid roles are"));
        assertTrue(exception.getMessage().contains("TOP"));
        assertTrue(exception.getMessage().contains("JUNGLE"));
        assertTrue(exception.getMessage().contains("MID"));
        assertTrue(exception.getMessage().contains("ADC"));
        assertTrue(exception.getMessage().contains("SUPPORT"));
    }
}