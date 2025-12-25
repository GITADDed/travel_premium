package org.javaguru.travel.insurance.core;

import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.validations.RequestValidator;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Component
class TravelCalculatePremiumRequestValidator {

    private final List<RequestValidator> requestValidators;

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {

        return requestValidators.stream()
                .map(v -> v.validate(request))
                .flatMap(Optional::stream)
                .toList();
    }
}