CREATE TABLE products
(
    id            BIGINT       NOT NULL,
    title         VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    price         DECIMAL      NULL,
    image         VARCHAR(255) NULL,
    category      VARCHAR(255) NULL,
    ratings       VARCHAR(255) NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);