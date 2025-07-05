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
('Cleber', 'CleberSilvaProfessor', '1234'),
('Eduardo', 'Eduardo', '1234'),
('Daniel', 'Daniel', '1234');

INSERT INTO produtos (nomeProduto, tipoProduto, preco, descricao) VALUES 
('Burger Clássico', 'Sanduíche', 19.90, 'Pão artesanal, hambúrguer 180g, alface, tomate e maionese da casa'),
('Cheddar Bacon', 'Sanduíche', 24.90, 'Pão australiano, hambúrguer 180g, cheddar cremoso e bacon crocante'),
('Batata Rústica', 'Acompanhamento', 12.00, 'Batatas com casca temperadas com páprica e ervas'),
('Refrigerante Lata', 'Bebida', 6.00, 'Coca-Cola, Guaraná ou Sprite - 350ml'),
('Milkshake Oreo', 'Sobremesa', 16.50, 'Milkshake de baunilha com pedaços de Oreo e chantilly');


SELECT * FROM usuarios;

DROP DATABASE hamburgueria_db;