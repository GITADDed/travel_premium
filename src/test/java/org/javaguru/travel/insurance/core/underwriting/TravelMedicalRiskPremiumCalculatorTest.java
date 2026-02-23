package org.javaguru.travel.insurance.core.underwriting;

import org.javaguru.travel.insurance.core.utils.DateTimeService;
import org.javaguru.travel.insurance.dto.Risk;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TravelMedicalRiskPremiumCalculatorTest {
    private final String timeFromStr = "2025-10-16T00:00:00";
    private final String timeToStr = "2025-10-20T00:00:00";
    private final ZoneId zoneId = ZoneId.systemDefault();

    @Mock
    DateTimeService dateTimeService;

    @InjectMocks
    TravelMedicalRiskPremiumCalculator calculator;

    @Test
    void shouldReturnPremium() {
        Date dateFrom = Date.from(LocalDateTime.parse(timeFromStr).atZone(zoneId).toInstant());
        Date dateTo = Date.from(LocalDateTime.parse(timeToStr).atZone(zoneId).toInstant());
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("", "",
                dateFrom, dateTo, Set.of("TRAVEL_MEDICAL"));

        Mockito.when(dateTimeService.calculateDaysBetween(Mockito.any(), Mockito.any())).thenReturn(BigDecimal.ONE);

        Risk risk = calculator.calculatePremium(request);

        assertEquals("TRAVEL_MEDICAL", risk.riskIc());
        assertEquals(BigDecimal.ONE, risk.premium());
    }

    @Test
    void shouldReturnZeroPremiumWhenNoHaveRisks() {
        Date dateFrom = Date.from(LocalDateTime.parse(timeFromStr).atZone(zoneId).toInstant());
        Date dateTo = Date.from(LocalDateTime.parse(timeToStr).atZone(zoneId).toInstant());
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("", "",
                dateFrom, dateTo, Set.of(""));

        Risk risk = calculator.calculatePremium(request);

        assertEquals("TRAVEL_MEDICAL", risk.riskIc());
        assertEquals(BigDecimal.ZERO, risk.premium());
    }


}