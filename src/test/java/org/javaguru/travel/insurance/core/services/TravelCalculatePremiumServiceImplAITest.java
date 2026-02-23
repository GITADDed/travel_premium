package org.javaguru.travel.insurance.core.services;

import org.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.javaguru.travel.insurance.core.validations.RequestValidator;
import org.javaguru.travel.insurance.dto.SummaryRisks;
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
    private RequestValidator requestValidator;

    @BeforeEach
    void setUp() {
        premiumUnderwriting = Mockito.mock(TravelPremiumUnderwriting.class);
        requestValidator = Mockito.mock(RequestValidator.class);
        Mockito.when(premiumUnderwriting.calculatePremium(Mockito.any())).thenReturn(new SummaryRisks(BigDecimal.ONE, List.of()));
        Mockito.when(requestValidator.validate(Mockito.any())).thenReturn(List.of());
        service = new TravelCalculatePremiumServiceImpl(requestValidator, premiumUnderwriting);
    }

    @Test
    @DisplayName("Checks that the first name is returned correctly")
    void testCalculatePremium_personFirstName() {
        Date from = new Date(1700000000000L);
        Date to = new Date(1700003600000L);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Ivan",null, from, to, null);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals("Ivan", response.getPersonFirstName());
    }

    @Test
    @DisplayName("Checks that the last name is returned correctly")
    void testCalculatePremium_personLastName() {
        Date from = new Date(1700000000000L);
        Date to = new Date(1700003600000L);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(null,"Petrov", from, to, null);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals("Petrov", response.getPersonLastName());
    }

    @Test
    @DisplayName("Checks that the agreement start date is returned correctly")
    void testCalculatePremium_agreementDateFrom() {
        Date from = new Date(1700000000000L);
        Date to = new Date(1700003600000L);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(null,null, from, to, null);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(from, response.getAgreementDateFrom());
    }

    @Test
    @DisplayName("Checks that the agreement end date is returned correctly")
    void testCalculatePremium_agreementDateTo() {
        Date from = new Date(1700000000000L);
        Date to = new Date(1700003600000L);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest(null,null, from, to, null);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(to, response.getAgreementDateTo());
    }

    @Test
    @DisplayName("Allows empty first name without exception")
    void testCalculatePremium_emptyPersonFirstName() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("","Petrov", new Date(), new Date(), null);

        assertDoesNotThrow(() -> service.calculatePremium(request));
    }

    @Test
    @DisplayName("Allows empty last name without exception")
    void testCalculatePremium_emptyPersonLastName() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Ivan","", new Date(), new Date(), null);

        assertDoesNotThrow(() -> service.calculatePremium(request));
    }

}
