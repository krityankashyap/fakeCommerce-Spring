CREATE TABLE category
(
    id         BIGINT       NOT NULL,
    created_at datetime     NOT NULL,
    updated_at datetime     NULL,
    deleted_at datetime     NULL,
    name       VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE order_product
(
    id         BIGINT   NOT NULL,
    created_at datetime NOT NULL,
    updated_at datetime NULL,
    deleted_at datetime NULL,
    quantity   INT      NULL,
    order_id   BIGINT   NOT NULL,
    product_id BIGINT   NOT NULL,
    CONSTRAINT pk_order_product PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id           BIGINT   NOT NULL,
    created_at   datetime NOT NULL,
    updated_at   datetime NULL,
    deleted_at   datetime NULL,
    order_status SMALLINT NULL,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

CREATE TABLE products
(
    id            BIGINT       NOT NULL,
    created_at    datetime     NOT NULL,
    updated_at    datetime     NULL,
    deleted_at    datetime     NULL,
    title         VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    price         DECIMAL      NULL,
    image         VARCHAR(255) NULL,
    category_id   BIGINT       NOT NULL,
    ratings       VARCHAR(255) NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

ALTER TABLE order_product
    ADD CONSTRAINT FK_ORDER_PRODUCT_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE order_product
    ADD CONSTRAINT FK_ORDER_PRODUCT_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);