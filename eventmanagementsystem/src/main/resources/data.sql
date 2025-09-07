INSERT INTO event (name, date)
VALUES ('TechCon', '2023-12-15');

INSERT INTO event (name, date)
VALUES ('Fashion Fest', '2023-11-05');

INSERT INTO event (name, date)
VALUES ('MusicFest', '2024-01-25');

INSERT INTO event (name, date)
VALUES ('EcoAwareness Conclave', '2023-11-10');

INSERT INTO sponsor (name, industry)
VALUES ('TechCorp', 'Technology');

INSERT INTO sponsor (name, industry)
VALUES ('Glamour Inc.', 'Fashion');

INSERT INTO sponsor (name, industry)
VALUES ('SoundWave Productions', 'Music Production');

INSERT INTO sponsor (name, industry)
VALUES ('EcoPlanet', 'Environmental Conservation');

INSERT INTO event_sponsor (eventId, sponsorId)
VALUES (1, 1);

INSERT INTO event_sponsor (eventId, sponsorId)
VALUES (1, 2);

INSERT INTO event_sponsor (eventId, sponsorId)
VALUES (2, 2);

INSERT INTO event_sponsor (eventId, sponsorId)
VALUES (3, 3);

INSERT INTO event_sponsor (eventId, sponsorId)
VALUES (3, 4);

INSERT INTO event_sponsor (eventId, sponsorId)
VALUES (4, 4);