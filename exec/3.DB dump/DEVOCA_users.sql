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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_idx` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `user_birth` char(10) NOT NULL,
  `user_email` varchar(40) NOT NULL,
  `user_nickname` varchar(20) NOT NULL COMMENT 'null 시 user_name으로',
  `user_intro` varchar(100) DEFAULT NULL,
  `user_exp` int NOT NULL DEFAULT '0',
  `user_img` varchar(2000) NOT NULL DEFAULT '/src/assets/images/profile.png',
  `user_join_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_update_date` datetime DEFAULT NULL,
  `user_revoke_date` datetime DEFAULT NULL,
  `user_refresh_token` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`user_idx`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (6,'3332004002','æ-따거','2024-02-16','æ-Devoca@gmail.com','æ-데보카','DEVoca와 배워볼까?',0,'https://raw.githubusercontent.com/Ryujy/TIL_java/main/ae_devoca(1).png','2024-02-15 20:07:17',NULL,NULL,NULL),(10,'3332899971','신주용','2024-02-15','cheesecat47@gmail.com','마이구미',NULL,0,'https://avatars.githubusercontent.com/u/41780495?v=4','2024-02-15 20:11:14',NULL,NULL,NULL),(11,'3332004001','심규영','1994-02-01','ssafy0201@gmail.com','까망베르','풀스택 가보자고',0,'https://avatars.githubusercontent.com/u/139435196?v=4','2024-02-15 20:12:49',NULL,NULL,NULL),(12,'3345759989','김주이','1999-04-09','juyi077@gmail.com','슈이','개발자 꿈나무',0,'https://avatars.githubusercontent.com/u/45526515?v=4','2024-02-15 20:22:09',NULL,NULL,NULL),(13,'3331181306','오승현','1996-09-23','se03013@naver.com','DevGenius','코드를 보면 잠이 와요...',0,'https://avatars.githubusercontent.com/u/85592632?v=4','2024-02-15 20:28:41',NULL,NULL,NULL),(14,'3345754570','배성연','1997-06-18','amysia@knu.ac.kr','배따거',NULL,0,'https://avatars.githubusercontent.com/u/105983418?v=4','2024-02-15 21:23:00',NULL,NULL,NULL),(15,'3346025882','은아','1999-03-04','erropp89@naver.com','은아',NULL,0,'/src/assets/images/profile.png','2024-02-16 02:08:53',NULL,NULL,NULL),(16,'3346026293','이호성','1998-12-18','ghtjd8264@naver.com','트라살비','안뇽',0,'/src/assets/images/profile.png','2024-02-16 02:09:19',NULL,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
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
