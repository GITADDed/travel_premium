package org.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final JsonFileReader jsonFileReader = new JsonFileReader();

    @ParameterizedTest(name = "case #{0}")
    @MethodSource("cases")
    public void shouldNullPersonFirstNameThanReturnErrorResponseWithMessageTest() throws Exception {
        String requestJson = jsonFileReader.readJsonFromFile("rest/requests/request1.json");
        String actualJson = mockMvc.perform(post("/insurance/travel/")
//                        .content("{" +
//                                "\"personFirstName\" : null,\n" +
//                                "\"personLastName\" : \"Pupkin\",\n" +
//                                "\"agreementDateFrom\" : \"2021-05-25\",\n" +
//                                "\"agreementDateTo\" : \"2021-05-29\"\n" +
//                                "}")
                        .content(requestJson)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
//                .andExpect(jsonPath(("errors[0]"),
//                        allOf(
//                                hasKey("field"),
//                                hasKey("message"),
//                                hasEntry("field", "personFirstName"),
//                                hasEntry("message", "Must not be empty!")
//                        )))
//                .andExpect(jsonPath("personFirstName", is(nullValue())))
//                .andExpect(jsonPath("personLastName", is(nullValue())))
//                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
//                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
//                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn().getResponse().getContentAsString();

        String expectedJson = jsonFileReader.readJsonFromFile("rest/responses/response1.json");
        JSONAssert.assertEquals(expectedJson, actualJson, JSONCompareMode.STRICT);

    }

    @Test
    public void shouldNullLastNameThanReturnErrorResponseWithMessageTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Vasya\",\n" +
                                "\"personLastName\" : null,\n" +
                                "\"agreementDateFrom\" : \"2021-05-25\",\n" +
                                "\"agreementDateTo\" : \"2021-05-29\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath(("errors[0]"),
                        allOf(
                                hasKey("field"),
                                hasKey("message"),
                                hasEntry("field", "personLastName"),
                                hasEntry("message", "Must not be empty!")
                        )))
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn();
    }

    @Test
    public void shouldNullAgreementDateFromThanReturnErrorResponseWithMessageTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Vasya\",\n" +
                                "\"personLastName\" : \"Pupkin\",\n" +
                                "\"agreementDateFrom\" : null,\n" +
                                "\"agreementDateTo\" : \"2021-05-29\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath(("errors[0]"),
                        allOf(
                                hasKey("field"),
                                hasKey("message"),
                                hasEntry("field", "agreementDateFrom"),
                                hasEntry("message", "Must not be empty!")
                        )))
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn();
    }

    @Test
    public void shouldNullAgreementDateToThanReturnErrorResponseWithMessageTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Vasya\",\n" +
                                "\"personLastName\" : \"Pupkin\",\n" +
                                "\"agreementDateFrom\" : \"2021-05-25\",\n" +
                                "\"agreementDateTo\" : null\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath(("errors[0]"),
                        allOf(
                                hasKey("field"),
                                hasKey("message"),
                                hasEntry("field", "agreementDateTo"),
                                hasEntry("message", "Must not be empty!")
                        )))
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn();
    }

    @Test
    public void shouldNullAllFieldsThanReturnErrorResponseWithMessageTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : null,\n" +
                                "\"personLastName\" : null,\n" +
                                "\"agreementDateFrom\" : null,\n" +
                                "\"agreementDateTo\" : null\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath(("errors[*].field"),
                        containsInAnyOrder("personFirstName",
                                "personLastName",
                                "agreementDateFrom",
                                "agreementDateTo")))
                .andExpect(jsonPath(("errors[*].message"),
                        everyItem(is("Must not be empty!"))))
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn();
    }

    @Test
    public void shouldAgreementDateFromLaterAgreementDateToThanReturnErrorResponseWithMessageTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Vasya\",\n" +
                                "\"personLastName\" : \"Pupkin\",\n" +
                                "\"agreementDateFrom\" : \"2021-06-25\",\n" +
                                "\"agreementDateTo\" : \"2021-05-29\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath(("errors[0]"),
                        allOf(
                                hasKey("field"),
                                hasKey("message"),
                                hasEntry("field", "agreementDateFrom"),
                                hasEntry("message", "Must be earlier than agreementDateTo!")
                        )))
                .andExpect(jsonPath("personFirstName", is(nullValue())))
                .andExpect(jsonPath("personLastName", is(nullValue())))
                .andExpect(jsonPath("agreementDateFrom", is(nullValue())))
                .andExpect(jsonPath("agreementDateTo", is(nullValue())))
                .andExpect(jsonPath("agreementPrice", is(nullValue())))
                .andReturn();
    }

    @Test
    public void shouldCorrectRequestThanReturnCorrectResponseTest() throws Exception {
        mockMvc.perform(post("/insurance/travel/")
                        .content("{" +
                                "\"personFirstName\" : \"Vasya\",\n" +
                                "\"personLastName\" : \"Pupkin\",\n" +
                                "\"agreementDateFrom\" : \"2021-05-25\",\n" +
                                "\"agreementDateTo\" : \"2021-05-29\"\n" +
                                "}")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("personFirstName", is("Vasya")))
                .andExpect(jsonPath("personLastName", is("Pupkin")))
                .andExpect(jsonPath("agreementDateFrom", is("2021-05-25")))
                .andExpect(jsonPath("agreementDateTo", is("2021-05-29")))
                .andExpect(jsonPath("agreementPrice", is(4)))
                .andReturn();
    }

    private static Stream<Integer> cases() {
        return Stream.of(1, 2, 3, 4, 5, 6, 7);
    }

}
