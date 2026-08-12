CREATE DATABASE  IF NOT EXISTS `projeto_final` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `projeto_final`;
-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: projeto_final
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `email` varchar(90) NOT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'João da Silva','joao@email.com'),(2,'Maria Souza','maria@email.com'),(3,'Pedro Santos','pedro@email.com');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historico_pedido`
--

DROP TABLE IF EXISTS `historico_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historico_pedido` (
  `id_historico` int(11) NOT NULL AUTO_INCREMENT,
  `id_pedido` int(11) NOT NULL,
  `descricao` varchar(255) NOT NULL,
  `data_hora` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id_historico`),
  KEY `id_pedido` (`id_pedido`),
  CONSTRAINT `historico_pedido_ibfk_1` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historico_pedido`
--

LOCK TABLES `historico_pedido` WRITE;
/*!40000 ALTER TABLE `historico_pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `historico_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incidentes`
--

DROP TABLE IF EXISTS `incidentes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incidentes` (
  `id_incidente` int(11) NOT NULL AUTO_INCREMENT,
  `id_pedido` int(11) NOT NULL,
  `tipo` enum('Problema no Caminhão','Problema no Produto') NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `acao_tomada` enum('Novo Caminhão Enviado','Nova Remessa em Produção') NOT NULL,
  `data_ocorrencia` datetime NOT NULL,
  PRIMARY KEY (`id_incidente`),
  KEY `id_pedido` (`id_pedido`),
  CONSTRAINT `incidentes_ibfk_1` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incidentes`
--

LOCK TABLES `incidentes` WRITE;
/*!40000 ALTER TABLE `incidentes` DISABLE KEYS */;
/*!40000 ALTER TABLE `incidentes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `id_pedido` int(11) NOT NULL AUTO_INCREMENT,
  `nome_pedido` varchar(200) NOT NULL,
  `peso` float NOT NULL,
  `quantidade` int(11) NOT NULL,
  `status` enum('Pedido Solicitado','Em Produção','Disponivel para Despacho','Em Rota','Entregue','Avaria na Carga','Incidente de Transporte') NOT NULL,
  `codigo` varchar(890) NOT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `id_entregador` int(11) DEFAULT NULL,
  `token` varchar(10) DEFAULT NULL,
  `localizacao_atual` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_pedido`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_entregador` (`id_entregador`),
  CONSTRAINT `pedidos_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  CONSTRAINT `pedidos_ibfk_2` FOREIGN KEY (`id_entregador`) REFERENCES `usuario` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (1,'Bolas de Futebol',5.5,10,'Pedido Solicitado','COD-0007',1,NULL,NULL,NULL),(2,'Chuteiras Nike',3.2,6,'Em Produção','COD-0008',2,NULL,NULL,NULL),(3,'Camisas de Time',2.1,20,'Disponivel para Despacho','COD-0009',3,NULL,NULL,NULL),(4,'Televisao Smart 50',12.5,2,'Disponivel para Despacho','COD-0010',1,NULL,NULL,NULL),(5,'Notebook Dell',3.2,5,'Disponivel para Despacho','COD-0011',2,NULL,NULL,NULL),(6,'Celulares Samsung',4.8,10,'Disponivel para Despacho','COD-0012',3,NULL,NULL,NULL),(7,'Geladeira Brastemp',65,1,'Disponivel para Despacho','COD-0013',1,NULL,NULL,NULL),(8,'Caixas de Ferramentas',18.5,4,'Disponivel para Despacho','COD-0014',2,NULL,NULL,NULL),(9,'Monitores Gamer',15,6,'Disponivel para Despacho','COD-0015',3,NULL,NULL,NULL),(10,'Impressoras',22.3,3,'Disponivel para Despacho','COD-0016',1,NULL,NULL,NULL),(11,'Fones de Ouvido',2.1,15,'Disponivel para Despacho','COD-0017',2,NULL,NULL,NULL),(12,'Ar Condicionado',35.7,2,'Disponivel para Despacho','COD-0018',3,NULL,NULL,NULL),(13,'Mesas de Escritorio',42,3,'Entregue','COD-0019',1,7,'0IQ5W',NULL);
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(70) NOT NULL,
  `email` varchar(90) NOT NULL,
  `telefone` varchar(20) NOT NULL,
  `senha` varchar(90) NOT NULL,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Carlos Motorista','carlos@entregas.com','11999999999','123456','Entregador'),(2,'Marcos Silva','marcos@gmail.com','11987654321','123','Entregador'),(3,'Ana Souza','ana@gmail.com','11981234567','123','Entregador'),(4,'Fernanda Oliveira','fernanda@gmail.com','43999887766','123','Operador Logistico'),(5,'Ricardo Santos','ricardo@gmail.com','43988776655','123','Entregador'),(6,'Juliana Costa','juliana@gmail.com','11995554433','123','Operador Logistico'),(7,'Pedro Almeida','pedro@gmail.com','43991112222','123','Entregador');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-08-11 21:18:47
