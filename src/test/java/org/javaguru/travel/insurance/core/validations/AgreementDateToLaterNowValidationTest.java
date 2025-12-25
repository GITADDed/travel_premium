package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgreementDateToLaterNowValidationTest extends ValidatorBasedTestClass {

    AgreementDateToLaterNowValidation validator;

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        validator = new AgreementDateToLaterNowValidation();
    }

    @Test
    @DisplayName("should return error with agreement date to in past")
    public void testValidateWithAgreementDateToInPast() {
        Date toInPast = new Date(0);

        request.setAgreementDateTo(toInPast);

        Optional<ValidationError> error = validator.validate(request);

        assertTrue(error.isPresent());
        assertEquals("agreementDateTo", error.get().getField());
        assertEquals("Must be later than now!", error.get().getMessage());

    }

    @Test
    @DisplayName("should return error with agreement date to in past")
    public void testValidateWithCorrectAgreementDateTo() {
        Optional<ValidationError> error = validator.validate(request);

        assertTrue(error.isEmpty());
    }
}
