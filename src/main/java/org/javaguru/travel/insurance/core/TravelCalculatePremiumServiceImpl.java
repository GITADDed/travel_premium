package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final DateTimeService dateTimeService;

    public TravelCalculatePremiumServiceImpl(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {

        if (request.getAgreementDateFrom() == null || request.getAgreementDateTo() == null) {
            throw new IllegalArgumentException("Agreement dates must not be null");
        }

        BigDecimal price = dateTimeService.calculateDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo());

        return new TravelCalculatePremiumResponse(request.getPersonFirstName(), request.getPersonLastName(), request.getAgreementDateFrom(), request.getAgreementDateTo(), price);
    }

    private LocalDate convertToLocalDate(Date dateToConvert) {
        return LocalDate.ofInstant(dateToConvert.toInstant(),
                ZoneId.systemDefault());
    }

    private BigDecimal calculateDaysBetween(Date fromD, Date toD) {
        LocalDate from = convertToLocalDate(fromD);
        LocalDate to = convertToLocalDate(toD);
        return BigDecimal.valueOf(ChronoUnit.DAYS.between(from, to));
    }

}
