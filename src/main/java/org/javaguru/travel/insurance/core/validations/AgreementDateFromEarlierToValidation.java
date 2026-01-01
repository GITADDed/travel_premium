package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class AgreementDateFromEarlierToValidation implements RequestValidation {
    private static final Logger log = LoggerFactory.getLogger(AgreementDateFromEarlierToValidation.class);

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        boolean invalid = request.getAgreementDateFrom() != null && request.getAgreementDateTo() != null && (request.getAgreementDateFrom().equals(request.getAgreementDateTo()) || request.getAgreementDateFrom().after(request.getAgreementDateTo()));

        if (invalid) {
            log.debug("Validation failed: field=agreementDateFrom, reason=before agreementDateTo {} < {}", request.getAgreementDateFrom(), request.getAgreementDateTo());

            return Optional.of(new ValidationError("agreementDateFrom", "Must be earlier than agreementDateTo!"));
        }

        return Optional.empty();
    }
}
