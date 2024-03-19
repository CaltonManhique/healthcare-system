CREATE DATABASE IF NOT EXISTS healthcare_db;
USE healthcare_db;

DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `medical_staff`;
DROP TABLE IF EXISTS `patient_records`;
DROP TABLE IF EXISTS `appointments`;

-- Table for users
CREATE TABLE users (
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,  
    enabled tinyint NOT NULL,
    PRIMARY KEY (`email`)
)   ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `users` 
VALUES 
('leo@xxx.com','{bcrypt}$2a$12$WX1KeMfa/0BTKAfJDTro6.wbu56iIl0BfduZBygelPYSfaeIOW1P6',1),
('susi@xxx.com','{bcrypt}$2a$12$WX1KeMfa/0BTKAfJDTro6.wbu56iIl0BfduZBygelPYSfaeIOW1P6',1),
('david@xxx.com','{bcrypt}$2a$12$WX1KeMfa/0BTKAfJDTro6.wbu56iIl0BfduZBygelPYSfaeIOW1P6',1); -- 1234


CREATE TABLE `roles` (
  `email` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities4_idx_1` (`email`,`authority`),
  CONSTRAINT `authorities4_ibfk_1` FOREIGN KEY (`email`) REFERENCES `users` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `roles` 
VALUES 
('leo@xxx.com','ROLE_STAFF'),
('susi@xxx.com','ROLE_STAFF'),
('susi@xxx.com','ROLE_SENSITIVE'),
('david@xxx.com','ROLE_STAFF'),
('david@xxx.com','ROLE_SENSITIVE'),
('david@xxx.com','ROLE_ADMIN');

-- Table for medical staff information
CREATE TABLE medical_staff (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    speciality VARCHAR(50) NOT NULL,
    FOREIGN KEY (email) REFERENCES users(email)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Table for patient records
CREATE TABLE patient_records (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(50) NOT NULL,
    patient_email VARCHAR(50) NOT NULL,
    patient_name VARCHAR(100) NOT NULL,
    patient_type  varchar(30)  not null,
    FOREIGN KEY (email) REFERENCES users(email)
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Table for appointments
CREATE TABLE appointments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    medical_staff_id INT NOT NULL,
    patient_id INT NOT NULL,
    appointment_date DATE,
    Foreign KEY (medical_staff_id) References medical_staff(id),
    Foreign KEY (patient_id) References patient_records(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



