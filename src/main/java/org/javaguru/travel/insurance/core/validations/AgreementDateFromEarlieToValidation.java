package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class AgreementDateFromEarlieToValidation implements RequestValidation {
    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return ( request.getAgreementDateFrom() != null && request.getAgreementDateTo() != null && (request.getAgreementDateFrom().equals(request.getAgreementDateTo()) || request.getAgreementDateFrom().after(request.getAgreementDateTo())))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be earlier than agreementDateTo!"))
                : Optional.empty();
    }
}
