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
-- Table structure for table `words_categories`
--

DROP TABLE IF EXISTS `words_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `words_categories` (
  `wc_word_id` int NOT NULL,
  `wc_category_id` int NOT NULL,
  PRIMARY KEY (`wc_word_id`,`wc_category_id`),
  KEY `FK_categories_TO_words_categories_1` (`wc_category_id`),
  CONSTRAINT `FK_categories_TO_words_categories_1` FOREIGN KEY (`wc_category_id`) REFERENCES `categories` (`category_id`),
  CONSTRAINT `FK_words_TO_words_categories_1` FOREIGN KEY (`wc_word_id`) REFERENCES `words` (`word_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `words_categories`
--

LOCK TABLES `words_categories` WRITE;
/*!40000 ALTER TABLE `words_categories` DISABLE KEYS */;
INSERT INTO `words_categories` VALUES (7,6),(89,6),(25,7),(9,8),(8,9),(121,9),(122,9),(123,9),(124,9),(12,10),(79,11),(26,12),(27,13),(28,14),(80,15),(29,16),(30,17),(52,18),(5,19),(33,19),(51,20),(113,20),(34,21),(3,22),(15,22),(35,22),(47,22),(55,22),(56,22),(69,22),(71,22),(74,22),(77,22),(84,22),(87,22),(88,22),(99,22),(102,22),(110,22),(117,22),(16,23),(17,23),(18,23),(48,23),(62,23),(114,23),(32,24),(92,24),(100,24),(122,24),(123,24),(124,24),(125,24),(126,24),(104,25),(105,26),(44,27),(73,27),(116,27),(96,28),(46,29),(19,30),(55,31),(57,32),(118,32),(58,33),(22,34),(66,35),(67,36),(121,36),(66,37),(75,38),(10,39),(21,39),(38,39),(39,39),(94,39),(95,39),(4,40),(20,40),(60,40),(68,41),(91,41),(83,42),(23,44),(37,44),(41,44),(42,44),(43,44),(45,44),(49,44),(53,44),(58,44),(59,44),(64,44),(76,44),(78,44),(81,44),(82,44),(83,44),(85,44),(90,44),(103,44),(109,44),(111,44),(112,44),(115,44),(119,44),(67,45),(86,45),(98,45),(106,46),(31,47),(40,47),(101,48),(6,49),(24,49),(36,49),(65,49),(70,49),(72,49),(120,49),(61,50),(97,50),(108,50),(46,51),(11,52),(63,52),(93,52),(107,52),(50,53),(54,53);
/*!40000 ALTER TABLE `words_categories` ENABLE KEYS */;
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
