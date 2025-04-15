    package com.lol.champs_info.validators;

    import org.springframework.stereotype.Component;

    import java.util.List;

    @Component
    public class ClassTypeValidator {

        public static final List<String> VALID_CLASSES = List.of(
                "Assassin", "Fighter", "Mage", "Marksman", "Support", "Tank"
        );

        public void validateClass(String classType) {
            if (classType == null || classType.trim().isEmpty()) {
                throw new IllegalArgumentException("Class is required!");
            }

            boolean isValid = VALID_CLASSES.stream()
                    .anyMatch(validClass -> validClass.equalsIgnoreCase(classType));

            if (!isValid) {
                throw new IllegalArgumentException("Invalid class: '" + classType +
                        "'. Valid classes are: " + String.join(", ", VALID_CLASSES));
            }
        }
    }