package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TravelCalculatePremiumServiceImplTest {

    private TravelCalculatePremiumServiceImpl service;
    private final String timeFromStr = "2025-10-16T00:00:00";
    private final String timeToStr = "2025-10-20T00:00:00";
    private final ZoneId zoneId = ZoneId.systemDefault();

    private TravelPremiumUnderwriting premiumUnderwriting;
    private TravelCalculatePremiumRequestValidator requestValidator;

    @BeforeEach
    public void setUp() {
        premiumUnderwriting = Mockito.mock(TravelPremiumUnderwriting.class);
        requestValidator = Mockito.mock(TravelCalculatePremiumRequestValidator.class);
        Mockito.when(premiumUnderwriting.calculatePremium(Mockito.any())).thenReturn(BigDecimal.ONE);
        Mockito.when(requestValidator.validate(Mockito.any())).thenReturn(List.of());
        service = new TravelCalculatePremiumServiceImpl(requestValidator, premiumUnderwriting);
    }

    @Test
    @DisplayName("should return first name when valid request")
    public void shouldReturnFirstNameWhenValidRequest() {
        Date dateFrom = Date.from(LocalDateTime.parse(timeFromStr).atZone(zoneId).toInstant());
        Date dateTo = Date.from(LocalDateTime.parse(timeToStr).atZone(zoneId).toInstant());
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Sergey", "Makarov", dateFrom, dateTo);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals("Sergey", response.getPersonFirstName());
        assertFalse(response.hasErrors());
    }

    @Test
    @DisplayName("should return last name when valid request")
    public void shouldReturnLastNameWhenValidRequest() {
        Date dateFrom = Date.from(LocalDateTime.parse(timeFromStr).atZone(zoneId).toInstant());
        Date dateTo = Date.from(LocalDateTime.parse(timeToStr).atZone(zoneId).toInstant());
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Sergey", "Makarov", dateFrom, dateTo);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals("Makarov", response.getPersonLastName());
        assertFalse(response.hasErrors());
    }

    @Test
    @DisplayName("should return agreement date from when valid request")
    public void shouldReturnAgreementDateFromWhenValidRequest() {
        Date dateFrom = Date.from(LocalDateTime.parse(timeFromStr).atZone(zoneId).toInstant());
        Date dateTo = Date.from(LocalDateTime.parse(timeToStr).atZone(zoneId).toInstant());
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Sergey", "Makarov", dateFrom, dateTo);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals(response.getAgreementDateFrom(), dateFrom);
        assertFalse(response.hasErrors());
    }

    @Test
    @DisplayName("should return agreement date to when valid request")
    public void shouldReturnAgreementDateToWhenValidRequest() {
        Date dateFrom = Date.from(LocalDateTime.parse(timeFromStr).atZone(zoneId).toInstant());
        Date dateTo = Date.from(LocalDateTime.parse(timeToStr).atZone(zoneId).toInstant());
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Sergey", "Makarov", dateFrom, dateTo);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals(response.getAgreementDateTo(), dateTo);
        assertFalse(response.hasErrors());
    }

}