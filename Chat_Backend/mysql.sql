-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: chatdb
-- ------------------------------------------------------
-- Server version	5.7.44-log


--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `message_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `message_type` enum('text','image') DEFAULT NULL,
  `message_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `sent_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`message_id`),
  KEY `sender_id` (`sender_id`),
  KEY `receiver_id` (`receiver_id`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`sender_id`) REFERENCES `user` (`uid`),
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (5,1,2,'text','chao hieu','2024-05-08 16:12:20'),(6,2,1,'text','chao hieu','2024-05-08 16:12:35'),(7,720332,2,'text','zjs','2024-05-12 10:27:52'),(8,720332,4,'text','xin chao bạn','2024-05-12 10:36:51'),(9,720332,4,'text','chao bé','2024-05-12 10:37:07'),(10,4,720332,'text','ú','2024-05-12 11:22:51'),(11,4,720332,'text','7iio','2024-05-12 11:22:54'),(12,720332,4,'text','hzjx','2024-05-12 11:30:10'),(13,720332,4,'text','p','2024-05-13 01:55:22'),(14,720332,4,'text','hưhq','2024-05-13 04:12:53'),(15,720332,4,'text','6','2024-05-13 04:12:57'),(16,720332,4,'text','ưuq','2024-05-13 04:13:01'),(17,720332,4,'text','uUa','2024-05-13 04:13:31'),(18,720332,4,'text','u','2024-05-13 04:13:37'),(19,720332,4,'text','77','2024-05-13 04:19:34'),(20,720332,4,'text','7','2024-05-13 04:19:37'),(21,720332,4,'text','hâu','2024-05-13 04:19:39'),(22,720332,4,'text','6','2024-05-13 04:21:18'),(23,720332,4,'text','?','2024-05-13 04:21:24'),(24,720332,1,'text','hau','2024-05-13 04:22:43'),(25,720332,1,'text','helli','2024-05-13 04:22:58'),(26,720332,4,'text','ư','2024-05-13 04:26:17'),(27,720332,1,'text','ư','2024-05-13 04:26:24'),(28,720332,1,'text','tr','2024-05-13 04:28:45'),(29,1,720332,'text','gga','2024-05-13 04:29:49'),(30,720332,1,'text','rha','2024-05-13 04:29:56'),(31,1,720332,'text','rjjj','2024-05-13 04:30:00'),(32,720332,1,'text','hhs','2024-05-13 04:37:01'),(33,720332,1,'text','uehe','2024-05-13 04:37:05'),(34,720332,1,'text','hs','2024-05-13 04:37:17'),(35,720332,1,'text','ư.','2024-05-13 04:37:44'),(36,720332,1,'text','ut','2024-05-13 04:38:55'),(37,720332,1,'text','chc','2024-05-13 04:39:02'),(38,720332,1,'text','nsns','2024-05-13 04:44:20'),(39,720332,1,'text','hh','2024-05-13 04:48:12'),(40,720332,1,'text','ư','2024-05-13 04:48:28'),(41,720332,1,'text','he','2024-05-13 04:50:15'),(42,720332,1,'text','bsdb','2024-05-13 04:51:18'),(43,720332,1,'text','ns','2024-05-13 04:51:32'),(44,720332,1,'text','ưg','2024-05-13 04:51:53'),(45,720332,1,'text','121','2024-05-13 04:52:03'),(46,720332,3,'text','hh','2024-05-13 04:52:39'),(47,720332,3,'text','dh','2024-05-13 04:53:06'),(48,720332,3,'text','xbd','2024-05-13 04:53:08'),(49,720332,3,'text','fnf','2024-05-13 04:53:11'),(50,720332,3,'text','bsd','2024-05-13 04:53:43'),(51,720332,4,'text','ứ7','2024-05-13 04:53:47'),(52,720332,3,'text','hsbs','2024-05-13 04:54:39'),(53,720332,1,'text','1','2024-05-13 04:57:31'),(54,720332,1,'text','1','2024-05-13 04:57:35'),(55,720332,4,'text','hhw','2024-05-13 04:57:54'),(56,720332,3,'text','jxkd','2024-05-13 04:58:29'),(57,720332,1,'text','uis','2024-05-14 03:19:23');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Duc Phu','phuhk123'),(2,'Duc hieu','phuhk123'),(3,'name','123'),(4,'sds','123'),(720332,'phu7','1');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
