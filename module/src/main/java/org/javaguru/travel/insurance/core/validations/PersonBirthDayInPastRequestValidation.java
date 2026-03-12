package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.utils.ValidationErrorFactory;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class PersonBirthDayInPastRequestValidation extends AbstractRequestValidation {
    private final Logger log = LoggerFactory.getLogger(PersonBirthDayInPastRequestValidation.class);
    private final String code = "ERROR_CODE_13";

    public PersonBirthDayInPastRequestValidation(ValidationErrorFactory validationErrorFactory) {
        super(validationErrorFactory);
    }

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        if (request.personBirthDate() == null)
            return Optional.empty();

        LocalDate currentDate = LocalDate.now();

        boolean invalid = currentDate.isBefore(request.personBirthDate());

        return super.getErrorOrEmpty(invalid, code, log, currentDate);
    }
}
