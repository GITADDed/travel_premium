package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TravelCalculatePremiumRequestValidatorTest {

    private TravelCalculatePremiumRequestValidator validator;

    @BeforeEach
    void setUp() {
        validator = new TravelCalculatePremiumRequestValidator();
    }

    @Test
    @DisplayName("should return empty list of errors with correct request")
    public void testValidate() {
        Date from = new Date(1700000000000L);
        Date to = new Date(1700003600000L);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Ivan");
        request.setPersonLastName("Ivanov");
        request.setAgreementDateFrom(from);
        request.setAgreementDateTo(to);
        List<ValidationError> errors = validator.validate(request);

        assertTrue(errors.isEmpty());
    }

    @Test
    @DisplayName("should return list of errors with null first name in request")
    public void testValidateWithNullFirstName() {
        Date from = new Date(1700000000000L);
        Date to = new Date(1700003600000L);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName(null);
        request.setPersonLastName("Ivanov");
        request.setAgreementDateFrom(from);
        request.setAgreementDateTo(to);
        List<ValidationError> errors = validator.validate(request);

        assertFalse(errors.isEmpty());
    }

    @Test
    @DisplayName("should return list of errors with empty first name in request")
    public void testValidateWithEmptyFirstName() {
        Date from = new Date(1700000000000L);
        Date to = new Date(1700003600000L);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("");
        request.setPersonLastName("Ivanov");
        request.setAgreementDateFrom(from);
        request.setAgreementDateTo(to);
        List<ValidationError> errors = validator.validate(request);

        assertFalse(errors.isEmpty());
    }
}
