package com.lol.champs_info.validators;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ClassTypeValidatorTest {

    private final ClassTypeValidator validator = new ClassTypeValidator();

    @ParameterizedTest
    @ValueSource(strings = {"Assassin", "Fighter", "Mage", "Marksman", "Support", "Tank"})
    void validateClass_shouldAcceptValidClasses(String validClass) {
        assertDoesNotThrow(() -> validator.validateClass(validClass));
    }

    @Test
    void validateClass_shouldThrowWhenClassIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validator.validateClass(null));

        assertEquals("Class is required!", exception.getMessage());
    }

    @Test
    void validateClass_shouldThrowWhenClassIsEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validator.validateClass("   "));

        assertEquals("Class is required!", exception.getMessage());
    }

    @Test
    void validateClass_shouldThrowWhenClassIsInvalid() {
        String invalidClass = "InvalidClass";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validator.validateClass(invalidClass));

        assertEquals("Invalid class: '" + invalidClass +
                        "'. Valid classes are: Assassin, Fighter, Mage, Marksman, Support, Tank",
                exception.getMessage());
    }

    @Test
    void validateClass_shouldBeCaseInsensitive() {
        assertDoesNotThrow(() -> validator.validateClass("mAge"));
        assertDoesNotThrow(() -> validator.validateClass("FIGHTER"));
    }
}