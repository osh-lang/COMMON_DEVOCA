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
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `fk_comment_user_idx` int NOT NULL,
  `comment_board_id` int DEFAULT NULL,
  `comment_card_id` int DEFAULT NULL,
  `comment_content` varchar(100) NOT NULL,
  `comment_picked` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'true : 채택',
  `comment_regist_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `comment_delete_date` datetime DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FK_users_TO_comments_1` (`fk_comment_user_idx`),
  CONSTRAINT `FK_users_TO_comments_1` FOREIGN KEY (`fk_comment_user_idx`) REFERENCES `users` (`user_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,11,1,NULL,'안녕하세요!',0,'2024-02-15 20:56:05','2024-02-15 21:05:38'),(2,13,1,NULL,'댓글',0,'2024-02-15 20:59:00','2024-02-15 20:59:29'),(3,11,2,NULL,'저도 있습니다.',0,'2024-02-15 20:59:14','2024-02-15 20:59:33'),(4,11,2,NULL,'아니요',0,'2024-02-15 21:05:30','2024-02-15 21:05:33'),(5,13,3,NULL,'들리나요',0,'2024-02-15 21:28:03','2024-02-15 21:28:05'),(6,13,3,NULL,'들리나요',0,'2024-02-15 21:28:18','2024-02-15 21:28:24'),(7,12,NULL,9,'맞아요 처음엔 어렵더라구요..',0,'2024-02-15 22:05:10',NULL),(8,11,3,NULL,'안녕하세요!',0,'2024-02-15 22:43:43',NULL),(9,11,2,NULL,'테스트',0,'2024-02-15 22:44:32',NULL),(10,12,NULL,21,'팁을 알려주신다면 감사하겠습니다!',0,'2024-02-15 23:18:57',NULL),(11,12,NULL,23,'오호 JPA를 사용하면 자동으로 객체 매핑이 되니 JPA도 ORM이라고 할 수 있는 건가요??',0,'2024-02-16 00:01:50',NULL),(12,11,NULL,6,'맞아요 mybatis에서는 mapping만 한참 걸렸었는데 JPA는 하나하나 안 해도 돼서 좋더라구요',0,'2024-02-16 00:48:15',NULL);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
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
