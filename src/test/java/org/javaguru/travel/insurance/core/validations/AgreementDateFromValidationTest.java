package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgreementDateFromValidationTest extends ValidatorBasedTestClass {
    AgreementDateFromValidation validator;

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        validator = new AgreementDateFromValidation();
    }

    @Test
    @DisplayName("should return error with null agreementDateFrom")
    public void testValidateWithFirstAndLastNameAndNullAgreementDateFrom() {
        request.setAgreementDateFrom(null);

        Optional<ValidationError> error = validator.validate(request);

        assertTrue(error.isPresent());
        assertEquals("agreementDateFrom", error.get().getField());
        assertEquals("Must not be empty!", error.get().getMessage());
    }

    @Test
    @DisplayName("should return optional empty (it's ok)")
    public void testValidateWithCorrectAgreementDateFrom() {
        Optional<ValidationError> error = validator.validate(request);

        assertTrue(error.isEmpty());
    }

}