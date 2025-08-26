🎓 Course Registration System
📌 Project Overview

The Course Registration System is a Java + MySQL application that makes course enrollment easy for students and management simple for institutions.
Admin can add, update, and delete courses.
Students can register for courses.
Faculty can view the list of students enrolled in their courses.
This system reduces paperwork, avoids errors, and provides a smooth digital solution for course management.

🚀 Key Features

Manage courses (Add, Update, Delete).
Student registration for available courses.
Faculty access to enrolled student lists.
MySQL database for secure data storage.
DAO structure for clean and modular code.

🛠 Tech Stack

Frontend: HTML, CSS, JavaScript
Backend: Core Java
Database: MySQL

📂 Project Structure

CourseRegistrationSystem/
├── src/
│   ├── Student.java
│   ├── Course.java
│   ├── Registration.java
│   ├── DatabaseUtil.java
│   ├── dao/
│   │   ├── StudentDAO.java
│   │   ├── CourseDAO.java
│   │   └── RegistrationDAO.java
│   └── Main.java
├── README.md
└── screenshots/


🗄️ Database Design

Tables:

Student → Stores student details (ID, name, email, password).
Course → Stores course details (ID, name, credits).
Registration → Links students with courses (student_id, course_id).

Relationships:

One student can register for many courses.
One course can have many students. 

📊 Workflow

Admin → Manages courses.
Student → Registers for courses.
Faculty → Views enrolled students.

📌 Future Improvements

Role-based login (Admin, Student, Faculty).
Timetable conflict detection.
Online portal with a modern web interface.
Notifications for successful registrations.
