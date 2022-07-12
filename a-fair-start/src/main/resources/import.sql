
INSERT INTO users (username, name, password, role) VALUES ('admin', 'Administrator', '$2a$10$YfY/ecjvrYqeFQ9xnuFLAe2EUhQBQkMal58kBUJx8sRbKL1VrbcC.', 'admin');
INSERT INTO users (username, name, password, role) VALUES ('user', 'User', '$2a$10$OfQWuRPsizS5HZJ7KSSMPuujQtu.ttm5X3PiWTuKoJ59At9TY8koe', 'user');

INSERT INTO device(id, type) VALUES (0, 0);
INSERT INTO device(id, type) VALUES (1, 1);
INSERT INTO device(id, type) VALUES (2, 0);

INSERT INTO User_deviceExpericence(User_username, deviceExpericence, deviceExpericence_KEY) VALUES ('admin', 1,0);
INSERT INTO User_deviceExpericence(User_username, deviceExpericence, deviceExpericence_KEY) VALUES ('admin', 1,1);