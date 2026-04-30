-- ============================================
-- PROJECT 2: Job Portal with Resume Matching
-- Run this script in MySQL first
-- ============================================

CREATE DATABASE IF NOT EXISTS job_db;
USE job_db;

CREATE TABLE IF NOT EXISTS jobs (
    id     INT AUTO_INCREMENT PRIMARY KEY,
    title  VARCHAR(100) NOT NULL,
    skills VARCHAR(200) NOT NULL   -- comma-separated, e.g. "Java,SQL,Spring"
);

CREATE TABLE IF NOT EXISTS candidates (
    id     INT AUTO_INCREMENT PRIMARY KEY,
    name   VARCHAR(50)  NOT NULL,
    skills VARCHAR(200) NOT NULL   -- comma-separated
);

CREATE TABLE IF NOT EXISTS applications (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    candidate_id INT NOT NULL,
    job_id       INT NOT NULL,
    applied_on   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (candidate_id) REFERENCES candidates(id) ON DELETE CASCADE,
    FOREIGN KEY (job_id)       REFERENCES jobs(id)       ON DELETE CASCADE
);

-- Sample data
INSERT INTO jobs (title, skills) VALUES
    ('Java Backend Developer', 'Java,SQL,Spring'),
    ('Python Data Analyst',    'Python,SQL,Pandas'),
    ('Full Stack Developer',   'Java,React,SQL');

INSERT INTO candidates (name, skills) VALUES
    ('Piyush Sharma', 'Java,SQL,Spring'),
    ('Ananya Rao',    'Python,SQL,Pandas'),
    ('Ravi Kumar',    'Java,React,SQL');
