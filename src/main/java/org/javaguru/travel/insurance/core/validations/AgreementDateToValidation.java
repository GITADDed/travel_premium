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
class AgreementDateToValidation extends AbstractRequestValidation {
    private static final Logger log = LoggerFactory.getLogger(AgreementDateToValidation.class);

    @Getter
    private final String code = "ERROR_CODE_4";

    public AgreementDateToValidation(ValidationErrorFactory validationErrorFactory) {
        super(validationErrorFactory);
    }

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        boolean invalid = request.agreementDateTo() == null;

        return super.getErrorOrEmpty(invalid, code, log, request.agreementDateTo());
    }
}
