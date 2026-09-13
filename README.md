# Employee Leave Hub

  A backend service for managing employee leave requests, built with Java as part of a full-stack learning project.

  ## Overview

  This project focuses on the backend infrastructure for a leave management system, featuring RESTful API endpoints for handling employee time-off requests, approval workflows, and leave balance tracking.

  ## Features

  - Employee leave request submission (vacation, sick, personal)
  - Manager approval/rejection workflow
  - Leave balance tracking and accrual calculation
  - RESTful API for frontend integration
  - Basic validation and error handling
  - In-memory data storage (planned migration to relational database)

  ## Technologies Used

  - **Language**: Java 17
  - **Framework**: Core Java (planned migration to Spring Boot)
  - **Architecture**: Layered (Controller, Service, Repository)
  - **Build**: Maven
  - **Testing**: JUnit 5 (planned)
  - **API**: RESTful endpoints with JSON

  ## Getting Started

  ### Prerequisites

  - JDK 17 or later
  - Maven 3.8+
  - IDE (IntelliJ IDEA, Eclipse, or VS Code with Java extensions)

  ### Installation

  1. Clone the repository:
     ```bash
     git clone https://github.com/cristi279/EmployeeLeaveHubProj.git

  2. Navigate to the project directory:
  cd EmployeeLeaveHubProj
  3. Build the project:
  mvn clean install
  4. Run the application:
  mvn spring-boot:run
     (Note: Will update to actual run command once Spring Boot is integrated)

  API Endpoints

  Leave Requests

  - POST /api/requests - Submit new leave request
  - GET /api/requests/{id} - Get request by ID
  - GET /api/requests/employee/{employeeId} - Get all requests for employee
  - PUT /api/requests/{id}/approve - Approve leave request
  - PUT /api/requests/{id}/reject - Reject leave request

  Employees

  - GET /api/employees/{id} - Get employee details
  - GET /api/employees - Get all employees

  Leave Balances

  - GET /api/balances/{employeeId} - Get leave balances for employee

  Project Structure

  EmployeeLeaveHubProj/
  ├── leave-hub-backend/
  │   ├── src/
  │   │   ├── main/
  │   │   │   ├── java/
  │   │   │   │   └── com/example/leavehub/
  │   │   │   │       ├── controller/
  │   │   │   │       ├── service/
  │   │   │   │       ├── repository/
  │   │   │   │       └── model/
  │   │   │   └── resources/
  │   │   └── test/
  │   │       └── java/
  │   └── pom.xml
  └── README.md

  Current Limitations & Future Work

  - [ ] Replace in-memory storage with PostgreSQL
  - [ ] Implement Spring Boot framework
  - [ ] Add comprehensive unit and integration tests
  - [ ] Implement JWT-based authentication
  - [ ] Add role-based access control (employee/manager/admin)
  - [ ] Create API documentation with Swagger/OpenAPI
  - [ ] Add leave type configurations (customizable accrual rates)
  - [ ] Implement email notifications for request updates
  - [ ] Add reporting and analytics endpoints

  Learning Objectives

  Through this project, I aimed to practice:
  - RESTful API design principles
  - Layered architecture separation
  - Java exception handling best practices
  - Maven dependency management
  - Basic data modeling and relationships
  - API testing concepts (planned implementation)

  Acknowledgments

  - Java Brains YouTube channel for architecture concepts
  - University coursework on web services and APIs
  - Open-source leave management systems for inspiration
