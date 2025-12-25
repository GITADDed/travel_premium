package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgreementDateFromLaterNowValidationTest extends ValidatorBasedTestClass {

    AgreementDateFromLaterNowValidation validator;

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        validator = new AgreementDateFromLaterNowValidation();
    }

    @Test
    @DisplayName("should return list of error with agreement date from in past")
    public void testValidateWithAgreementDateFromInPast() {
        Date fromInPast = new Date(0);

        request.setAgreementDateFrom(fromInPast);

        Optional<ValidationError> error = validator.validate(request);

        assertTrue(error.isPresent());
        assertEquals("agreementDateFrom", error.get().getField());
        assertEquals("Must be later than now!", error.get().getMessage());
    }

    @Test
    @DisplayName("should return list of error with agreement date from in past")
    public void testValidateWithCorrectAgreementDateFrom() {
        Optional<ValidationError> error = validator.validate(request);

        assertTrue(error.isEmpty());
    }

}