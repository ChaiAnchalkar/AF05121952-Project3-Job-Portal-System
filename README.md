Job Portal with Resume Matching
A MySQL-based Job Portal system that matches candidates to job listings based on skill compatibility.
---
Project Structure
```
job-portal/
├── job_db.sql       # Database schema and sample data
└── README.md
```
---
Database Schema
`jobs`
Column	Type	Description
`id`	INT (PK)	Auto-incremented job ID
`title`	VARCHAR(100)	Job title
`skills`	VARCHAR(200)	Required skills (comma-separated)
`candidates`
Column	Type	Description
`id`	INT (PK)	Auto-incremented candidate ID
`name`	VARCHAR(50)	Candidate's full name
`skills`	VARCHAR(200)	Candidate's skills (comma-separated)
`applications`
Column	Type	Description
`id`	INT (PK)	Auto-incremented application ID
`candidate_id`	INT (FK)	References `candidates(id)`
`job_id`	INT (FK)	References `jobs(id)`
`applied_on`	TIMESTAMP	Application timestamp (default: now)

---
Setup Instructions
Prerequisites
MySQL 5.7+ or MariaDB 10+
Steps
Clone the repository
```bash
   git clone https://github.com/your-username/job-portal.git
   cd job-portal
   ```
Run the SQL script
```bash
   mysql -u root -p < job_db.sql
   ```
Verify the setup
```sql
   USE job_db;
   SHOW TABLES;
   SELECT * FROM jobs;
   SELECT * FROM candidates;
   ```
---

 Sample Data
Jobs:
ID	Title	Skills Required
1	Java Backend Developer	Java, SQL, Spring
2	Python Data Analyst	Python, SQL, Pandas
3	Full Stack Developer	Java, React, SQL
Candidates:
ID	Name	Skills
1	Piyush Sharma	Java, SQL, Spring
2	Ananya Rao	Python, SQL, Pandas
3	Ravi Kumar	Java, React, SQL
---

Example Queries
Find all applications with candidate and job details:
```sql
SELECT
    c.name        AS candidate,
    j.title       AS job,
    a.applied_on
FROM applications a
JOIN candidates c ON a.candidate_id = c.id
JOIN jobs j       ON a.job_id       = j.id;
```
Find jobs a candidate's skills match:
```sql
SELECT j.title, j.skills
FROM jobs j
JOIN candidates c ON c.id = 1
WHERE c.skills = j.skills;
```

Features
Job Listings — Store job postings with required skill tags
Candidate Profiles — Store candidates with their skill sets
Applications — Track which candidates applied to which jobs
Skill Matching — Query-based resume-to-job skill matching

---
Tech Stack
Database: MySQL
Language: SQL
---
