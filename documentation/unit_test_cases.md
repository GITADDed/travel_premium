# Unit Test Scenarios

## class core.DateTimeService

### Method: convertToLocalDate

| # | Scenario                         | Input        | Mocks      | Expected                                        | Notes         |
|---|----------------------------------|--------------|------------|-------------------------------------------------|---------------|
| 1 | Convert valid Date to LocaleDate | Date correct | Not called | LocaleDate result correct and equate input date | Basic success |

                        
---

### Method: calculateDaysBetween

| # | Scenario                     | Input                        | Mocks      | Expected                  | Notes           |
|---|------------------------------|------------------------------|------------|---------------------------|-----------------|
| 1 | Calculate days between OK    | Date dateFrom before  dateTo | Not called | BigDecimal result correct | Basic success   |
| 2 | Calculate days between ERROR | Date dateFrom after dateTo   | Not called | IlligalArgumentException  | Throw exception |

---

## class core.TravelCalculatePremiumRequestValidator

### Method: validatePersonFirstName

| # | Scenario                        | Input                                                            | Mocks      | Expected                                | Notes                     |
|---|---------------------------------|------------------------------------------------------------------|------------|-----------------------------------------|---------------------------|
| 1 | Validate first name OK          | TravelCalculatePremiumRequest correct request                    | Not called | Optional result empty                   | Basic success             |
| 2 | Validate first name NULL ERROR  | TravelCalculatePremiumReques request.getPersonFirstName is NULL  | Not called | Optional result contain ValidationError | Return error with message |
| 3 | Validate first name EMPTY ERROR | TravelCalculatePremiumReques request.getPersonFirstName is EMPTY | Not called | Optional result contain ValidationError | Return error with message |

---

### Method: validatePersonLastName

| # | Scenario                       | Input                                                           | Mocks      | Expected                                | Notes                     |
|---|--------------------------------|-----------------------------------------------------------------|------------|-----------------------------------------|---------------------------|
| 1 | Validate last name OK          | TravelCalculatePremiumRequest correct request                   | Not called | Optional result empty                   | Basic success             |
| 2 | Validate last name NULL ERROR  | TravelCalculatePremiumReques request.getPersonLastName is NULL  | Not called | Optional result contain ValidationError | Return error with message |
| 3 | Validate last name EMPTY ERROR | TravelCalculatePremiumReques request.getPersonLastName is EMPTY | Not called | Optional result contain ValidationError | Return error with message |

---

### Method: validateAgreementDateFrom

| # | Scenario                       | Input                                                             | Mocks      | Expected                                | Notes                     |
|---|--------------------------------|-------------------------------------------------------------------|------------|-----------------------------------------|---------------------------|
| 1 | Validate first name OK         | TravelCalculatePremiumRequest correct request                     | Not called | Optional result empty                   | Basic success             |
| 2 | Validate first name NULL ERROR | TravelCalculatePremiumReques request.getAgreementDateFrom is NULL | Not called | Optional result contain ValidationError | Return error with message |

---

### Method: validateAgreementDateTo

| # | Scenario                       | Input                                                           | Mocks      | Expected                                | Notes                     |
|---|--------------------------------|-----------------------------------------------------------------|------------|-----------------------------------------|---------------------------|
| 1 | Validate first name OK         | TravelCalculatePremiumRequest correct request                   | Not called | Optional result empty                   | Basic success             |
| 2 | Validate first name NULL ERROR | TravelCalculatePremiumReques request.getAgreementDateTo is NULL | Not called | Optional result contain ValidationError | Return error with message |

---