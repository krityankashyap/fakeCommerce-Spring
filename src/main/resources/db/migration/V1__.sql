CREATE TABLE category
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NULL,
    deleted_at DATETIME NULL,
    name VARCHAR(255) NOT NULL,

    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NULL,
    deleted_at DATETIME NULL,
    order_status SMALLINT NULL,

    CONSTRAINT pk_orders PRIMARY KEY (id)
);

CREATE TABLE products
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NULL,
    deleted_at DATETIME NULL,

    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    image VARCHAR(255) NOT NULL,
    ratings VARCHAR(255) NOT NULL,

    category_id BIGINT NOT NULL,

    CONSTRAINT pk_products PRIMARY KEY (id)
);

CREATE TABLE order_product
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NULL,
    deleted_at DATETIME NULL,

    quantity INT NOT NULL,

    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,

    CONSTRAINT pk_order_product PRIMARY KEY (id)
);

CREATE TABLE reviews
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NULL,
    deleted_at DATETIME NULL,

    rating DECIMAL(3,2) NOT NULL,
    comment TEXT NULL,

    prod_id BIGINT NULL,
    order_id BIGINT NULL,

    CONSTRAINT pk_reviews PRIMARY KEY (id)
);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CATEGORY
        FOREIGN KEY (category_id)
            REFERENCES category(id);

ALTER TABLE order_product
    ADD CONSTRAINT FK_ORDER_PRODUCT_ON_ORDER
        FOREIGN KEY (order_id)
            REFERENCES orders(id);

ALTER TABLE order_product
    ADD CONSTRAINT FK_ORDER_PRODUCT_ON_PRODUCT
        FOREIGN KEY (product_id)
            REFERENCES products(id);

ALTER TABLE reviews
    ADD CONSTRAINT FK_REVIEWS_ON_PROD
        FOREIGN KEY (prod_id)
            REFERENCES products(id);

ALTER TABLE reviews
    ADD CONSTRAINT FK_REVIEWS_ON_ORDER
        FOREIGN KEY (order_id)
            REFERENCES orders(id);