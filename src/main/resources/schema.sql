-- Tabela de usuários
CREATE TABLE users (
    user_id BIGINT  PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Tabela de produtos
CREATE TABLE products (
    product_id BIGINT  PRIMARY KEY,
    name VARCHAR(255)
);

-- Tabela de pedidos
CREATE TABLE orders (
    order_id BIGINT  PRIMARY KEY,
    total DECIMAL(10, 2),
    date DATE NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_order_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);

-- Tabela de itens do pedido
CREATE TABLE order_item (
    id BIGINT  PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    product_value DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_order_item_order FOREIGN KEY (order_id) REFERENCES orders (order_id),
    CONSTRAINT fk_order_item_product FOREIGN KEY (product_id) REFERENCES products (product_id)
);
