# Job Portal System

A full-stack web application that connects recruiters and job seekers.
Recruiters can post jobs and manage applicants. Job seekers can browse
jobs, apply, and track their application status.

Built with Spring Boot (backend) and React (frontend), deployed live.

🔗 Live: https://jobportal-frontend-kohl.vercel.app

---

## Screenshots

**Recruiter Dashboard**
![Recruiter Dashboard](screenshots/recruiter-dashboard.png)

**Job Detail & Apply**
![Job Detail](screenshots/job-detail.png)

**Seeker Dashboard**
![Seeker Dashboard](screenshots/seeker-dashboard.png)

**Managing Applicants**
![Recruiter Applicants](screenshots/recruiter-applicants.png)

---

## What it does

**As a Recruiter:**
- Post job listings with title, location, salary and required skills
- See who applied for each job
- Change applicant status to Reviewed, Accepted or Rejected

**As a Job Seeker:**
- Browse and search jobs by keyword or location
- Apply for a job and upload your resume
- Check your application status from the dashboard

---

## Tech used

- Java 17, Spring Boot 3.2
- Spring Security with JWT authentication
- Hibernate / JPA with MySQL
- React 18, React Router, Axios
- Cloudinary for resume file upload
- Deployed on Render (backend) and Vercel (frontend)
- Database hosted on Railway

---

## API Reference

**Auth**

POST /api/auth/register
POST /api/auth/login

**Jobs**
GET    /api/jobs               - list all jobs (public)
GET    /api/jobs/{id}          - job details (public)
GET    /api/jobs/search        - search by keyword/location
GET    /api/jobs/my            - recruiter's own jobs
POST   /api/jobs               - post a job (recruiter)
PUT    /api/jobs/{id}          - edit a job (recruiter)
DELETE /api/jobs/{id}          - delete a job (recruiter)

**Applications**
POST /api/applications              - apply with resume (seeker)
GET  /api/applications/my           - my applications (seeker)
GET  /api/applications/job/{jobId}  - applicants for a job (recruiter)
PUT  /api/applications/{id}/status  - update status (recruiter)

---

## Database
users        — id, name, email, password, role, created_at
jobs         — id, title, description, location, salary, skills, posted_by
applications — id, user_id, job_id, resume_link, status, applied_at

---

## Running locally

**Backend**
```bash
git clone https://github.com/Nived05/jobportal.git
cd jobportal/backend
cp src/main/resources/application.properties.example \
   src/main/resources/application.properties
# fill in your MySQL and Cloudinary details
./mvnw spring-boot:run
```

**Frontend**
```bash
cd jobportal/frontend
npm install
# create .env with VITE_API_URL=http://localhost:8080
npm run dev
```

---

## Project structure

jobportal/
├── backend/
│   └── src/main/java/com/jobportal/backend/
│       ├── controller/
│       ├── service/
│       ├── repository/
│       ├── entity/
│       ├── dto/
│       ├── security/
│       └── exception/
└── frontend/
└── src/
├── api/
├── components/
├── context/
└── pages/
---

Made by Chitturi Nived — github.com/Nived05

