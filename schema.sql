#DROP DATABASE IF EXISTS projeto_final;
CREATE DATABASE projeto_final;
USE projeto_final;

CREATE TABLE usuario (
    id_usuario INT(11) NOT NULL AUTO_INCREMENT,
    nome VARCHAR(70) NOT NULL,
    email VARCHAR(90) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    senha VARCHAR(90) NOT NULL,
    role VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_usuario)
);

CREATE TABLE cliente (
    id_cliente INT(11) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(90) NOT NULL
);

CREATE TABLE pedidos (
    id_pedido INT(11) PRIMARY KEY AUTO_INCREMENT,
    nome_pedido VARCHAR(200) NOT NULL,
    peso FLOAT NOT NULL,
    quantidade INT(11) NOT NULL,
    status ENUM('Pedido Solicitado', 'Em Produção', 'Disponivel para Despacho', 'Em Rota', 'Entregue', 'Avaria na Carga', 'Incidente de Transporte') NOT NULL,
    codigo VARCHAR(890) NOT NULL,
    id_cliente INT(11),
    id_entregador INT(11) NULL,
    token VARCHAR(10) NULL,
    localizacao_atual VARCHAR(100) NULL,
    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
    FOREIGN KEY (id_entregador) REFERENCES usuario(id_usuario)
);

CREATE TABLE incidentes (
    id_incidente INT(11) PRIMARY KEY AUTO_INCREMENT,
    id_pedido INT(11) NOT NULL,
    tipo ENUM('Problema no Caminhão', 'Problema no Produto') NOT NULL,
    descricao VARCHAR(255),
    acao_tomada ENUM('Novo Caminhão Enviado', 'Nova Remessa em Produção') NOT NULL,
    data_ocorrencia DATETIME NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido)
);

CREATE TABLE historico_pedido (
    id_historico INT(11) PRIMARY KEY AUTO_INCREMENT,
    id_pedido INT(11) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    data_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido)
);

INSERT INTO cliente (nome, email) VALUES
('João da Silva', 'joao@email.com'),
('Maria Souza', 'maria@email.com'),
('Pedro Santos', 'pedro@email.com');

INSERT INTO usuario (nome, email, telefone, senha, role) VALUES
('Carlos Motorista', 'carlos@entregas.com', '11999999999', '123456', 'Entregador'),
('Marcos Silva', 'marcos@gmail.com', '11987654321', '123', 'Entregador'),
('Ana Souza', 'ana@gmail.com', '11981234567', '123', 'Entregador'),
('Fernanda Oliveira', 'fernanda@gmail.com', '43999887766', '123', 'Operador Logistico'),
('Ricardo Santos', 'ricardo@gmail.com', '43988776655', '123', 'Entregador'),
('Juliana Costa', 'juliana@gmail.com', '11995554433', '123', 'Operador Logistico'),
('Pedro Almeida', 'pedro@gmail.com', '43991112222', '123', 'Entregador');

INSERT INTO pedidos (nome_pedido, peso, quantidade, status, codigo, id_cliente) VALUES
('Bolas de Futebol', 5.50, 10, 'Pedido Solicitado', 'COD-0007', 1),
('Chuteiras Nike', 3.20, 6, 'Em Produção', 'COD-0008', 2),
('Camisas de Time', 2.10, 20, 'Disponivel para Despacho', 'COD-0009', 3);

INSERT INTO pedidos
(nome_pedido, peso, quantidade, status, codigo, id_cliente)
VALUES
('Televisao Smart 50', 12.5, 2, 'Disponivel para Despacho', 'COD-0010', 1),
('Notebook Dell', 3.2, 5, 'Disponivel para Despacho', 'COD-0011', 2),
('Celulares Samsung', 4.8, 10, 'Disponivel para Despacho', 'COD-0012', 3),
('Geladeira Brastemp', 65.0, 1, 'Disponivel para Despacho', 'COD-0013', 1),
('Caixas de Ferramentas', 18.5, 4, 'Disponivel para Despacho', 'COD-0014', 2),
('Monitores Gamer', 15.0, 6, 'Disponivel para Despacho', 'COD-0015', 3),
('Impressoras', 22.3, 3, 'Disponivel para Despacho', 'COD-0016', 1),
('Fones de Ouvido', 2.1, 15, 'Disponivel para Despacho', 'COD-0017', 2),
('Ar Condicionado', 35.7, 2, 'Disponivel para Despacho', 'COD-0018', 3),
('Mesas de Escritorio', 42.0, 3, 'Disponivel para Despacho', 'COD-0019', 1);