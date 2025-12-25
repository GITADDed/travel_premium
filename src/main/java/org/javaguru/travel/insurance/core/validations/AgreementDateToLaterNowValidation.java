package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class AgreementDateToLaterNowValidation implements RequestValidator {
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date now = new Date();

        if (request.getAgreementDateTo() == null) {
            return Optional.empty();
        }

        return request.getAgreementDateTo().after(now) ? Optional.empty()
                : Optional.of(new ValidationError("agreementDateTo", "Must be later than now!"));
    }
}
