INSERT INTO patient (name, gender, birth_date, email, blood_group)
VALUES
    ('Smriti Dube','FEMALE', '2004-01-21','smriti@gmail.com','B_POSITIVE'),
    ('Abhinav Rajhans','MALE', '2002-02-01','abhinav@gmail.com','B_POSITIVE'),
    ('Sadhana Dube','FEMALE', '1980-11-03','sadhana@gmail.com','O_POSITIVE'),
    ('Radhika Rajhans','FEMALE', '1977-12-12','radhika@gmail.com','A_POSITIVE'),
    ('Abhiniti Rajhans','FEMALE', '2023-08-19','abhiniti@gmail.com','A_POSITIVE');

INSERT INTO doctor (name, specialization,email)
VALUES
    ('Dr. Pramod Dube', 'Cardiology','pramod@gmail.com'),
    ('Dr. Smriti Rajhans', 'Dermatology','smritirajhans@gmail.com'),
    ('Dr. Abhinav Dube', 'Neurologist','abhinavdube@gmail.com'),
    ('Dr. Balkrishna Rajhans', 'Orthopedic','balkrishna@gmail.com')

INSERT INTO appointment(appointment_time,reason,doctor_id,patient_id)
VALUES
    ('2025-07-21 10:30:00','General Checkup',1,2),
    ('2025-07-22 11:00:00','Skin Pigmentation',2,3),
    ('2025-07-22 11:30:00','Mad about daughters love marriage',3,3),
    ('2025-07-22 12:30:00','Knee pain connected to heart relieved from Doctor attention',4,4),
    ('2025-07-23 10:30:00','Heart ache for not much attention from husband',1,3),