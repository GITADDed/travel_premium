package org.javaguru.travel.insurance.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TravelCalculatePremiumResponseLogger {

    private final Logger log = LoggerFactory.getLogger(TravelCalculatePremiumResponseLogger.class);
    private final ObjectMapper objectMapper;

    public void logResponse(TravelCalculatePremiumResponse response) {
        log.info("RESPONSE: {}", toJson(response));
    }

    String toJson(TravelCalculatePremiumResponse response) {
        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            log.warn("RESPONSE: ", e);
            return "[failed to serialize TravelCalculatePremiumResponse]";
        }
    }
}

