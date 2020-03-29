INSERT INTO category (name,description) VALUES ('Books','Books category');
SET @category_id = LAST_INSERT_ID();

INSERT IGNORE INTO product (name,price, description, sku) VALUES ('The art of Tao',15.45,'A meditation journey',13345);
SET @product_id = LAST_INSERT_ID();
INSERT INTO category_products (categories_id,products_id) VALUES(@category_id, @product_id);

INSERT IGNORE INTO product (name,price, description, sku) VALUES ('Leonardo Da Vinci',42.98,'Great thinkers',34534);
SET @product_id = LAST_INSERT_ID();
INSERT INTO category_products (categories_id,products_id) VALUES(@category_id, @product_id);

INSERT IGNORE INTO product (name,price, description, sku) VALUES ('Sapiens',37.83,'Human history',7567546);
SET @product_id = LAST_INSERT_ID();
INSERT INTO category_products (categories_id,products_id) VALUES(@category_id, @product_id);

INSERT INTO category (name,description) VALUES ('Electronics','Electronics category');
SET @category_id = LAST_INSERT_ID();

INSERT IGNORE INTO product (name,price, description, sku) VALUES ('Beats headphones',150.45,'Great sound!',13345);
SET @product_id = LAST_INSERT_ID();
INSERT INTO category_products (categories_id,products_id) VALUES(@category_id, @product_id);

INSERT IGNORE INTO product (name,price, description, sku) VALUES ('iPhone 8',750.62,'This is a the latest Apple phone',34534);
SET @product_id = LAST_INSERT_ID();
INSERT INTO category_products (categories_id,products_id) VALUES(@category_id, @product_id);

INSERT IGNORE INTO product (name,price, description, sku) VALUES ('Ring camera',99.78,'Protect your house!',7567546);
SET @product_id = LAST_INSERT_ID();
INSERT INTO category_products (categories_id,products_id) VALUES(@category_id, @product_id);

INSERT IGNORE INTO product (name,price, description, sku) VALUES ('Samsung tv',600.23,'Great image!',13345);
SET @product_id = LAST_INSERT_ID();
INSERT INTO category_products (categories_id,products_id) VALUES(@category_id, @product_id);

INSERT IGNORE INTO product (name,price, description, sku) VALUES ('Alexa11124444',1000.54,'The best personal assistant!',34534);
SET @product_id = LAST_INSERT_ID();
INSERT INTO category_products (categories_id,products_id) VALUES(@category_id, @product_id);

INSERT IGNORE INTO product (name,price, description, sku) VALUES ('iBot',890.55,'This robot can do anything!',7567546);
SET @product_id = LAST_INSERT_ID();
INSERT INTO category_products (categories_id,products_id) VALUES(@category_id, @product_id);
