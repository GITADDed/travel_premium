package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    private TravelCalculatePremiumServiceImpl service;
    private final String timeFromStr = "2025-10-16T00:00:00";
    private final String timeToStr = "2025-10-20T00:00:00";
    private final ZoneId zoneId = ZoneId.systemDefault();

    private DateTimeService dateTimeService;

    @BeforeEach
    public void setUp() {
        dateTimeService = Mockito.mock(DateTimeService.class);
        Mockito.when(dateTimeService.calculateDaysBetween(Mockito.any(), Mockito.any())).thenReturn(BigDecimal.ONE);
        service = new TravelCalculatePremiumServiceImpl(dateTimeService);
    }

    @Test
    @DisplayName("should return first name when valid request")
    public void shouldReturnFirstNameWhenValidRequest() {
        Date dateFrom = Date.from(LocalDateTime.parse(timeFromStr).atZone(zoneId).toInstant());
        Date dateTo = Date.from(LocalDateTime.parse(timeToStr).atZone(zoneId).toInstant());
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Sergey", "Makarov", dateFrom, dateTo);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals("Sergey", response.getPersonFirstName());
    }

    @Test
    @DisplayName("should return last name when valid request")
    public void shouldReturnLastNameWhenValidRequest() {
        Date dateFrom = Date.from(LocalDateTime.parse(timeFromStr).atZone(zoneId).toInstant());
        Date dateTo = Date.from(LocalDateTime.parse(timeToStr).atZone(zoneId).toInstant());
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Sergey", "Makarov", dateFrom, dateTo);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals("Makarov", response.getPersonLastName());
    }

    @Test
    @DisplayName("should return agreement date from when valid request")
    public void shouldReturnAgreementDateFromWhenValidRequest() {
        Date dateFrom = Date.from(LocalDateTime.parse(timeFromStr).atZone(zoneId).toInstant());
        Date dateTo = Date.from(LocalDateTime.parse(timeToStr).atZone(zoneId).toInstant());
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Sergey", "Makarov", dateFrom, dateTo);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals(response.getAgreementDateFrom(), dateFrom);
    }

    @Test
    @DisplayName("should return agreement date to when valid request")
    public void shouldReturnAgreementDateToWhenValidRequest() {
        Date dateFrom = Date.from(LocalDateTime.parse(timeFromStr).atZone(zoneId).toInstant());
        Date dateTo = Date.from(LocalDateTime.parse(timeToStr).atZone(zoneId).toInstant());
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Sergey", "Makarov", dateFrom, dateTo);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals(response.getAgreementDateTo(), dateTo);
    }

}