-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: pinkroccadedb
-- ------------------------------------------------------
-- Server version	5.5.62

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
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `birth_date` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `parent1_id` bigint(20) DEFAULT NULL,
  `parent2_id` bigint(20) DEFAULT NULL,
  `partner_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbeayl8e4orsbl2dgfa7y75vkh` (`parent1_id`),
  KEY `FK3d397jqbvgtki58pcrospv055` (`parent2_id`),
  KEY `FKmt6li2p202wevmukdfp48po7w` (`partner_id`),
  CONSTRAINT `FKmt6li2p202wevmukdfp48po7w` FOREIGN KEY (`partner_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FK3d397jqbvgtki58pcrospv055` FOREIGN KEY (`parent2_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FKbeayl8e4orsbl2dgfa7y75vkh` FOREIGN KEY (`parent1_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (2,'1997-02-03 00:00:00','Ernst',5,4,3),(3,'1996-10-04 00:00:00','Biance',9,10,2),(4,'1986-05-04 00:00:00','Nico',7,6,5),(5,'1989-08-08 00:00:00','Yolanda',7,6,4),(6,'1900-01-01 00:00:00','Adam',7,6,7),(7,'1900-01-01 00:00:00','Eve',7,6,6),(8,'1900-01-01 00:00:00','Lanique',5,4,NULL),(9,'1980-08-03 00:00:00','Piere',6,7,10),(10,'1980-02-03 00:00:00','Zelda',6,7,9),(11,'2000-01-01 00:00:00','Henroux',9,10,NULL),(12,'1997-01-01 00:00:00','Randy',9,10,NULL),(14,'2005-01-01 00:00:00','Simone',9,10,NULL);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_children`
--

DROP TABLE IF EXISTS `person_children`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person_children` (
  `person_id` bigint(20) NOT NULL,
  `children_id` bigint(20) NOT NULL,
  KEY `FKkwnfqkuv03rtt2qvabatqwbt4` (`children_id`),
  KEY `FKnbnbm9sp3peex9hdd2wfncknk` (`person_id`),
  CONSTRAINT `FKnbnbm9sp3peex9hdd2wfncknk` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FKkwnfqkuv03rtt2qvabatqwbt4` FOREIGN KEY (`children_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_children`
--

LOCK TABLES `person_children` WRITE;
/*!40000 ALTER TABLE `person_children` DISABLE KEYS */;
INSERT INTO `person_children` VALUES (10,3),(10,11),(10,12),(10,14),(9,3),(9,11),(9,12),(9,14);
/*!40000 ALTER TABLE `person_children` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-16 22:56:56
