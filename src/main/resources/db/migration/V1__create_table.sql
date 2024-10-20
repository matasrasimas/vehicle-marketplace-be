CREATE TABLE categories (
    id UNIQUEIDENTIFIER PRIMARY KEY,
    title NVARCHAR(MAX) NOT NULL
);

CREATE TABLE users (
    id UNIQUEIDENTIFIER PRIMARY KEY,
    first_name NVARCHAR(MAX) NOT NULL,
    last_name NVARCHAR(MAX) NOT NULL,
    phone_number NVARCHAR(MAX) NOT NULL,
    username NVARCHAR(MAX) NOT NULL,
    password_hash NVARCHAR(MAX) NOT NULL,
    role NVARCHAR(MAX) NOT NULL
);

CREATE TABLE posts (
    id UNIQUEIDENTIFIER PRIMARY KEY,
    category_id UNIQUEIDENTIFIER NOT NULL,
    user_id UNIQUEIDENTIFIER NOT NULL,
    description NVARCHAR(MAX),
    brand NVARCHAR(MAX) NOT NULL,
    model NVARCHAR(MAX) NOT NULL,
    manufacture_year INTEGER NOT NULL,
    mileage FLOAT, -- Use FLOAT instead of DOUBLE PRECISION
    price FLOAT NOT NULL, -- Same as above
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE comments (
    id UNIQUEIDENTIFIER PRIMARY KEY,
    post_id UNIQUEIDENTIFIER NOT NULL,
    user_id UNIQUEIDENTIFIER NOT NULL,
    content NVARCHAR(MAX) NOT NULL,
    rating INTEGER NOT NULL CHECK (rating BETWEEN 1 AND 5),
    FOREIGN KEY (post_id) REFERENCES posts(id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE NO ACTION ON UPDATE NO ACTION
);