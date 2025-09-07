# Event Management System

The Event Management System is a Spring Boot–based REST API for managing sponsors and events.  
The application models a many-to-many relationship where multiple sponsors can back multiple events, and vice versa.  
It provides endpoints supporting complete CRUD operations for both entities, as well as endpoints to query relationships between sponsors and events.

---

## Features
- CRUD operations for Event and Sponsor entities  
- Many-to-Many mapping between Event and Sponsor  
- REST endpoints to:  
  - Retrieve all sponsors of a given event  
  - Retrieve all events of a given sponsor  
- Preloaded sample data using the H2 in-memory database  

---

## Technology Stack
- Java 21  
- Spring Boot 3.x (Web, Data JPA, Validation)  
- H2 Database  
- Maven  

---

## Database Schema

**Sponsor**  
- sponsorId (PK)  
- sponsorName  
- industry  

**Event**  
- eventId (PK)  
- eventName  
- date  

**Junction Table (event_sponsor)**  
- eventId (FK → Event)  
- sponsorId (FK → Sponsor)  
- Primary Key: combination of eventId + sponsorId  

---

## REST Endpoints

### Event APIs
- `GET /events` — List all events  
- `POST /events` — Create a new event  
- `GET /events/{eventId}` — Retrieve an event by ID  
- `PUT /events/{eventId}` — Update an existing event  
- `DELETE /events/{eventId}` — Delete an event  
- `GET /events/{eventId}/sponsors` — List all sponsors for a given event  
- `GET /events/sponsors` — List all event-sponsor relationships  
- `POST /events/sponsors` — Create a new event-sponsor relationship  

### Sponsor APIs
- `GET /events/sponsors/{sponsorId}` — Retrieve a sponsor by ID  
- `PUT /events/sponsors/{sponsorId}` — Update an existing sponsor  
- `DELETE /events/sponsors/{sponsorId}` — Delete a sponsor  
- `GET /sponsors/{sponsorId}/events` — List all events sponsored by a given sponsor  

---

## Running the Application

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run

## The application starts at:
http://localhost:8080