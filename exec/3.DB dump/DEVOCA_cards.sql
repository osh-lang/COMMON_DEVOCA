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
-- Table structure for table `cards`
--

DROP TABLE IF EXISTS `cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cards` (
  `card_id` int NOT NULL AUTO_INCREMENT,
  `fk_card_user_idx` int NOT NULL,
  `card_repost_cnt` int DEFAULT '0',
  `card_word_id` int DEFAULT NULL,
  `card_origin_card_id` int DEFAULT NULL,
  `card_content` varchar(300) DEFAULT NULL,
  `card_link` varchar(2000) DEFAULT NULL,
  `card_regist_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `card_update_date` datetime DEFAULT NULL,
  `card_delete_date` datetime DEFAULT NULL,
  `card_related_keyword` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`card_id`),
  KEY `FK_users_TO_cards_1` (`fk_card_user_idx`),
  CONSTRAINT `FK_users_TO_cards_1` FOREIGN KEY (`fk_card_user_idx`) REFERENCES `users` (`user_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cards`
--

LOCK TABLES `cards` WRITE;
/*!40000 ALTER TABLE `cards` DISABLE KEYS */;
INSERT INTO `cards` VALUES (1,6,0,57,NULL,'안녕하세요! <br>[DEVoca: 개발자를 위한 단어장]에 오신 것을 환영합니다! ?<br>아래로 내려보세요!',NULL,'2024-02-15 20:59:00',NULL,NULL,NULL),(2,6,0,123,NULL,'의견 카드를 작성할 땐 단어 정의를 포함할 수 있어요! <br>카드 왼쪽 하단에 있는 북마크 버튼을 눌러 단어장에 저장해 볼까요?',NULL,'2024-02-15 20:58:00',NULL,NULL,NULL),(3,11,0,86,NULL,'자바보다는 파이썬이 문법이 덜 복잡한 것 같아요.','','2024-02-15 21:10:44',NULL,'2024-02-16 00:20:38',NULL),(4,10,0,67,NULL,'우리나라에서는 자바가 정말 많이 쓰이더라고요',NULL,'2024-02-15 21:11:14',NULL,NULL,NULL),(5,12,0,121,NULL,'mybatis를 사용해서 프로젝트를 진행해보고 JPA에 대해 공부를 하게 됐는데 mybatis보다 훨씬 사용하기가 간편하더라구요!\nmybatis만 써보셨던 분들은 JPA 한 번 공부해보셔도 좋을 것 같아요!',NULL,'2024-02-15 21:21:50',NULL,NULL,NULL),(6,13,0,121,NULL,'JPA를 사용하면 반복적인 SQL작업이 줄어들더라구요. 그래서 우리가 편하게 객체지향적으로 코드를 짤 수 있습니다!',NULL,'2024-02-15 21:22:41',NULL,NULL,NULL),(7,11,0,67,NULL,'2학기 때는 자바를 공부하고 싶었는데...\n시간이 없어 슬프네요',NULL,'2024-02-15 21:31:49',NULL,'2024-02-15 21:35:18',NULL),(9,10,3,121,NULL,'JPA는 자바 어플리케이션에서 데이터베이스와의 상호작용을 용이하게 만들어주는 굉장히 중요한 기술입니다.\n\n실제 프로젝트에서 JPA를 사용할 때는 구현체를 선택해야 하는데, 대표적으로 Hibernate를 사용합니다.\n\nJPA를 학습하고 활용하는 것은 처음엔 다소 복잡하게 느껴질 수 있지만, 한 번 익숙해지면 데이터베이스 작업의 생산성과 유지 보수성을 크게 개선할 수 있어요. 당장 완벽하게 이해하기 어렵더라도, 차근차근 학습해나가면서 실제 프로젝트에 적용해보는걸 추천합니다.',NULL,'2024-02-15 21:33:52',NULL,NULL,'[Hibernate]'),(11,6,0,67,4,'다른 개발자의 의견 카드를 인용할 수 있어요!',NULL,'2024-02-15 20:57:00',NULL,NULL,NULL),(13,6,0,67,NULL,'화면 아래쪽의 아이콘을 눌러 메뉴를 이동할 수 있어요. 왼쪽부터 단어 사전, 게시판, 메인화면, 검색, 마이페이지입니다.',NULL,'2024-02-15 20:56:00',NULL,NULL,NULL),(16,11,0,121,9,'테스트',NULL,'2024-02-15 22:10:42',NULL,'2024-02-15 22:10:50',NULL),(17,11,0,121,9,'이해가 잘 되네요 역시 신데브님이다!',NULL,'2024-02-15 22:15:20',NULL,'2024-02-16 00:20:46',NULL),(18,13,0,121,NULL,'Jpa',NULL,'2024-02-15 22:48:54',NULL,NULL,NULL),(19,13,0,32,NULL,'Db',NULL,'2024-02-15 22:55:35',NULL,NULL,NULL),(20,12,0,123,NULL,'엔터티는 백엔드 코드 작성할 때 DB랑 매핑하기 편리합니다',NULL,'2024-02-15 23:10:41',NULL,NULL,NULL),(21,12,0,36,NULL,'디버깅은 너무 어려워요... 로그 보다가 눈이 빠지는 줄 알았어요ㅠㅠㅠ',NULL,'2024-02-15 23:16:39',NULL,NULL,NULL),(22,12,0,7,NULL,'1일 1알고리즘 하기로 마음 먹었었는데 어렵네요ㅠㅠ',NULL,'2024-02-15 23:18:07',NULL,NULL,NULL),(23,10,0,122,NULL,'ORM을 쓰면 쿼리 결과를 자동으로 객체로 매핑해줘서 정말 편해요. 타입이 다르면 에러가 나니 확실하기도 하고요.',NULL,'2024-02-15 23:59:21',NULL,NULL,NULL),(24,11,0,67,NULL,'2학기 때는 꼭 자바 마스터해야하는데...<br>시간이 없어ㅠㅠ ',NULL,'2024-02-16 00:22:07',NULL,NULL,NULL),(25,14,0,94,NULL,'프론트엔드 업무 하면서 보는 라우터랑 다른 개념인데 넓게 보면 역할이 비슷하기는 한 것 같아요. 길의 중심에서 경로를 지정해주고 이동시켜준다는 점이요!! 그렇게 생각하면 좀 외우기 쉬우실수도...?',NULL,'2024-02-16 00:22:20',NULL,NULL,NULL),(26,14,0,121,9,'맞아요 저도 사실 mybatis만 쓰다가 JPA를 배우기가 조금 무서웠는데 막상 해보니까 너무 편리하더라구요! 아무래도 유지 보수가 용이하다는 점 때문에 회사에서도 주로 쓰기도 하구요ㅎㅎ',NULL,'2024-02-16 00:25:32',NULL,NULL,NULL),(27,11,0,10,NULL,'주파수랑 대역폭의 차이가 헷갈릴 수 있어요! 주파수는 단위 시간당 파동 사이클 수, 대역폭은 단위 시간 당 전송되는 데이터의 양입니다.. 그리고 주파수 대역폭이라는 것도 있는데, 이건 신호가 차지하는 주파수의 스펙트럼입니다! 저는 신호제어 쪽이라 주파수 대역폭을 많이 쓰는데 광통신 쪽은 파장 대역폭을 더 많이 쓴다고 하더라구요','http://www.ktword.co.kr/test/view/view.php?m_temp1=1157','2024-02-16 00:44:38',NULL,NULL,'[주파수, 주파수 대역폭, 파장 대역폭, 통신이론, 신호제어]'),(28,11,0,10,NULL,'주파수랑 대역폭의 차이가 헷갈릴 수 있어요! 주파수는 단위 시간당 파동 사이클 수, 대역폭은 단위 시간 당 전송되는 데이터의 양입니다.. 그리고 주파수 대역폭이라는 것도 있는데, 이건 신호가 차지하는 주파수의 스펙트럼입니다! 저는 신호제어 쪽이라 주파수 대역폭을 많이 쓰는데 광통신 쪽은 파장 대역폭을 더 많이 쓴다고 하더라구요','http://www.ktword.co.kr/test/view/view.php?m_temp1=1157','2024-02-16 00:45:20',NULL,'2024-02-16 00:45:32','[주파수, 주파수 대역폭, 파장 대역폭, 신호제어, 통신이론]'),(29,13,0,47,NULL,'Ffff',NULL,'2024-02-16 02:13:31',NULL,NULL,NULL);
/*!40000 ALTER TABLE `cards` ENABLE KEYS */;
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
