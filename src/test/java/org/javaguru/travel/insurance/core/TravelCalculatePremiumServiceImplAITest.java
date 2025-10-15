package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TravelCalculatePremiumServiceImplAITest {

    @Test
    @DisplayName("Checks that the first name is returned correctly")
    void testCalculatePremium_personFirstName() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Ivan");
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals("Ivan", response.getPersonFirstName());
    }

    @Test
    @DisplayName("Checks that the last name is returned correctly")
    void testCalculatePremium_personLastName() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonLastName("Petrov");
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals("Petrov", response.getPersonLastName());
    }

    @Test
    @DisplayName("Checks that the agreement start date is returned correctly")
    void testCalculatePremium_agreementDateFrom() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        Date from = new Date(1700000000000L);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(from);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(from, response.getAgreementDateFrom());
    }

    @Test
    @DisplayName("Checks that the agreement end date is returned correctly")
    void testCalculatePremium_agreementDateTo() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        Date to = new Date(1700003600000L);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateTo(to);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(to, response.getAgreementDateTo());
    }

    @Test
    @DisplayName("Checks that an empty first name is returned correctly")
    void testCalculatePremium_emptyPersonFirstName() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("");
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals("", response.getPersonFirstName());
    }

    @Test
    @DisplayName("Checks that an empty last name is returned correctly")
    void testCalculatePremium_emptyPersonLastName() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonLastName("");
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals("", response.getPersonLastName());
    }

    @Test
    @DisplayName("Checks that null agreement start date is returned correctly")
    void testCalculatePremium_nullAgreementDateFrom() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(null);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertNull(response.getAgreementDateFrom());
    }

    @Test
    @DisplayName("Checks that null agreement end date is returned correctly")
    void testCalculatePremium_nullAgreementDateTo() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateTo(null);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertNull(response.getAgreementDateTo());
    }
}
