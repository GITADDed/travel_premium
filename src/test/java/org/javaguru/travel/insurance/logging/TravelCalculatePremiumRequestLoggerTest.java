package org.javaguru.travel.insurance.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaguru.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@JsonTest
class TravelCalculatePremiumRequestLoggerTest {

    TravelCalculatePremiumRequestLogger logger;
    TravelCalculatePremiumRequest request;
    final Date from = new Date(1700000000000L);
    final Date to = new Date(1700003600000L);
    @Autowired ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        logger = new TravelCalculatePremiumRequestLogger(mapper);
        request = new TravelCalculatePremiumRequest("Vasja", "Pupkin", from, to, null);
    }

    @Test
    void shouldReturnCorrectJson() {
        String json = logger.toJson(request);
        assertThat(json).contains("\"personFirstName\":\"Vasja\",\"personLastName\":\"Pupkin\",\"agreementDateFrom\":\"2023-11-14\",\"agreementDateTo\":\"2023-11-14\"");
    }

    @Test
    void shouldThrowException() throws JsonProcessingException {
        ObjectMapper mockMapper = mock(ObjectMapper.class);

        when(mockMapper.writeValueAsString(any())).thenThrow(JsonProcessingException.class);

        logger = new TravelCalculatePremiumRequestLogger(mockMapper);

        String json = logger.toJson(request);

        assertThat(json).isEqualTo("[failed to serialize TravelCalculatePremiumRequest]");
        verify(mockMapper).writeValueAsString(any());
    }
}