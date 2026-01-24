package org.javaguru.travel.insurance.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.math.BigDecimal;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@JsonTest
class TravelCalculatePremiumResponseLoggerTest {
    TravelCalculatePremiumResponseLogger logger;
    TravelCalculatePremiumResponse response;
    final Date from = new Date(1700000000000L);
    final Date to = new Date(1700003600000L);
    @Autowired
    ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        logger = new TravelCalculatePremiumResponseLogger(mapper);
        response = new TravelCalculatePremiumResponse("Vasja", "Pupkin", from, to,  BigDecimal.ONE);
    }

    @Test
    void shouldReturnCorrectJson() {
        String json = logger.toJson(response);
        assertThat(json).contains("\"errors\":null,\"personFirstName\":\"Vasja\",\"personLastName\":\"Pupkin\",\"agreementDateFrom\":\"2023-11-14\",\"agreementDateTo\":\"2023-11-14\",\"agreementPrice\":1");
    }

    @Test
    void shouldThrowException() throws JsonProcessingException {
        ObjectMapper mockMapper = mock(ObjectMapper.class);

        when(mockMapper.writeValueAsString(any())).thenThrow(JsonProcessingException.class);

        logger = new TravelCalculatePremiumResponseLogger(mockMapper);

        String json = logger.toJson(response);

        assertThat(json).isEqualTo("[failed to serialize TravelCalculatePremiumResponse]");
        verify(mockMapper).writeValueAsString(any());
    }
}