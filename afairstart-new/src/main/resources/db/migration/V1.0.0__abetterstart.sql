create sequence booking_seq start 1 increment 1;
create sequence device_seq start 1 increment 1;

    create table Booking (
       id int8 not null,
        actualDuration int8,
        actualStart timestamp,
        deviceId int8 not null,
        expectedDuration int8,
        intendedDuration int8 not null,
        scheduledStart timestamp,
        username varchar(255),
        version int4 not null,
        primary key (id)
    );

    create table Device (
       id int8 not null,
        type varchar(255),
        primary key (id)
    );

    create table User_deviceExpericence (
       User_username varchar(255) not null,
        deviceExpericence int8,
        deviceExpericence_KEY varchar(255) not null,
        primary key (User_username, deviceExpericence_KEY)
    );

    create table users (
       username varchar(255) not null,
        name varchar(255),
        password varchar(255),
        role varchar(255),
        primary key (username)
    );

    alter table if exists User_deviceExpericence 
       add constraint FKovaoyid9nj5m23h0q8y8ka408 
       foreign key (User_username) 
       references users;

INSERT INTO users (username, name, password, role) VALUES ('admin', 'Administrator', '$2a$10$YfY/ecjvrYqeFQ9xnuFLAe2EUhQBQkMal58kBUJx8sRbKL1VrbcC.', 'admin');
INSERT INTO users (username, name, password, role) VALUES ('user', 'User', '$2a$10$OfQWuRPsizS5HZJ7KSSMPuujQtu.ttm5X3PiWTuKoJ59At9TY8koe', 'user');
