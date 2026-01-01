package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class AgreementDateToValidation implements RequestValidation {
    private static final Logger log = LoggerFactory.getLogger(AgreementDateToValidation.class);

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        boolean invalid = request.getAgreementDateTo() == null;

        if (invalid) {
            log.debug("Validation failed: field=agreementDateTo, reason=null");
            return Optional.of(new ValidationError("agreementDateTo", "Must not be empty!"));
        }

        return Optional.empty();
    }
}
