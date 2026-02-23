package org.javaguru.travel.insurance.core.underwriting;

import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.utils.DateTimeService;
import org.javaguru.travel.insurance.dto.Risk;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    private final DateTimeService dateTimeService;

    @Override
    public Risk calculatePremium(TravelCalculatePremiumRequest request) {
        if (request.selectedRisks().contains(getRiskIc()))
            return new Risk(getRiskIc(), dateTimeService.calculateDaysBetween(request.agreementDateFrom(), request.agreementDateTo()));
        else
            return new Risk(getRiskIc(), BigDecimal.ZERO);
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }
}
