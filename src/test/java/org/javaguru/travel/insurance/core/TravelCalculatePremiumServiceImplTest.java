package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TravelCalculatePremiumServiceImplTest {

    private TravelCalculatePremiumServiceImpl service;
    private String timeFromStr = "2025-10-16T00:00:00";
    private String timeToStr = "2025-10-20T00:00:00";
    private ZoneId zoneId = ZoneId.systemDefault();

    @BeforeEach
    public void setUp() {
        service = new TravelCalculatePremiumServiceImpl();
    }


    @Test
    public void deleteMe() {

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