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

INSERT INTO usuarios (nomeUsuario, loginUsuario, senhaUsuario) VALUES ('Cleber', 'CleberSilvaProfessor', '1234');
INSERT INTO usuarios (nomeUsuario, loginUsuario, senhaUsuario) VALUES ('Eduardo', 'Eduardo', '1234');
INSERT INTO usuarios (nomeUsuario, loginUsuario, senhaUsuario) VALUES ('Daniel', 'Daniel', '1234');

SELECT * FROM usuarios;