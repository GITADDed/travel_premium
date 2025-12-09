package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TravelCalculatePremiumServiceImplAITest {

    private TravelCalculatePremiumServiceImpl service;
    private TravelPremiumUnderwriting premiumUnderwriting;
    private TravelCalculatePremiumRequestValidator requestValidator;

    @BeforeEach
    void setUp() {
        premiumUnderwriting = Mockito.mock(TravelPremiumUnderwriting.class);
        requestValidator = Mockito.mock(TravelCalculatePremiumRequestValidator.class);
        Mockito.when(premiumUnderwriting.calculatePremium(Mockito.any())).thenReturn(BigDecimal.ONE);
        Mockito.when(requestValidator.validate(Mockito.any())).thenReturn(List.of());
        service = new TravelCalculatePremiumServiceImpl(requestValidator, premiumUnderwriting);
    }

    @Test
    @DisplayName("Checks that the first name is returned correctly")
    void testCalculatePremium_personFirstName() {
        Date from = new Date(1700000000000L);
        Date to = new Date(1700003600000L);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Ivan");
        request.setAgreementDateFrom(from);
        request.setAgreementDateTo(to);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals("Ivan", response.getPersonFirstName());
    }

    @Test
    @DisplayName("Checks that the last name is returned correctly")
    void testCalculatePremium_personLastName() {
        Date from = new Date(1700000000000L);
        Date to = new Date(1700003600000L);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();

        request.setPersonLastName("Petrov");
        request.setAgreementDateFrom(from);
        request.setAgreementDateTo(to);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals("Petrov", response.getPersonLastName());
    }

    @Test
    @DisplayName("Checks that the agreement start date is returned correctly")
    void testCalculatePremium_agreementDateFrom() {
        Date from = new Date(1700000000000L);
        Date to = new Date(1700003600000L);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(from);
        request.setAgreementDateTo(to);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(from, response.getAgreementDateFrom());
    }

    @Test
    @DisplayName("Checks that the agreement end date is returned correctly")
    void testCalculatePremium_agreementDateTo() {
        Date from = new Date(1700000000000L);
        Date to = new Date(1700003600000L);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(from);
        request.setAgreementDateTo(to);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(to, response.getAgreementDateTo());
    }

    @Test
    @DisplayName("Allows empty first name without exception")
    void testCalculatePremium_emptyPersonFirstName() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("");
        request.setPersonLastName("Ivanov");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());
        assertDoesNotThrow(() -> service.calculatePremium(request));
    }

    @Test
    @DisplayName("Allows empty last name without exception")
    void testCalculatePremium_emptyPersonLastName() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Ivan");
        request.setPersonLastName("");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());
        assertDoesNotThrow(() -> service.calculatePremium(request));
    }

}
