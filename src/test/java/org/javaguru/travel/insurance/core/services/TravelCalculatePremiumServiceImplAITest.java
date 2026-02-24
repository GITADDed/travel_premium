package org.javaguru.travel.insurance.core.services;

import org.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.javaguru.travel.insurance.core.validations.RequestValidator;
import org.javaguru.travel.insurance.dto.SummaryRisks;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import testdto.TravelCalculatePremiumRequestTestDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TravelCalculatePremiumServiceImplAITest {

    private TravelCalculatePremiumServiceImpl service;
    private TravelPremiumUnderwriting premiumUnderwriting;
    private RequestValidator requestValidator;
    private TravelCalculatePremiumRequestTestDTO request;
    private final Date from = new Date(1700000000000L);
    private final Date to = new Date(1700003600000L);

    @BeforeEach
    void setUp() {
        premiumUnderwriting = Mockito.mock(TravelPremiumUnderwriting.class);
        requestValidator = Mockito.mock(RequestValidator.class);
        Mockito.when(premiumUnderwriting.calculatePremium(Mockito.any())).thenReturn(new SummaryRisks(BigDecimal.ONE, List.of()));
        Mockito.when(requestValidator.validate(Mockito.any())).thenReturn(List.of());
        service = new TravelCalculatePremiumServiceImpl(requestValidator, premiumUnderwriting);
        request = new TravelCalculatePremiumRequestTestDTO(null, null, from, to, null, null, null, null);
    }

    @Test
    @DisplayName("Checks that the first name is returned correctly")
    void testCalculatePremium_personFirstName() {
        request.setPersonFirstName("Ivan");

        TravelCalculatePremiumResponse response = service.calculatePremium(request.toDto());
        assertEquals("Ivan", response.getPersonFirstName());
    }

    @Test
    @DisplayName("Checks that the last name is returned correctly")
    void testCalculatePremium_personLastName() {
        request.setPersonLastName("Petrov");

        TravelCalculatePremiumResponse response = service.calculatePremium(request.toDto());
        assertEquals("Petrov", response.getPersonLastName());
    }

    @Test
    @DisplayName("Checks that the agreement start date is returned correctly")
    void testCalculatePremium_agreementDateFrom() {
        TravelCalculatePremiumResponse response = service.calculatePremium(request.toDto());
        assertEquals(from, response.getAgreementDateFrom());
    }

    @Test
    @DisplayName("Checks that the agreement end date is returned correctly")
    void testCalculatePremium_agreementDateTo() {
        TravelCalculatePremiumResponse response = service.calculatePremium(request.toDto());
        assertEquals(to, response.getAgreementDateTo());
    }

    @Test
    @DisplayName("Allows empty first name without exception")
    void testCalculatePremium_emptyPersonFirstName() {
        request.setPersonFirstName("");
        request.setPersonLastName("Petrov");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());

        assertDoesNotThrow(() -> service.calculatePremium(request.toDto()));
    }

    @Test
    @DisplayName("Allows empty last name without exception")
    void testCalculatePremium_emptyPersonLastName() {
        request.setPersonFirstName("Ivan");
        request.setPersonLastName("");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());

        assertDoesNotThrow(() -> service.calculatePremium(request.toDto()));
    }

}
