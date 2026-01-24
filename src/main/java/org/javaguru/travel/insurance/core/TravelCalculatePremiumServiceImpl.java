package org.javaguru.travel.insurance.core;

import com.google.common.base.Stopwatch;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.validations.RequestValidator;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {
    private final Logger log = LoggerFactory.getLogger(TravelCalculatePremiumServiceImpl.class);
    private static final long SLOW_THRESHOLD_MS = 300;

    private final RequestValidator requestValidator;
    private final TravelPremiumUnderwriting premiumUnderwriting;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        Stopwatch sw = Stopwatch.createStarted();

        try {

            log.info("Premium calculation started");

            if (log.isDebugEnabled()) {
                log.debug("Request details: agreementDateFrom={}, agreementDateTo={}",
                        request.getAgreementDateFrom(), request.getAgreementDateTo());
            }

            List<ValidationError> errors = requestValidator.validate(request);

            if (!errors.isEmpty()) {
                log.info("Premium calculation rejected by validation: errorsCount={}", errors.size());

                if (log.isDebugEnabled()) {
                    errors.forEach(e ->
                            log.debug("Validation error: field={}, message={}", e.getField(), e.getMessage()));
                }

                return buildErrorResponse(errors);
            }

            BigDecimal price = premiumUnderwriting.calculatePremium(request);

            log.info("Premium calculation finished successfully");

            if (log.isDebugEnabled()) {
                log.debug("Calculated premium={}", price);
            }

            return buildSuccessResponse(request, price);
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

    private TravelCalculatePremiumResponse buildSuccessResponse(TravelCalculatePremiumRequest request, BigDecimal price) {
        return new TravelCalculatePremiumResponse(request.getPersonFirstName(), request.getPersonLastName(), request.getAgreementDateFrom(), request.getAgreementDateTo(), price);
    }

}
