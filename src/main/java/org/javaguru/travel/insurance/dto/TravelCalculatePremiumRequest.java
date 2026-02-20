package org.javaguru.travel.insurance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Set;

public record TravelCalculatePremiumRequest(
        String personFirstName,
        String personLastName,
        Date agreementDateFrom,
        Date agreementDateTo,

        @NotNull
        @NotEmpty
        @JsonProperty("selected_risks")
        Set<@NotBlank String> selectedRisks) {
}


