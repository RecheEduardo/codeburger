CREATE DATABASE IF NOT EXISTS hamburgueria_db;

USE hamburgueria_db;

CREATE TABLE IF NOT EXISTS usuarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    login VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(50) NOT NULL
);

INSERT INTO usuarios (nome, login, senha) VALUES ('Cleber', 'CleberSilvaProfessor', '1234');

SELECT * FROM usuarios;