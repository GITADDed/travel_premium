package org.javaguru.travel.insurance.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.javaguru.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final TravelCalculatePremiumRequestValidator requestValidator;
    private final TravelPremiumUnderwriting premiumUnderwriting;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {

        List<ValidationError> errors = requestValidator.validate(request);

        return errors.isEmpty() ? buildSuccessResponse(request, premiumUnderwriting.calculatePremium(request)) : buildErrorResponse(errors);
    }

    private TravelCalculatePremiumResponse buildErrorResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }

    private TravelCalculatePremiumResponse buildSuccessResponse(TravelCalculatePremiumRequest request, BigDecimal price) {
        return new TravelCalculatePremiumResponse(request.getPersonFirstName(), request.getPersonLastName(), request.getAgreementDateFrom(), request.getAgreementDateTo(), price);
    }

}
