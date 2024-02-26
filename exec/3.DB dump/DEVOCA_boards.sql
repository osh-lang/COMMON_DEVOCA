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
-- Table structure for table `boards`
--

DROP TABLE IF EXISTS `boards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `boards` (
  `board_id` int NOT NULL AUTO_INCREMENT,
  `fk_board_user_idx` int NOT NULL,
  `board_title` varchar(50) NOT NULL,
  `board_content` varchar(500) NOT NULL,
  `board_type` int NOT NULL COMMENT '0 : 일반, 1: Q&A, 2:건의',
  `board_regist_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `board_update_date` datetime DEFAULT NULL,
  `board_delete_date` datetime DEFAULT NULL,
  PRIMARY KEY (`board_id`),
  KEY `FK_users_TO_boards_1` (`fk_board_user_idx`),
  CONSTRAINT `FK_users_TO_boards_1` FOREIGN KEY (`fk_board_user_idx`) REFERENCES `users` (`user_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boards`
--

LOCK TABLES `boards` WRITE;
/*!40000 ALTER TABLE `boards` DISABLE KEYS */;
INSERT INTO `boards` VALUES (1,11,'안녕하세요','반갑습니다!',0,'2024-02-15 20:55:29',NULL,NULL),(2,11,'아무도 없나요?','저만 있나요?',1,'2024-02-15 20:59:00',NULL,NULL),(3,13,'안녕하세요!!','들리시나요~',0,'2024-02-15 21:27:45',NULL,NULL),(4,14,'요즘 실무에서는 DB 관리 무슨 툴로 하나요?','저는 아무래도 DB에 직접 access하진 않는 직군이라 잘 모르고 살았는데 친구네 회사 보니 DBA쪽을 좀 배워두는 것도 좋을 것 같더라구요.. 그런데 아는 거라곤 학부생 시절에 했던 sql 쿼리 몇 개 뿐이라ㅎㅎㅠㅠㅠ 조금 걱정이 됩니다.. mysql이나 오라클이 있다는 정도는 아는데, 실무에 도움이 되려면 어느 dbms를 공부해야 할까요?',1,'2024-02-16 00:34:28',NULL,NULL);
/*!40000 ALTER TABLE `boards` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-16 11:42:41
