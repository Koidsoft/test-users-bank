
CREATE TABLE users (
       id VARCHAR(80) PRIMARY KEY,
       email VARCHAR(80) NOT NULL UNIQUE,
       name VARCHAR(60) NOT NULL,
       password VARCHAR(15),
       enable BOOLEAN NOT NULL DEFAULT TRUE,
       last_login TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       token VARCHAR(80),
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE user_phones (
       id VARCHAR(80) PRIMARY KEY,
       id_user VARCHAR(80) NOT NULL,
       phone VARCHAR(17) NOT NULL,
       city_code VARCHAR(5) NOT NULL,
       country_code VARCHAR(5) NOT NULL,
       CONSTRAINT fk_users_phone foreign key (id_user) references users(id) on DELETE CASCADE
);