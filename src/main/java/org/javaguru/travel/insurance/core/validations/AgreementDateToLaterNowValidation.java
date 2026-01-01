package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class AgreementDateToLaterNowValidation implements RequestValidation {
    private static final Logger log = LoggerFactory.getLogger(AgreementDateToLaterNowValidation.class);

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date now = new Date();

        if (request.getAgreementDateTo() == null) {
            return Optional.empty();
        }

        boolean invalid = request.getAgreementDateTo().before(now);

        if (invalid) {
            log.debug("Validation failed: field=agreementDateTo, reason=before now {}", now);
            return Optional.of(new ValidationError("agreementDateTo", "Must be later than now!"));
        }

        return Optional.empty();
    }
}
