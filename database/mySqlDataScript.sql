INSERT INTO classifiers(title, description)
VALUES ('RISK_TYPE', 'Risk type classifier');

INSERT INTO classifiers(title, description)
VALUES ('COUNTRY', 'Country classifier');

INSERT INTO classifiers_value(classifier_id, ic, description)
SELECT cl.id,
       v.ic,
       v.description
FROM classifiers AS cl
         JOIN (SELECT 'TRAVEL_MEDICAL' AS ic, 'Travel policy medical risk type' AS description
               UNION ALL
               SELECT 'TRAVEL_CANCELLATION', 'Travel policy trip cancellation risk type'
               UNION ALL
               SELECT 'TRAVEL_LOSS_BAGGAGE',
                      'Travel policy baggage lose risk type'
               UNION ALL
               SELECT 'TRAVEL_THIRD_PARTY_LIABILITY',
                      'Travel policy third party liability risk type'
               UNION ALL
               SELECT 'TRAVEL_EVACUATION',
                      'Travel policy evacuation risk type'
               UNION ALL
               SELECT 'TRAVEL_SPORT_ACTIVITIES',
                      'Travel policy sport activities risk type') AS v(ic, description)
WHERE cl.title = 'RISK_TYPE';

INSERT INTO classifiers_value(classifier_id, ic, description)
SELECT cl.id,
       v.ic,
       v.description
FROM classifiers AS cl
         JOIN (SELECT 'LATVIA' AS ic, 'Latvia country classifier value' AS description
               UNION ALL
               SELECT 'SPAIN', 'Spain country classifier value'
               UNION ALL
               SELECT 'JAPAN', 'Japan country classifier value') AS v
WHERE cl.title = 'COUNTRY';

INSERT INTO country_default_day_rate(day_rate, classifier_value_id)
SELECT 1.00, id
FROM classifiers_value
WHERE ic = 'LATVIA';

INSERT INTO country_default_day_rate(day_rate, classifier_value_id)
SELECT 2.50, id
FROM classifiers_value
WHERE ic = 'SPAIN';

INSERT INTO country_default_day_rate(day_rate, classifier_value_id)
SELECT 3.50, id
FROM classifiers_value
WHERE ic = 'JAPAN';

INSERT INTO age_coefficient(age_from, age_to, coefficient)
SELECT 0 AS age_from, 5 AS age_to, 1.1 AS coefficient
UNION ALL
SELECT 6, 10, 0.7
UNION ALL
SELECT 11, 17, 1.0
UNION ALL
SELECT 18, 40, 1.1
UNION ALL
SELECT 41, 65, 1.2
UNION ALL
SELECT 66, 150, 1.5;

INSERT INTO classifiers(title, description)
VALUES ('MEDICAL_RISK_LIMIT_LEVEL', 'Maximum payout amount in case of an insured event');

INSERT INTO classifiers_value(classifier_id, ic, description)
SELECT cl.id, v.ic, v.description
FROM classifiers AS cl
         JOIN (SELECT 'LEVEL_10000'                  AS ic,
                      'Maximum payout amount 10000$' AS description
               UNION ALL
               SELECT 'LEVEL_15000', 'Maximum payout amount 15000$'
               UNION ALL
               SELECT 'LEVEL_20000', 'Maximum payout amount 20000$'
               UNION ALL
               SELECT 'LEVEL_50000', 'Maximum payout amount 50000$') AS v
WHERE cl.title = 'MEDICAL_RISK_LIMIT_LEVEL';

INSERT INTO medical_risk_limit_level(medical_risk_limit_level_ic, coefficient)
SELECT ic, 1.0
FROM classifiers_value
WHERE ic = 'LEVEL_10000';

INSERT INTO medical_risk_limit_level(medical_risk_limit_level_ic, coefficient)
SELECT ic, 1.2
FROM classifiers_value
WHERE ic = 'LEVEL_15000';

INSERT INTO medical_risk_limit_level(medical_risk_limit_level_ic, coefficient)
SELECT ic, 1.5
FROM classifiers_value
WHERE ic = 'LEVEL_20000';

INSERT INTO medical_risk_limit_level(medical_risk_limit_level_ic, coefficient)
SELECT ic, 2.0
FROM classifiers_value
WHERE ic = 'LEVEL_50000';
