package org.javaguru.travel.insurance.core;

import org.javaguru.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.javaguru.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TravelCalculatePremiumServiceImplAITest {

    @Test
    @DisplayName("Checks that the first name is returned correctly")
    void testCalculatePremium_personFirstName() {
        Date from = new Date(1700000000000L);
        Date to = new Date(1700003600000L);
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Ivan");
        request.setAgreementDateFrom(from);
        request.setAgreementDateTo(to);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals("Ivan", response.getPersonFirstName());
    }

    @Test
    @DisplayName("Checks that the last name is returned correctly")
    void testCalculatePremium_personLastName() {
        Date from = new Date(1700000000000L);
        Date to = new Date(1700003600000L);
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();

        request.setPersonLastName("Petrov");
        request.setAgreementDateFrom(from);
        request.setAgreementDateTo(to);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals("Petrov", response.getPersonLastName());
    }

    @Test
    @DisplayName("Checks that the agreement start date is returned correctly")
    void testCalculatePremium_agreementDateFrom() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        Date from = new Date(1700000000000L);
        Date to = new Date(1700003600000L);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(from);
        request.setAgreementDateTo(to);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(from, response.getAgreementDateFrom());
    }

    @Test
    @DisplayName("Checks that the agreement end date is returned correctly")
    void testCalculatePremium_agreementDateTo() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        Date from = new Date(1700000000000L);
        Date to = new Date(1700003600000L);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(from);
        request.setAgreementDateTo(to);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals(to, response.getAgreementDateTo());
    }

    @Test
    @DisplayName("Allows empty first name without exception")
    void testCalculatePremium_emptyPersonFirstName() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("");
        request.setPersonLastName("Ivanov");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());
        assertDoesNotThrow(() -> service.calculatePremium(request));
    }

    @Test
    @DisplayName("Allows empty last name without exception")
    void testCalculatePremium_emptyPersonLastName() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Ivan");
        request.setPersonLastName("");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(new Date());
        assertDoesNotThrow(() -> service.calculatePremium(request));
    }

    @Test
    @DisplayName("Throws IllegalArgumentException when agreement start date is null")
    void testCalculatePremium_nullAgreementDateFrom() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Ivan");
        request.setPersonLastName("Ivanov");
        request.setAgreementDateFrom(null);
        request.setAgreementDateTo(new Date());
        assertThrows(IllegalArgumentException.class, () -> service.calculatePremium(request));
    }

    @Test
    @DisplayName("Throws IllegalArgumentException when agreement end date is null")
    void testCalculatePremium_nullAgreementDateTo() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Ivan");
        request.setPersonLastName("Ivanov");
        request.setAgreementDateFrom(new Date());
        request.setAgreementDateTo(null);
        assertThrows(IllegalArgumentException.class, () -> service.calculatePremium(request));
    }

    @Test
    @DisplayName("Throws IllegalArgumentException when both agreement dates are null")
    void testCalculatePremium_agreementPriceNullDates() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Ivan");
        request.setPersonLastName("Ivanov");
        request.setAgreementDateFrom(null);
        request.setAgreementDateTo(null);
        assertThrows(IllegalArgumentException.class, () -> service.calculatePremium(request));
    }

    @Test
    @DisplayName("Checks that agreement price is calculated correctly for 5 days interval")
    void testCalculatePremium_agreementPriceFiveDays() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        Date from = new Date(1700000000000L); // 2023-11-14
        Date to = new Date(1700432000000L);  // 2023-11-19
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(from);
        request.setAgreementDateTo(to);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals("5", response.getAgreementPrice().toPlainString());
    }

    @Test
    @DisplayName("Checks that agreement price is zero when dates are equal")
    void testCalculatePremium_agreementPriceZeroDays() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        Date same = new Date(1700000000000L);
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(same);
        request.setAgreementDateTo(same);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals("0", response.getAgreementPrice().toPlainString());
    }

    @Test
    @DisplayName("Checks that agreement price is negative when from is after to")
    void testCalculatePremium_agreementPriceNegativeDays() {
        TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl();
        Date from = new Date(1700432000000L); // 2023-11-19
        Date to = new Date(1700000000000L);  // 2023-11-14
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(from);
        request.setAgreementDateTo(to);
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        assertEquals("-5", response.getAgreementPrice().toPlainString());
    }
}
