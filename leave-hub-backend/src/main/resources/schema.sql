CREATE TABLE IF NOT EXISTS leave_requests (
                                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                              employee_name VARCHAR(150) NOT NULL,
    department VARCHAR(100) NOT NULL,
    leave_type VARCHAR(50) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    requested_days INT NOT NULL,
    reason VARCHAR(500),
    status VARCHAR(30) NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );