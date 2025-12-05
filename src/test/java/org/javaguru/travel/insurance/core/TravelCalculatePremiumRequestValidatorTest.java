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

        assertEquals(1, errors.size());
        assertEquals("personFirstName", errors.getFirst().getField());
        assertEquals("Must not be empty!", errors.getFirst().getMessage());
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

        assertEquals(1, errors.size());
        assertEquals("personFirstName", errors.getFirst().getField());
        assertEquals("Must not be empty!", errors.getFirst().getMessage());
    }

    @Test
    @DisplayName("should return list of errors with empty last name in request")
    public void testValidateWithEmptyLastName() {
        Date from = new Date(1700000000000L);
        Date to = new Date(1700003600000L);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Ivan");
        request.setPersonLastName("");
        request.setAgreementDateFrom(from);
        request.setAgreementDateTo(to);
        List<ValidationError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("personLastName", errors.getFirst().getField());
        assertEquals("Must not be empty!", errors.getFirst().getMessage());
    }

    @Test
    @DisplayName("should return list of errors with null last name in request")
    public void testValidateWithNullLastName() {
        Date from = new Date(1700000000000L);
        Date to = new Date(1700003600000L);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Ivan");
        request.setPersonLastName(null);
        request.setAgreementDateFrom(from);
        request.setAgreementDateTo(to);
        List<ValidationError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("personLastName", errors.getFirst().getField());
        assertEquals("Must not be empty!", errors.getFirst().getMessage());
    }

    @Test
    @DisplayName("should return list of errors with empty last and first name in request")
    public void testValidateWithEmptyFirstAndLastName() {
        Date from = new Date(1700000000000L);
        Date to = new Date(1700003600000L);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("");
        request.setPersonLastName("");
        request.setAgreementDateFrom(from);
        request.setAgreementDateTo(to);
        List<ValidationError> errors = validator.validate(request);

        assertEquals(2, errors.size());
        assertEquals("personFirstName", errors.get(0).getField());
        assertEquals("personLastName", errors.get(1).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
        assertEquals("Must not be empty!", errors.get(1).getMessage());
    }

    @Test
    @DisplayName("should return list of errors with empty last and null first name in request")
    public void testValidateWithNullFirstAndEmptyLastName() {
        Date from = new Date(1700000000000L);
        Date to = new Date(1700003600000L);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName(null);
        request.setPersonLastName("");
        request.setAgreementDateFrom(from);
        request.setAgreementDateTo(to);
        List<ValidationError> errors = validator.validate(request);

        assertEquals(2, errors.size());
        assertEquals("personFirstName", errors.get(0).getField());
        assertEquals("personLastName", errors.get(1).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
        assertEquals("Must not be empty!", errors.get(1).getMessage());
    }

    @Test
    @DisplayName("should return list of errors with null last and empty first name in request")
    public void testValidateWithEmptyFirstAndNullLastName() {
        Date from = new Date(1700000000000L);
        Date to = new Date(1700003600000L);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("");
        request.setPersonLastName(null);
        request.setAgreementDateFrom(from);
        request.setAgreementDateTo(to);
        List<ValidationError> errors = validator.validate(request);

        assertEquals(2, errors.size());
        assertEquals("personFirstName", errors.get(0).getField());
        assertEquals("personLastName", errors.get(1).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
        assertEquals("Must not be empty!", errors.get(1).getMessage());
    }

    @Test
    @DisplayName("should return list of errors with null last and first name in request")
    public void testValidateWithNullFirstAndLastName() {
        Date from = new Date(1700000000000L);
        Date to = new Date(1700003600000L);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName(null);
        request.setPersonLastName(null);
        request.setAgreementDateFrom(from);
        request.setAgreementDateTo(to);
        List<ValidationError> errors = validator.validate(request);

        assertEquals(2, errors.size());
        assertEquals("personFirstName", errors.get(0).getField());
        assertEquals("personLastName", errors.get(1).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
        assertEquals("Must not be empty!", errors.get(1).getMessage());
    }


}
