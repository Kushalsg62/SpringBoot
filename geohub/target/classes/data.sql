INSERT INTO country (countryName, currency, population, latitude, longitude) VALUES 
('India', 'INR', 1393409038, '20.5937° N', '78.9629° E'),
('USA', 'USD', 331893745, '37.7749° N', '122.4194° W'),
('Australia', 'AUD', 25687041, '25.2744° S', '133.7751° E'),
('Canada', 'CAD', 38008005, '56.1304° N', '106.3468° W'),
('UK', 'GBP', 68207116, '51.5074° N', '0.1278° W');

INSERT INTO city (cityName, population, latitude, longitude, countryId) VALUES 
('Mumbai', 20185064, '19.0760° N', '72.8777° E', 1),
('Bangalore', 12425304, '12.9716° N', '77.5946° E', 1),
('New York', 8419600, '40.7128° N', '74.0060° W', 2),
('Chicago', 2716000, '41.8781° N', '87.6298° W', 2),
('Sydney', 5303000, '33.8688° S', '151.2093° E', 3),
('Melbourne', 5084000, '37.8136° S', '144.9631° E', 3),
('Vancouver', 675218, '49.2827° N', '123.1207° W', 4),
('Toronto', 3140000, '43.651070° N', '79.347015° W', 4),
('London', 8982000, '51.5074° N', '0.1278° W', 5),
('Manchester', 547627, '53.4808° N', '2.2426° W', 5);
