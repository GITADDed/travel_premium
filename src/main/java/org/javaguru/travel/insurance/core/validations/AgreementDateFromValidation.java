package org.javaguru.travel.insurance.core.validations;

import org.javaguru.travel.insurance.core.utils.ValidationErrorFactory;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class AgreementDateFromValidation extends AbstractRequestValidation {
    private static final Logger log = LoggerFactory.getLogger(AgreementDateFromValidation.class);

    private final String code = "ERROR_CODE_3";

    public AgreementDateFromValidation(ValidationErrorFactory validationErrorFactory) {
        super(validationErrorFactory);
    }

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {
        boolean invalid = request.agreementDateFrom() == null;

        return super.getErrorOrEmpty(invalid, code, log, request.agreementDateFrom());
    }
}
