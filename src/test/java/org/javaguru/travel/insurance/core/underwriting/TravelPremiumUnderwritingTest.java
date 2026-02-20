package org.javaguru.travel.insurance.core.underwriting;

import org.javaguru.travel.insurance.core.services.DateTimeService;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class TravelPremiumUnderwritingTest {
    private final String timeFromStr = "2025-10-16T00:00:00";
    private final String timeToStr = "2025-10-20T00:00:00";
    private final ZoneId zoneId = ZoneId.systemDefault();

    private TravelPremiumUnderwriting premiumUnderwriting;

    @BeforeEach
    public void setUp() {
        DateTimeService dateTimeService = Mockito.mock(DateTimeService.class);
        Mockito.when(dateTimeService.calculateDaysBetween(Mockito.any(), Mockito.any())).thenReturn(BigDecimal.ONE);
        premiumUnderwriting = new TravelPremiumUnderwriting(dateTimeService);
    }

    @Test
    @DisplayName("should return value")
    void testCalculatePremium(){
        Date dateFrom = Date.from(LocalDateTime.parse(timeFromStr).atZone(zoneId).toInstant());
        Date dateTo = Date.from(LocalDateTime.parse(timeToStr).atZone(zoneId).toInstant());

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Sergey", "Makarov", dateFrom, dateTo, null);

        BigDecimal answer = premiumUnderwriting.calculatePremium(request);

        Assertions.assertNotNull(answer);
    }
}
