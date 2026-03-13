package org.javaguru.travel.insurance.rest;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonFileReader jsonFileReader;

    @ParameterizedTest(name = "Test for 200 ok response case #{0}")
    @MethodSource("org.javaguru.travel.insurance.rest.TestCaseProvider#okCaseNumbers")
    public void shouldReturnExpectedResponseTest(int caseNo) throws Exception {
        String requestJson = jsonFileReader.readJsonFromFile("rest/ok/requests/request" + caseNo + ".json");
        String actualJson = mockMvc.perform(post("/insurance/travel/api/")
                        .content(requestJson)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expectedJson = jsonFileReader.readJsonFromFile("rest/ok/responses/response" + caseNo + ".json");
        JSONAssert.assertEquals(expectedJson, actualJson, JSONCompareMode.LENIENT);

    }

    @ParameterizedTest(name = "Test for 400 bad_request response case #{0}")
    @MethodSource("org.javaguru.travel.insurance.rest.TestCaseProvider#badRequestCaseNumbers")
    void shouldReturnBadRequestWhen(int caseNo) throws Exception {
        String requestJson = jsonFileReader.readJsonFromFile("rest/bad_request/requests/request" + caseNo + ".json");

        String actualJson = mockMvc.perform(post("/insurance/travel/api/")
                        .content(requestJson)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expectedJson = jsonFileReader.readJsonFromFile("rest/bad_request/responses/response" + caseNo + ".json");
        JSONAssert.assertEquals(expectedJson, actualJson, JSONCompareMode.LENIENT);
    }
}
