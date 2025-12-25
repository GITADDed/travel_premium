package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgreementDateFromEarlieToValidationTest extends ValidatorBasedTestClass {

    AgreementDateFromEarlieToValidation validator;

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        validator = new AgreementDateFromEarlieToValidation();
    }

    @Test
    @DisplayName("should return error with dates in incorrect order")
    public void testValidateWithDatesInIncorrectOrder() {
        request.setAgreementDateFrom(to);
        request.setAgreementDateTo(from);

        Optional<ValidationError> error = validator.validate(request);

        assertTrue(error.isPresent());
        assertEquals("agreementDateFrom", error.get().getField());
        assertEquals("Must be earlier than agreementDateTo!", error.get().getMessage());
    }

    @Test
    @DisplayName("should return optional empty (it's ok)")
    public void testValidateWithDatesIncorrectOrder() {
        Optional<ValidationError> error = validator.validate(request);

        assertTrue(error.isEmpty());
    }

}