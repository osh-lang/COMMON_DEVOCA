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
-- Table structure for table `battles`
--

DROP TABLE IF EXISTS `battles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `battles` (
  `battle_id` int NOT NULL AUTO_INCREMENT,
  `fk_battle_from_user_idx` int NOT NULL,
  `fk_battle_to_user_idx` int NOT NULL,
  `fk_battle_sl_id` int NOT NULL,
  `battle_from_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `battle_to_date` datetime DEFAULT NULL,
  `battle_from_score` int DEFAULT NULL,
  `battle_to_score` int DEFAULT NULL,
  `battle_word_cnt` int DEFAULT NULL,
  `fk_battle_quiz_id` int NOT NULL,
  PRIMARY KEY (`battle_id`),
  KEY `FK_users_TO_battles_1` (`fk_battle_from_user_idx`),
  KEY `FK_users_TO_battles_2` (`fk_battle_to_user_idx`),
  KEY `FK_voca_lists_TO_battles_1` (`fk_battle_sl_id`),
  KEY `FK_quizzes_TO_battles_1` (`fk_battle_quiz_id`),
  CONSTRAINT `FK_quizzes_TO_battles_1` FOREIGN KEY (`fk_battle_quiz_id`) REFERENCES `quizzes` (`quiz_id`),
  CONSTRAINT `FK_users_TO_battles_1` FOREIGN KEY (`fk_battle_from_user_idx`) REFERENCES `users` (`user_idx`),
  CONSTRAINT `FK_users_TO_battles_2` FOREIGN KEY (`fk_battle_to_user_idx`) REFERENCES `users` (`user_idx`),
  CONSTRAINT `FK_voca_lists_TO_battles_1` FOREIGN KEY (`fk_battle_sl_id`) REFERENCES `voca_lists` (`vl_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `battles`
--

LOCK TABLES `battles` WRITE;
/*!40000 ALTER TABLE `battles` DISABLE KEYS */;
INSERT INTO `battles` VALUES (1,10,11,1,'2024-02-15 21:46:48',NULL,10,NULL,1,2),(2,10,13,1,'2024-02-15 21:47:56',NULL,10,NULL,1,3),(3,12,13,3,'2024-02-15 22:38:40','2024-02-15 22:38:43',10,0,1,7),(4,13,12,3,'2024-02-15 22:39:14','2024-02-15 22:39:20',10,0,1,8),(5,13,12,3,'2024-02-15 22:40:00','2024-02-15 22:40:52',10,10,1,9),(6,13,12,6,'2024-02-15 22:59:19','2024-02-16 01:26:38',10,10,1,10),(7,12,10,3,'2024-02-15 23:57:09','2024-02-16 00:00:32',10,10,2,11),(8,12,14,5,'2024-02-16 00:30:09',NULL,10,NULL,1,19),(9,13,10,6,'2024-02-16 01:25:58','2024-02-16 01:28:38',10,0,1,20),(10,13,11,6,'2024-02-16 02:17:39',NULL,10,NULL,1,21);
/*!40000 ALTER TABLE `battles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-16 11:42:43
