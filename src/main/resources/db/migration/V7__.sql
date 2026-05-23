CREATE TABLE IF NOT EXISTS reviews
(
    id         BIGINT   NOT NULL,
    created_at datetime NOT NULL,
    updated_at datetime NULL,
    deleted_at datetime NULL,
    prod_id    BIGINT   NULL,
    CONSTRAINT pk_reviews PRIMARY KEY (id)
);

ALTER TABLE reviews
    ADD CONSTRAINT FK_REVIEWS_ON_PROD FOREIGN KEY (prod_id) REFERENCES products (id);
