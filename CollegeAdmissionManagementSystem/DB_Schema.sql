CREATE DATABASE CollegeAdmissionDb;
USE CollegeAdmissionDb;

CREATE TABLE Students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    marks INT
);

CREATE TABLE Courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    cutoff INT
);

CREATE TABLE Applications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'Pending',
    FOREIGN KEY (id) REFERENCES Students(id) ON DELETE CASCADE,
    FOREIGN KEY (id) REFERENCES Courses(id) ON DELETE CASCADE
);