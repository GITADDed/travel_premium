package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonFirstNameValidationTest extends ValidatorBasedTestClass {
    private PersonFirstNameValidation validator;

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        validator = new PersonFirstNameValidation();
    }

    @Test
    @DisplayName("should return error with null first name in request")
    public void testValidateWithNullFirstName() {
        request.setPersonFirstName(null);
        Optional<ValidationError> error = validator.validate(request);

        assertTrue(error.isPresent());
        assertEquals("personFirstName", error.get().getField());
        assertEquals("Must not be empty!", error.get().getMessage());
    }

    @Test
    @DisplayName("should return error with empty string first name in request")
    public void testValidateWithEmptyFirstName() {
        request.setPersonFirstName("");
        Optional<ValidationError> error = validator.validate(request);

        assertTrue(error.isPresent());
        assertEquals("personFirstName", error.get().getField());
        assertEquals("Must not be empty!", error.get().getMessage());
    }

    @Test
    @DisplayName("should return empty optional (its ok)")
    public void testValidateWithCorrectFirstName() {
        Optional<ValidationError> error = validator.validate(request);

        assertTrue(error.isEmpty());
    }
}