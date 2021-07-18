CREATE TABLE IF NOT EXISTS hotels (
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    address VARCHAR(128),
    star_rating INTEGER
);

CREATE TABLE IF NOT EXISTS bookings (
    id SERIAL NOT NULL PRIMARY KEY,
    hotel_id INTEGER NOT NULL REFERENCES hotels(id),
    customer_name VARCHAR(128) NOT NULL,
    customer_surname VARCHAR(128) NOT NULL,
    num_of_pax INTEGER NOT NULL DEFAULT 1,
    price INTEGER NOT NULL DEFAULT 50 * num_of_pax,
    currency VARCHAR(16) NOT NULL DEFAULT 'EUR',
    UNIQUE(customer_name, customer_surname, hotel_id)
);