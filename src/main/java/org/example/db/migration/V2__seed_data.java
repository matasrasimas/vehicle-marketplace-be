package org.example.db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;

public class V2__seed_data extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        Connection connection = context.getConnection();
        DSLContext dsl = DSL.using(connection, SQLDialect.DEFAULT);

        dsl.execute("""
                
                INSERT INTO categories (id, title) VALUES
                    (NEWID(), 'Cars'),
                    (NEWID(), 'Bikes'),
                    (NEWID(), 'Trucks'),
                    (NEWID(), 'SUVs'),
                    (NEWID(), 'Vans');
                
                
                INSERT INTO users (id, first_name, last_name, phone_number, username, password_hash, role)
                VALUES
                    (NEWID(), 'John', 'Doe', '+1-202-555-0171', 'johndoe', 'password123', 'USER'),
                    (NEWID(), 'Jane', 'Smith', '+44 7911 123456', 'janesmith', 'janepassword', 'USER'),
                    (NEWID(), 'Robert', 'Brown', '(555) 555-1234', 'robertbrown', 'roberthash', 'USER'),
                    (NEWID(), 'Admin', 'Admin', '555-867-5309', 'admin', 'admin', 'ADMIN');
                
                INSERT INTO posts (id, category_id, user_id, description, brand, model, manufacture_year, mileage, price) VALUES
                    (NEWID(), (SELECT TOP 1 id FROM categories WHERE CAST(title AS NVARCHAR(MAX)) = 'Cars'), (SELECT TOP 1 id FROM users WHERE username = 'johndoe'), '2020 Toyota Camry', 'Toyota', 'Camry', 2020, 15000, 24000.99),
                    (NEWID(), (SELECT TOP 1 id FROM categories WHERE CAST(title AS NVARCHAR(MAX)) = 'Cars'), (SELECT TOP 1 id FROM users WHERE username = 'johndoe'), '2018 Honda Accord', 'Honda', 'Accord', 2018, 25000, 22000.50),
                    (NEWID(), (SELECT TOP 1 id FROM categories WHERE CAST(title AS NVARCHAR(MAX)) = 'Bikes'), (SELECT TOP 1 id FROM users WHERE username = 'johndoe'), '2021 Harley Davidson Sportster', 'Harley Davidson', 'Sportster', 2021, 5000, 12000.00),
                    (NEWID(), (SELECT TOP 1 id FROM categories WHERE CAST(title AS NVARCHAR(MAX)) = 'Bikes'), (SELECT TOP 1 id FROM users WHERE username = 'johndoe'), '2020 Yamaha YZF-R3', 'Yamaha', 'YZF-R3', 2020, 1000, 8000.00),
                    (NEWID(), (SELECT TOP 1 id FROM categories WHERE CAST(title AS NVARCHAR(MAX)) = 'SUVs'), (SELECT TOP 1 id FROM users WHERE username = 'johndoe'), '2019 Ford Explorer', 'Ford', 'Explorer', 2019, 30000, 32000.75),
                    (NEWID(), (SELECT TOP 1 id FROM categories WHERE CAST(title AS NVARCHAR(MAX)) = 'Vans'), (SELECT TOP 1 id FROM users WHERE username = 'johndoe'), '2022 Honda Odyssey', 'Honda', 'Odyssey', 2022, 1000, 36000.99),
                    (NEWID(), (SELECT TOP 1 id FROM categories WHERE CAST(title AS NVARCHAR(MAX)) = 'Trucks'), (SELECT TOP 1 id FROM users WHERE username = 'johndoe'), '2020 Ford F-150', 'Ford', 'F-150', 2020, 20000, 35000.50),
                    (NEWID(), (SELECT TOP 1 id FROM categories WHERE CAST(title AS NVARCHAR(MAX)) = 'Cars'), (SELECT TOP 1 id FROM users WHERE username = 'johndoe'), '2021 Nissan Altima', 'Nissan', 'Altima', 2021, 12000, 24000.00),
                    (NEWID(), (SELECT TOP 1 id FROM categories WHERE CAST(title AS NVARCHAR(MAX)) = 'Cars'), (SELECT TOP 1 id FROM users WHERE username = 'johndoe'), '2019 Chevrolet Malibu', 'Chevrolet', 'Malibu', 2019, 23000, 18000.00),
                    (NEWID(), (SELECT TOP 1 id FROM categories WHERE CAST(title AS NVARCHAR(MAX)) = 'Bikes'), (SELECT TOP 1 id FROM users WHERE username = 'johndoe'), '2019 Kawasaki Ninja', 'Kawasaki', 'Ninja', 2019, 2000, 9500.00),
                    (NEWID(), (SELECT TOP 1 id FROM categories WHERE CAST(title AS NVARCHAR(MAX)) = 'Bikes'), (SELECT TOP 1 id FROM users WHERE username = 'johndoe'), '2022 Ducati Panigale', 'Ducati', 'Panigale', 2022, 500, 22000.00),
                    (NEWID(), (SELECT TOP 1 id FROM categories WHERE CAST(title AS NVARCHAR(MAX)) = 'SUVs'), (SELECT TOP 1 id FROM users WHERE username = 'robertbrown'), '2005 Ford Escape', 'Ford', 'Escape', 2005, 120000, 9000.00),
                    (NEWID(), (SELECT TOP 1 id FROM categories WHERE CAST(title AS NVARCHAR(MAX)) = 'Cars'), (SELECT TOP 1 id FROM users WHERE username = 'robertbrown'), '2003 Nissan Altima', 'Nissan', 'Altima', 2003, 110000, 7000.00),
                    (NEWID(), (SELECT TOP 1 id FROM categories WHERE CAST(title AS NVARCHAR(MAX)) = 'Cars'), (SELECT TOP 1 id FROM users WHERE username = 'robertbrown'), '2000 Honda Accord', 'Honda', 'Accord', 2000, 130000, 6000.00),
                    (NEWID(), (SELECT TOP 1 id FROM categories WHERE CAST(title AS NVARCHAR(MAX)) = 'Cars'), (SELECT TOP 1 id FROM users WHERE username = 'robertbrown'), '2001 Chevrolet Impala', 'Chevrolet', 'Impala', 2001, 120000, 5500.00),
                    (NEWID(), (SELECT TOP 1 id FROM categories WHERE CAST(title AS NVARCHAR(MAX)) = 'Cars'), (SELECT TOP 1 id FROM users WHERE username = 'admin'), '2002 Nissan Sentra', 'Nissan', 'Sentra', 2002, 115000, 5000.00),
                    (NEWID(), (SELECT TOP 1 id FROM categories WHERE CAST(title AS NVARCHAR(MAX)) = 'Cars'), (SELECT TOP 1 id FROM users WHERE username = 'admin'), '2003 Ford Taurus', 'Ford', 'Taurus', 2003, 90000, 4500.00),
                    (NEWID(), (SELECT TOP 1 id FROM categories WHERE CAST(title AS NVARCHAR(MAX)) = 'Cars'), (SELECT TOP 1 id FROM users WHERE username = 'admin'), '2004 Subaru Impreza', 'Subaru', 'Impreza', 2004, 100000, 7000.00),
                    (NEWID(), (SELECT TOP 1 id FROM categories WHERE CAST(title AS NVARCHAR(MAX)) = 'Cars'), (SELECT TOP 1 id FROM users WHERE username = 'admin'), '2006 Mazda 3', 'Mazda', 'Mazda 3', 2006, 80000, 8000.00),
                    (NEWID(), (SELECT TOP 1 id FROM categories WHERE CAST(title AS NVARCHAR(MAX)) = 'Cars'), (SELECT TOP 1 id FROM users WHERE username = 'admin'), '2008 Volkswagen Jetta', 'Volkswagen', 'Jetta', 2008, 60000, 9000.00),
                    (NEWID(), (SELECT TOP 1 id FROM categories WHERE CAST(title AS NVARCHAR(MAX)) = 'Cars'), (SELECT TOP 1 id FROM users WHERE username = 'admin'), '2010 Kia Forte', 'Kia', 'Forte', 2010, 30000, 12000.00),
                    (NEWID(), (SELECT TOP 1 id FROM categories WHERE CAST(title AS NVARCHAR(MAX)) = 'SUVs'), (SELECT TOP 1 id FROM users WHERE username = 'admin'), '2001 Ford Explorer', 'Ford', 'Explorer', 2001, 140000, 7000.00),
                    (NEWID(), (SELECT TOP 1 id FROM categories WHERE CAST(title AS NVARCHAR(MAX)) = 'SUVs'), (SELECT TOP 1 id FROM users WHERE username = 'admin'), '2003 Honda CR-V', 'Honda', 'CR-V', 2003, 115000, 8000.00),
                    (NEWID(), (SELECT TOP 1 id FROM categories WHERE CAST(title AS NVARCHAR(MAX)) = 'SUVs'), (SELECT TOP 1 id FROM users WHERE username = 'admin'), '2005 Nissan Pathfinder', 'Nissan', 'Pathfinder', 2005, 95000, 10000.00);
                
                INSERT INTO comments (id, post_id, user_id, content, rating) VALUES
                    (NEWID(), (SELECT TOP 1 id FROM posts WHERE CAST(description AS NVARCHAR(MAX)) = '2020 Toyota Camry'), (SELECT TOP 1 id FROM users WHERE username = 'janesmith'), 'Great car! Drives smoothly.', 5),
                    (NEWID(), (SELECT TOP 1 id FROM posts WHERE CAST(description AS NVARCHAR(MAX)) = '2020 Toyota Camry'), (SELECT TOP 1 id FROM users WHERE username = 'janesmith'), 'Interior could be better, but overall good.', 4),
                    (NEWID(), (SELECT TOP 1 id FROM posts WHERE CAST(description AS NVARCHAR(MAX)) = '2018 Honda Accord'), (SELECT TOP 1 id FROM users WHERE username = 'janesmith'), 'The fuel economy is amazing!', 5),
                    (NEWID(), (SELECT TOP 1 id FROM posts WHERE CAST(description AS NVARCHAR(MAX)) = '2018 Honda Accord'), (SELECT TOP 1 id FROM users WHERE username = 'janesmith'), 'Feels a bit outdated in terms of technology.', 3),
                    (NEWID(), (SELECT TOP 1 id FROM posts WHERE CAST(description AS NVARCHAR(MAX)) = '2021 Harley Davidson Sportster'), (SELECT TOP 1 id FROM users WHERE username = 'janesmith'), 'Best bike I’ve ever owned. Love the roar!', 5),
                    (NEWID(), (SELECT TOP 1 id FROM posts WHERE CAST(description AS NVARCHAR(MAX)) = '2020 Yamaha YZF-R3'), (SELECT TOP 1 id FROM users WHERE username = 'robertbrown'), 'Perfect for new riders, handles well.', 4),
                    (NEWID(), (SELECT TOP 1 id FROM posts WHERE CAST(description AS NVARCHAR(MAX)) = '2020 Yamaha YZF-R3'), (SELECT TOP 1 id FROM users WHERE username = 'robertbrown'), 'Not as fast as I expected.', 3),
                    (NEWID(), (SELECT TOP 1 id FROM posts WHERE CAST(description AS NVARCHAR(MAX)) = '2019 Ford Explorer'), (SELECT TOP 1 id FROM users WHERE username = 'robertbrown'), 'Very spacious and reliable.', 5),
                    (NEWID(), (SELECT TOP 1 id FROM posts WHERE CAST(description AS NVARCHAR(MAX)) = '2019 Ford Explorer'), (SELECT TOP 1 id FROM users WHERE username = 'robertbrown'), 'Could use better fuel efficiency.', 4),
                    (NEWID(), (SELECT TOP 1 id FROM posts WHERE CAST(description AS NVARCHAR(MAX)) = '2022 Honda Odyssey'), (SELECT TOP 1 id FROM users WHERE username = 'robertbrown'), 'Amazing family vehicle with plenty of space.', 5),
                    (NEWID(), (SELECT TOP 1 id FROM posts WHERE CAST(description AS NVARCHAR(MAX)) = '2022 Honda Odyssey'), (SELECT TOP 1 id FROM users WHERE username = 'robertbrown'), 'Not very fuel efficient for long trips.', 3),
                    (NEWID(), (SELECT TOP 1 id FROM posts WHERE CAST(description AS NVARCHAR(MAX)) = '2020 Ford F-150'), (SELECT TOP 1 id FROM users WHERE username = 'janesmith'), 'Great power and towing capacity.', 5),
                    (NEWID(), (SELECT TOP 1 id FROM posts WHERE CAST(description AS NVARCHAR(MAX)) = '2020 Ford F-150'), (SELECT TOP 1 id FROM users WHERE username = 'johndoe'), 'Interior could be more luxurious for the price.', 4),
                    (NEWID(), (SELECT TOP 1 id FROM posts WHERE CAST(description AS NVARCHAR(MAX)) = '2021 Nissan Altima'), (SELECT TOP 1 id FROM users WHERE username = 'johndoe'), 'Solid car but nothing extraordinary.', 4),
                    (NEWID(), (SELECT TOP 1 id FROM posts WHERE CAST(description AS NVARCHAR(MAX)) = '2019 Chevrolet Malibu'), (SELECT TOP 1 id FROM users WHERE username = 'johndoe'), 'Affordable and reliable, great for daily use.', 4),
                    (NEWID(), (SELECT TOP 1 id FROM posts WHERE CAST(description AS NVARCHAR(MAX)) = '2019 Kawasaki Ninja'), (SELECT TOP 1 id FROM users WHERE username = 'johndoe'), 'Incredible performance and handling.', 5),
                    (NEWID(), (SELECT TOP 1 id FROM posts WHERE CAST(description AS NVARCHAR(MAX)) = '2019 Kawasaki Ninja'), (SELECT TOP 1 id FROM users WHERE username = 'johndoe'), 'Too aggressive for daily commuting.', 3);
                
                
                
                
                """);
    }
}
