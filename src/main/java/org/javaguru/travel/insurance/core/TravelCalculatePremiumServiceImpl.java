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

    private final DateTimeService dateTimeService;
    private final TravelCalculatePremiumRequestValidator requestValidator;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {

        List<ValidationError> errors = requestValidator.validate(request);
        BigDecimal price = dateTimeService.calculateDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo());

        return errors.isEmpty() ? buildSuccessResponse(request, price) : buildErrorResponse(errors);
    }

    private TravelCalculatePremiumResponse buildErrorResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }

    private TravelCalculatePremiumResponse buildSuccessResponse(TravelCalculatePremiumRequest request, BigDecimal price) {
        return new TravelCalculatePremiumResponse(request.getPersonFirstName(), request.getPersonLastName(), request.getAgreementDateFrom(), request.getAgreementDateTo(), price);
    }

}
