package org.javaguru.travel.insurance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public record TravelCalculatePremiumRequest(
        String personFirstName,
        String personLastName,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date agreementDateFrom,
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        Date agreementDateTo,
        @JsonProperty("selected_risks")
        Set<String> selectedRisks,
        String country,
        LocalDate personBirthDate,
        String medicalRiskLimitLevel) {
        public TravelCalculatePremiumRequest() {
                this(null, null, null, null, null, null, null, null);
        }
}


