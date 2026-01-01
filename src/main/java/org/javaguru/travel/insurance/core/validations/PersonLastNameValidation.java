package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class PersonLastNameValidation implements RequestValidation {
    private static final Logger log = LoggerFactory.getLogger(PersonLastNameValidation.class);

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        boolean invalid = request.getPersonLastName() == null || request.getPersonLastName().isEmpty();

        if (invalid) {
            log.debug("Validation failed: field=personLastName, reason=blank");
            return Optional.of(new ValidationError("personLastName", "Must not be empty!"));
        }

        return Optional.empty();
    }
}
