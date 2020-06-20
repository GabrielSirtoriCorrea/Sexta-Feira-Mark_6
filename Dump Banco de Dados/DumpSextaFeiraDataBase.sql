CREATE DATABASE  IF NOT EXISTS `sextafeiradatabase` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sextafeiradatabase`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: sextafeiradatabase
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `device` (
  `DeviceID` int NOT NULL AUTO_INCREMENT,
  `DeviceName` varchar(20) DEFAULT NULL,
  `DeviceDescription` text,
  `DeviceJson` text,
  PRIMARY KEY (`DeviceID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` VALUES (1,'Device','Description','Json'),(2,'newDevice','newDevice','newDevice'),(3,'newDevice','newDevice','newDevice');
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `homeworkmanagement`
--

DROP TABLE IF EXISTS `homeworkmanagement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `homeworkmanagement` (
  `HomeWorkID` int NOT NULL AUTO_INCREMENT,
  `HomeWorkType` varchar(20) DEFAULT NULL,
  `HomeWorkSubject` varchar(30) DEFAULT NULL,
  `HomeWork` varchar(60) DEFAULT NULL,
  `HomeWorkDelivery` date DEFAULT NULL,
  `HomeWorkDescription` text,
  PRIMARY KEY (`HomeWorkID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `homeworkmanagement`
--

LOCK TABLES `homeworkmanagement` WRITE;
/*!40000 ALTER TABLE `homeworkmanagement` DISABLE KEYS */;
INSERT INTO `homeworkmanagement` VALUES (1,'Type','subject','homework','2020-06-02','desc'),(2,'newHomeWork','newHomeWork','newHomeWork','2020-06-02','newHomeWork');
/*!40000 ALTER TABLE `homeworkmanagement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interactions`
--

DROP TABLE IF EXISTS `interactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interactions` (
  `InteractionID` int NOT NULL AUTO_INCREMENT,
  `KeyWord1` varchar(30) DEFAULT NULL,
  `KeyWord2` varchar(30) DEFAULT NULL,
  `KeyWord3` varchar(30) DEFAULT NULL,
  `Response1` text,
  `Response2` text,
  `Response3` text,
  `Command` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`InteractionID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interactions`
--

LOCK TABLES `interactions` WRITE;
/*!40000 ALTER TABLE `interactions` DISABLE KEYS */;
INSERT INTO `interactions` VALUES (1,'ola','ola','ola','olá chefe','olá chefe','olá chefe',''),(2,'horas','sao','sao','','','','dateTime'),(3,'fech','tabela','tabela','','','','sendCloseToInterface'),(4,'mostr','interacoes','interacoes','Atualmente essas são as interações contidas no meu banco de dados','Essas são todas as interações contidas no meu banco de dados','Aqui estão as interações contidas no meu banco de dados','sendInteractionsToInterface'),(5,'mostr','tarefa','tarefa','Essas são todas as suas tarefas pendentes','Aqui estão as suas tarefas pendentes','Atualmente essas são suas tarefas pendentes','sendHomeWorksToInterface'),(6,'mostr','projeto','projeto','Esses são todos os projetos em andamento','Aqui estão os projetos em desenvolvimento','Atualmente esse são os projetos em desenvolvimento','sendProjectsToInterface'),(7,'mostr','device','device','Esses são os devices sincronizados','Aqui estão os devices sincronizados','Atualmente esse são os devices sincronizados com o meu sistema','sendDevicesToInterface'),(8,'fech','image','image','','','','sendCloseToInterface'),(9,'mostr','tabela','periodica','tabela periodica aberta','tabela periodica aberta','tabela periodica aberta','sendPeriodicTableToInterface'),(10,'mostr','mapa','brasil politico','Esse é o mapa político do Brasil','Esse é o mapa político do Brasil','Esse é o mapa político do Brasil','sendPoliticalBrazilToInterface');
/*!40000 ALTER TABLE `interactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projects` (
  `ProjectID` int NOT NULL AUTO_INCREMENT,
  `ProjectName` varchar(60) DEFAULT NULL,
  `ProjectLanguages` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ProjectID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES (1,'Project','language'),(2,'NewProject','Python');
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-19 21:00:51
