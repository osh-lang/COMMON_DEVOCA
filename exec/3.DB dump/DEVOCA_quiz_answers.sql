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
-- Table structure for table `quiz_answers`
--

DROP TABLE IF EXISTS `quiz_answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz_answers` (
  `qw_id` int NOT NULL,
  `qa_user_idx` int NOT NULL,
  `qa_answer` varchar(50) DEFAULT NULL,
  `qa_yn` int DEFAULT NULL COMMENT '0: 오답, 1: 정답',
  PRIMARY KEY (`qw_id`,`qa_user_idx`),
  KEY `FK_users_TO_quiz_answers_1` (`qa_user_idx`),
  CONSTRAINT `FK_quiz_words_TO_quiz_answers_1` FOREIGN KEY (`qw_id`) REFERENCES `quiz_words` (`qw_id`),
  CONSTRAINT `FK_users_TO_quiz_answers_1` FOREIGN KEY (`qa_user_idx`) REFERENCES `users` (`user_idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz_answers`
--

LOCK TABLES `quiz_answers` WRITE;
/*!40000 ALTER TABLE `quiz_answers` DISABLE KEYS */;
INSERT INTO `quiz_answers` VALUES (11,10,'Java',1),(12,10,'자바',1),(13,12,'테스트',0),(13,13,'컨테이너',1),(14,12,'22',0),(14,13,'동시성',1),(15,12,'ㅋㅋ',0),(15,13,'암호화',1),(16,12,'애자일 소프트웨어 방법론',0),(16,13,'에자일 소프트웨어 개발론',0),(17,12,'몰라요',0),(17,13,'실행',1),(18,12,'몰라요',0),(18,13,'모이용',0),(19,12,'뭘까요?',0),(19,13,'아아아',0),(20,12,'불린',0),(20,13,'가가',0),(21,12,'rdbms',0),(21,13,'다다',0),(22,12,'zzz',0),(22,13,'데이터',0),(23,12,'jpa',1),(23,13,'ㅇㅇㅇ',0),(24,12,'뭘까요',0),(24,13,'Jpa',1),(25,12,'jpa',1),(25,13,'Jpa',1),(26,12,'jpa',1),(26,13,'Jpa',1),(27,10,'데이터베이스',1),(27,12,'데이터베이스',1),(28,10,'jpa',1),(28,12,'jpa',1),(45,12,'데이터베이스',1),(46,10,'java',0),(46,13,'Jpa',1),(47,13,'Entity',1);
/*!40000 ALTER TABLE `quiz_answers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-16 11:42:45
