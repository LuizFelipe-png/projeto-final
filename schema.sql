#drop database projeto_final;
create database projeto_final;
use projeto_final;

create table usuario (
    id_usuario INT(11) not null auto_increment,
    nome varchar(70) not null,
    email varchar(90) not null,
    telefone varchar(20) not null,
    senha varchar(90) not null,
    role varchar(50) not null,
    primary key (id_usuario)
);

create table pedidos (
    id_pedido int(11) primary key auto_increment,
    nome_pedido varchar(200) not null,
    peso float not null,
    quantidade int(11) not null,
    status enum('Pedido Solicitado', 'Em Produção', 'Disponivel para Despacho', 'Em Rota', 'Entregue') not null,
    codigo varchar(890) not null,
    id_cliente int(11)
);

CREATE TABLE cliente (
    id_cliente int(11) primary key auto_increment,
    nome varchar(100) not null,
    email varchar(90) not null
);

create table entregador (
  id_entregador Integer primary key,
  veiculo varchar(100),
  placa varchar(20),
  FOREIGN KEY (id_entregador) references usuario(id_usuario)
);

INSERT INTO cliente (nome, email) VALUES
('João da Silva', 'joao@email.com'),
('Maria Souza', 'maria@email.com'),
('Pedro Santos', 'pedro@email.com');

CREATE TABLE incidentes (
    id_incidente int(11) primary key auto_increment,
    id_pedido int(11) not null,
    tipo enum('Problema no Caminhão', 'Problema no Produto') not null,
    descricao varchar(255),
    acao_tomada enum('Novo Caminhão Enviado', 'Nova Remessa em Produção') not null,
    data_ocorrencia datetime not null,
    FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido)
);

INSERT INTO pedidos (nome_pedido, peso, quantidade, status, codigo, id_cliente) VALUES
('Bolas de Futebol', 5.50, 10, 'Pedido Solicitado', 'COD-0007', 1),
('Chuteiras Nike', 3.20, 6, 'Em Produção', 'COD-0008', 2),
('Camisas de Time', 2.10, 20, 'Disponivel para Despacho', 'COD-0009', 3);

ALTER TABLE pedidos
    ADD COLUMN id_entregador INT NULL,
    ADD COLUMN token VARCHAR(10) NULL,
    ADD CONSTRAINT fk_pedidos_entregador FOREIGN KEY (id_entregador) REFERENCES entregador(id_entregador);
    
CREATE TABLE historico_pedido (
    id_historico INT(11) PRIMARY KEY AUTO_INCREMENT,
    id_pedido INT(11) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    data_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido)
);