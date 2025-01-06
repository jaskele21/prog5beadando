CREATE TABLE customers (
                           id serial PRIMARY KEY,
                           name VARCHAR(100) NOT NULL,
                           phone_number VARCHAR(100) NOT NULL UNIQUE,
                           password VARCHAR(100) NOT NULL,
                           key VARCHAR(255) NULL
);
CREATE TABLE tyres (
                       id serial PRIMARY KEY,
                       customer_id integer NOT NULL REFERENCES customers(id),
                       brand VARCHAR(100) NOT NULL,
                       war VARCHAR(100) NOT NULL,
                       quantity smallint NOT NULL
);
CREATE TABLE services (
                          id serial PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          description text NULL
);
CREATE TABLE appointments (
                        id serial PRIMARY KEY,
                        customer_id integer NOT NULL REFERENCES customers(id),
                        appointment timestamp with time zone NOT NULL,
                        service_id integer NOT NULL REFERENCES services(id)
);