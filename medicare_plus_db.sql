create database medicare_plus;
use medicare_plus;

CREATE TABLE Patient (
  PatientID INT PRIMARY KEY AUTO_INCREMENT,
  FullName VARCHAR(100) NOT NULL,
  NIC VARCHAR(15) UNIQUE NOT NULL,
  Gender ENUM('Male', 'Female', 'Other') NOT NULL,
  Age INT CHECK (Age > 0),
  PhoneNumber VARCHAR(15),
  Email VARCHAR(100),
  MedicalHistory TEXT,
  DateCreated DATETIME DEFAULT CURRENT_TIMESTAMP
);



CREATE TABLE Doctor (
    DoctorID INT PRIMARY KEY AUTO_INCREMENT,
    FullName VARCHAR(100) NOT NULL,
    NIC VARCHAR(20) UNIQUE NOT NULL,
    Specialization VARCHAR(50) NOT NULL,
    ContactNumber VARCHAR(15),
    Email VARCHAR(100),
    Branch VARCHAR(50) NOT NULL,
	DateCreated DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE DoctorAvailability (
    AvailabilityID INT PRIMARY KEY AUTO_INCREMENT,
    DoctorID INT NOT NULL,
    DayOfWeek ENUM('Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday') NOT NULL,
    StartTime TIME NOT NULL,
    EndTime TIME NOT NULL,
    FOREIGN KEY (DoctorID) REFERENCES Doctor(DoctorID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE DoctorSlots (
    SlotID INT PRIMARY KEY AUTO_INCREMENT,
    DoctorID INT NOT NULL,
    SlotDate DATE NOT NULL,
    StartTime TIME NOT NULL,
    EndTime TIME NOT NULL,
    IsBooked BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (DoctorID) REFERENCES Doctor(DoctorID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    UNIQUE(DoctorID, SlotDate, StartTime) -- ensures no overlapping slots
);

CREATE TABLE Appointment (
    AppointmentID INT PRIMARY KEY AUTO_INCREMENT,
    PatientID INT NOT NULL,
    SlotID INT NOT NULL,
    Status ENUM('Scheduled', 'Completed', 'Cancelled') DEFAULT 'Scheduled',
    CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    
    -- Foreign Keys
    FOREIGN KEY (PatientID) REFERENCES Patient(PatientID),
    FOREIGN KEY (SlotID) REFERENCES DoctorSlots(SlotID)
);

ALTER TABLE Appointment 
MODIFY Status ENUM('Scheduled', 'Completed', 'Cancelled', 'Delayed') DEFAULT 'Scheduled';


CREATE TABLE Notification (
    NotificationID INT PRIMARY KEY AUTO_INCREMENT,
    UserType ENUM('Doctor', 'Patient') NOT NULL,  -- who the notification is for
    UserID INT NOT NULL,                           -- references DoctorID or PatientID
    Message VARCHAR(255) NOT NULL,
    IsRead BOOLEAN DEFAULT FALSE,
    CreatedAt DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 1. Get all doctors
SELECT * FROM Doctor;

-- 2. Get all doctor availabilities
SELECT * FROM DoctorAvailability;

-- 3. Get all doctor slots
SELECT * FROM DoctorSlots;

-- 4. Get all patients
SELECT * FROM Patient;

-- 5. Get all appointments
SELECT * FROM Appointment;

-- 6. Get all notifications
SELECT * FROM Notification;


INSERT INTO Patient (FullName, NIC, Gender, Age, PhoneNumber, Email, MedicalHistory) VALUES
('Nimal Perera', '972341234V', 'Male', 48, '0712345678', 'nimal.perera@gmail.com', 'Diabetes, High blood pressure'),
('Kasun Fernando', '200133456V', 'Male', 24, '0761234567', 'kasun.fernando@yahoo.com', 'No known conditions'),
('Anushka Silva', '981234567V', 'Female', 35, '0779876543', 'anushka.silva@gmail.com', 'Asthma'),
('Dilani Weerasinghe', '995678234V', 'Female', 29, '0756784321', 'dilaniw@gmail.com', 'Allergy to penicillin'),
('Saman Jayawardena', '967654321V', 'Male', 52, '0712456789', 'saman.jay@gmail.com', 'Heart disease'),
('Thisara Gamage', '200045678V', 'Male', 23, '0744567890', 'thisara.gamage@outlook.com', 'None'),
('Ruwani Dissanayake', '975672345V', 'Female', 42, '0785567321', 'ruwani.d@gmail.com', 'Migraine'),
('Isuru Bandara', '995432167V', 'Male', 27, '0769087654', 'isuru.b@gmail.com', 'Skin rash (occasional)');

SELECT COUNT(*) FROM Patient WHERE MONTH(DateCreated)=11 AND YEAR(DateCreated)=2025;


INSERT INTO Doctor (FullName, NIC, Specialization, ContactNumber, Email, Branch) VALUES
('Dr. Samantha Perera', '987654321V', 'Cardiology', '0711234567', 'samantha@example.com', 'Colombo'),
('Dr. Nimal Fernando', '987654322V', 'Pediatrics', '0712345678', 'nimal@example.com', 'Kandy'),
('Dr. Kavindi Jayasuriya', '987654323V', 'Dermatology', '0713456789', 'kavindi@example.com', 'Colombo');

INSERT INTO DoctorAvailability (DoctorID, DayOfWeek, StartTime, EndTime) VALUES
(1, 'Monday', '09:00:00', '13:00:00'),
(1, 'Tuesday', '09:00:00', '13:00:00'),
(2, 'Monday', '10:00:00', '14:00:00'),
(2, 'Wednesday', '10:00:00', '14:00:00'),
(3, 'Thursday', '13:00:00', '17:00:00');

INSERT INTO DoctorSlots (DoctorID, SlotDate, StartTime, EndTime, IsBooked) VALUES
(1, '2025-11-03', '09:00:00', '09:30:00', TRUE),
(1, '2025-11-03', '09:30:00', '10:00:00', FALSE),
(1, '2025-11-04', '09:00:00', '09:30:00', TRUE),
(2, '2025-11-03', '10:00:00', '10:30:00', TRUE),
(2, '2025-11-05', '10:00:00', '10:30:00', FALSE),
(3, '2025-11-06', '13:00:00', '13:30:00', TRUE);

INSERT INTO Appointment (PatientID, SlotID, Status, CreatedAt) VALUES
(1, 1, 'Completed', '2025-11-01 08:00:00'),
(2, 3, 'Completed', '2025-11-02 09:00:00'),
(3, 4, 'Scheduled', '2025-11-03 09:30:00'),
(4, 6, 'Completed', '2025-11-04 12:00:00');

