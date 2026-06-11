Overview : 
Java backend Movie Booking System API built using Spring Boot, MongoDB, JWT Authentication, and Docker.

System Architecture:
Client (Postman)
        ↓
JWT Authentication Filter
        ↓
Spring Boot REST API
        ↓
Service Layer (Business Logic)
        ↓
MongoDB (Docker Container)

It supports:
User registration & login (JWT secured)
Movie management (CRUD)
Ticket booking system
Payment simulation
Seat management (auto update on booking/cancel)
MongoDB running inside Docker container


Tech Stack used :
Java 21
Spring Boot Backend Framework
Spring Data MongoDB
Spring Security + JWT
Maven
Docker (MongoDB container)
Lombok

Authentication Flow (JWT)
1. User Login: User sends username & password Server validates credentials JWT token is generated
2. Token Usage : Token sent in header: Authorization: Bearer <token>
3. Security Filter : Validates token for every request and Extracts user details from token.

User Module Features:
Login User (JWT token generation)
Get user details
Endpoints:
POST /auth/register
POST /auth/login
GET  /users/getAllUsers
GET  /users/{id}

Movie Module (CRUD) Features:
-> Add Movie
-> Update Movie
-> Delete Movie
-> Get All Movies
-> Manage available seats

Movie Fields:
{
  "movieId": "string",
  "movieName": "string",
  "availableSeats": 100,
  "price": 250
}

Endpoints:
POST   /movies
GET    /movies
GET    /movies/{id}
DELETE /movies/{id}

Booking Module Features:
Book movie tickets
View bookings
Cancel booking
Auto seat update
-> Booking Logic
-> Create Booking

User sends:

{
  "userId": "U101",
  "movieId": "M101",
  "seats": 2
}

What happens internally:
Check movie exists
Check available seats

Reduce seats:

availableSeats = availableSeats - bookedSeats
Save booking with status = CONFIRMED

Cancel Booking:
Find booking by bookingId
Get movieId + seats

Increase seats back:
availableSeats = availableSeats + cancelledSeats
Update booking status = CANCELLED

Endpoints:
POST /bookings/create
GET  /bookings/{userId}
DELETE /bookings/{bookingId}

Payment Module Features:
Payment after booking Linked to bookingId

Flow:
Booking created
Payment initiated
Payment status saved
Endpoints:
POST /payments/pay
GET  /payments/{bookingId}
Payment Model:
{
  "paymentId": "P101",
  "bookingId": "B101",
  "amount": 500,
  "status": "SUCCESS"
}

MongoDB Database Design
Collections:
1. users : { "userId": "U1", "username": "test", "password": "encrypted" }
2. movies : { "movieId": "M1", "name": "Avatar", "availableSeats": 100 }
3. bookings : { "bookingId": "B1", "userId": "U1", "movieId": "M1", "seats": 2 }
4. payments : { "paymentId": "P1", "bookingId": "B1", "status": "SUCCESS" }

Docker Setup (MongoDB Only):
docker-compose.yml:
version: "3.8"

services:
  mongodb:
    image: mongo:7
    container_name: mongodb
    ports:
      - "27017:27017"
    volumes:
      - mongo-data:/data/db

volumes:
  mongo-data:
  
Run MongoDB : docker-compose up -d

How to Run Project
Step 1: Start MongoDB
docker-compose up -d
Step 2: Run Spring Boot
mvn spring-boot:run
