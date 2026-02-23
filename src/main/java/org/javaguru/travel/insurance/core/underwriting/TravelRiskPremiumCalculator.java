package org.javaguru.travel.insurance.core.underwriting;

import org.javaguru.travel.insurance.dto.Risk;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;

public interface TravelRiskPremiumCalculator {
    Risk calculatePremium(TravelCalculatePremiumRequest request);
    String getRiskIc();
}
