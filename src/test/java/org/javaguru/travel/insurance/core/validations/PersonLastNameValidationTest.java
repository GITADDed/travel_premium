package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonLastNameValidationTest extends ValidatorBasedTestClass {
    private PersonLastNameValidation validator;

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        validator = new PersonLastNameValidation();
    }

    @Test
    @DisplayName("should return error with empty last name in request")
    public void testValidateWithEmptyLastName() {
        request.setPersonLastName("");

        Optional<ValidationError> error = validator.validate(request);

        assertTrue(error.isPresent());
        assertEquals("personLastName", error.get().getField());
        assertEquals("Must not be empty!", error.get().getMessage());
    }

    @Test
    @DisplayName("should return error with null last name in request")
    public void testValidateWithNullLastName() {
        request.setPersonLastName(null);

        Optional<ValidationError> error = validator.validate(request);

        assertTrue(error.isPresent());
        assertEquals("personLastName", error.get().getField());
        assertEquals("Must not be empty!", error.get().getMessage());
    }

    @Test
    @DisplayName("should return empty optional (its ok)")
    public void testValidateWithCorrectLastName() {
        Optional<ValidationError> error = validator.validate(request);

        assertTrue(error.isEmpty());
    }

}