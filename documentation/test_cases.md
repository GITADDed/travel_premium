# Test cases

| # | Scenario                                          | Input                 | Expected                                                       | Output                 |
|---|---------------------------------------------------|-----------------------|----------------------------------------------------------------|------------------------|
| 1 | firstName not specified                           | [example](#1-request) | error with message about firstName is empty                    | [example](#1-response) |
| 2 | lastName not specified                            | [example](#2-request) | error with message about lastName is empty                     | [example](#2-response) |
| 3 | agreementDateFrom not specified                   | [example](#3-request) | error with message about agreementDateFrom is empty            | [example](#3-response) |
| 4 | agreementDateTo not specified                     | [example](#4-request) | error with message about agreementDateTo is empty              | [example](#4-response) |
| 5 | all fields not specified                          | [example](#5-request) | error with message about each field is empty                   | [example](#5-response) |
| 6 | agreementDateTo < agreementDateFrom not specified | [example](#6-request) | error with message agreementDateTo less than agreementDateFrom | [example](#6-response) |
| 7 | all fields specified                              | [example](#7-request) | without errors                                                 | [example](#7-response) |

## Requests

### 1 request

[response](#1-response)

~~~ json
{
  "personFirstName" : null,
  "personLastName" : "Pupkin",
  "agreementDateFrom" : "2021-05-25",
  "agreementDateTo" : "2021-05-29"
}
~~~

### 2 request

[response](#2-response)

~~~ json
{
  "personFirstName" : "Vasya",
  "personLastName" : null,
  "agreementDateFrom" : "2021-05-25",
  "agreementDateTo" : "2021-05-29"
}
~~~

### 3 request

[response](#3-response)

~~~ json
{
  "personFirstName" : "Vasya",
  "personLastName" : "Pupkin",
  "agreementDateFrom" : null,
  "agreementDateTo" : "2021-05-29"
}
~~~

### 4 request

[response](#4-response)

~~~ json
{
  "personFirstName" : "Vasya",
  "personLastName" : "Pupkin",
  "agreementDateFrom" : "2021-05-25",
  "agreementDateTo" : null
}
~~~

### 5 request

[response](#5-response)

~~~ json
{
  "personFirstName" : null,
  "personLastName" : null,
  "agreementDateFrom" : null,
  "agreementDateTo" : null
}
~~~

### 6 request

[response](#6-response)

~~~ json
{
  "personFirstName" : "Vasya",
  "personLastName" : "Pupkin",
  "agreementDateFrom" : "2021-06-25",
  "agreementDateTo" : "2021-05-29"
}
~~~

### 7 request

[response](#7-response)

~~~ json
{
  "personFirstName" : "Vasya",
  "personLastName" : "Pupkin",
  "agreementDateFrom" : "2021-05-25",
  "agreementDateTo" : "2021-05-29"
}
~~~

---

## Responses

### 1 response

[request](#1-request)

~~~ json
{
  "errors": [
    {
      "field": "personFirstName",
      "message": "Must not be empty!"
    }
  ],
  "personFirstName": null,
  "personLastName": null,
  "agreementDateFrom": null,
  "agreementDateTo": null,
  "agreementPrice": null
}
~~~

### 2 response

[request](#2-request)

~~~ json
{
  "errors": [
    {
      "field": "personLastName",
      "message": "Must not be empty!"
    }
  ],
  "personFirstName": null,
  "personLastName": null,
  "agreementDateFrom": null,
  "agreementDateTo": null,
  "agreementPrice": null
}
~~~

### 3 response

[request](#3-request)

~~~ json
{
  "errors": [
    {
      "field": "agreementDateFrom",
      "message": "Must not be empty!"
    }
  ],
  "personFirstName": null,
  "personLastName": null,
  "agreementDateFrom": null,
  "agreementDateTo": null,
  "agreementPrice": null
}
~~~

### 4 response

[request](#4-request)

~~~ json
{
  "errors": [
    {
      "field": "agreementDateTo",
      "message": "Must not be empty!"
    }
  ],
  "personFirstName": null,
  "personLastName": null,
  "agreementDateFrom": null,
  "agreementDateTo": null,
  "agreementPrice": null
}
~~~

### 5 response

[request](#5-request)

~~~ json
{
  "errors": [
    {
      "field": "personFirstName",
      "message": "Must not be empty!"
    },
    {
      "field": "personLastName",
      "message": "Must not be empty!"
    },
    {
      "field": "agreementDateFrom",
      "message": "Must not be empty!"
    },
    {
      "field": "agreementDateTo",
      "message": "Must not be empty!"
    }  
  ],
  "personFirstName": null,
  "personLastName": null,
  "agreementDateFrom": null,
  "agreementDateTo": null,
  "agreementPrice": null
}
~~~

### 6 response

[request](#6-request)

~~~ json
{
  "errors": [
    {
      "field": "agreementDateFrom",
      "message": "Must be earlier than agreementDateTo!"
    }
  ],
  "personFirstName": null,
  "personLastName": null,
  "agreementDateFrom": null,
  "agreementDateTo": null,
  "agreementPrice": null
}
~~~

### 7 response

[request](#7-request)

~~~ json
{
  "personFirstName" : "Vasya",
  "personLastName" : "Pupkin",
  "agreementDateFrom" : "2021-05-25",
  "agreementDateTo" : "2021-05-29",
  "agreementPrice" : 4
}
~~~
