-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: cinema
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Dumping data for table `seats`
--

LOCK TABLES `seats` WRITE;
/*!40000 ALTER TABLE `seats` DISABLE KEYS */;
INSERT INTO `seats` VALUES (12,'a2',1,1000,1,_binary '',1),(13,'a1',2,45000,1,_binary '',1),(15,'a3',3,45000,1,_binary '',1),(16,'a5',5,45000,1,_binary '',1),(17,'A4',4,45000,1,_binary '',1),(18,'A6',6,45000,1,_binary '',1),(19,'A7',7,45000,1,_binary '',1),(20,'A8',8,45000,1,_binary '',1),(21,'B1',1,45000,2,_binary '',1),(22,'B2',2,45000,2,_binary '',1),(23,'B3',3,45000,2,_binary '',1),(24,'B4',4,45000,2,_binary '',1),(25,'B5',5,45000,2,_binary '',1),(26,'B6',6,45000,2,_binary '',1),(27,'B7',7,45000,2,_binary '',1),(28,'B8',8,45000,2,_binary '',1),(29,'C1',1,45000,3,_binary '',1),(30,'C2',2,45000,3,_binary '',1),(31,'C3',3,45000,3,_binary '',1),(32,'C4',4,45000,3,_binary '',1),(33,'C5',5,45000,3,_binary '',1),(34,'C6',6,45000,3,_binary '',1),(35,'C7',7,45000,3,_binary '',1),(36,'C8',8,45000,3,_binary '',1),(37,'D1',1,45,4,_binary '',1),(38,'D2',2,45000,4,_binary '',1),(39,'D3',3,45000,4,_binary '',1),(40,'D4',4,45000,4,_binary '',1),(41,'D5',5,45000,4,_binary '',1),(43,'D6',6,45000,4,_binary '',1),(44,'D7',7,45000,4,_binary '',1),(45,'D8',8,45000,4,_binary '',1),(46,'A5',1,100000,1,_binary '',3);
/*!40000 ALTER TABLE `seats` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-20 18:00:46
