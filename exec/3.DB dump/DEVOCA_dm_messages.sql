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
-- Table structure for table `dm_messages`
--

DROP TABLE IF EXISTS `dm_messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dm_messages` (
  `dm_id` int NOT NULL AUTO_INCREMENT,
  `fk_dm_dr_idx` int NOT NULL,
  `fk_dm_user_idx` int NOT NULL,
  `dm_content` varchar(300) NOT NULL,
  `dm_send_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dm_battle_quiz_id` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`dm_id`),
  KEY `FK_users_TO_dm_messages_1` (`fk_dm_user_idx`),
  KEY `FK_dm_rooms_TO_dm_messages_1` (`fk_dm_dr_idx`),
  CONSTRAINT `FK_dm_rooms_TO_dm_messages_1` FOREIGN KEY (`fk_dm_dr_idx`) REFERENCES `dm_rooms` (`dr_idx`),
  CONSTRAINT `FK_users_TO_dm_messages_1` FOREIGN KEY (`fk_dm_user_idx`) REFERENCES `users` (`user_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm_messages`
--

LOCK TABLES `dm_messages` WRITE;
/*!40000 ALTER TABLE `dm_messages` DISABLE KEYS */;
INSERT INTO `dm_messages` VALUES (1,1,10,'hi','2024-02-15 21:44:54',0),(2,2,10,'hihi','2024-02-15 21:46:09',0),(3,2,11,'안녕!','2024-02-15 21:46:17',0),(4,2,10,'마이구미님이 도전장을 보내셨습니다!','2024-02-15 21:46:36',2),(5,3,10,'반가워요 ; )','2024-02-15 21:47:24',0),(6,3,10,'마이구미님이 도전장을 보내셨습니다!','2024-02-15 21:47:46',3),(7,3,13,'안녕하세여!','2024-02-15 21:48:02',0),(8,5,13,'안녕하세요 스터디 잘 부탁드립니다!','2024-02-15 22:25:16',0),(9,5,11,'안녕하세요~ 스터디 잘 부탁드립니다!','2024-02-15 22:25:44',0),(10,5,11,'같이 열심히 해봅시다!','2024-02-15 22:26:35',0),(11,5,13,'좋아요 JPA 고수가 되어봅시다!','2024-02-15 22:26:53',0),(12,5,13,'JPA 책 사셨나요??','2024-02-15 22:27:32',0),(13,5,11,'네!!! 책은 사뒀습니다!!!','2024-02-15 22:27:53',0),(14,5,13,'제가 좀 훑어봤는데','2024-02-15 22:29:12',0),(15,5,13,'1장은 금방 읽을 수 있을 거 같고','2024-02-15 22:29:26',0),(16,5,13,'일단 2장까지 공부해오는 걸로 할까요?','2024-02-15 22:29:57',0),(17,5,13,'어떠세요?','2024-02-15 22:30:01',0),(18,5,11,'네!!!좋아요!!!','2024-02-15 22:30:08',0),(19,5,13,'그럼 이따 뵙겠습니다~','2024-02-15 22:30:35',0),(20,5,13,'화이팅입니다!!','2024-02-15 22:30:45',0),(21,5,11,'넵!!! 화이팅!!!','2024-02-15 22:30:47',0),(22,6,12,'슈이님이 도전장을 보내셨습니다!','2024-02-15 22:38:31',7),(23,6,13,'DevGenius님이 도전장을 보내셨습니다!','2024-02-15 22:39:04',8),(24,6,13,'DevGenius님이 도전장을 보내셨습니다!','2024-02-15 22:39:54',9),(25,6,12,'d','2024-02-15 22:58:23',0),(26,6,13,'네 다 했습니다. 테스트 해볼까요?','2024-02-15 22:58:57',0),(27,6,13,'DevGenius님이 도전장을 보내셨습니다!','2024-02-15 22:59:11',10),(28,7,12,'슈이님이 도전장을 보내셨습니다!','2024-02-15 23:56:47',11),(29,8,12,'슈이님이 도전장을 보내셨습니다!','2024-02-16 00:29:51',19),(30,8,14,'시렁시렁','2024-02-16 00:35:15',0),(31,4,14,'안녕하세요','2024-02-16 00:49:02',0),(32,4,14,'님이 쓰신 글이 너무 좋아서 팔로우했어요ㅎㅎ','2024-02-16 00:49:24',0),(33,4,14,'그런데 오늘 올리신 카드에 오류가 조금 있는 것 같아서요..','2024-02-16 00:50:07',0),(34,3,13,'DevGenius님이 도전장을 보내셨습니다!','2024-02-16 01:25:50',20),(35,5,11,'공부 다 하셨을까요?','2024-02-16 02:16:49',0),(36,5,13,'네 다 했습니다. 테스트 해볼까요?','2024-02-16 02:17:02',0),(37,5,11,'네~^.^','2024-02-16 02:17:03',0),(38,5,13,'DevGenius님이 도전장을 보내셨습니다!','2024-02-16 02:17:29',21);
/*!40000 ALTER TABLE `dm_messages` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-16 11:42:38
