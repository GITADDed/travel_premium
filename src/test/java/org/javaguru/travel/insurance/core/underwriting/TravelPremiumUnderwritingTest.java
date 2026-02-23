package org.javaguru.travel.insurance.core.underwriting;

import org.javaguru.travel.insurance.dto.Risk;
import org.javaguru.travel.insurance.dto.SummaryRisks;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingTest {
    private final String timeFromStr = "2025-10-16T00:00:00";
    private final String timeToStr = "2025-10-20T00:00:00";
    private final ZoneId zoneId = ZoneId.systemDefault();

    @Mock
    TravelRiskPremiumCalculator travelMedicalRiskPremiumCalculator;

    private TravelPremiumUnderwriting premiumUnderwriting;


    @BeforeEach
    public void setUp() {
        premiumUnderwriting = new TravelPremiumUnderwritingImpl(List.of(travelMedicalRiskPremiumCalculator));
    }

    @Test
    @DisplayName("should return value")
    void testCalculatePremium() {
        when(travelMedicalRiskPremiumCalculator.calculatePremium(Mockito.any())).thenReturn(new Risk("TRAVEL_MEDICAL", BigDecimal.ONE));

        Date dateFrom = Date.from(LocalDateTime.parse(timeFromStr).atZone(zoneId).toInstant());
        Date dateTo = Date.from(LocalDateTime.parse(timeToStr).atZone(zoneId).toInstant());

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest("Sergey", "Makarov", dateFrom, dateTo, Set.of("TRAVEL_MEDICAL"), "JAPAN");

        SummaryRisks answer = premiumUnderwriting.calculatePremium(request);

        Assertions.assertNotNull(answer.premium());
    }
}
