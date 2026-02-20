package org.javaguru.travel.insurance.core.underwriting;

import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.utils.DateTimeService;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class TravelPremiumUnderwriting {
    private final DateTimeService dateTimeService;

    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return dateTimeService.calculateDaysBetween(request.agreementDateFrom(), request.agreementDateTo());
    }
}
