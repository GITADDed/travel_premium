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
