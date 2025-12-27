package org.javaguru.travel.insurance.core.validations;

import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Component
class TravelCalculatePremiumRequestValidator implements RequestValidator {

    private final List<RequestValidation> requestValidators;

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {

        return requestValidators.stream()
                .map(v -> v.validate(request))
                .flatMap(Optional::stream)
                .toList();
    }
}