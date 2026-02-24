package org.javaguru.travel.insurance.core.services;

import com.google.common.base.Stopwatch;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.javaguru.travel.insurance.core.validations.RequestValidator;
import org.javaguru.travel.insurance.dto.SummaryRisks;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {
    private final Logger log = LoggerFactory.getLogger(TravelCalculatePremiumServiceImpl.class);
    private static final long SLOW_THRESHOLD_MS = 300;

    private final RequestValidator requestValidator;
    private final TravelPremiumUnderwriting premiumUnderwriting;

    @Override
    public TravelCalculatePremiumResponse  calculatePremium(TravelCalculatePremiumRequest request) {
        Stopwatch sw = Stopwatch.createStarted();

        try {

            log.info("Premium calculation started");
            log.info("Request details: selected_risks={}", request.selectedRisks());

            if (log.isDebugEnabled()) {
                log.debug("Request details: agreementDateFrom={}, agreementDateTo={}",
                        request.agreementDateFrom(), request.agreementDateTo());
            }

            List<ValidationError> errors = requestValidator.validate(request);

            if (!errors.isEmpty()) {
                log.info("Premium calculation rejected by validation: errorsCount={}", errors.size());

                return buildErrorResponse(errors);
            }

            SummaryRisks risks = premiumUnderwriting.calculatePremium(request);

            log.info("Premium calculation finished successfully");

            if (log.isDebugEnabled()) {
                log.debug("Calculated premium={}", risks.premium());
            }

            return buildSuccessResponse(request, risks);
        } finally {
            long ms = sw.elapsed().toMillis();
            log.info("calculatePremium finished in {} ms", ms);

            if (ms > SLOW_THRESHOLD_MS)
                log.warn("calculatePremium finished slow: {} ms (threshold={} ms)",
                        ms, SLOW_THRESHOLD_MS);
        }
    }

    private TravelCalculatePremiumResponse buildErrorResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }

    private TravelCalculatePremiumResponse buildSuccessResponse(TravelCalculatePremiumRequest request, SummaryRisks risks) {
        return new TravelCalculatePremiumResponse(request.personFirstName(), request.personLastName(),
                request.agreementDateFrom(), request.agreementDateTo(), request.country(),
                risks.premium(), risks.risks(), request.personBirthDate(), request.medicalRiskLimitLevel());
    }

}
