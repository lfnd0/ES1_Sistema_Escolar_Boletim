<<<<<<< HEAD
CREATE DATABASE  IF NOT EXISTS `seb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `seb`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: seb
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `administrador` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `data_nascimento` varchar(15) NOT NULL,
  `login` varchar(45) DEFAULT NULL,
  `senha` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES (1,'José Ricardo de Souza Filho','12-01-1992','es1','es1');
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `aluno` (
  `matricula_aluno` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `data_nascimento` varchar(15) NOT NULL,
  `login` varchar(45) DEFAULT NULL,
  `senha` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`matricula_aluno`),
  UNIQUE KEY `matricula_aluno` (`matricula_aluno`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno`
--

LOCK TABLES `aluno` WRITE;
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` VALUES (1,'Danillo Rodrigues','12-01-1992','danillo','123'),(2,'Luiz Fernando','12-01-1992','fernando','123'),(3,'João','12-01-1992',NULL,NULL),(4,'paulo','12-01-1992',NULL,NULL),(5,'Marta','12-01-1992','marta','123');
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aluno_cursa_disciplina`
--

DROP TABLE IF EXISTS `aluno_cursa_disciplina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `aluno_cursa_disciplina` (
  `matricula_alunoFK` int(10) unsigned NOT NULL,
  `codigo_disciplinaFK` int(10) unsigned NOT NULL,
  PRIMARY KEY (`matricula_alunoFK`,`codigo_disciplinaFK`),
  KEY `codigo_disciplinaFK` (`codigo_disciplinaFK`),
  CONSTRAINT `aluno_cursa_disciplina_ibfk_1` FOREIGN KEY (`matricula_alunoFK`) REFERENCES `aluno` (`matricula_aluno`) ON DELETE CASCADE,
  CONSTRAINT `aluno_cursa_disciplina_ibfk_2` FOREIGN KEY (`codigo_disciplinaFK`) REFERENCES `disciplina` (`codigo_disciplina`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno_cursa_disciplina`
--

LOCK TABLES `aluno_cursa_disciplina` WRITE;
/*!40000 ALTER TABLE `aluno_cursa_disciplina` DISABLE KEYS */;
INSERT INTO `aluno_cursa_disciplina` VALUES (1,1),(2,1);
/*!40000 ALTER TABLE `aluno_cursa_disciplina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aluno_tem_nota_disciplina`
--

DROP TABLE IF EXISTS `aluno_tem_nota_disciplina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `aluno_tem_nota_disciplina` (
  `matricula_alunoFK` int(10) unsigned NOT NULL,
  `id_notaFK` int(10) unsigned NOT NULL,
  `codigo_disciplinaFK` int(10) unsigned NOT NULL,
  PRIMARY KEY (`matricula_alunoFK`,`id_notaFK`,`codigo_disciplinaFK`),
  KEY `id_notaFK` (`id_notaFK`),
  KEY `codigo_disciplinaFK` (`codigo_disciplinaFK`),
  CONSTRAINT `aluno_tem_nota_disciplina_ibfk_1` FOREIGN KEY (`matricula_alunoFK`) REFERENCES `aluno` (`matricula_aluno`) ON DELETE CASCADE,
  CONSTRAINT `aluno_tem_nota_disciplina_ibfk_2` FOREIGN KEY (`id_notaFK`) REFERENCES `nota` (`id`) ON DELETE CASCADE,
  CONSTRAINT `aluno_tem_nota_disciplina_ibfk_3` FOREIGN KEY (`codigo_disciplinaFK`) REFERENCES `disciplina` (`codigo_disciplina`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno_tem_nota_disciplina`
--

LOCK TABLES `aluno_tem_nota_disciplina` WRITE;
/*!40000 ALTER TABLE `aluno_tem_nota_disciplina` DISABLE KEYS */;
INSERT INTO `aluno_tem_nota_disciplina` VALUES (1,1,1),(2,2,1);
/*!40000 ALTER TABLE `aluno_tem_nota_disciplina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disciplina`
--

DROP TABLE IF EXISTS `disciplina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `disciplina` (
  `codigo_disciplina` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `matricula_professorFK` int(10) unsigned NOT NULL,
  `nome` varchar(45) NOT NULL,
  `carga_horaria` varchar(45) NOT NULL,
  PRIMARY KEY (`codigo_disciplina`),
  UNIQUE KEY `codigo_disciplina` (`codigo_disciplina`),
  KEY `matricula_professorFK` (`matricula_professorFK`),
  CONSTRAINT `disciplina_ibfk_1` FOREIGN KEY (`matricula_professorFK`) REFERENCES `professor` (`matricula_professor`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disciplina`
--

LOCK TABLES `disciplina` WRITE;
/*!40000 ALTER TABLE `disciplina` DISABLE KEYS */;
INSERT INTO `disciplina` VALUES (1,1,'Português','80'),(2,2,'Matemática','80'),(3,6,'História','80');
/*!40000 ALTER TABLE `disciplina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nota`
--

DROP TABLE IF EXISTS `nota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `nota` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `bimestre1` double DEFAULT NULL,
  `bimestre2` double DEFAULT NULL,
  `bimestre3` double DEFAULT NULL,
  `bimestre4` double DEFAULT NULL,
  `reavaliacao1` double DEFAULT NULL,
  `reavaliacao2` double DEFAULT NULL,
  `matricula_professorFK` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `matricula_professorFK` (`matricula_professorFK`),
  CONSTRAINT `nota_ibfk_1` FOREIGN KEY (`matricula_professorFK`) REFERENCES `professor` (`matricula_professor`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nota`
--

LOCK TABLES `nota` WRITE;
/*!40000 ALTER TABLE `nota` DISABLE KEYS */;
INSERT INTO `nota` VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL,1),(2,10,10,10,10,0,0,1);
/*!40000 ALTER TABLE `nota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor`
--

DROP TABLE IF EXISTS `professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `professor` (
  `matricula_professor` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `data_nascimento` varchar(15) NOT NULL,
  `login` varchar(45) DEFAULT NULL,
  `senha` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`matricula_professor`),
  UNIQUE KEY `matricula_professor` (`matricula_professor`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor`
--

LOCK TABLES `professor` WRITE;
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
INSERT INTO `professor` VALUES (1,'Maria','12-01-1992','maria','123'),(2,'José','12-01-1992','jose','123'),(3,'Matheus','12-01-1992',NULL,NULL),(4,'Jonas','12-01-1992',NULL,NULL),(5,'Betânia','12-01-1992',NULL,NULL),(6,'Geral','12-01-1992',NULL,NULL);
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-12 22:22:45
=======
-- -----------------------------------------------------
-- Banco seb
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS seb;

-- -----------------------------------------------------
-- Tabela seb.administrador
-- -----------------------------------------------------
CREATE TABLE seb.administrador (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  nome VARCHAR(45) NOT NULL,
  data_nascimento VARCHAR(15) NOT NULL,
  login VARCHAR(45) UNIQUE NULL,
  senha VARCHAR(45) NULL,
  CONSTRAINT PRIMARY KEY (id)
)ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabela seb.aluno
-- -----------------------------------------------------
CREATE TABLE seb.aluno (
  matricula_aluno INT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  nome VARCHAR(45) NOT NULL,
  data_nascimento VARCHAR(15) NOT NULL,
  login VARCHAR(45) UNIQUE NULL,
  senha VARCHAR(45) NULL,
  CONSTRAINT PRIMARY KEY (matricula_aluno)
)ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabela seb.professor
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS seb.professor (
  matricula_professor INT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  nome VARCHAR(45) NOT NULL,
  data_nascimento VARCHAR(15) NOT NULL,
  login VARCHAR(45) UNIQUE NULL,
  senha VARCHAR(45) NULL,
  CONSTRAINT PRIMARY KEY (matricula_professor)
)ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabela seb.disciplina
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS seb.disciplina (
  codigo_disciplina INT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  matricula_professorFK INT UNSIGNED NOT NULL,
  nome VARCHAR(45) NOT NULL,
  carga_horaria VARCHAR(45) NOT NULL,
  CONSTRAINT PRIMARY KEY (codigo_disciplina),
  CONSTRAINT FOREIGN KEY (matricula_professorFK)
	REFERENCES seb.professor (matricula_professor)
    ON DELETE CASCADE
)ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabela seb.nota
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS seb.nota (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  bimestre1 DOUBLE NULL,
  bimestre2 DOUBLE NULL,
  bimestre3 DOUBLE NULL,
  bimestre4 DOUBLE NULL,
  reavaliacao1 DOUBLE NULL,
  reavaliacao2 DOUBLE NULL,
  matricula_professorFK INT UNSIGNED NOT NULL,
  CONSTRAINT PRIMARY KEY (id),
  CONSTRAINT FOREIGN KEY (matricula_professorFK)
	REFERENCES seb.professor (matricula_professor)
    ON DELETE CASCADE
)ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabela seb.aluno_cursa_disciplina
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS seb.aluno_cursa_disciplina (
  matricula_alunoFK INT UNSIGNED NOT NULL,
  codigo_disciplinaFK INT UNSIGNED NOT NULL,
  CONSTRAINT PRIMARY KEY (matricula_alunoFK, codigo_disciplinaFK),
  CONSTRAINT FOREIGN KEY (matricula_alunoFK)
	REFERENCES seb.aluno (matricula_aluno)
    ON DELETE CASCADE,
  CONSTRAINT FOREIGN KEY (codigo_disciplinaFK)
	REFERENCES seb.disciplina (codigo_disciplina)
    ON DELETE CASCADE
)ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabela seb.aluno_tem_nota_disciplina
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS seb.aluno_tem_nota_disciplina (
  matricula_alunoFK INT UNSIGNED NOT NULL,
  id_notaFK INT UNSIGNED NOT NULL,
  codigo_disciplinaFK INT UNSIGNED NOT NULL,
  CONSTRAINT PRIMARY KEY (matricula_alunoFK, id_notaFK, codigo_disciplinaFK),
  CONSTRAINT FOREIGN KEY (matricula_alunoFK)
    REFERENCES seb.aluno (matricula_aluno)
    ON DELETE CASCADE,
  CONSTRAINT FOREIGN KEY (id_notaFK)
    REFERENCES seb.nota (id)
    ON DELETE CASCADE,
  CONSTRAINT FOREIGN KEY (codigo_disciplinaFK)
    REFERENCES seb.disciplina (codigo_disciplina)
    ON DELETE CASCADE
)ENGINE = InnoDB;

-- -----------------------------------------------------
-- Criando usuario administrador
-- -----------------------------------------------------
  INSERT INTO seb.administrador (nome, data_nascimento, login, senha)
  VALUES ("José Ricardo de Souza Filho", "12-01-1992", "es1", "es1");
  
-- -----------------------------------------------------
-- Criando usuarios
-- -----------------------------------------------------
  INSERT INTO seb.aluno (matricula_aluno, nome , data_nascimento, login, senha)
  VALUES (1, "Danillo Rodrigues", "12-01-1992", "danillo", "123");
  
-- -----------------------------------------------------
-- Criando usuarios
-- -----------------------------------------------------
  INSERT INTO seb.aluno (matricula_aluno, nome, data_nascimento, login, senha)
  VALUES (2, "Luiz Fernando", "12-01-1992", "fernando", "123");
  
-- -----------------------------------------------------
-- Criando usuarios
-- -----------------------------------------------------
  INSERT INTO seb.aluno (matricula_aluno, nome, data_nascimento)
  VALUES (3, "João", "12-01-1992");
  
-- -----------------------------------------------------
-- Criando usuarios
-- -----------------------------------------------------
  INSERT INTO seb.aluno (matricula_aluno, nome, data_nascimento)
  VALUES (4, "paulo", "12-01-1992");

-- -----------------------------------------------------
-- Criando usuarios
-- -----------------------------------------------------
  INSERT INTO seb.aluno (matricula_aluno, nome, data_nascimento, login, senha)
  VALUES (5, "Marta", "12-01-1992", "marta", "123");

-- -----------------------------------------------------
-- Criando usuarios
-- -----------------------------------------------------
  INSERT INTO seb.professor (matricula_professor, nome, data_nascimento, login, senha)
  VALUES (1, "Maria", "12-01-1992", "maria", "123");

-- -----------------------------------------------------
-- Criando usuarios
-- -----------------------------------------------------
  INSERT INTO seb.professor (matricula_professor, nome, data_nascimento, login, senha)
  VALUES (2, "José", "12-01-1992", "jose", "123");

-- -----------------------------------------------------
-- Criando usuarios
-- -----------------------------------------------------
  INSERT INTO seb.professor (matricula_professor, nome, data_nascimento)
  VALUES (3, "Matheus", "12-01-1992");
  
-- -----------------------------------------------------
-- Criando usuarios
-- -----------------------------------------------------
  INSERT INTO seb.professor (matricula_professor, nome, data_nascimento)
  VALUES (4, "Jonas", "12-01-1992");
  
-- -----------------------------------------------------
-- Criando usuarios
-- -----------------------------------------------------
  INSERT INTO seb.professor (matricula_professor, nome, data_nascimento)
  VALUES (5, "Betânia", "12-01-1992");
  
-- -----------------------------------------------------
-- Criando usuarios
-- -----------------------------------------------------
  INSERT INTO seb.professor (matricula_professor, nome, data_nascimento)
  VALUES (6, "Geral", "12-01-1992");
    
-- -----------------------------------------------------
-- Criando Disciplinas
-- -----------------------------------------------------
  INSERT INTO seb.disciplina (matricula_professorFK, nome, carga_horaria)
  VALUES (1 , "Português", 80);
  
-- -----------------------------------------------------
-- Criando Disciplinas
-- -----------------------------------------------------
  INSERT INTO seb.disciplina (matricula_professorFK, nome, carga_horaria)
  VALUES (2 , "Matemática", 80);

-- -----------------------------------------------------
-- Criando Disciplinas
-- -----------------------------------------------------
  INSERT INTO seb.disciplina (matricula_professorFK, nome, carga_horaria)
  VALUES (6 , "História", 80);
    
-- -----------------------------------------------------
-- Criando Relação de Aluno com Disciplina
-- -----------------------------------------------------
  INSERT INTO seb.aluno_cursa_disciplina (matricula_alunoFK, codigo_disciplinaFK)
  VALUES (1 , 1);
 
-- -----------------------------------------------------
-- Criando Nota do Aluno
-- -----------------------------------------------------
  INSERT INTO seb.nota (id, matricula_professorFK)
  VALUES (1 , 1);
  
-- -----------------------------------------------------
-- Criando Relação de Aluno com Disciplina e Nota
-- -----------------------------------------------------
  INSERT INTO seb.aluno_tem_nota_disciplina (matricula_alunoFK, id_notaFK, codigo_disciplinaFK)
  VALUES (1 , 1, 1);
  
  -- -----------------------------------------------------
-- Criando Relação de Aluno com Disciplina
-- -----------------------------------------------------
  INSERT INTO seb.aluno_cursa_disciplina (matricula_alunoFK, codigo_disciplinaFK)
  VALUES (2 , 1);
  
-- -----------------------------------------------------
-- Criando Nota do Aluno
-- -----------------------------------------------------
  INSERT INTO seb.nota (id, matricula_professorFK, bimestre1, bimestre2, bimestre3, bimestre4, reavaliacao1, reavaliacao2)
  VALUES (2 , 1, 10, 10, 10, 10, 0, 0);
  
-- -----------------------------------------------------
-- Criando Relação de Aluno com Disciplina e Nota
-- -----------------------------------------------------
  INSERT INTO seb.aluno_tem_nota_disciplina (matricula_alunoFK, id_notaFK, codigo_disciplinaFK)
  VALUES (2 , 2, 1);
>>>>>>> 011c709aa022d062534c73c68801a574c095b41f
