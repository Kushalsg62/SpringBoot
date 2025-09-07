# GeoHub

GeoHub is a Spring Boot–based REST API designed for managing countries and their associated cities.  
The application models a one-to-many relationship where multiple cities belong to a single country.  
It provides endpoints supporting complete CRUD operations for both entities.

---

## Features
- CRUD APIs for Country and City entities  
- Many-to-One mapping between City and Country  
- REST endpoints for retrieving a city’s country and all cities in a country  
- Preloaded sample data with H2 in-memory database  

---

## Tech Stack
- Java 21  
- Spring Boot 3.x (Web, Data JPA, Validation)  
- H2 Database  
- Maven  

---

## Database Schema

**Country**  
- countryId (PK)  
- countryName  
- currency  
- population  
- latitude  
- longitude  

**City**  
- cityId (PK)  
- cityName  
- population  
- latitude  
- longitude  
- countryId (FK → Country)  

---

## REST Endpoints

### Country APIs
- `GET /countries` — List all countries  
- `POST /countries` — Add a new country  
- `GET /countries/{countryId}` — Fetch a country by ID  
- `PUT /countries/{countryId}` — Update a country  
- `DELETE /countries/{countryId}` — Remove a country  

### City APIs
- `GET /countries/cities` — List all cities  
- `POST /countries/cities` — Add a new city  
- `GET /countries/cities/{cityId}` — Fetch a city by ID  
- `PUT /countries/cities/{cityId}` — Update a city  
- `DELETE /countries/cities/{cityId}` — Remove a city  
- `GET /cities/{cityId}/country` — Fetch the country of a given city  

---

## Running the Application

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run

##The application starts at:
http://localhost:8080
