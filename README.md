# 🎓 Student Management System

A RESTful web application built with **Spring Boot 4.0.5** and **Java 21** for managing students, courses, enrollments, and class schedules. Features a modern **Single-Page Application (SPA)** frontend powered by vanilla JavaScript — no external frameworks required.

---

## 📋 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Architecture](#-architecture)
- [Modules](#-modules)
- [REST API Reference](#-rest-api-reference)
- [Database Schema](#-database-schema)
- [Getting Started](#-getting-started)

---

## ✨ Features

- ✅ Full CRUD operations for all entities
- ✅ RESTful API design following HTTP verb conventions
- ✅ Single-Page Application with vanilla JavaScript
- ✅ Dark-themed UI with responsive design
- ✅ Toast notifications for success/error feedback
- ✅ Real-time record counts displayed in UI
- ✅ Cascading deletes for referential integrity
- ✅ Auto-initialization of database schema on startup
- ✅ Course scheduling with day/time/room support

---

## 🛠 Tech Stack

| Component     | Technology                        |
|---------------|-----------------------------------|
| Framework     | Spring Boot 4.0.5                 |
| Language      | Java 21                           |
| Web Framework | Spring WebMVC (REST API)          |
| Data Access   | Spring JDBC (JdbcTemplate)        |
| Database      | MySQL                             |
| Frontend      | Vanilla JavaScript + Embedded CSS |
| Build Tool    | Maven                             |
| Dev Tools     | spring-boot-devtools              |

---

## 📁 Project Structure

```
student-management/
├── src/main/java/StudentManagement/app/
│   ├── StudentManagementApplication.java
│   ├── controller/      # 3 REST controllers
│   ├── model/           # 4 entity classes
│   └── dao/             # 4 DAO classes
├── src/main/resources/
│   ├── application.properties
│   ├── schema.sql
│   └── static/
│       └── index.html   # Single-page frontend
└── pom.xml
```

---

## 🏗 Architecture

```
┌─────────────────────────────────────────┐
│    Single-Page Application (HTML/JS)    │
│           static/index.html             │
└─────────────────────────────────────────┘
                  │ REST API calls
                  ▼
┌─────────────────────────────────────────┐
│       Controller Layer (REST API)       │
│  StudentController, CourseController,   │
│  EnrollmentController                   │
└─────────────────────────────────────────┘
                  │
                  ▼
┌─────────────────────────────────────────┐
│         DAO Layer (JdbcTemplate)        │
│  StudentDAO, CourseDAO,                 │
│  EnrollmentDAO, ClassScheduleDAO        │
└─────────────────────────────────────────┘
                  │
                  ▼
┌─────────────────────────────────────────┐
│            MySQL Database               │
│  students, courses, enrollments,        │
│  class_schedule                         │
└─────────────────────────────────────────┘
```

---

## 📦 Modules

| Module                | Description                                                      |
|-----------------------|------------------------------------------------------------------|
| **Student Management** | Register students, maintain contact information and email       |
| **Course Management**  | Create courses with codes, credits, descriptions, and schedules |
| **Enrollment System**  | Enroll students in courses, track enrollment status             |
| **Class Scheduling**   | Define class meeting times, days of week, and room locations    |

---

## 🔌 REST API Reference

### Students — `/students`

| Method | Endpoint         | Description       |
|--------|------------------|-------------------|
| GET    | `/students`      | Get all students  |
| GET    | `/students/{id}` | Get student by ID |
| POST   | `/students`      | Create student    |
| PUT    | `/students/{id}` | Update student    |
| DELETE | `/students/{id}` | Delete student    |

### Courses — `/courses`

| Method | Endpoint                          | Description         |
|--------|-----------------------------------|---------------------|
| GET    | `/courses`                        | Get all courses     |
| GET    | `/courses/{id}`                   | Get course by ID    |
| POST   | `/courses`                        | Create course       |
| PUT    | `/courses/{id}`                   | Update course       |
| DELETE | `/courses/{id}`                   | Delete course       |
| GET    | `/courses/{id}/schedule`          | Get course schedule |
| POST   | `/courses/{id}/schedule`          | Add schedule        |
| PUT    | `/courses/schedule/{scheduleId}`  | Update schedule     |
| DELETE | `/courses/schedule/{scheduleId}`  | Delete schedule     |

### Enrollments — `/enrollments`

| Method | Endpoint                                               | Description              |
|--------|--------------------------------------------------------|--------------------------|
| GET    | `/enrollments`                                         | Get all enrollments      |
| GET    | `/enrollments/student/{studentId}`                     | Get by student           |
| GET    | `/enrollments/course/{courseId}`                       | Get by course            |
| POST   | `/enrollments/student/{studentId}/course/{courseId}`   | Enroll student           |
| DELETE | `/enrollments/student/{studentId}/course/{courseId}`   | Unenroll student         |
| GET    | `/enrollments/course/{courseId}/students`              | Get students in course   |
| GET    | `/enrollments/student/{studentId}/courses`             | Get courses for student  |

---

## 🗄 Database Schema

```sql
students
├── id             (PK, AUTO_INCREMENT)
├── first_name     (VARCHAR(50), NOT NULL)
├── last_name      (VARCHAR(50), NOT NULL)
└── email          (VARCHAR(100), NOT NULL, UNIQUE)

courses
├── id             (PK, AUTO_INCREMENT)
├── course_name    (VARCHAR(100), NOT NULL)
├── course_code    (VARCHAR(20), NOT NULL, UNIQUE)
├── credits        (INT, NOT NULL)
└── description    (VARCHAR(500))

enrollments
├── id              (PK, AUTO_INCREMENT)
├── student_id      (FK → students, ON DELETE CASCADE)
├── course_id       (FK → courses, ON DELETE CASCADE)
├── enrollment_date (DATE, DEFAULT CURRENT_DATE)
├── status          (VARCHAR(20), DEFAULT 'ACTIVE')
└── UNIQUE (student_id, course_id)

class_schedule
├── id           (PK, AUTO_INCREMENT)
├── course_id    (FK → courses, ON DELETE CASCADE)
├── day_of_week  (VARCHAR(10), NOT NULL)
├── start_time   (TIME, NOT NULL)
├── end_time     (TIME, NOT NULL)
└── room         (VARCHAR(50))
```

---

## 🚀 Getting Started

### Prerequisites

- Java 21+
- Maven (or use the included `mvnw` wrapper)
- MySQL running locally

### Database Setup

Create the database before running the application:

```sql
CREATE DATABASE student_management;
```

Then update `src/main/resources/application.properties` with your credentials if needed:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_management
spring.datasource.username=root
spring.datasource.password=your_password
spring.sql.init.mode=always
```

> The schema is auto-initialized from `schema.sql` on startup — no manual table creation needed.

### Run the Application

```bash
cd student-management
./mvnw spring-boot:run
```

> On Windows:
> ```cmd
> mvnw spring-boot:run
> ```

Once started, open your browser at:

```
http://localhost:8080
```

---

> Built with ❤️ using Spring Boot, Java 21 & vanilla JavaScript
