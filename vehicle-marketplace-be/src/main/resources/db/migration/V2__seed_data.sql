CREATE EXTENSION IF NOT EXISTS "pgcrypto";

INSERT INTO categories (id, title) VALUES
    (gen_random_uuid(), 'Cars'),
    (gen_random_uuid(), 'Bikes'),
    (gen_random_uuid(), 'Trucks'),
    (gen_random_uuid(), 'SUVs'),
    (gen_random_uuid(), 'Vans');


INSERT INTO users (id, first_name, last_name, phone_number, username, password_hash, role)
VALUES
    (gen_random_uuid(), 'John', 'Doe', '+1-202-555-0171', 'johndoe', 'password123', 'USER'),
    (gen_random_uuid(), 'Jane', 'Smith', '+44 7911 123456', 'janesmith', 'janepassword', 'USER'),
    (gen_random_uuid(), 'Robert', 'Brown', '(555) 555-1234', 'robertbrown', 'roberthash', 'USER'),
    (gen_random_uuid(), 'Admin', 'Admin', '555-867-5309', 'admin', 'admin', 'ADMIN');


INSERT INTO posts (id, category_id, user_id, description, brand, model, manufacture_year, mileage, price) VALUES
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Cars' LIMIT 1), (SELECT id FROM users WHERE username = 'johndoe' LIMIT 1), '2020 Toyota Camry', 'Toyota', 'Camry', 2020, 15000, 24000.99),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Cars' LIMIT 1), (SELECT id FROM users WHERE username = 'johndoe' LIMIT 1), '2018 Honda Accord', 'Honda', 'Accord', 2018, 25000, 22000.50),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Bikes' LIMIT 1), (SELECT id FROM users WHERE username = 'johndoe' LIMIT 1), '2021 Harley Davidson Sportster', 'Harley Davidson', 'Sportster', 2021, 5000, 12000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Bikes' LIMIT 1), (SELECT id FROM users WHERE username = 'johndoe' LIMIT 1), '2020 Yamaha YZF-R3', 'Yamaha', 'YZF-R3', 2020, 1000, 8000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'SUVs' LIMIT 1), (SELECT id FROM users WHERE username = 'johndoe' LIMIT 1), '2019 Ford Explorer', 'Ford', 'Explorer', 2019, 30000, 32000.75),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Vans' LIMIT 1), (SELECT id FROM users WHERE username = 'johndoe' LIMIT 1), '2022 Honda Odyssey', 'Honda', 'Odyssey', 2022, 1000, 36000.99),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Trucks' LIMIT 1), (SELECT id FROM users WHERE username = 'johndoe' LIMIT 1), '2020 Ford F-150', 'Ford', 'F-150', 2020, 20000, 35000.50),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Cars' LIMIT 1), (SELECT id FROM users WHERE username = 'johndoe' LIMIT 1), '2021 Nissan Altima', 'Nissan', 'Altima', 2021, 12000, 24000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Cars' LIMIT 1), (SELECT id FROM users WHERE username = 'johndoe' LIMIT 1), '2019 Chevrolet Malibu', 'Chevrolet', 'Malibu', 2019, 23000, 18000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Bikes' LIMIT 1), (SELECT id FROM users WHERE username = 'johndoe' LIMIT 1), '2019 Kawasaki Ninja', 'Kawasaki', 'Ninja', 2019, 2000, 9500.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Bikes' LIMIT 1), (SELECT id FROM users WHERE username = 'johndoe' LIMIT 1), '2022 Ducati Panigale', 'Ducati', 'Panigale', 2022, 500, 22000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'SUVs' LIMIT 1), (SELECT id FROM users WHERE username = 'johndoe' LIMIT 1), '2021 Toyota RAV4', 'Toyota', 'RAV4', 2021, 10000, 29000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'SUVs' LIMIT 1), (SELECT id FROM users WHERE username = 'janesmith' LIMIT 1),  '2020 Jeep Grand Cherokee', 'Jeep', 'Grand Cherokee', 2020, 15000, 35000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Vans' LIMIT 1), (SELECT id FROM users WHERE username = 'janesmith' LIMIT 1), '2021 Chrysler Pacifica', 'Chrysler', 'Pacifica', 2021, 8000, 32000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Vans' LIMIT 1), (SELECT id FROM users WHERE username = 'janesmith' LIMIT 1), '2019 Toyota Sienna', 'Toyota', 'Sienna', 2019, 20000, 28000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Trucks' LIMIT 1), (SELECT id FROM users WHERE username = 'janesmith' LIMIT 1), '2021 Chevrolet Silverado', 'Chevrolet', 'Silverado', 2021, 12000, 45000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Trucks' LIMIT 1), (SELECT id FROM users WHERE username = 'janesmith' LIMIT 1), '2020 RAM 1500', 'RAM', '1500', 2020, 15000, 40000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Cars' LIMIT 1), (SELECT id FROM users WHERE username = 'janesmith' LIMIT 1), '2022 Hyundai Sonata', 'Hyundai', 'Sonata', 2022, 5000, 25000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Cars' LIMIT 1), (SELECT id FROM users WHERE username = 'janesmith' LIMIT 1), '2020 Ford Fusion', 'Ford', 'Fusion', 2020, 18000, 21000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Bikes' LIMIT 1), (SELECT id FROM users WHERE username = 'janesmith' LIMIT 1), '2021 KTM 390 Duke', 'KTM', '390 Duke', 2021, 1000, 7500.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Bikes' LIMIT 1), (SELECT id FROM users WHERE username = 'janesmith' LIMIT 1), '2020 BMW S1000RR', 'BMW', 'S1000RR', 2020, 3000, 21000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'SUVs' LIMIT 1), (SELECT id FROM users WHERE username = 'janesmith' LIMIT 1), '2022 Ford Bronco', 'Ford', 'Bronco', 2022, 2000, 41000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'SUVs' LIMIT 1), (SELECT id FROM users WHERE username = 'janesmith' LIMIT 1), '2021 Honda CR-V', 'Honda', 'CR-V', 2021, 12000, 29000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Vans' LIMIT 1), (SELECT id FROM users WHERE username = 'janesmith' LIMIT 1), '2022 Kia Carnival', 'Kia', 'Carnival', 2022, 500, 35000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Trucks' LIMIT 1), (SELECT id FROM users WHERE username = 'janesmith' LIMIT 1), '2022 Ford F-250', 'Ford', 'F-250', 2022, 8000, 55000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Cars' LIMIT 1), (SELECT id FROM users WHERE username = 'janesmith' LIMIT 1), '2005 Toyota Corolla', 'Toyota', 'Corolla', 2005, 120000, 8000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Cars' LIMIT 1), (SELECT id FROM users WHERE username = 'janesmith' LIMIT 1), '2007 Honda Civic', 'Honda', 'Civic', 2007, 95000, 8500.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Cars' LIMIT 1), (SELECT id FROM users WHERE username = 'janesmith' LIMIT 1), '2009 Ford Fusion', 'Ford', 'Fusion', 2009, 70000, 10000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Cars' LIMIT 1), (SELECT id FROM users WHERE username = 'robertbrown' LIMIT 1), '2010 Chevrolet Malibu', 'Chevrolet', 'Malibu', 2010, 60000, 11000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'SUVs' LIMIT 1), (SELECT id FROM users WHERE username = 'robertbrown' LIMIT 1),  '2006 Jeep Grand Cherokee', 'Jeep', 'Grand Cherokee', 2006, 110000, 14000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'SUVs' LIMIT 1), (SELECT id FROM users WHERE username = 'robertbrown' LIMIT 1), '2008 Toyota RAV4', 'Toyota', 'RAV4', 2008, 85000, 13000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Vans' LIMIT 1), (SELECT id FROM users WHERE username = 'robertbrown' LIMIT 1), '2007 Honda Odyssey', 'Honda', 'Odyssey', 2007, 95000, 12000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Vans' LIMIT 1), (SELECT id FROM users WHERE username = 'robertbrown' LIMIT 1), '2005 Chrysler Town & Country', 'Chrysler', 'Town & Country', 2005, 105000, 10000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Trucks' LIMIT 1), (SELECT id FROM users WHERE username = 'robertbrown' LIMIT 1), '2008 Ford F-150', 'Ford', 'F-150', 2008, 80000, 16000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Trucks' LIMIT 1), (SELECT id FROM users WHERE username = 'robertbrown' LIMIT 1), '2010 Chevrolet Silverado', 'Chevrolet', 'Silverado', 2010, 65000, 18000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Bikes' LIMIT 1), (SELECT id FROM users WHERE username = 'robertbrown' LIMIT 1), '2004 Harley Davidson Sportster', 'Harley Davidson', 'Sportster', 2004, 20000, 7500.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Bikes' LIMIT 1), (SELECT id FROM users WHERE username = 'robertbrown' LIMIT 1), '2008 Yamaha YZF-R6', 'Yamaha', 'YZF-R6', 2008, 15000, 8500.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'SUVs' LIMIT 1), (SELECT id FROM users WHERE username = 'robertbrown' LIMIT 1), '2005 Ford Escape', 'Ford', 'Escape', 2005, 120000, 9000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Cars' LIMIT 1), (SELECT id FROM users WHERE username = 'robertbrown' LIMIT 1), '2003 Nissan Altima', 'Nissan', 'Altima', 2003, 110000, 7000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Cars' LIMIT 1), (SELECT id FROM users WHERE username = 'robertbrown' LIMIT 1), '2000 Honda Accord', 'Honda', 'Accord', 2000, 130000, 6000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Cars' LIMIT 1), (SELECT id FROM users WHERE username = 'robertbrown' LIMIT 1), '2001 Chevrolet Impala', 'Chevrolet', 'Impala', 2001, 120000, 5500.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Cars' LIMIT 1), (SELECT id FROM users WHERE username = 'admin' LIMIT 1), '2002 Nissan Sentra', 'Nissan', 'Sentra', 2002, 115000, 5000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Cars' LIMIT 1), (SELECT id FROM users WHERE username = 'admin' LIMIT 1), '2003 Ford Taurus', 'Ford', 'Taurus', 2003, 90000, 4500.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Cars' LIMIT 1), (SELECT id FROM users WHERE username = 'admin' LIMIT 1), '2004 Subaru Impreza', 'Subaru', 'Impreza', 2004, 100000, 7000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Cars' LIMIT 1), (SELECT id FROM users WHERE username = 'admin' LIMIT 1), '2006 Mazda 3', 'Mazda', 'Mazda 3', 2006, 80000, 8000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Cars' LIMIT 1), (SELECT id FROM users WHERE username = 'admin' LIMIT 1), '2008 Volkswagen Jetta', 'Volkswagen', 'Jetta', 2008, 60000, 9000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'Cars' LIMIT 1), (SELECT id FROM users WHERE username = 'admin' LIMIT 1), '2010 Kia Forte', 'Kia', 'Forte', 2010, 30000, 12000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'SUVs' LIMIT 1), (SELECT id FROM users WHERE username = 'admin' LIMIT 1), '2001 Ford Explorer', 'Ford', 'Explorer', 2001, 140000, 7000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'SUVs' LIMIT 1), (SELECT id FROM users WHERE username = 'admin' LIMIT 1), '2003 Honda CR-V', 'Honda', 'CR-V', 2003, 115000, 8000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'SUVs' LIMIT 1), (SELECT id FROM users WHERE username = 'admin' LIMIT 1), '2005 Nissan Pathfinder', 'Nissan', 'Pathfinder', 2005, 95000, 10000.00),
    (gen_random_uuid(), (SELECT id FROM categories WHERE title = 'SUVs' LIMIT 1), (SELECT id FROM users WHERE username = 'admin' LIMIT 1), '2006 Chevrolet Tahoe', 'Chevrolet', 'Tahoe', 2006, 80000, 18000.00);



INSERT INTO comments (id, post_id, user_id, content, rating) VALUES
    (gen_random_uuid(), (SELECT id FROM posts WHERE description = '2020 Toyota Camry' LIMIT 1), (SELECT id FROM users WHERE username = 'janesmith' LIMIT 1), 'Great car! Drives smoothly.', 5),
    (gen_random_uuid(), (SELECT id FROM posts WHERE description = '2020 Toyota Camry' LIMIT 1), (SELECT id FROM users WHERE username = 'janesmith' LIMIT 1), 'Interior could be better, but overall good.', 4),
    (gen_random_uuid(), (SELECT id FROM posts WHERE description = '2018 Honda Accord' LIMIT 1), (SELECT id FROM users WHERE username = 'janesmith' LIMIT 1), 'The fuel economy is amazing!', 5),
    (gen_random_uuid(), (SELECT id FROM posts WHERE description = '2018 Honda Accord' LIMIT 1), (SELECT id FROM users WHERE username = 'janesmith' LIMIT 1), 'Feels a bit outdated in terms of technology.', 3),
    (gen_random_uuid(), (SELECT id FROM posts WHERE description = '2021 Harley Davidson Sportster' LIMIT 1), (SELECT id FROM users WHERE username = 'janesmith' LIMIT 1), 'Best bike I’ve ever owned. Love the roar!', 5),
    (gen_random_uuid(), (SELECT id FROM posts WHERE description = '2020 Yamaha YZF-R3' LIMIT 1), (SELECT id FROM users WHERE username = 'robertbrown' LIMIT 1), 'Perfect for new riders, handles well.', 4),
    (gen_random_uuid(), (SELECT id FROM posts WHERE description = '2020 Yamaha YZF-R3' LIMIT 1), (SELECT id FROM users WHERE username = 'robertbrown' LIMIT 1), 'Not as fast as I expected.', 3),
    (gen_random_uuid(), (SELECT id FROM posts WHERE description = '2019 Ford Explorer' LIMIT 1), (SELECT id FROM users WHERE username = 'robertbrown' LIMIT 1), 'Very spacious and reliable.', 5),
    (gen_random_uuid(), (SELECT id FROM posts WHERE description = '2019 Ford Explorer' LIMIT 1), (SELECT id FROM users WHERE username = 'robertbrown' LIMIT 1), 'Could use better fuel efficiency.', 4),
    (gen_random_uuid(), (SELECT id FROM posts WHERE description = '2022 Honda Odyssey' LIMIT 1), (SELECT id FROM users WHERE username = 'robertbrown' LIMIT 1), 'Amazing family vehicle with plenty of space.', 5),
    (gen_random_uuid(), (SELECT id FROM posts WHERE description = '2022 Honda Odyssey' LIMIT 1), (SELECT id FROM users WHERE username = 'robertbrown' LIMIT 1), 'Not very fuel efficient for long trips.', 3),
    (gen_random_uuid(), (SELECT id FROM posts WHERE description = '2020 Ford F-150' LIMIT 1), (SELECT id FROM users WHERE username = 'janesmith' LIMIT 1), 'Great power and towing capacity.', 5),
    (gen_random_uuid(), (SELECT id FROM posts WHERE description = '2020 Ford F-150' LIMIT 1), (SELECT id FROM users WHERE username = 'johndoe' LIMIT 1), 'Interior could be more luxurious for the price.', 4),
    (gen_random_uuid(), (SELECT id FROM posts WHERE description = '2021 Nissan Altima' LIMIT 1), (SELECT id FROM users WHERE username = 'johndoe' LIMIT 1), 'Solid car but nothing extraordinary.', 4),
    (gen_random_uuid(), (SELECT id FROM posts WHERE description = '2019 Chevrolet Malibu' LIMIT 1), (SELECT id FROM users WHERE username = 'johndoe' LIMIT 1), 'Affordable and reliable, great for daily use.', 4),
    (gen_random_uuid(), (SELECT id FROM posts WHERE description = '2019 Kawasaki Ninja' LIMIT 1), (SELECT id FROM users WHERE username = 'johndoe' LIMIT 1), 'Incredible performance and handling.', 5),
    (gen_random_uuid(), (SELECT id FROM posts WHERE description = '2019 Kawasaki Ninja' LIMIT 1), (SELECT id FROM users WHERE username = 'johndoe' LIMIT 1), 'Too aggressive for daily commuting.', 3);


