package org.javaguru.travel.insurance.core.validations;

import org.slf4j.Logger;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Component
class TravelCalculatePremiumRequestValidator implements RequestValidator {
    private static final Logger log = LoggerFactory.getLogger(TravelCalculatePremiumRequestValidator.class);

    private final List<RequestValidation> requestValidators;

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        log.info("Validate TravelCalculatePremiumRequest started");

        if (log.isDebugEnabled()) {
            log.debug("Validation input: agreementDateFrom={}, agreementDateTo={}, firstNamePresent={}," +
                            "lastNamePresent={}",
                    request.agreementDateFrom(),
                    request.agreementDateTo(),
                    request.personFirstName() != null && !request.personFirstName().isBlank(),
                    request.personLastName() != null && !request.personLastName().isBlank());
        }

        List<ValidationError> errors = requestValidators.stream()
                .map(v -> v.validate(request))
                .flatMap(Optional::stream)
                .toList();

        log.info("Validation finished: errors count : {}", errors.size());

        if (log.isDebugEnabled() && !errors.isEmpty()) {
            errors.forEach(e -> log.debug("Validation error: field={}, message={}",
                    e.getErrorCode(), e.getDescription()));
        }

        return errors;
    }
}