package org.javaguru.travel.insurance.core.validations;

import testdto.TravelCalculatePremiumRequestTestDTO;

import java.util.Date;

abstract class ValidatorBasedTestClass {
    Date from;
    Date to;
    String firstName;
    String lastName;
    TravelCalculatePremiumRequestTestDTO request;

    void setUp() {
        firstName = "Ivan";
        lastName = "Ivanov";
        from = new Date(1900000000000L);
        to = new Date(1900003600000L);
        request = new TravelCalculatePremiumRequestTestDTO(firstName, lastName, from, to, null);
    }

}
