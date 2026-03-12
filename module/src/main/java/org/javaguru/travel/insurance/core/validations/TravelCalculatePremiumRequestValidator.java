package org.javaguru.travel.insurance.core.validations;

import org.slf4j.Logger;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Component
class TravelCalculatePremiumRequestValidator implements RequestValidator {
    private static final Logger log = LoggerFactory.getLogger(TravelCalculatePremiumRequestValidator.class);

    private final List<RequestValidation> requestValidations;

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

        List<ValidationError> errorsFromSingle = getErrorsFromSingleErrorValidation(request);

        List<ValidationError> errorsFromList = getErrorsFromListErrorValidation(request);

        List<ValidationError> errors = concatList(errorsFromSingle, errorsFromList);

        log.info("Validation finished: errors count single : {}, list: {}, total: {}", errorsFromSingle.size(),
                errorsFromList.size(), errors.size());

        if (log.isDebugEnabled() && !errors.isEmpty()) {
            errors.forEach(e -> log.debug("Validation error: field={}, message={}",
                    e.errorCode(), e.description()));
        }

        return errors;
    }

    List<ValidationError> concatList(List<ValidationError> list1, List<ValidationError> list2) {
        List<ValidationError> errors = new ArrayList<>(list1);
        errors.addAll(list2);
        return errors;
    }

    private List<ValidationError> getErrorsFromListErrorValidation(TravelCalculatePremiumRequest request) {
        log.info("Validate with rules which return List of errors started");

        return requestValidations.stream()
                .map(v -> v.validateList(request))
                .flatMap(List::stream)
                .toList();
    }

    private List<ValidationError> getErrorsFromSingleErrorValidation(TravelCalculatePremiumRequest request) {
        log.info("Validate with rules which return Single errors started");

        return requestValidations.stream()
                .map(v -> v.validate(request))
                .flatMap(Optional::stream)
                .toList();
    }
}