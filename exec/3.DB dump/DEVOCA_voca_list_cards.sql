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
-- Table structure for table `voca_list_cards`
--

DROP TABLE IF EXISTS `voca_list_cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voca_list_cards` (
  `vlc_id` int NOT NULL AUTO_INCREMENT,
  `vlc_sl_id` int NOT NULL,
  `vlc_wc_id` int NOT NULL,
  `vlc_wc_select` int NOT NULL COMMENT '1 : 단어, 2 : 카드',
  `vlc_store_date` datetime DEFAULT NULL,
  PRIMARY KEY (`vlc_id`,`vlc_sl_id`),
  KEY `FK_voca_lists_TO_voca_list_cards_1` (`vlc_sl_id`),
  CONSTRAINT `FK_voca_lists_TO_voca_list_cards_1` FOREIGN KEY (`vlc_sl_id`) REFERENCES `voca_lists` (`vl_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voca_list_cards`
--

LOCK TABLES `voca_list_cards` WRITE;
/*!40000 ALTER TABLE `voca_list_cards` DISABLE KEYS */;
INSERT INTO `voca_list_cards` VALUES (2,2,4,2,NULL),(4,1,13,2,NULL),(7,3,9,2,NULL),(8,6,18,2,NULL),(9,2,1,2,NULL),(13,4,19,2,NULL),(15,3,19,2,NULL),(18,6,20,2,NULL),(19,6,23,2,NULL),(23,5,27,2,NULL),(24,5,23,2,NULL),(27,14,1,2,NULL),(30,4,29,2,NULL);
/*!40000 ALTER TABLE `voca_list_cards` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-16 11:42:40
