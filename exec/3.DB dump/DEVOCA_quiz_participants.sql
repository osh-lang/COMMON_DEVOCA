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
-- Table structure for table `quiz_participants`
--

DROP TABLE IF EXISTS `quiz_participants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz_participants` (
  `qp_quiz_id` int NOT NULL,
  `fk_qp_user_idx` int NOT NULL,
  `qp_participate_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `qp_score` int NOT NULL,
  PRIMARY KEY (`qp_quiz_id`,`fk_qp_user_idx`),
  KEY `FK_users_TO_quiz_participants_1` (`fk_qp_user_idx`),
  CONSTRAINT `FK_quizzes_TO_quiz_participants_1` FOREIGN KEY (`qp_quiz_id`) REFERENCES `quizzes` (`quiz_id`),
  CONSTRAINT `FK_users_TO_quiz_participants_1` FOREIGN KEY (`fk_qp_user_idx`) REFERENCES `users` (`user_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz_participants`
--

LOCK TABLES `quiz_participants` WRITE;
/*!40000 ALTER TABLE `quiz_participants` DISABLE KEYS */;
INSERT INTO `quiz_participants` VALUES (2,10,'2024-02-15 21:46:48',10),(3,10,'2024-02-15 21:47:56',10),(4,12,'2024-02-15 22:37:43',0),(4,13,'2024-02-15 22:37:21',4),(7,12,'2024-02-15 22:38:40',10),(7,13,'2024-02-15 22:38:43',0),(8,12,'2024-02-15 22:39:20',0),(8,13,'2024-02-15 22:39:14',10),(9,12,'2024-02-15 22:40:52',10),(9,13,'2024-02-15 22:40:00',10),(10,12,'2024-02-16 01:26:38',10),(10,13,'2024-02-15 22:59:19',10),(11,10,'2024-02-16 00:00:32',10),(11,12,'2024-02-15 23:57:09',10),(19,12,'2024-02-16 00:30:09',10),(20,10,'2024-02-16 01:28:38',0),(20,13,'2024-02-16 01:25:58',10),(21,13,'2024-02-16 02:17:39',10);
/*!40000 ALTER TABLE `quiz_participants` ENABLE KEYS */;
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
