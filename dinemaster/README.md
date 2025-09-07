DineMaster:

DineMaster is a Spring Boot–based REST API for managing restaurants and their chefs.
The application models a one-to-many relationship where multiple chefs work in a single restaurant.
It provides endpoints supporting complete CRUD operations for both entities.

Features:

1.CRUD operations for Restaurant and Chef entities
2.Many-to-One mapping between Chef and Restaurant
3.REST endpoints to:
    Retrieve the restaurant of a given chef
    Retrieve all chefs working in a restaurant
4.Preloaded sample data using the H2 in-memory database

Technology Stack:

Java 21
Spring Boot 3.x (Web, Data JPA, Validation)
H2 Database
Maven

Database Schema:

Restaurant:
id (PK)
name
address
cuisineType
rating

Chef:
id (PK)
firstName
lastName
expertise
experienceYears
restaurantId (FK → Restaurant)

REST Endpoints:

Restaurant APIs:
GET /restaurants — List all restaurants
POST /restaurants — Create a new restaurant
GET /restaurants/{restaurantId} — Retrieve a restaurant by ID
PUT /restaurants/{restaurantId} — Update an existing restaurant
DELETE /restaurants/{restaurantId} — Delete a restaurant

Chef APIs:

GET /restaurants/chefs — List all chefs
POST /restaurants/chefs — Create a new chef
GET /restaurants/chefs/{chefId} — Retrieve a chef by ID
PUT /restaurants/chefs/{chefId} — Update an existing chef
DELETE /restaurants/chefs/{chefId} — Delete a chef
GET /chefs/{chefId}/restaurant — Retrieve the restaurant of a given chef

Running the Application:
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run


The application starts at:
http://localhost:8080