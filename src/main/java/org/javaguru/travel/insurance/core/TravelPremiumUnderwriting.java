package org.javaguru.travel.insurance.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelPremiumUnderwriting {
    private final DateTimeService dateTimeService;

    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return dateTimeService.calculateDaysBetween(request.agreementDateFrom(), request.agreementDateTo());
    }
}
