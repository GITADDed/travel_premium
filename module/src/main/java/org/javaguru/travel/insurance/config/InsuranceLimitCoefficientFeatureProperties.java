package org.javaguru.travel.insurance.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "medical.risk.limit.level")
public class InsuranceLimitCoefficientFeatureProperties {
    private Boolean enabled;
}
