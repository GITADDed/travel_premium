package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.utils.ValidationErrorFactory;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CountryNotNullRequestValidation extends AbstractRequestValidation {
    private final Logger log = LoggerFactory.getLogger(CountryNotNullRequestValidation.class);
    private final String code = "ERROR_CODE_10";

    public CountryNotNullRequestValidation(ValidationErrorFactory validationErrorFactory) {
        super(validationErrorFactory);
    }

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        if (request.selectedRisks() == null || !request.selectedRisks().contains("TRAVEL_MEDICAL")) {
            return Optional.empty();
        }

        boolean invalid = request.country() == null || request.country().isBlank();

        return getErrorOrEmpty(invalid, code, log, request.country());
    }
}
