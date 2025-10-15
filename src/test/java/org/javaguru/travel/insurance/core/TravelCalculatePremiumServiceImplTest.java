package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
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
    public void calculatePremiumValidRequest() {
        Date dateFrom = Date.from(LocalDateTime.parse(timeFromStr).atZone(zoneId).toInstant());
        Date dateTo = Date.from(LocalDateTime.parse(timeToStr).atZone(zoneId).toInstant());
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Sergey", "Makarov", dateFrom, dateTo);

        TravelCalculatePremiumResponse response = service.calculatePremium(request);

        assertEquals("Sergey", response.getPersonFirstName());
        assertEquals("Makarov", response.getPersonLastName());
        assertEquals(response.getAgreementDateFrom(), dateFrom);
        assertEquals(response.getAgreementDateTo(), dateTo);
    }

}