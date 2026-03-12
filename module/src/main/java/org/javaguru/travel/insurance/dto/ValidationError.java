package org.javaguru.travel.insurance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ValidationError (
    @JsonProperty("error_code")
    String errorCode,
    String description
) {}