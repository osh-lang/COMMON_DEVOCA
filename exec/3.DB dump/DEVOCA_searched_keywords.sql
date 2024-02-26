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
-- Table structure for table `searched_keywords`
--

DROP TABLE IF EXISTS `searched_keywords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `searched_keywords` (
  `sk_user_idx` int NOT NULL,
  `sk_searched_keyword` varchar(40) NOT NULL,
  `sk_searched_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`sk_user_idx`,`sk_searched_keyword`),
  CONSTRAINT `FK_users_TO_searched_keywords_1` FOREIGN KEY (`sk_user_idx`) REFERENCES `users` (`user_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `searched_keywords`
--

LOCK TABLES `searched_keywords` WRITE;
/*!40000 ALTER TABLE `searched_keywords` DISABLE KEYS */;
INSERT INTO `searched_keywords` VALUES (10,'Java','2024-02-15 21:58:58'),(11,'java','2024-02-16 02:29:56'),(11,'jpa','2024-02-15 22:44:57'),(13,'Dd','2024-02-15 22:16:41'),(13,'Hibernate','2024-02-15 22:17:07'),(13,'Jpa','2024-02-15 21:24:44'),(16,'andoid','2024-02-16 02:10:12'),(16,'java','2024-02-16 02:17:35'),(16,'jp','2024-02-16 02:13:11'),(16,'jpa','2024-02-16 02:13:09'),(16,'select','2024-02-16 02:16:02'),(16,'solid','2024-02-16 02:12:53'),(16,'객체지향','2024-02-16 02:12:59'),(16,'리스코프','2024-02-16 02:12:41');
/*!40000 ALTER TABLE `searched_keywords` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-16 11:42:42
