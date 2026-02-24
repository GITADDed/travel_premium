package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.utils.ValidationErrorFactory;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonBirthDayNotNullRequestValidation extends AbstractRequestValidation {
    private final Logger log = LoggerFactory.getLogger(PersonBirthDayNotNullRequestValidation.class);
    private final String code = "ERROR_CODE_12";

    public PersonBirthDayNotNullRequestValidation(ValidationErrorFactory validationErrorFactory) {
        super(validationErrorFactory);
    }

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        boolean invalid = request.personBirthDate() == null;
        return super.getErrorOrEmpty(invalid, code, log, request.personBirthDate());
    }
}
