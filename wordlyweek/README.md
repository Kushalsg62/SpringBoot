# Wordly Week

Wordly Week is a Spring Boot–based REST API for managing writers and magazines.  
The application models a many-to-many relationship where multiple writers can contribute to multiple magazines, and vice versa.  
It provides endpoints supporting complete CRUD operations for both entities, as well as endpoints to query relationships between writers and magazines.

---

## Features
- CRUD operations for Writer and Magazine entities  
- Many-to-Many mapping between Writer and Magazine  
- REST endpoints to:  
  - Retrieve all writers for a given magazine  
  - Retrieve all magazines of a given writer  
- Preloaded sample data using the H2 in-memory database  

---

## Technology Stack
- Java 21  
- Spring Boot 3.x (Web, Data JPA, Validation)  
- H2 Database  
- Maven  

---

## Database Schema

**Writer**  
- writerId (PK)  
- writerName  
- bio  

**Magazine**  
- magazineId (PK)  
- magazineName  
- publicationDate  

**Junction Table (writer_magazine)**  
- writerId (FK → Writer)  
- magazineId (FK → Magazine)  
- Primary Key: combination of writerId + magazineId  

---

## REST Endpoints

### Magazine APIs
- `GET /magazines` — List all magazines  
- `POST /magazines` — Create a new magazine  
- `GET /magazines/{magazineId}` — Retrieve a magazine by ID  
- `PUT /magazines/{magazineId}` — Update an existing magazine  
- `DELETE /magazines/{magazineId}` — Delete a magazine  
- `GET /magazines/{magazineId}/writers` — List all writers for a given magazine  
- `GET /magazines/writers` — List all magazine-writer relationships  
- `POST /magazines/writers` — Create a new magazine-writer relationship  

### Writer APIs
- `GET /magazines/writers/{writerId}` — Retrieve a writer by ID  
- `PUT /magazines/writers/{writerId}` — Update an existing writer  
- `DELETE /magazines/writers/{writerId}` — Delete a writer  
- `GET /writers/{writerId}/magazines` — List all magazines for a given writer  

---

## Running the Application

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run

##The application starts at:
http://localhost:8080