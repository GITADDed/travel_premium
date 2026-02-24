package org.javaguru.travel.insurance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public record TravelCalculatePremiumRequest(
        String personFirstName,
        String personLastName,
        Date agreementDateFrom,
        Date agreementDateTo,
        @JsonProperty("selected_risks")
        Set<String> selectedRisks,
        String country,
        LocalDate personBirthDate,
        String medicalRiskLimitLevel) {
}


