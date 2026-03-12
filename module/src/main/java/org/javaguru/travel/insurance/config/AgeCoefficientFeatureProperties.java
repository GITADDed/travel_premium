package org.javaguru.travel.insurance.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "medical.age.coefficient")
public class AgeCoefficientFeatureProperties {
    private Boolean enabled;
}
