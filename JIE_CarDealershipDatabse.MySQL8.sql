DROP DATABASE car_dealership;
CREATE DATABASE car_dealership;
USE car_dealership;

CREATE TABLE dealerships (
    dealership_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    address VARCHAR(50),
    phone VARCHAR(12)
);

CREATE TABLE vehicles (
    VIN VARCHAR(17) PRIMARY KEY,
    make VARCHAR(50),
    model VARCHAR(50),
    year INT,
    color VARCHAR(30),
    price DECIMAL(12,2),
    sold BOOLEAN
);

CREATE TABLE inventory (
    dealership_id INT,
    VIN VARCHAR(17),
    FOREIGN KEY (dealership_id) REFERENCES dealerships(dealership_id),
    FOREIGN KEY (VIN) REFERENCES vehicles(VIN)
);

CREATE TABLE sales_contracts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    VIN VARCHAR(17),
    sale_date DATE,
    sale_price DECIMAL(12,2),
    customer_name VARCHAR(50),
    FOREIGN KEY (VIN) REFERENCES vehicles(VIN)
);

CREATE TABLE lease_contracts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    VIN VARCHAR(17),
    lease_start DATE,
    lease_end DATE,
    monthly_payment DECIMAL(10,2),
    customer_name VARCHAR(50),
    FOREIGN KEY (VIN) REFERENCES vehicles(VIN)
);


-- name, address, phone--
INSERT INTO
	dealerships (name, address, phone)
VALUES
	('Gold Coast Supercars', '123 Luxury Blvd, Miami, FL', '3055551234'),
	('Prestige Auto Gallery', '456 Elite Dr, Beverly Hills, CA', '2135555678'),
	('Ultra Motors', '789 Dream Car Way, NYC, NY', '2125559999');
    
-- vin, make, model, year, color, price, sold--
INSERT INTO
	vehicles (VIN, make, model, year, color, price, sold)
VALUES
	('1HGCM82633A123456', 'Lamborghini', 'Aventador', 2022, 'Yellow', 500000.00, FALSE),
	('2HGCM82633A654321', 'Ferrari', 'SF90 Stradale', 2023, 'Red', 625000.00, FALSE),
	('3HGCM82633A987654', 'Bugatti', 'Chiron Super Sport', 2022, 'Blue', 3900000.00, FALSE),
	('4HGCM82633A111111', 'Rolls-Royce', 'Ghost', 2023, 'Black', 340000.00, FALSE),
	('5HGCM82633A222222', 'McLaren', 'P1', 2021, 'Orange', 1200000.00, FALSE),
	('6HGCM82633A333333', 'Koenigsegg', 'Jesko', 2023, 'White', 3100000.00, FALSE),
	('7HGCM82633A444444', 'Aston Martin', 'Valkyrie', 2023, 'Green', 3000000.00, FALSE);

INSERT INTO
	inventory (dealership_id, VIN)
VALUES
	(1, '1HGCM82633A123456'),  -- Lamborghini at Gold Coast Supercars
	(1, '2HGCM82633A654321'),  -- Ferrari at Gold Coast Supercars
	(2, '3HGCM82633A987654'),  -- Bugatti at Prestige Auto Gallery
	(2, '4HGCM82633A111111'),  -- Rolls-Royce at Prestige Auto Gallery
	(3, '5HGCM82633A222222'),  -- McLaren at Ultra Motors
	(3, '6HGCM82633A333333'),  -- Koenigsegg at Ultra Motors
	(1, '7HGCM82633A444444');  -- Aston Martin at Gold Coast Supercars

INSERT INTO 
	sales_contracts (VIN, sale_date, sale_price, customer_name) 
VALUES
	('1HGCM82633A123456', '2024-11-10', 490000.00, 'Elon R. Richman');

UPDATE
	vehicles
SET 
	sold = TRUE WHERE VIN = '1HGCM82633A123456';

INSERT INTO
	lease_contracts (VIN, lease_start, lease_end, monthly_payment, customer_name)
VALUES
	('2HGCM82633A654321', '2024-10-01', '2026-10-01', 8500.00, 'Sophia V. Luxe'),   -- Ferrari SF90
	('4HGCM82633A111111', '2025-01-15', '2027-01-15', 4200.00, 'James Goldsmith'),  -- Rolls-Royce Ghost
	('5HGCM82633A222222', '2024-07-01', '2026-07-01', 10500.00, 'Victoria Sterling'), -- McLaren P1
	('6HGCM82633A333333', '2025-03-10', '2027-03-10', 15000.00, 'Dmitri Kozlov'),   -- Koenigsegg Jesko
	('7HGCM82633A444444', '2024-08-20', '2026-08-20', 9800.00, 'Amara Blake');      -- Aston Martin Valkyrie

ALTER TABLE
	vehicles
ADD
	leased
BOOLEAN DEFAULT FALSE; -- Didn't know you could Alter tables with function command alter, i believe you can add and delete?

UPDATE
	vehicles
SET
	leased = TRUE WHERE VIN IN (
    '2HGCM82633A654321',
    '4HGCM82633A111111',
    '5HGCM82633A222222',
    '6HGCM82633A333333',
    '7HGCM82633A444444'
    );
	
-- Create test .sql scripts with the following queries to verify that your database
-- has been created and has been populated correctly. Each of these queries should
-- be in their own .sql file and SHOULD NOT be included in the main database
-- script.
-- 1. Get all dealerships
-- 2. Find all vehicles for a specific dealership
-- 3. Find a car by VIN
-- 4. Find the dealership where a certain car is located, by VIN
-- 5. Find all Dealerships that have a certain car type (i.e. Red Ford Mustang)
-- 6. Get all sales information for a specific dealer for a specific date range

    
