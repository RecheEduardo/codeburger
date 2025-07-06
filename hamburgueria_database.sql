DROP DATABASE hamburgueria_db;
CREATE DATABASE IF NOT EXISTS hamburgueria_db;
USE hamburgueria_db;

CREATE TABLE IF NOT EXISTS usuarios (
    idUsuario INT PRIMARY KEY AUTO_INCREMENT,
    nomeUsuario VARCHAR(100) NOT NULL,
    loginUsuario VARCHAR(50) NOT NULL UNIQUE,
    senhaUsuario VARCHAR(50) NOT NULL
);

CREATE TABLE produtos (
    idProduto INT AUTO_INCREMENT PRIMARY KEY,
    nomeProduto VARCHAR(100) NOT NULL,
    tipoProduto VARCHAR(30),
    preco DECIMAL(10,2) NOT NULL,
    descricao TEXT
);

CREATE TABLE pedidos (
    idPedido INT AUTO_INCREMENT PRIMARY KEY,
    idUsuario INT,
    dataPedido DATETIME DEFAULT CURRENT_TIMESTAMP,
    statusPedido VARCHAR(20) DEFAULT 'Pendente',
    FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario)
);

CREATE TABLE estoque (
    idItem INT AUTO_INCREMENT PRIMARY KEY,
    nomeItem VARCHAR(50) NOT NULL,
    quantidade INT NOT NULL,
    validade DATE,
    idFornecedor INT
    -- FOREIGN KEY (idFornecedor) REFERENCES fornecedores(idFornecedor)
);

INSERT INTO usuarios (nomeUsuario, loginUsuario, senhaUsuario) VALUES 
('Cleber', 'CleberProfessor', '1234'),
('Eduardo', 'Eduardo', '1234'),
('Daniel', 'Daniel', '1234');

INSERT INTO produtos (nomeProduto, tipoProduto, preco, descricao) VALUES 
('Burger Clássico', 'Sanduíche', 19.90, 'Pão artesanal, hambúrguer 180g, alface, tomate e maionese da casa'),
('Cheddar Bacon', 'Sanduíche', 24.90, 'Pão australiano, hambúrguer 180g, cheddar cremoso e bacon crocante'),
('Batata Rústica', 'Acompanhamento', 12.00, 'Batatas com casca temperadas com páprica e ervas'),
('Refrigerante Lata', 'Bebida', 6.00, 'Coca-Cola, Guaraná ou Sprite - 350ml'),
('Milkshake Oreo', 'Sobremesa', 16.50, 'Milkshake de baunilha com pedaços de Oreo e chantilly'),
('Double Smash', 'Sanduíche', 27.90, 'Dois smash burgers de 120g, queijo prato, cebola caramelizada e molho especial'),
('Chicken Crispy', 'Sanduíche', 22.90, 'Frango empanado crocante, alface, tomate e maionese da casa'),
('Onion Rings', 'Acompanhamento', 11.00, 'Anéis de cebola empanados e fritos, servidos com molho barbecue'),
('Nuggets Artesanais', 'Acompanhamento', 13.00, 'Porção com 8 unidades de nuggets de frango caseiro'),
('Água Mineral', 'Bebida', 4.00, 'Garrafa de 500ml com ou sem gás'),
('Refrigerante 1L', 'Bebida', 9.00, 'Coca-Cola ou Guaraná Antarctica 1 litro'),
('Milkshake Morango', 'Sobremesa', 15.00, 'Milkshake com sorvete de morango e calda especial'),
('Brownie com Sorvete', 'Sobremesa', 17.50, 'Brownie artesanal servido com bola de sorvete de creme'),
('Combo Clássico', 'Combo', 34.90, 'Burger Clássico + Batata Rústica + Refrigerante Lata'),
('Combo Chicken', 'Combo', 36.90, 'Chicken Crispy + Onion Rings + Refrigerante Lata');

INSERT INTO estoque (nomeItem, quantidade, validade, idFornecedor) VALUES
('Pão Brioche', 200, '2025-07-20', 1),
('Carne Bovina 180g', 150, '2025-07-15', 2),
('Queijo Cheddar', 100, '2025-07-18', 3),
('Bacon Fatiado', 80, '2025-07-12', 4),
('Alface Americana', 50, '2025-07-08', 5),
('Tomate Italiano', 60, '2025-07-09', 5),
('Batata Rústica', 120, '2025-07-30', 6),
('Óleo de Soja', 30, '2026-01-01', 7),
('Pão Australiano', 100, '2025-07-22', 1),
('Refrigerante Cola', 90, '2025-12-31', 8),
('Refrigerante Guaraná', 70, '2025-12-31', 8),
('Milkshake Baunilha', 40, '2025-08-10', 9),
('Oreo Triturado', 35, '2025-08-15', 9),
('Maionese da Casa', 60, '2025-07-14', 3),
('Molho Barbecue', 50, '2025-07-25', 3);

INSERT INTO pedidos (idUsuario, dataPedido, statusPedido) VALUES
(1, '2025-07-01 12:30:00', 'Pendente'),
(2, '2025-07-01 13:15:00', 'Entregue'),
(1, '2025-07-02 18:45:00', 'Cancelado'),
(3, '2025-07-03 11:10:00', 'Entregue'),
(2, '2025-07-03 19:30:00', 'Pendente'),
(1, '2025-07-04 12:00:00', 'Entregue'),
(2, '2025-07-04 12:45:00', 'Pendente'),
(3, '2025-07-05 13:10:00', 'Entregue'),
(1, '2025-07-05 14:00:00', 'Cancelado'),
(3, '2025-07-06 17:20:00', 'Entregue'),
(2, '2025-07-06 18:00:00', 'Pendente'),
(1, '2025-07-07 19:00:00', 'Entregue'),
(3, '2025-07-08 12:15:00', 'Entregue'),
(2, '2025-07-08 13:30:00', 'Cancelado'),
(1, '2025-07-09 14:45:00', 'Pendente');

SELECT * FROM usuarios;