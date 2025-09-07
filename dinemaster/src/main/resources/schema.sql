CREATE TABLE IF NOT EXISTS restaurant(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name TEXT,
    address TEXT,
    cuisineType TEXT,
    rating INTEGER
);

CREATE TABLE IF NOT EXISTS chef(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    firstName TEXT,
    lastName TEXT,
    expertise TEXT,
    experienceYears INTEGER,
    restaurantId INTEGER,
    FOREIGN KEY (restaurantId) REFERENCES restaurant(id)
);