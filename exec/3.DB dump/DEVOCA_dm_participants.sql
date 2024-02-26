-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: i10d112.p.ssafy.io    Database: DEVOCA
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `dm_participants`
--

DROP TABLE IF EXISTS `dm_participants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dm_participants` (
  `dp_dr_idx` int NOT NULL,
  `dp_user_idx` int NOT NULL,
  `dp_last_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`dp_dr_idx`,`dp_user_idx`),
  KEY `FK_users_TO_dm_participants_1` (`dp_user_idx`),
  CONSTRAINT `FK_dm_rooms_TO_dm_participants_1` FOREIGN KEY (`dp_dr_idx`) REFERENCES `dm_rooms` (`dr_idx`),
  CONSTRAINT `FK_users_TO_dm_participants_1` FOREIGN KEY (`dp_user_idx`) REFERENCES `users` (`user_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_participants`
--

LOCK TABLES `dm_participants` WRITE;
/*!40000 ALTER TABLE `dm_participants` DISABLE KEYS */;
INSERT INTO `dm_participants` VALUES (1,10,'2024-02-15 23:56:27'),(1,14,'2024-02-15 21:58:31'),(2,10,'2024-02-15 23:56:31'),(2,11,'2024-02-15 22:25:23'),(3,10,'2024-02-16 01:28:24'),(3,13,'2024-02-16 02:27:41'),(4,13,'2024-02-16 00:50:15'),(4,14,'2024-02-16 00:50:08'),(5,11,'2024-02-16 02:42:07'),(5,13,'2024-02-16 02:24:10'),(6,12,'2024-02-16 01:26:32'),(6,13,'2024-02-16 01:01:23'),(7,10,'2024-02-15 23:59:48'),(7,12,'2024-02-16 01:26:25'),(8,12,'2024-02-16 01:26:24'),(8,14,'2024-02-16 00:35:18'),(9,6,'2024-02-16 02:09:01'),(9,15,'2024-02-16 02:09:01');
/*!40000 ALTER TABLE `dm_participants` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-16 11:42:39
