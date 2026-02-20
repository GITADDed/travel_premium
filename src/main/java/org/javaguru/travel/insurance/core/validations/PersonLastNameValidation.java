package org.javaguru.travel.insurance.core.validations;

import lombok.Getter;
import org.javaguru.travel.insurance.core.ValidationErrorFactory;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class PersonLastNameValidation extends AbstractRequestValidation {
    private static final Logger log = LoggerFactory.getLogger(PersonLastNameValidation.class);

    @Getter
    private final String code = "ERROR_CODE_2";

    public PersonLastNameValidation(ValidationErrorFactory validationErrorFactory) {
        super(validationErrorFactory);
    }

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        boolean invalid = request.personLastName() == null || request.personLastName().isBlank();

        return super.getErrorOrEmpty(invalid, code, log, request.personLastName());
    }
}
