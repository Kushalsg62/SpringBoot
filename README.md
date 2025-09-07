GeoHub

GeoHub is a Spring Boot–based REST API designed for managing countries and their associated cities.
The application models a one-to-many relationship where multiple cities belong to a single country.
It provides endpoints supporting complete CRUD operations for both entities.

Features:

1.CRUD APIs for Country and City entities

2.Many-to-One mapping between City and Country

3.REST endpoints for retrieving a city’s country and all cities in a country

4.Preloaded sample data with H2 in-memory database

Tech Stack:

1.Java 21

2.Spring Boot 3.x (Web, Data JPA, Validation)

3.H2 Database

4.Maven

Database Schema:


Country:

countryId (PK)

countryName

currency

population

latitude

longitude


City:

cityId (PK)

cityName

population

latitude

longitude

countryId (FK → Country)


REST Endpoints:


Country APIs:

GET /countries — list all countries

POST /countries — add new country

GET /countries/{countryId} — fetch country by ID

PUT /countries/{countryId} — update country

DELETE /countries/{countryId} — remove country


City APIs:

GET /countries/cities — list all cities

POST /countries/cities — add new city

GET /countries/cities/{cityId} — fetch city by ID

PUT /countries/cities/{cityId} — update city

DELETE /countries/cities/{cityId} — remove city

GET /cities/{cityId}/country — fetch the country of a given city


Running the Application:

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run



Application starts at:
👉 http://localhost:8080
