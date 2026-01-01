package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class AgreementDateFromValidation implements RequestValidation {
    private static final Logger log = LoggerFactory.getLogger(AgreementDateFromValidation.class);

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        boolean invalid = request.getAgreementDateFrom() == null;

        if (invalid) {
            log.debug("Validation failed: field=agreementDateFrom, reason=null");
            return Optional.of(new ValidationError("agreementDateFrom", "Must not be empty!"));
        }

        return Optional.empty();
    }
}
