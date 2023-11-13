INSERT INTO patient(id, firstname, lastname, gender, date_of_birth, creation_date, modification_date)
VALUES ('5ec7f26e-7371-42c2-936b-9fd0b2f9610f', 'Sergiu', 'Cartoafa', 'MALE',
        '2000-11-08', '2023-11-06 12:34:56', '2023-11-06 12:34:56');

INSERT INTO medication(id, dosage, time, description, unit, creation_date, modification_date, patient_id)
VALUES ('6b38f598-1741-4900-a1b2-63b246073e36', 18.4, '20:30:45', 'medication',
        'GRAMS', '2023-11-05 12:34:56', '2023-11-05 12:34:56',
        '5ec7f26e-7371-42c2-936b-9fd0b2f9610f'),
       ('e4999a07-4f0d-42ee-861b-cc7f792ce3a6', 12.4, '10:30:45', 'good',
        'GRAMS', '2023-11-06 12:34:56', '2023-11-06 12:34:56',
        '5ec7f26e-7371-42c2-936b-9fd0b2f9610f');

