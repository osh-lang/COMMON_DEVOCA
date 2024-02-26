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
-- Table structure for table `quiz_words`
--

DROP TABLE IF EXISTS `quiz_words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz_words` (
  `qw_id` int NOT NULL AUTO_INCREMENT,
  `qw_word_id` int NOT NULL,
  `qw_quiz_id` int NOT NULL,
  PRIMARY KEY (`qw_id`),
  KEY `FK_words_TO_quiz_words_1` (`qw_word_id`),
  KEY `FK_quizzes_TO_quiz_words_1` (`qw_quiz_id`),
  CONSTRAINT `FK_quizzes_TO_quiz_words_1` FOREIGN KEY (`qw_quiz_id`) REFERENCES `quizzes` (`quiz_id`),
  CONSTRAINT `FK_words_TO_quiz_words_1` FOREIGN KEY (`qw_word_id`) REFERENCES `words` (`word_id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz_words`
--

LOCK TABLES `quiz_words` WRITE;
/*!40000 ALTER TABLE `quiz_words` DISABLE KEYS */;
INSERT INTO `quiz_words` VALUES (1,70,1),(2,71,1),(3,76,1),(4,7,1),(5,72,1),(6,83,1),(7,79,1),(8,12,1),(9,121,1),(10,73,1),(11,67,2),(12,67,3),(13,30,4),(14,29,4),(15,31,4),(16,6,4),(17,43,4),(18,84,4),(19,105,4),(20,17,4),(21,32,4),(22,64,4),(23,121,7),(24,121,8),(25,121,9),(26,121,10),(27,32,11),(28,121,11),(45,32,19),(46,121,20),(47,123,21);
/*!40000 ALTER TABLE `quiz_words` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-16 11:42:44
