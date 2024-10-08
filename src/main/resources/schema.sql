DROP TABLE IF EXISTS prices;
DROP TABLE IF EXISTS brands;

CREATE TABLE brands
(
    id   int AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) NOT NULL
);

CREATE TABLE prices
(
    id int AUTO_INCREMENT PRIMARY KEY,
    brand_id   int REFERENCES brands (id),
    start_date varchar(50)      NOT NULL,
    end_date   varchar(50)      NOT NULL,
    price_list int           NOT NULL,
    product_id int           NOT NULL,
    priority   int           NOT NULL,
    price      decimal(6, 2) NOT NULL,
    curr       varchar(3)    NOT NULL,
    CHECK (start_date <= end_date)
);