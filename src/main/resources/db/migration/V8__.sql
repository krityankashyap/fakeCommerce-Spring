ALTER TABLE reviews
    ADD COLUMN rating DECIMAL(3,2) NOT NULL,
    ADD COLUMN comment TEXT,
    ADD COLUMN order_id BIGINT;

ALTER TABLE reviews
    ADD CONSTRAINT FK_REVIEWS_ON_ORDER
        FOREIGN KEY (order_id)
            REFERENCES orders (id);