package org.javaguru.travel.insurance.core.underwriting;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.Risk;
import org.javaguru.travel.insurance.dto.SummaryRisks;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting {

    private final List<TravelRiskPremiumCalculator> calculators;

    public SummaryRisks calculatePremium(TravelCalculatePremiumRequest request) {
       List<Risk> risks = calculators.stream().map(calculator -> calculator.calculatePremium(request))
                .toList();

       BigDecimal premium = risks.stream().map(Risk::premium).reduce(BigDecimal.ZERO, BigDecimal::add)
               .setScale(2, RoundingMode.HALF_UP);

       return new SummaryRisks(premium, risks);
    }
}
