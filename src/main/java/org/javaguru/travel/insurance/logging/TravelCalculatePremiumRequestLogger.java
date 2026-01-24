package org.javaguru.travel.insurance.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TravelCalculatePremiumRequestLogger {

    private final Logger log = LoggerFactory.getLogger(TravelCalculatePremiumRequestLogger.class);
    private final ObjectMapper objectMapper;

    public void logRequest(TravelCalculatePremiumRequest request) {
        log.info("REQUEST: {}", toJson(request));
    }

    String toJson(TravelCalculatePremiumRequest request) {
        try {
            return objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            log.warn("REQUEST: ", e);
            return "[failed to serialize TravelCalculatePremiumRequest]";
        }
    }
}

