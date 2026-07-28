create database projeto_final;
use projeto_final;

create table cliente (
	id_cliente int(11) primary key,
    nome varchar(90) not null,
    email varchar(91) not null
);

create table incidentes (
	id_incidente int(11) primary key,
    id_pedido int(11),
    tipo enum('Problema no Caminhao','Problema no Produto') not null,
    descricao varchar(255) not null,
    acao_tomada enum('Novo Caminhão Enviado', 'Nova Remessa em Produção') not null,
    data_ocorrencia datetime not null
);

create table pedidos (
	id_pedido int(11) primary key,
    nome_pedido varchar(200) not null,
    peso float not null,
    quantidade int(20),
    status enum ('Pedido Solicitado', 'Em Produção','Disponivel para Despacho', 'Em Rota', 'Entregue', 'Avaria na Carga', 'Incidente de Transporte') not null,
    codigo varchar(890) not null,
    foreign key
    
);

alter table pedidos modify id_entregador not null; 

create table entregador (
  id_entregador Integer primary key,
  veiculo varchar(100),
  placa varchar(20),
  FOREIGN KEY (id_entregador) references usuario(id_usuario)
);