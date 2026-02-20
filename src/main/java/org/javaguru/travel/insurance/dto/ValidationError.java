package org.javaguru.travel.insurance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class ValidationError {
    @JsonProperty("error_code")
    private String errorCode;
    
    private String description;
}