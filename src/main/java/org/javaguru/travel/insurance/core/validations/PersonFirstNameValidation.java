package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class PersonFirstNameValidation implements RequestValidation {
    private static final Logger log = LoggerFactory.getLogger(PersonFirstNameValidation.class);

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        boolean invalid = request.getPersonFirstName() == null || request.getPersonFirstName().isEmpty();

        if (invalid) {
            log.debug("Validation failed: field=personFirstName, reason=blank");
            return Optional.of(new ValidationError("personFirstName", "Must not be empty!"));
        }

        return Optional.empty();
    }
}
