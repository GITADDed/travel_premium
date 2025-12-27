package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
class AgreementDateFromLaterNowValidation implements RequestValidation {
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        Date now = new Date();

        if (request.getAgreementDateFrom() == null) {
            return Optional.empty();
        }

        return request.getAgreementDateFrom().after(now) ? Optional.empty()
                : Optional.of(new ValidationError("agreementDateFrom", "Must be later than now!"));
    }
}
