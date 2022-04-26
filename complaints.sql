CREATE DATABASE  IF NOT EXISTS `paflab` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `paflab`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: paflab
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
-- Table structure for table `new_table`
--

DROP TABLE IF EXISTS `new_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `new_table` (
  `complaintID` int NOT NULL AUTO_INCREMENT,
  `complainerName` varchar(20) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phoneNumber` int NOT NULL,
  `complaintCategory` varchar(12) NOT NULL,
  `complaint` varchar(70) NOT NULL,
  `issueArea` varchar(80) NOT NULL,
  `remarks` varchar(200) NOT NULL,
  PRIMARY KEY (`complaintID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new_table`
--

LOCK TABLES `new_table` WRITE;
/*!40000 ALTER TABLE `new_table` DISABLE KEYS */;
INSERT INTO `new_table` VALUES (3,'Vishwani','vish@gmail.com',745262050,'Breakdown','Meter burning','Matara, Welegoda','We have a power cut for about five hours now'),(4,'Sanduni','sadu@gmail.com',779252627,'Breakdown','Tree fallen on to the line','Kaduwela','We have a power cut for about seven hours now'),(5,'Dhanushka','danu@gmail.com',779278654,'Breakdown','Broken service Wire','Malabe','We have a power cut for about two hours now'),(6,'Amish','amish@gmail.com',714567891,'Breakdown','High voltage','Matara, kekanadura','We have a power cut for about three hours now.'),(7,'Dhanushka','danu@gmail.com',779278654,'Breakdown','Broken service Wire','Malabe','We have a power cut for about two hours now');
/*!40000 ALTER TABLE `new_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-26 22:02:06
