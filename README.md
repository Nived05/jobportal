# 💼 Full Stack Job Portal System

A production-ready full-stack job portal application built with Spring Boot and React.

## 🌐 Live Demo
- **Frontend:** https://fullstack-job-portal.vercel.app
- **Backend API:** https://jobportal-backend-b68j.onrender.com

## 🛠️ Tech Stack

### Backend
- Java 17 + Spring Boot 3.2
- Spring Security + JWT Authentication
- JPA / Hibernate ORM
- MySQL (Railway)
- Cloudinary (Resume Upload)
- Docker (Deployment)

### Frontend
- React 18 + Vite
- React Router v6
- Axios
- Context API

### Deployment
- Backend → Render (Docker)
- Frontend → Vercel
- Database → Railway MySQL

## ✨ Features
- JWT-based authentication with role-based access
- **Recruiter:** Post, edit, delete jobs — view & manage applicants
- **Job Seeker:** Browse, search, filter jobs — apply with resume upload
- Pagination and keyword/location search
- Application status tracking (Pending/Reviewed/Accepted/Rejected)
- Resume upload via Cloudinary

## 📁 Project Structure
jobportal/
├── backend/          # Spring Boot REST API
│   ├── src/
│   │   └── main/java/com/jobportal/backend/
│   │       ├── controller/
│   │       ├── service/
│   │       ├── repository/
│   │       ├── entity/
│   │       ├── dto/
│   │       ├── security/
│   │       └── exception/
│   └── Dockerfile
└── frontend/         # React Application
└── src/
├── api/
├── components/
├── context/
└── pages/

## 🚀 Local Setup

### Backend
1. Clone the repo
2. Copy `backend/src/main/resources/application.properties.example` → `application.properties`
3. Fill in your MySQL, JWT and Cloudinary credentials
4. Run:
```bash
cd backend
./mvnw spring-boot:run
```

### Frontend
1. Copy `frontend/.env.example` → `.env`
2. Set `VITE_API_URL=http://localhost:8080`
3. Run:
```bash
cd frontend
npm install
npm run dev
```

## 📸 Screenshots
> Register → Login → Browse Jobs → Apply → Track Status

## 👨‍💻 Author
**Chitturi Nived**
- GitHub: [@Nived05](https://github.com/Nived05)