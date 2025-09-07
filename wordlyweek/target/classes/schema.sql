CREATE TABLE IF NOT EXISTS writer(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    bio VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS magazine(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    publicationDate DATE
);

CREATE TABLE IF NOT EXISTS writer_magazine(
    writerId INTEGER,
    magazineId INTEGER,
    PRIMARY KEY(writerId, magazineId),
    FOREIGN KEY (writerId) REFERENCES writer(id),
    FOREIGN KEY (magazineId) REFERENCES magazine(id)
);