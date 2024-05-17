CREATE DATABASE  IF NOT EXISTS `bookstore4` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bookstore4`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: bookstore.cvzu4xrxvkdz.eu-south-1.rds.amazonaws.com    Database: bookstore4
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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `surname_name_pk` (`name`,`surname`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1,'J.K.','Rowling','British author and philanthropist'),(2,'Isabel','Allende','The world\'s most widely read Spanish-language author'),(3,'George','Orwell','English novelist, essayist, journalist, and critic'),(4,'Oscar','Wilde','Flamboyant and sparklingly witty Anglo-Irish playwright, poet and critic'),(5,'Lev','Tolstoy','Master of realistic fiction and one of the world\'s greatest novelists'),(6,'Jane','Austen','English writer who first gave the novel its distinctly modern character through her treatment of ordinary people in everyday life'),(10,'Alessandro','Manzoni','Italian poet, novelist and philosopher'),(11,'Gianluca','Gotto','Ispirazione di vita!!'),(12,'Robert Louis','Stevenson','Scottish novelist, essayist, poet and travel writer'),(13,'Alessandro','Baricco','Italian writer');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `baskets`
--

DROP TABLE IF EXISTS `baskets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `baskets` (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_id` int NOT NULL,
  `user_id` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `book_id_idx` (`book_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `book_id` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baskets`
--

LOCK TABLES `baskets` WRITE;
/*!40000 ALTER TABLE `baskets` DISABLE KEYS */;
INSERT INTO `baskets` VALUES (32,5,5,3);
/*!40000 ALTER TABLE `baskets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `author_id` int NOT NULL,
  `editor` varchar(100) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `author_title_pk` (`title`,`author_id`),
  KEY `author_id_fk_idx` (`author_id`),
  CONSTRAINT `author_id_fk` FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Harry Potter and the Philosopher\'s Stone','Fantasy novel',1,'Bloomsbury',20.00,5),(2,'Harry Potter and the Chamber of Secrets','Fantasy novel',1,'Bloomsbury',15.00,13),(3,'Harry Potter and the Prisoner of Azkaban','Fantasy novel',1,'Bloomsbury',18.00,5),(4,'Harry Potter and the Goblet of Fire','Fantasy novel',1,'Bloomsbury',21.00,18),(5,'Harry Potter and the Order of the Phoenix','Fantasy novel',1,'Bloomsbury',22.00,5),(6,'Harry Potter and the Half-Blood Prince','Fantasy novel',1,'Bloomsbury',27.00,20),(7,'Harry Potter and the Deathly Hallows','Fantasy novel',1,'Bloomsbury',28.00,30),(8,'The House of the Spirits','Clara del Valle, has paranormal powers and keeps a detailed diary of her life',2,'Plaza & Janés',18.00,15),(9,'City of the Beasts','Story of Alexander Cold, who is 15 years old and going through a family crisis',2,'Sudamericana',17.00,14),(10,'Eva Luna','Eva Luna takes us into the life of the eponymous protagonist, an orphan who grows up in an unidentified country in South America',2,'Editorial Oveja Negra',12.00,7),(11,'\rIsland Beneath the Sea','Zarité (known as Tété) is the daughter of an African mother she never knew and one of the white sailors who brought her into bondage',2,'Sudamericana',19.00,21),(23,'Animal farm','A group of anthropomorphic farm animals who rebel against their human farmer',3,'Secker & Warburg',50.00,2),(24,'1984','Dystopic novel',3,'HarperCollins',16.00,3),(25,'The picture of Dorian Grey',' A young man named Dorian Gray who has a portrait painted of himself',4,'Simon & Schuster',12.50,19),(26,'War and Peace','broadly focuses on Napoleon\'s invasion of Russia in 1812 and follows three of the most well-known characters in literature',5,'Simon & Schuster',30.00,2),(29,'Pride and Prejudice','The turbulent relationship between Elizabeth Bennet, the daughter of a country gentleman, and Fitzwilliam Darcy, a rich aristocratic landowner',6,'T. Egerton, Whitehall',10.50,0),(30,'Promessi sposi','Italian historical novel',10,'Antonio Fortunato Stella',19.00,2),(31,'L\'importanza di chiamarsi Ernesto','opera teatrale',4,'Rusconi',25.50,2),(32,'Strange Case of Dr Jekyll and Mr Hyde','Gothic novella ',12,'Longmans, Green & Co.',19.00,7),(33,'Anna Karenina','Considered to be one of the greatest works of literature ever written',5,'The Russian Messenger',22.00,15);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `book_id` int NOT NULL,
  `quantity` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id_fk_idx` (`order_id`),
  KEY `book_id_fk_idx` (`book_id`),
  CONSTRAINT `book_id_fk` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  CONSTRAINT `order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (7,14,24,1,16.00),(8,14,11,2,19.00),(9,14,23,1,50.00),(10,15,24,1,16.00),(11,16,11,2,19.00),(12,16,30,1,20.50),(13,16,29,1,10.50),(14,17,7,1,28.00),(15,17,11,1,19.00),(16,17,24,1,16.00),(17,18,7,1,28.00),(18,18,1,1,15.00),(19,18,9,1,17.00),(20,19,1,1,15.00),(22,21,1,1,15.00),(23,21,26,1,30.00),(24,22,1,1,20.00),(25,23,3,8,18.00),(28,33,3,1,18.00),(29,33,9,1,17.00),(30,33,11,2,38.00),(31,34,3,1,18.00),(32,34,9,1,17.00),(33,34,11,2,38.00),(34,34,31,2,51.00),(35,35,1,2,40.00),(36,35,2,2,30.00);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `user_id` int NOT NULL,
  `status` varchar(1) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `shipping_address` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_fk_idx` (`user_id`),
  CONSTRAINT `user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (13,'2023-07-19 15:35:37',1,'C',104.00,'via sconosciuta'),(14,'2023-07-19 15:37:51',1,'I',104.00,'via sconosciuta'),(15,'2023-07-19 15:57:28',1,'C',104.00,'Via saraàgiustononloso'),(16,'2023-07-19 16:04:04',1,'I',69.00,'Via saraàgiustononloso'),(17,'2023-07-19 16:38:15',1,'C',63.00,'Via prova delle 16:38'),(18,'2023-07-19 17:04:57',5,'C',60.00,'via della disperazione 3'),(19,'2023-07-24 11:11:07',1,'C',45.00,'prova delle 11:11'),(21,'2023-07-24 11:34:16',1,'C',45.00,'prova delle 11:34'),(22,'2023-07-25 09:14:14',1,'C',20.00,'prova delle 09:13'),(23,'2023-08-08 16:48:47',5,'P',144.00,'via prova'),(33,'2023-09-22 14:38:56',5,'I',0.00,'indirizzo di prova'),(34,'2023-09-22 14:41:00',5,'I',124.00,'indirizzo di prova'),(35,'2023-09-22 16:39:08',5,'I',70.00,'indirizzo di prova');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'alice.ceccarelli@gmail.com','00000','Alice','Ceccarelli','2023-07-14 15:23:00'),(5,'chiara.iannetta@gmail.com','11111','Chiara','Iannetta','2023-07-17 15:32:12'),(7,'prova@gmail.com','prova','Prova','Provaprova','2023-07-24 14:08:07'),(8,'post@post','00000','aaa','iiii','2023-08-09 10:24:38'),(9,'dsa','dsa','ads','dsa','2023-09-22 12:08:18'),(10,'aaaa','aaa','aa','aaa','2023-09-22 12:34:07');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-17 10:20:37
CREATE DATABASE  IF NOT EXISTS `fly_mary` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fly_mary`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: bookstore.cvzu4xrxvkdz.eu-south-1.rds.amazonaws.com    Database: fly_mary
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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authority` (
  `id` int NOT NULL,
  `authority` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` VALUES (1,'USER'),(2,'ADMIN');
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookings` (
  `id` int NOT NULL AUTO_INCREMENT,
  `purchase_id` int DEFAULT NULL,
  `departure` datetime NOT NULL,
  `arrival` datetime NOT NULL,
  `departure_xport_id` int DEFAULT NULL,
  `arrival_xport_id` int DEFAULT NULL,
  `n_tickets` int NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `travel_id` int NOT NULL,
  `schedule_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `departure_stop_fk_idx` (`departure_xport_id`),
  KEY `arrival_stop_fk_idx` (`arrival_xport_id`),
  KEY `purchase_id_fk_2_idx` (`purchase_id`),
  CONSTRAINT `arrival_stop_fk` FOREIGN KEY (`arrival_xport_id`) REFERENCES `xports` (`id`),
  CONSTRAINT `departure_stop_fk` FOREIGN KEY (`departure_xport_id`) REFERENCES `xports` (`id`),
  CONSTRAINT `purchase_id_fk_2` FOREIGN KEY (`purchase_id`) REFERENCES `purchases` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (34,48,'2023-09-20 08:30:00','2023-09-20 10:30:00',7,15,1,120.22,26,6),(35,49,'2023-09-25 10:00:00','2023-09-25 11:00:00',20,14,1,30.00,30,14),(36,50,'2023-09-20 06:30:00','2023-09-20 12:30:00',7,15,1,140.22,31,3),(37,50,'2023-09-20 06:30:12','2023-09-20 10:53:32',15,7,1,140.22,29,4),(38,51,'2023-09-20 08:30:00','2023-09-20 10:30:00',7,15,1,120.22,26,6),(39,52,'2023-09-27 08:30:00','2023-09-27 10:30:00',7,15,1,120.22,32,6),(40,53,'2023-09-20 06:30:00','2023-09-20 12:30:00',7,15,1,140.22,31,3),(41,53,'2023-09-20 08:56:04','2023-09-20 10:56:04',15,7,1,110.22,33,5),(42,54,'2023-09-20 06:30:00','2023-09-20 12:30:00',7,15,1,140.22,31,3),(43,55,'2023-09-20 06:30:00','2023-09-20 12:30:00',7,15,1,140.22,31,3),(44,55,'2023-09-20 06:30:12','2023-09-20 10:53:32',15,7,1,140.22,29,4),(45,56,'2023-09-20 06:30:00','2023-09-20 12:30:00',7,15,1,140.22,31,3),(46,56,'2023-09-20 06:30:12','2023-09-20 10:53:32',15,7,1,140.22,29,4),(47,57,'2023-09-19 19:50:00','2023-09-19 23:01:00',22,6,1,20.80,34,27),(48,57,'2023-09-19 14:00:00','2023-09-19 17:11:00',6,22,1,45.25,35,11),(49,58,'2023-09-20 06:30:00','2023-09-20 12:30:00',7,15,1,140.22,31,3),(50,58,'2023-09-20 06:30:00','2023-09-20 10:53:20',15,7,1,140.22,29,4),(51,59,'2023-09-27 06:30:00','2023-09-27 12:30:00',7,15,1,140.22,36,3),(52,59,'2023-09-27 08:56:00','2023-09-27 10:56:00',15,7,1,110.22,37,5),(53,60,'2023-09-27 06:30:00','2023-09-27 12:30:00',7,15,1,140.22,36,3),(54,61,'2023-09-20 06:30:00','2023-09-20 12:30:00',7,15,1,140.22,31,3),(55,61,'2023-09-20 08:56:00','2023-09-20 10:56:00',15,7,1,110.22,33,5),(56,62,'2023-09-27 06:30:00','2023-09-27 12:30:00',7,15,1,140.22,36,3),(57,62,'2023-09-27 08:56:00','2023-09-27 10:56:00',15,7,1,110.22,37,5),(58,63,'2023-09-27 14:50:00','2023-09-27 16:50:00',7,15,2,40.00,38,7),(59,64,'2023-09-27 04:30:00','2023-09-27 07:41:00',6,22,2,41.60,39,11),(60,65,'2023-09-27 14:50:00','2023-09-27 16:50:00',7,15,1,20.00,38,7),(61,65,'2023-09-27 08:56:00','2023-09-27 10:56:00',15,7,1,110.22,37,5),(62,66,'2023-09-27 14:50:00','2023-09-27 16:50:00',7,15,2,40.00,38,7),(63,67,'2023-09-27 08:56:00','2023-09-27 10:56:00',15,7,1,110.22,37,5),(64,68,'2023-10-18 06:30:00','2023-10-18 12:30:00',7,15,1,140.22,40,3),(65,68,'2023-10-18 06:30:00','2023-10-18 10:33:00',15,7,1,140.22,41,4),(66,69,'2023-10-11 14:50:00','2023-10-11 16:50:00',7,15,1,20.00,42,7),(67,69,'2023-10-11 08:56:00','2023-10-11 10:56:00',15,7,1,110.22,43,5),(68,70,'2023-10-18 14:50:00','2023-10-18 16:50:00',7,15,1,20.00,44,7),(69,70,'2023-10-18 06:30:00','2023-10-18 10:33:00',15,7,1,140.22,41,4),(70,71,'2023-10-11 06:30:00','2023-10-11 12:30:00',7,15,1,140.22,45,3),(71,71,'2023-10-11 06:30:00','2023-10-11 10:33:00',15,7,1,140.22,46,4),(72,72,'2023-10-18 06:30:00','2023-10-18 12:30:00',7,15,1,140.22,40,3),(73,72,'2023-10-18 06:30:00','2023-10-18 10:33:00',15,7,1,140.22,41,4),(74,73,'2023-10-09 22:00:00','2023-10-23 06:00:00',7,32,1,18.50,47,8),(75,74,'2023-10-11 14:50:00','2023-10-11 16:50:00',7,15,1,20.00,42,7),(76,74,'2023-10-11 08:56:00','2023-10-11 10:56:00',15,7,1,110.22,43,5),(77,75,'2023-10-23 10:00:00','2023-10-23 11:00:00',20,14,1,30.00,48,14),(78,76,'2023-10-11 06:30:00','2023-10-11 12:30:00',7,15,1,140.22,45,3),(79,76,'2023-10-11 06:30:00','2023-10-11 10:33:00',15,7,1,140.22,46,4),(80,77,'2023-10-11 06:30:00','2023-10-11 12:30:00',7,15,1,140.22,45,3),(81,77,'2023-10-11 06:30:00','2023-10-11 10:33:00',15,7,1,140.22,46,4),(82,78,'2023-10-11 06:30:00','2023-10-11 12:30:00',7,15,1,140.22,45,3),(83,79,'2023-10-11 08:30:00','2023-10-11 14:30:00',7,15,1,140.22,45,3),(84,79,'2023-10-11 10:56:00','2023-10-11 12:56:00',15,7,1,110.22,43,5),(85,80,'2023-10-11 08:30:00','2023-10-11 14:30:00',7,15,1,140.22,45,3),(86,80,'2023-10-11 08:30:00','2023-10-11 12:33:00',15,7,1,140.22,46,4),(87,81,'2023-10-11 08:30:00','2023-10-11 14:30:00',7,15,1,140.22,45,3),(88,82,'2023-10-10 00:00:00','2023-10-23 08:00:00',7,32,1,18.50,47,8),(89,83,'2023-10-12 16:00:00','2023-10-12 18:00:00',1,20,2,60.00,49,53),(90,83,'2023-10-12 16:00:00','2023-10-12 18:00:00',20,1,2,40.00,50,43),(91,93,'2023-10-18 06:30:00','2023-10-18 12:30:00',7,15,2,418.44,40,3),(92,94,'2023-10-18 06:30:00','2023-10-18 12:30:00',7,15,2,418.44,40,3),(93,94,'2023-10-25 20:30:00','2023-10-25 21:30:00',15,7,2,418.44,51,61),(94,95,'2023-10-18 14:50:00','2023-10-18 16:50:00',7,15,2,40.00,44,7),(95,95,'2023-10-25 20:30:00','2023-10-25 21:30:00',15,7,2,138.00,51,61),(96,97,'2023-10-18 16:50:00','2023-10-18 18:50:00',7,15,2,40.00,44,7),(97,97,'2023-10-18 10:56:00','2023-10-18 12:56:00',15,7,2,220.44,53,5),(98,98,'2023-10-18 14:50:00','2023-10-18 16:50:00',7,15,2,40.00,44,7),(99,98,'2023-10-25 20:30:00','2023-10-25 21:30:00',15,7,2,138.00,51,61);
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cities` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `time_zone` varchar(5) DEFAULT NULL,
  `country` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `code_fk_idx` (`country`),
  CONSTRAINT `code_fk` FOREIGN KEY (`country`) REFERENCES `countries` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=1102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES (247,'Tirana','CET','ALB'),(248,'Andorra la Vella','CET','AND'),(249,'Wien','CET','AUT'),(250,'Graz','CET','AUT'),(251,'Linz','CET','AUT'),(252,'Salzburg','CET','AUT'),(253,'Innsbruck','CET','AUT'),(254,'Klagenfurt','CET','AUT'),(255,'Antwerpen','CET','BEL'),(256,'Gent','CET','BEL'),(257,'Charleroi','CET','BEL'),(258,'Liège','CET','BEL'),(259,'Bruxelles [Brussel]','CET','BEL'),(260,'Brugge','CET','BEL'),(261,'Schaerbeek','CET','BEL'),(262,'Namur','CET','BEL'),(263,'Mons','CET','BEL'),(264,'Sofija','EET','BGR'),(265,'Plovdiv','EET','BGR'),(266,'Varna','EET','BGR'),(267,'Burgas','EET','BGR'),(268,'Ruse','EET','BGR'),(269,'Stara Zagora','EET','BGR'),(270,'Pleven','EET','BGR'),(271,'Sliven','EET','BGR'),(272,'Dobric','EET','BGR'),(273,'Šumen','EET','BGR'),(274,'Sarajevo','CET','BIH'),(275,'Banja Luka','CET','BIH'),(276,'Zenica','CET','BIH'),(277,'Minsk','EET','BLR'),(278,'Gomel','EET','BLR'),(279,'Mogiljov','EET','BLR'),(280,'Vitebsk','EET','BLR'),(281,'Grodno','EET','BLR'),(282,'Brest','EET','BLR'),(283,'Bobruisk','EET','BLR'),(284,'Baranovitši','EET','BLR'),(285,'Borisov','EET','BLR'),(286,'Pinsk','EET','BLR'),(287,'Orša','EET','BLR'),(288,'Mozyr','EET','BLR'),(289,'Novopolotsk','EET','BLR'),(290,'Lida','EET','BLR'),(291,'Soligorsk','EET','BLR'),(292,'Molodetšno','EET','BLR'),(293,'Zürich','CET','CHE'),(294,'Geneve','CET','CHE'),(295,'Basel','CET','CHE'),(296,'Bern','CET','CHE'),(297,'Lausanne','CET','CHE'),(298,'Praha','CET','CZE'),(299,'Brno','CET','CZE'),(300,'Ostrava','CET','CZE'),(301,'Plzen','CET','CZE'),(302,'Olomouc','CET','CZE'),(303,'Liberec','CET','CZE'),(304,'Ceské Budejovice','CET','CZE'),(305,'Hradec Králové','CET','CZE'),(306,'Ústí nad Labem','CET','CZE'),(307,'Pardubice','CET','CZE'),(308,'Berlin','CET','DEU'),(309,'Hamburg','CET','DEU'),(310,'Munich','CET','DEU'),(311,'Köln','CET','DEU'),(312,'Frankfurt am Main','CET','DEU'),(313,'Essen','CET','DEU'),(314,'Dortmund','CET','DEU'),(315,'Stuttgart','CET','DEU'),(316,'Düsseldorf','CET','DEU'),(317,'Bremen','CET','DEU'),(318,'Duisburg','CET','DEU'),(319,'Hannover','CET','DEU'),(320,'Leipzig','CET','DEU'),(321,'Nürnberg','CET','DEU'),(322,'Dresden','CET','DEU'),(323,'Bochum','CET','DEU'),(324,'Wuppertal','CET','DEU'),(325,'Bielefeld','CET','DEU'),(326,'Mannheim','CET','DEU'),(327,'Bonn','CET','DEU'),(328,'Gelsenkirchen','CET','DEU'),(329,'Karlsruhe','CET','DEU'),(330,'Wiesbaden','CET','DEU'),(331,'Münster','CET','DEU'),(332,'Mönchengladbach','CET','DEU'),(333,'Chemnitz','CET','DEU'),(334,'Augsburg','CET','DEU'),(335,'Halle/Saale','CET','DEU'),(336,'Braunschweig','CET','DEU'),(337,'Aachen','CET','DEU'),(338,'Krefeld','CET','DEU'),(339,'Magdeburg','CET','DEU'),(340,'Kiel','CET','DEU'),(341,'Oberhausen','CET','DEU'),(342,'Lübeck','CET','DEU'),(343,'Hagen','CET','DEU'),(344,'Rostock','CET','DEU'),(345,'Freiburg im Breisgau','CET','DEU'),(346,'Erfurt','CET','DEU'),(347,'Kassel','CET','DEU'),(348,'Saarbrücken','CET','DEU'),(349,'Mainz','CET','DEU'),(350,'Hamm','CET','DEU'),(351,'Herne','CET','DEU'),(352,'Mülheim an der Ruhr','CET','DEU'),(353,'Solingen','CET','DEU'),(354,'Osnabrück','CET','DEU'),(355,'Ludwigshafen am Rhein','CET','DEU'),(356,'Leverkusen','CET','DEU'),(357,'Oldenburg','CET','DEU'),(358,'Neuss','CET','DEU'),(359,'Heidelberg','CET','DEU'),(360,'Darmstadt','CET','DEU'),(361,'Paderborn','CET','DEU'),(362,'Potsdam','CET','DEU'),(363,'Würzburg','CET','DEU'),(364,'Regensburg','CET','DEU'),(365,'Recklinghausen','CET','DEU'),(366,'Göttingen','CET','DEU'),(367,'Bremerhaven','CET','DEU'),(368,'Wolfsburg','CET','DEU'),(369,'Bottrop','CET','DEU'),(370,'Remscheid','CET','DEU'),(371,'Heilbronn','CET','DEU'),(372,'Pforzheim','CET','DEU'),(373,'Offenbach am Main','CET','DEU'),(374,'Ulm','CET','DEU'),(375,'Ingolstadt','CET','DEU'),(376,'Gera','CET','DEU'),(377,'Salzgitter','CET','DEU'),(378,'Cottbus','CET','DEU'),(379,'Reutlingen','CET','DEU'),(380,'Fürth','CET','DEU'),(381,'Siegen','CET','DEU'),(382,'Koblenz','CET','DEU'),(383,'Moers','CET','DEU'),(384,'Bergisch Gladbach','CET','DEU'),(385,'Zwickau','CET','DEU'),(386,'Hildesheim','CET','DEU'),(387,'Witten','CET','DEU'),(388,'Schwerin','CET','DEU'),(389,'Erlangen','CET','DEU'),(390,'Kaiserslautern','CET','DEU'),(391,'Trier','CET','DEU'),(392,'Jena','CET','DEU'),(393,'Iserlohn','CET','DEU'),(394,'Gütersloh','CET','DEU'),(395,'Marl','CET','DEU'),(396,'Lünen','CET','DEU'),(397,'Düren','CET','DEU'),(398,'Ratingen','CET','DEU'),(399,'Velbert','CET','DEU'),(400,'Esslingen am Neckar','CET','DEU'),(401,'København','CET','DNK'),(402,'Århus','CET','DNK'),(403,'Odense','CET','DNK'),(404,'Aalborg','CET','DNK'),(405,'Frederiksberg','CET','DNK'),(406,'Madrid','CET','ESP'),(407,'Barcelona','CET','ESP'),(408,'Valencia','CET','ESP'),(409,'Sevilla','CET','ESP'),(410,'Zaragoza','CET','ESP'),(411,'Málaga','CET','ESP'),(412,'Bilbao','CET','ESP'),(413,'Las Palmas de Gran Canaria','CET','ESP'),(414,'Murcia','CET','ESP'),(415,'Palma de Mallorca','CET','ESP'),(416,'Valladolid','CET','ESP'),(417,'Córdoba','CET','ESP'),(418,'Vigo','CET','ESP'),(419,'Alicante [Alacant]','CET','ESP'),(420,'Gijón','CET','ESP'),(421,'L´Hospitalet de Llobregat','CET','ESP'),(422,'Granada','CET','ESP'),(423,'A Coruña (La Coruña)','CET','ESP'),(424,'Vitoria-Gasteiz','CET','ESP'),(425,'Santa Cruz de Tenerife','CET','ESP'),(426,'Badalona','CET','ESP'),(427,'Oviedo','CET','ESP'),(428,'Móstoles','CET','ESP'),(429,'Elche [Elx]','CET','ESP'),(430,'Sabadell','CET','ESP'),(431,'Santander','CET','ESP'),(432,'Jerez de la Frontera','CET','ESP'),(433,'Pamplona [Iruña]','CET','ESP'),(434,'Donostia-San Sebastián','CET','ESP'),(435,'Cartagena','CET','ESP'),(436,'Leganés','CET','ESP'),(437,'Fuenlabrada','CET','ESP'),(438,'Almería','CET','ESP'),(439,'Terrassa','CET','ESP'),(440,'Alcalá de Henares','CET','ESP'),(441,'Burgos','CET','ESP'),(442,'Salamanca','CET','ESP'),(443,'Albacete','CET','ESP'),(444,'Getafe','CET','ESP'),(445,'Cádiz','CET','ESP'),(446,'Alcorcón','CET','ESP'),(447,'Huelva','CET','ESP'),(448,'León','CET','ESP'),(449,'Castellón de la Plana [Castell','CET','ESP'),(450,'Badajoz','CET','ESP'),(451,'San Cristóbal de la Laguna','CET','ESP'),(452,'Logroño','CET','ESP'),(453,'Santa Coloma de Gramenet','CET','ESP'),(454,'Tarragona','CET','ESP'),(455,'Lleida (Lérida)','CET','ESP'),(456,'Jaén','CET','ESP'),(457,'Ourense (Orense)','CET','ESP'),(458,'Mataró','CET','ESP'),(459,'Algeciras','CET','ESP'),(460,'Marbella','CET','ESP'),(461,'Barakaldo','CET','ESP'),(462,'Dos Hermanas','CET','ESP'),(463,'Santiago de Compostela','CET','ESP'),(464,'Torrejón de Ardoz','CET','ESP'),(465,'Tallinn','EET','EST'),(466,'Tartu','EET','EST'),(467,'Helsinki [Helsingfors]','EET','FIN'),(468,'Espoo','EET','FIN'),(469,'Tampere','EET','FIN'),(470,'Vantaa','EET','FIN'),(471,'Turku [Åbo]','EET','FIN'),(472,'Oulu','EET','FIN'),(473,'Lahti','EET','FIN'),(474,'Paris','CET','FRA'),(475,'Marseille','CET','FRA'),(476,'Lyon','CET','FRA'),(477,'Toulouse','CET','FRA'),(478,'Nice','CET','FRA'),(479,'Nantes','CET','FRA'),(480,'Strasbourg','CET','FRA'),(481,'Montpellier','CET','FRA'),(482,'Bordeaux','CET','FRA'),(483,'Rennes','CET','FRA'),(484,'Le Havre','CET','FRA'),(485,'Reims','CET','FRA'),(486,'Lille','CET','FRA'),(487,'St-Étienne','CET','FRA'),(488,'Toulon','CET','FRA'),(489,'Grenoble','CET','FRA'),(490,'Angers','CET','FRA'),(491,'Dijon','CET','FRA'),(492,'Brest','CET','FRA'),(493,'Le Mans','CET','FRA'),(494,'Clermont-Ferrand','CET','FRA'),(495,'Amiens','CET','FRA'),(496,'Aix-en-Provence','CET','FRA'),(497,'Limoges','CET','FRA'),(498,'Nîmes','CET','FRA'),(499,'Tours','CET','FRA'),(500,'Villeurbanne','CET','FRA'),(501,'Metz','CET','FRA'),(502,'Besançon','CET','FRA'),(503,'Caen','CET','FRA'),(504,'Orléans','CET','FRA'),(505,'Mulhouse','CET','FRA'),(506,'Rouen','CET','FRA'),(507,'Boulogne-Billancourt','CET','FRA'),(508,'Perpignan','CET','FRA'),(509,'Nancy','CET','FRA'),(510,'Roubaix','CET','FRA'),(511,'Argenteuil','CET','FRA'),(512,'Tourcoing','CET','FRA'),(513,'Montreuil','CET','FRA'),(514,'Tórshavn','EET','FRO'),(515,'London','WET','GBR'),(516,'Birmingham','WET','GBR'),(517,'Glasgow','WET','GBR'),(518,'Liverpool','WET','GBR'),(519,'Edinburgh','WET','GBR'),(520,'Sheffield','WET','GBR'),(521,'Manchester','WET','GBR'),(522,'Leeds','WET','GBR'),(523,'Bristol','WET','GBR'),(524,'Cardiff','WET','GBR'),(525,'Coventry','WET','GBR'),(526,'Leicester','WET','GBR'),(527,'Bradford','WET','GBR'),(528,'Belfast','WET','GBR'),(529,'Nottingham','WET','GBR'),(530,'Kingston upon Hull','WET','GBR'),(531,'Plymouth','WET','GBR'),(532,'Stoke-on-Trent','WET','GBR'),(533,'Wolverhampton','WET','GBR'),(534,'Derby','WET','GBR'),(535,'Swansea','WET','GBR'),(536,'Southampton','WET','GBR'),(537,'Aberdeen','WET','GBR'),(538,'Northampton','WET','GBR'),(539,'Dudley','WET','GBR'),(540,'Portsmouth','WET','GBR'),(541,'Newcastle upon Tyne','WET','GBR'),(542,'Sunderland','WET','GBR'),(543,'Luton','WET','GBR'),(544,'Swindon','WET','GBR'),(545,'Southend-on-Sea','WET','GBR'),(546,'Walsall','WET','GBR'),(547,'Bournemouth','WET','GBR'),(548,'Peterborough','WET','GBR'),(549,'Brighton','WET','GBR'),(550,'Blackpool','WET','GBR'),(551,'Dundee','WET','GBR'),(552,'West Bromwich','WET','GBR'),(553,'Reading','WET','GBR'),(554,'Oldbury/Smethwick (Warley)','WET','GBR'),(555,'Middlesbrough','WET','GBR'),(556,'Huddersfield','WET','GBR'),(557,'Oxford','WET','GBR'),(558,'Poole','WET','GBR'),(559,'Bolton','WET','GBR'),(560,'Blackburn','WET','GBR'),(561,'Newport','WET','GBR'),(562,'Preston','WET','GBR'),(563,'Stockport','WET','GBR'),(564,'Norwich','WET','GBR'),(565,'Rotherham','WET','GBR'),(566,'Cambridge','WET','GBR'),(567,'Watford','WET','GBR'),(568,'Ipswich','WET','GBR'),(569,'Slough','WET','GBR'),(570,'Exeter','WET','GBR'),(571,'Cheltenham','WET','GBR'),(572,'Gloucester','WET','GBR'),(573,'Saint Helens','WET','GBR'),(574,'Sutton Coldfield','WET','GBR'),(575,'York','WET','GBR'),(576,'Oldham','WET','GBR'),(577,'Basildon','WET','GBR'),(578,'Worthing','WET','GBR'),(579,'Chelmsford','WET','GBR'),(580,'Colchester','WET','GBR'),(581,'Crawley','WET','GBR'),(582,'Gillingham','WET','GBR'),(583,'Solihull','WET','GBR'),(584,'Rochdale','WET','GBR'),(585,'Birkenhead','WET','GBR'),(586,'Worcester','WET','GBR'),(587,'Hartlepool','WET','GBR'),(588,'Halifax','WET','GBR'),(589,'Woking/Byfleet','WET','GBR'),(590,'Southport','WET','GBR'),(591,'Maidstone','WET','GBR'),(592,'Eastbourne','WET','GBR'),(593,'Grimsby','WET','GBR'),(594,'Saint Helier','WET','GBR'),(595,'Douglas','WET','GBR'),(596,'Gibraltar','CET','GIB'),(597,'Athenai','EET','GRC'),(598,'Thessaloniki','EET','GRC'),(599,'Pireus','EET','GRC'),(600,'Patras','EET','GRC'),(601,'Peristerion','EET','GRC'),(602,'Herakleion','EET','GRC'),(603,'Kallithea','EET','GRC'),(604,'Larisa','EET','GRC'),(605,'Zagreb','CET','HRV'),(606,'Split','CET','HRV'),(607,'Rijeka','CET','HRV'),(608,'Osijek','CET','HRV'),(609,'Budapest','CET','HUN'),(610,'Debrecen','CET','HUN'),(611,'Miskolc','CET','HUN'),(612,'Szeged','CET','HUN'),(613,'Pécs','CET','HUN'),(614,'Györ','CET','HUN'),(615,'Nyiregyháza','CET','HUN'),(616,'Kecskemét','CET','HUN'),(617,'Székesfehérvár','CET','HUN'),(618,'Dublin','WET','IRL'),(619,'Cork','WET','IRL'),(620,'Reykjavík','WET','ISL'),(621,'Roma','CET','ITA'),(622,'Milano','CET','ITA'),(623,'Napoli','CET','ITA'),(624,'Torino','CET','ITA'),(625,'Palermo','CET','ITA'),(626,'Genova','CET','ITA'),(627,'Bologna','CET','ITA'),(628,'Firenze','CET','ITA'),(629,'Catania','CET','ITA'),(630,'Bari','CET','ITA'),(631,'Venezia','CET','ITA'),(632,'Messina','CET','ITA'),(633,'Verona','CET','ITA'),(634,'Trieste','CET','ITA'),(635,'Padova','CET','ITA'),(636,'Taranto','CET','ITA'),(637,'Brescia','CET','ITA'),(638,'Reggio di Calabria','CET','ITA'),(639,'Modena','CET','ITA'),(640,'Prato','CET','ITA'),(641,'Parma','CET','ITA'),(642,'Cagliari','CET','ITA'),(643,'Livorno','CET','ITA'),(644,'Perugia','CET','ITA'),(645,'Foggia','CET','ITA'),(646,'Reggio nell´ Emilia','CET','ITA'),(647,'Salerno','CET','ITA'),(648,'Ravenna','CET','ITA'),(649,'Ferrara','CET','ITA'),(650,'Rimini','CET','ITA'),(651,'Syrakusa','CET','ITA'),(652,'Sassari','CET','ITA'),(653,'Monza','CET','ITA'),(654,'Bergamo','CET','ITA'),(655,'Pescara','CET','ITA'),(656,'Latina','CET','ITA'),(657,'Vicenza','CET','ITA'),(658,'Terni','CET','ITA'),(659,'Forlì','CET','ITA'),(660,'Trento','CET','ITA'),(661,'Novara','CET','ITA'),(662,'Piacenza','CET','ITA'),(663,'Ancona','CET','ITA'),(664,'Lecce','CET','ITA'),(665,'Bolzano','CET','ITA'),(666,'Catanzaro','CET','ITA'),(667,'La Spezia','CET','ITA'),(668,'Udine','CET','ITA'),(669,'Torre del Greco','CET','ITA'),(670,'Andria','CET','ITA'),(671,'Brindisi','CET','ITA'),(672,'Giugliano in Campania','CET','ITA'),(673,'Pisa','CET','ITA'),(674,'Barletta','CET','ITA'),(675,'Arezzo','CET','ITA'),(676,'Alessandria','CET','ITA'),(677,'Cesena','CET','ITA'),(678,'Pesaro','CET','ITA'),(679,'Schaan','CET','LIE'),(680,'Vaduz','CET','LIE'),(681,'Vilnius','EET','LTU'),(682,'Kaunas','EET','LTU'),(683,'Klaipeda','EET','LTU'),(684,'Šiauliai','EET','LTU'),(685,'Panevezys','EET','LTU'),(686,'Luxembourg','CET','LUX'),(687,'Riga','EET','LVA'),(688,'Daugavpils','EET','LVA'),(689,'Liepaja','EET','LVA'),(690,'Monte-Carlo','CET','MCO'),(691,'Monaco-Ville','CET','MCO'),(692,'Chisinau','EET','MDA'),(693,'Tiraspol','EET','MDA'),(694,'Balti','EET','MDA'),(695,'Bender (Tîghina)','EET','MDA'),(696,'Skopje','CET','MKD'),(697,'Birkirkara','CET','MLT'),(698,'Valletta','CET','MLT'),(699,'Amsterdam','CET','NLD'),(700,'Rotterdam','CET','NLD'),(701,'Haag','CET','NLD'),(702,'Utrecht','CET','NLD'),(703,'Eindhoven','CET','NLD'),(704,'Tilburg','CET','NLD'),(705,'Groningen','CET','NLD'),(706,'Breda','CET','NLD'),(707,'Apeldoorn','CET','NLD'),(708,'Nijmegen','CET','NLD'),(709,'Enschede','CET','NLD'),(710,'Haarlem','CET','NLD'),(711,'Almere','CET','NLD'),(712,'Arnhem','CET','NLD'),(713,'Zaanstad','CET','NLD'),(714,'Den Bosch','CET','NLD'),(715,'Amersfoort','CET','NLD'),(716,'Maastricht','CET','NLD'),(717,'Dordrecht','CET','NLD'),(718,'Leiden','CET','NLD'),(719,'Haarlemmermeer','CET','NLD'),(720,'Zoetermeer','CET','NLD'),(721,'Emmen','CET','NLD'),(722,'Zwolle','CET','NLD'),(723,'Ede','CET','NLD'),(724,'Delft','CET','NLD'),(725,'Heerlen','CET','NLD'),(726,'Alkmaar','CET','NLD'),(727,'Oslo','CET','NOR'),(728,'Bergen','CET','NOR'),(729,'Trondheim','CET','NOR'),(730,'Stavanger','CET','NOR'),(731,'Bærum','CET','NOR'),(732,'Warszawa','CET','POL'),(733,'Lódz','CET','POL'),(734,'Kraków','CET','POL'),(735,'Wroclaw','CET','POL'),(736,'Poznan','CET','POL'),(737,'Gdansk','CET','POL'),(738,'Szczecin','CET','POL'),(739,'Bydgoszcz','CET','POL'),(740,'Lublin','CET','POL'),(741,'Katowice','CET','POL'),(742,'Bialystok','CET','POL'),(743,'Czestochowa','CET','POL'),(744,'Gdynia','CET','POL'),(745,'Sosnowiec','CET','POL'),(746,'Radom','CET','POL'),(747,'Kielce','CET','POL'),(748,'Gliwice','CET','POL'),(749,'Torun','CET','POL'),(750,'Bytom','CET','POL'),(751,'Zabrze','CET','POL'),(752,'Bielsko-Biala','CET','POL'),(753,'Olsztyn','CET','POL'),(754,'Rzeszów','CET','POL'),(755,'Ruda Slaska','CET','POL'),(756,'Rybnik','CET','POL'),(757,'Walbrzych','CET','POL'),(758,'Tychy','CET','POL'),(759,'Dabrowa Górnicza','CET','POL'),(760,'Plock','CET','POL'),(761,'Elblag','CET','POL'),(762,'Opole','CET','POL'),(763,'Gorzów Wielkopolski','CET','POL'),(764,'Wloclawek','CET','POL'),(765,'Chorzów','CET','POL'),(766,'Tarnów','CET','POL'),(767,'Zielona Góra','CET','POL'),(768,'Koszalin','CET','POL'),(769,'Legnica','CET','POL'),(770,'Kalisz','CET','POL'),(771,'Grudziadz','CET','POL'),(772,'Slupsk','CET','POL'),(773,'Jastrzebie-Zdrój','CET','POL'),(774,'Jaworzno','CET','POL'),(775,'Jelenia Góra','CET','POL'),(776,'Lisboa','WET','PRT'),(777,'Porto','WET','PRT'),(778,'Amadora','WET','PRT'),(779,'Coímbra','WET','PRT'),(780,'Braga','WET','PRT'),(781,'Bucuresti','EET','ROM'),(782,'Iasi','EET','ROM'),(783,'Constanta','EET','ROM'),(784,'Cluj-Napoca','EET','ROM'),(785,'Galati','EET','ROM'),(786,'Timisoara','EET','ROM'),(787,'Brasov','EET','ROM'),(788,'Craiova','EET','ROM'),(789,'Ploiesti','EET','ROM'),(790,'Braila','EET','ROM'),(791,'Oradea','EET','ROM'),(792,'Bacau','EET','ROM'),(793,'Pitesti','EET','ROM'),(794,'Arad','EET','ROM'),(795,'Sibiu','EET','ROM'),(796,'Târgu Mures','EET','ROM'),(797,'Baia Mare','EET','ROM'),(798,'Buzau','EET','ROM'),(799,'Satu Mare','EET','ROM'),(800,'Botosani','EET','ROM'),(801,'Piatra Neamt','EET','ROM'),(802,'Râmnicu Vâlcea','EET','ROM'),(803,'Suceava','EET','ROM'),(804,'Drobeta-Turnu Severin','EET','ROM'),(805,'Târgoviste','EET','ROM'),(806,'Focsani','EET','ROM'),(807,'Târgu Jiu','EET','ROM'),(808,'Tulcea','EET','ROM'),(809,'Resita','EET','ROM'),(810,'Moscow','FET','RUS'),(811,'St Petersburg','FET','RUS'),(812,'Novosibirsk','FET','RUS'),(813,'Nizni Novgorod','FET','RUS'),(814,'Jekaterinburg','FET','RUS'),(815,'Samara','FET','RUS'),(816,'Omsk','FET','RUS'),(817,'Kazan','FET','RUS'),(818,'Ufa','FET','RUS'),(819,'Tšeljabinsk','FET','RUS'),(820,'Rostov-na-Donu','FET','RUS'),(821,'Perm','FET','RUS'),(822,'Volgograd','FET','RUS'),(823,'Voronez','FET','RUS'),(824,'Krasnojarsk','FET','RUS'),(825,'Saratov','FET','RUS'),(826,'Toljatti','FET','RUS'),(827,'Uljanovsk','FET','RUS'),(828,'Izevsk','FET','RUS'),(829,'Krasnodar','FET','RUS'),(830,'Jaroslavl','FET','RUS'),(831,'Habarovsk','FET','RUS'),(832,'Vladivostok','FET','RUS'),(833,'Irkutsk','FET','RUS'),(834,'Barnaul','FET','RUS'),(835,'Novokuznetsk','FET','RUS'),(836,'Penza','FET','RUS'),(837,'Rjazan','FET','RUS'),(838,'Orenburg','FET','RUS'),(839,'Lipetsk','FET','RUS'),(840,'Nabereznyje Tšelny','FET','RUS'),(841,'Tula','FET','RUS'),(842,'Tjumen','FET','RUS'),(843,'Kemerovo','FET','RUS'),(844,'Astrahan','FET','RUS'),(845,'Tomsk','FET','RUS'),(846,'Kirov','FET','RUS'),(847,'Ivanovo','FET','RUS'),(848,'Tšeboksary','FET','RUS'),(849,'Brjansk','FET','RUS'),(850,'Tver','FET','RUS'),(851,'Kursk','FET','RUS'),(852,'Magnitogorsk','FET','RUS'),(853,'Kaliningrad','FET','RUS'),(854,'Nizni Tagil','FET','RUS'),(855,'Murmansk','FET','RUS'),(856,'Ulan-Ude','FET','RUS'),(857,'Kurgan','FET','RUS'),(858,'Arkangeli','FET','RUS'),(859,'Sotši','FET','RUS'),(860,'Smolensk','FET','RUS'),(861,'Orjol','FET','RUS'),(862,'Stavropol','FET','RUS'),(863,'Belgorod','FET','RUS'),(864,'Kaluga','FET','RUS'),(865,'Vladimir','FET','RUS'),(866,'Mahatškala','FET','RUS'),(867,'Tšerepovets','FET','RUS'),(868,'Saransk','FET','RUS'),(869,'Tambov','FET','RUS'),(870,'Vladikavkaz','FET','RUS'),(871,'Tšita','FET','RUS'),(872,'Vologda','FET','RUS'),(873,'Veliki Novgorod','FET','RUS'),(874,'Komsomolsk-na-Amure','FET','RUS'),(875,'Kostroma','FET','RUS'),(876,'Volzski','FET','RUS'),(877,'Taganrog','FET','RUS'),(878,'Petroskoi','FET','RUS'),(879,'Bratsk','FET','RUS'),(880,'Dzerzinsk','FET','RUS'),(881,'Surgut','FET','RUS'),(882,'Orsk','FET','RUS'),(883,'Sterlitamak','FET','RUS'),(884,'Angarsk','FET','RUS'),(885,'Joškar-Ola','FET','RUS'),(886,'Rybinsk','FET','RUS'),(887,'Prokopjevsk','FET','RUS'),(888,'Niznevartovsk','FET','RUS'),(889,'Naltšik','FET','RUS'),(890,'Syktyvkar','FET','RUS'),(891,'Severodvinsk','FET','RUS'),(892,'Bijsk','FET','RUS'),(893,'Niznekamsk','FET','RUS'),(894,'Blagoveštšensk','FET','RUS'),(895,'Šahty','FET','RUS'),(896,'Staryi Oskol','FET','RUS'),(897,'Zelenograd','FET','RUS'),(898,'Balakovo','FET','RUS'),(899,'Novorossijsk','FET','RUS'),(900,'Pihkova','FET','RUS'),(901,'Zlatoust','FET','RUS'),(902,'Jakutsk','FET','RUS'),(903,'Podolsk','FET','RUS'),(904,'Petropavlovsk-Kamtšatski','FET','RUS'),(905,'Kamensk-Uralski','FET','RUS'),(906,'Engels','FET','RUS'),(907,'Syzran','FET','RUS'),(908,'Grozny','FET','RUS'),(909,'Novotšerkassk','FET','RUS'),(910,'Berezniki','FET','RUS'),(911,'Juzno-Sahalinsk','FET','RUS'),(912,'Volgodonsk','FET','RUS'),(913,'Abakan','FET','RUS'),(914,'Maikop','FET','RUS'),(915,'Miass','FET','RUS'),(916,'Armavir','FET','RUS'),(917,'Ljubertsy','FET','RUS'),(918,'Rubtsovsk','FET','RUS'),(919,'Kovrov','FET','RUS'),(920,'Nahodka','FET','RUS'),(921,'Ussurijsk','FET','RUS'),(922,'Salavat','FET','RUS'),(923,'Mytištši','FET','RUS'),(924,'Kolomna','FET','RUS'),(925,'Elektrostal','FET','RUS'),(926,'Murom','FET','RUS'),(927,'Kolpino','FET','RUS'),(928,'Norilsk','FET','RUS'),(929,'Almetjevsk','FET','RUS'),(930,'Novomoskovsk','FET','RUS'),(931,'Dimitrovgrad','FET','RUS'),(932,'Pervouralsk','FET','RUS'),(933,'Himki','FET','RUS'),(934,'Balašiha','FET','RUS'),(935,'Nevinnomyssk','FET','RUS'),(936,'Pjatigorsk','FET','RUS'),(937,'Korolev','FET','RUS'),(938,'Serpuhov','FET','RUS'),(939,'Odintsovo','FET','RUS'),(940,'Orehovo-Zujevo','FET','RUS'),(941,'Kamyšin','FET','RUS'),(942,'Novotšeboksarsk','FET','RUS'),(943,'Tšerkessk','FET','RUS'),(944,'Atšinsk','FET','RUS'),(945,'Magadan','FET','RUS'),(946,'Mitšurinsk','FET','RUS'),(947,'Kislovodsk','FET','RUS'),(948,'Jelets','FET','RUS'),(949,'Seversk','FET','RUS'),(950,'Noginsk','FET','RUS'),(951,'Velikije Luki','FET','RUS'),(952,'Novokuibyševsk','FET','RUS'),(953,'Neftekamsk','FET','RUS'),(954,'Leninsk-Kuznetski','FET','RUS'),(955,'Oktjabrski','FET','RUS'),(956,'Sergijev Posad','FET','RUS'),(957,'Arzamas','FET','RUS'),(958,'Kiseljovsk','FET','RUS'),(959,'Novotroitsk','FET','RUS'),(960,'Obninsk','FET','RUS'),(961,'Kansk','FET','RUS'),(962,'Glazov','FET','RUS'),(963,'Solikamsk','FET','RUS'),(964,'Sarapul','FET','RUS'),(965,'Ust-Ilimsk','FET','RUS'),(966,'Štšolkovo','FET','RUS'),(967,'Mezduretšensk','FET','RUS'),(968,'Usolje-Sibirskoje','FET','RUS'),(969,'Elista','FET','RUS'),(970,'Novošahtinsk','FET','RUS'),(971,'Votkinsk','FET','RUS'),(972,'Kyzyl','FET','RUS'),(973,'Serov','FET','RUS'),(974,'Zelenodolsk','FET','RUS'),(975,'Zeleznodoroznyi','FET','RUS'),(976,'Kinešma','FET','RUS'),(977,'Kuznetsk','FET','RUS'),(978,'Uhta','FET','RUS'),(979,'Jessentuki','FET','RUS'),(980,'Tobolsk','FET','RUS'),(981,'Neftejugansk','FET','RUS'),(982,'Bataisk','FET','RUS'),(983,'Nojabrsk','FET','RUS'),(984,'Balašov','FET','RUS'),(985,'Zeleznogorsk','FET','RUS'),(986,'Zukovski','FET','RUS'),(987,'Anzero-Sudzensk','FET','RUS'),(988,'Bugulma','FET','RUS'),(989,'Zeleznogorsk','FET','RUS'),(990,'Novouralsk','FET','RUS'),(991,'Puškin','FET','RUS'),(992,'Vorkuta','FET','RUS'),(993,'Derbent','FET','RUS'),(994,'Kirovo-Tšepetsk','FET','RUS'),(995,'Krasnogorsk','FET','RUS'),(996,'Klin','FET','RUS'),(997,'Tšaikovski','FET','RUS'),(998,'Novyi Urengoi','FET','RUS'),(1000,'Serravalle','CET','SMR'),(1001,'San Marino','CET','SMR'),(1002,'Bratislava','CET','SVK'),(1003,'Košice','CET','SVK'),(1004,'Prešov','CET','SVK'),(1005,'Ljubljana','EET','SVN'),(1006,'Maribor','EET','SVN'),(1007,'Stockholm','CET','SWE'),(1008,'Gothenburg [Göteborg]','CET','SWE'),(1009,'Malmö','CET','SWE'),(1010,'Uppsala','CET','SWE'),(1011,'Linköping','CET','SWE'),(1012,'Västerås','CET','SWE'),(1013,'Örebro','CET','SWE'),(1014,'Norrköping','CET','SWE'),(1015,'Helsingborg','CET','SWE'),(1016,'Jönköping','CET','SWE'),(1017,'Umeå','CET','SWE'),(1018,'Lund','CET','SWE'),(1019,'Borås','CET','SWE'),(1020,'Sundsvall','CET','SWE'),(1021,'Gävle','CET','SWE'),(1022,'Kyiv','EET','UKR'),(1023,'Harkova [Harkiv]','EET','UKR'),(1024,'Dnipropetrovsk','EET','UKR'),(1025,'Donetsk','EET','UKR'),(1026,'Odesa','EET','UKR'),(1027,'Zaporizzja','EET','UKR'),(1028,'Lviv','EET','UKR'),(1029,'Kryvyi Rig','EET','UKR'),(1030,'Mykolajiv','EET','UKR'),(1031,'Mariupol','EET','UKR'),(1032,'Lugansk','EET','UKR'),(1033,'Vinnytsja','EET','UKR'),(1034,'Makijivka','EET','UKR'),(1035,'Herson','EET','UKR'),(1036,'Sevastopol','EET','UKR'),(1037,'Simferopol','EET','UKR'),(1038,'Pultava [Poltava]','EET','UKR'),(1039,'Tšernigiv','EET','UKR'),(1040,'Tšerkasy','EET','UKR'),(1041,'Gorlivka','EET','UKR'),(1042,'Zytomyr','EET','UKR'),(1043,'Sumy','EET','UKR'),(1044,'Dniprodzerzynsk','EET','UKR'),(1045,'Kirovograd','EET','UKR'),(1046,'Hmelnytskyi','EET','UKR'),(1047,'Tšernivtsi','EET','UKR'),(1048,'Rivne','EET','UKR'),(1049,'Krementšuk','EET','UKR'),(1050,'Ivano-Frankivsk','EET','UKR'),(1051,'Ternopil','EET','UKR'),(1052,'Lutsk','EET','UKR'),(1053,'Bila Tserkva','EET','UKR'),(1054,'Kramatorsk','EET','UKR'),(1055,'Melitopol','EET','UKR'),(1056,'Kertš','EET','UKR'),(1057,'Nikopol','EET','UKR'),(1058,'Berdjansk','EET','UKR'),(1059,'Pavlograd','EET','UKR'),(1060,'Sjeverodonetsk','EET','UKR'),(1061,'Slovjansk','EET','UKR'),(1062,'Uzgorod','EET','UKR'),(1063,'Altševsk','EET','UKR'),(1064,'Lysytšansk','EET','UKR'),(1065,'Jevpatorija','EET','UKR'),(1066,'Kamjanets-Podilskyi','EET','UKR'),(1067,'Jenakijeve','EET','UKR'),(1068,'Krasnyi Lutš','EET','UKR'),(1069,'Stahanov','EET','UKR'),(1070,'Oleksandrija','EET','UKR'),(1071,'Konotop','EET','UKR'),(1072,'Kostjantynivka','EET','UKR'),(1073,'Berdytšiv','EET','UKR'),(1074,'Izmajil','EET','UKR'),(1075,'Šostka','EET','UKR'),(1076,'Uman','EET','UKR'),(1077,'Brovary','EET','UKR'),(1078,'Mukatševe','EET','UKR'),(1079,'Città del Vaticano','CET','VAT'),(1088,'Matera','CET','ITA'),(1100,'Rovigo','CET','ITA'),(1101,'Prova1','CET','ALB');
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `companies`
--

DROP TABLE IF EXISTS `companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `companies` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `disabled_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companies`
--

LOCK TABLES `companies` WRITE;
/*!40000 ALTER TABLE `companies` DISABLE KEYS */;
INSERT INTO `companies` VALUES (2,'American Airlines',NULL),(3,'Qatar Airways',NULL),(4,'Ryanair',NULL),(5,'British Airways',NULL),(6,'EasyJet ',NULL),(7,'Air France',NULL),(8,'Delta Air Lines',NULL),(11,'WizzAir','2023-08-02 14:52:23'),(12,'Pippo Airlines','2023-08-02 14:52:23'),(39,'MockitoYay',NULL),(40,'MockitoYAuth','2023-10-12 15:17:37');
/*!40000 ALTER TABLE `companies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `countries` (
  `id` varchar(3) NOT NULL,
  `name` varchar(100) NOT NULL,
  `local_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `local_name_UNIQUE` (`local_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES ('ALB','Albania','Shqipëria'),('AND','Andorra','Andorra'),('AUT','Austria','Österreich'),('BEL','Belgium','België/Belgique'),('BGR','Bulgaria','Balgarija'),('BIH','Bosnia and Herzegovina','Bosna i Hercegovina'),('BLR','Belarus','Belarus'),('CHE','Switzerland','Schweiz/Suisse/Svizzera/Svizra'),('CZE','Czech Republic','¸esko'),('DEU','Germany','Deutschland'),('DNK','Denmark','Danmark'),('ESP','Spain','España'),('EST','Estonia','Eesti'),('FIN','Finland','Suomi'),('FRA','France','France'),('FRO','Faroe Islands','Føroyar'),('GBR','United Kingdom','United Kingdom'),('GIB','Gibraltar','Gibraltar'),('GRC','Greece','Elláda'),('HRV','Croatia','Hrvatska'),('HUN','Hungary','Magyarország'),('IRL','Ireland','Ireland/Éire'),('ISL','Iceland','Ísland'),('ITA','Italy','Italia'),('LIE','Liechtenstein','Liechtenstein'),('LTU','Lithuania','Lietuva'),('LUX','Luxembourg','Luxembourg/Lëtzebuerg'),('LVA','Latvia','Latvija'),('MCO','Monaco','Monaco'),('MDA','Moldova','Moldova'),('MKD','Macedonia','Makedonija'),('MLT','Malta','Malta'),('NLD','Netherlands','Nederland'),('NOR','Norway','Norge'),('POL','Poland','Polska'),('PRT','Portugal','Portugal'),('ROM','Romania','România'),('RUS','Russian Federation','Rossija'),('SMR','San Marino','San Marino'),('SVK','Slovakia','Slovensko'),('SVN','Slovenia','Slovenija'),('SWE','Sweden','Sverige'),('UKR','Ukraine','Ukrajina'),('VAT','Holy See (Vatican City State)','Santa Sede/Città del Vaticano');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `days_of_week`
--

DROP TABLE IF EXISTS `days_of_week`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `days_of_week` (
  `id` int NOT NULL AUTO_INCREMENT,
  `schedule_id` int NOT NULL,
  `day` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `schedule_id_day_of_week` (`schedule_id`,`day`),
  KEY `schedule_id_fk_2_idx` (`schedule_id`),
  CONSTRAINT `schedule_id_fk_2` FOREIGN KEY (`schedule_id`) REFERENCES `schedules` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=298 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `days_of_week`
--

LOCK TABLES `days_of_week` WRITE;
/*!40000 ALTER TABLE `days_of_week` DISABLE KEYS */;
INSERT INTO `days_of_week` VALUES (108,2,'1'),(113,2,'2'),(114,2,'3'),(115,2,'4'),(116,2,'5'),(91,3,'1'),(92,3,'2'),(93,3,'3'),(94,3,'4'),(95,3,'5'),(9,4,'1'),(10,4,'2'),(24,4,'3'),(11,4,'4'),(117,4,'5'),(21,5,'0'),(15,5,'3'),(12,5,'4'),(13,5,'5'),(81,6,'3'),(82,7,'1'),(83,7,'3'),(84,7,'5'),(103,8,'0'),(102,8,'1'),(55,8,'3'),(45,8,'4'),(46,8,'5'),(47,8,'6'),(123,10,'1'),(56,10,'3'),(172,11,'3'),(173,11,'4'),(174,11,'6'),(96,14,'1'),(97,15,'1'),(98,16,'1'),(99,16,'3'),(100,17,'0'),(105,21,'1'),(106,23,'1'),(279,23,'3'),(107,25,'1'),(109,25,'2'),(110,25,'3'),(111,25,'4'),(112,25,'5'),(122,26,'1'),(134,27,'0'),(131,27,'2'),(132,27,'5'),(133,27,'6'),(135,28,'1'),(136,28,'4'),(137,28,'5'),(138,29,'1'),(139,29,'3'),(140,29,'4'),(141,30,'2'),(142,30,'4'),(143,30,'6'),(147,31,'0'),(144,31,'1'),(145,31,'4'),(146,31,'6'),(151,32,'0'),(148,32,'1'),(149,32,'4'),(150,32,'6'),(155,33,'0'),(152,33,'2'),(153,33,'4'),(154,33,'5'),(156,34,'2'),(157,34,'3'),(158,34,'4'),(159,35,'1'),(160,35,'2'),(161,35,'5'),(162,35,'6'),(163,37,'5'),(164,37,'6'),(166,38,'2'),(176,39,'0'),(175,39,'1'),(177,40,'0'),(178,40,'2'),(179,40,'4'),(180,40,'5'),(187,41,'0'),(181,41,'1'),(182,41,'2'),(183,41,'3'),(184,41,'4'),(185,41,'5'),(186,41,'6'),(194,42,'0'),(188,42,'1'),(189,42,'2'),(190,42,'3'),(191,42,'4'),(192,42,'5'),(193,42,'6'),(196,43,'0'),(195,43,'1'),(289,43,'2'),(290,43,'3'),(203,44,'0'),(197,44,'1'),(198,44,'2'),(199,44,'3'),(200,44,'4'),(201,44,'5'),(202,44,'6'),(215,45,'0'),(209,45,'1'),(210,45,'2'),(211,45,'3'),(212,45,'4'),(213,45,'5'),(214,45,'6'),(225,46,'0'),(219,46,'1'),(220,46,'2'),(221,46,'3'),(222,46,'4'),(223,46,'5'),(224,46,'6'),(232,47,'0'),(226,47,'1'),(227,47,'2'),(228,47,'3'),(229,47,'4'),(230,47,'5'),(231,47,'6'),(239,48,'0'),(233,48,'1'),(234,48,'2'),(235,48,'3'),(236,48,'4'),(237,48,'5'),(238,48,'6'),(246,49,'0'),(240,49,'1'),(241,49,'2'),(242,49,'3'),(243,49,'4'),(244,49,'5'),(245,49,'6'),(255,50,'0'),(249,50,'1'),(250,50,'2'),(251,50,'3'),(252,50,'4'),(253,50,'5'),(254,50,'6'),(262,51,'0'),(256,51,'1'),(257,51,'2'),(258,51,'3'),(259,51,'4'),(260,51,'5'),(261,51,'6'),(269,52,'0'),(263,52,'1'),(264,52,'2'),(265,52,'3'),(266,52,'4'),(267,52,'5'),(268,52,'6'),(280,53,'4'),(281,53,'5'),(282,54,'4'),(283,54,'5'),(284,55,'4'),(285,55,'5'),(286,56,'4'),(287,56,'5'),(292,57,'3'),(293,57,'4'),(294,57,'5'),(295,61,'1'),(296,61,'3'),(297,62,'0');
/*!40000 ALTER TABLE `days_of_week` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passengers`
--

DROP TABLE IF EXISTS `passengers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passengers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `purchase_id` int DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `surname` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_purchase_fk_idx` (`purchase_id`),
  CONSTRAINT `id_purchase_fk` FOREIGN KEY (`purchase_id`) REFERENCES `purchases` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passengers`
--

LOCK TABLES `passengers` WRITE;
/*!40000 ALTER TABLE `passengers` DISABLE KEYS */;
INSERT INTO `passengers` VALUES (54,48,'ncvnx','Cognome'),(55,49,'Matera','cognome'),(56,50,'a','b'),(57,51,'nome','cognome'),(58,52,'Matera','Cognome'),(59,53,'Jane','Austen'),(60,54,'a','b'),(61,55,'aa','aa'),(62,56,'a','a'),(63,57,'Paperino','Disney'),(64,58,'a','a'),(65,59,'Jane','Austen'),(66,60,'Jane','Provaprova'),(67,61,'a','a'),(68,62,'Jane','Austen'),(69,63,'Pippi','Calzelunghe'),(70,63,'Ciccio','Pasticcio'),(71,64,'Anna','Rossi'),(72,64,'Mario','Esposito'),(73,65,'Jane','Austen'),(74,66,'asdfa','asdf'),(75,66,'asdf','asdf'),(78,68,'a','a'),(79,69,'AAA','SSS'),(80,70,'Lara','Bianchini'),(81,71,'dd','ddd'),(82,72,'a','q'),(83,73,'minnie','topolino'),(84,74,'aa','aa'),(85,75,'Matera','Cognome'),(86,76,'a','a'),(87,77,'minnie','topolino'),(88,78,'Keinny','Ulloa'),(89,79,'a','a'),(90,80,'minnie','topolino'),(91,81,'aa','aa'),(92,82,'Giovanni','Neri'),(93,83,'Marianna','Pupo'),(94,83,'Marina','Pupo'),(99,90,'Pippo','Pippo'),(100,90,'Pupo','Pupo'),(101,91,'Pippo','Pippo'),(102,91,'Pupo','Pupo'),(103,92,'aa','aa'),(104,92,'aa','aa'),(105,93,'aa','aa'),(106,93,'aa','aa'),(107,94,'aa','aa'),(108,94,'aa','aa'),(109,95,'aa','aa'),(110,95,'aa','aa'),(113,97,'AA','AA'),(114,97,'AA','AA'),(115,98,'aa','aa'),(116,98,'aa','aa');
/*!40000 ALTER TABLE `passengers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchases`
--

DROP TABLE IF EXISTS `purchases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchases` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `n_tickets` int NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_fk_3_idx` (`user_id`),
  CONSTRAINT `user_id_fk_3` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchases`
--

LOCK TABLES `purchases` WRITE;
/*!40000 ALTER TABLE `purchases` DISABLE KEYS */;
INSERT INTO `purchases` VALUES (48,13,1,120.22),(49,13,1,30.00),(50,13,2,280.44),(51,1,1,120.22),(52,1,1,120.22),(53,13,2,250.44),(54,13,1,140.22),(55,13,2,280.44),(56,13,2,280.44),(57,13,2,66.05),(58,13,2,280.44),(59,13,2,250.44),(60,13,1,140.22),(61,13,2,250.44),(62,13,2,250.44),(63,13,1,40.00),(64,55,1,41.60),(65,13,2,130.22),(66,13,1,40.00),(67,57,1,110.22),(68,13,2,280.44),(69,13,2,130.22),(70,13,2,160.22),(71,13,2,280.44),(72,55,2,280.44),(73,13,1,18.50),(74,13,2,130.22),(75,61,1,30.00),(76,13,2,280.44),(77,13,2,280.44),(78,13,1,140.22),(79,13,2,250.44),(80,13,2,280.44),(81,13,1,140.22),(82,46,1,18.50),(83,13,2,100.00),(90,13,2,418.44),(91,13,2,418.44),(92,13,2,418.44),(93,13,2,418.44),(94,13,2,418.44),(95,13,2,178.00),(97,59,2,260.44),(98,13,4,178.00);
/*!40000 ALTER TABLE `purchases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refresh_token`
--

DROP TABLE IF EXISTS `refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refresh_token` (
  `id` int NOT NULL AUTO_INCREMENT,
  `token` varchar(150) NOT NULL,
  `expiration_date` timestamp NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token_UNIQUE` (`token`),
  KEY `fk_refresh_token_user_idx` (`user_id`),
  CONSTRAINT `fk_refresh_token_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refresh_token`
--

LOCK TABLES `refresh_token` WRITE;
/*!40000 ALTER TABLE `refresh_token` DISABLE KEYS */;
INSERT INTO `refresh_token` VALUES (15,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBhZG1pbiIsImV4cCI6MTY5NzcwNDk4OX0.TEBlr3_Zz_yn4__b8fsLgrS3y6bdZg-_mdPLSDkuB3k','2023-10-19 10:43:09',13),(16,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwaXBwbzJAZ21haWwuY29tIiwiZXhwIjoxNjk3NzI0MDEyfQ.xGsNpzEzskM-HXmPMm9QoNHsUlitwASHFfg2B5rSgLo','2023-10-19 16:00:13',59);
/*!40000 ALTER TABLE `refresh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `routes`
--

DROP TABLE IF EXISTS `routes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `routes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `departure_xport_id` int NOT NULL,
  `arrival_xport_id` int NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `rout_details` (`departure_xport_id`,`arrival_xport_id`,`type`) /*!80000 INVISIBLE */,
  KEY `departure_stop_id_fk_idx` (`departure_xport_id`),
  KEY `arrival_stop_id_fk_idx` (`arrival_xport_id`),
  CONSTRAINT `arrival_stop_id_fk` FOREIGN KEY (`arrival_xport_id`) REFERENCES `xports` (`id`),
  CONSTRAINT `departure_stop_id_fk` FOREIGN KEY (`departure_xport_id`) REFERENCES `xports` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=398 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `routes`
--

LOCK TABLES `routes` WRITE;
/*!40000 ALTER TABLE `routes` DISABLE KEYS */;
INSERT INTO `routes` VALUES (1,7,15,'plane'),(2,15,7,'plane'),(3,6,22,'plane'),(6,7,32,'plane'),(7,32,7,'plane'),(8,22,6,'plane'),(9,1,20,'plane'),(10,20,1,'plane'),(11,8,58,'plane'),(12,58,8,'plane'),(13,1,58,'plane'),(14,58,1,'plane'),(15,8,62,'plane'),(16,62,8,'plane'),(17,2,10,'plane'),(18,10,2,'plane'),(19,2,21,'plane'),(20,21,2,'plane'),(21,4,46,'plane'),(22,46,4,'plane'),(23,9,89,'plane'),(24,89,9,'plane'),(25,4,76,'plane'),(26,76,4,'plane'),(27,5,20,'plane'),(28,20,5,'plane'),(29,14,7,'plane'),(30,9,37,'plane'),(31,37,9,'plane'),(32,7,14,'plane'),(33,20,31,'plane'),(34,31,20,'plane'),(35,10,19,'plane'),(36,19,10,'plane'),(37,11,22,'plane'),(38,22,11,'plane'),(39,21,61,'plane'),(40,61,21,'plane'),(41,2,79,'plane'),(42,26,23,'plane'),(43,23,26,'plane'),(44,79,2,'plane'),(46,22,39,'plane'),(47,39,22,'plane'),(48,26,24,'plane'),(49,24,26,'plane'),(50,77,84,'plane'),(51,109,10,'plane'),(52,10,109,'plane'),(53,84,77,'plane'),(54,27,39,'plane'),(55,39,27,'plane'),(56,77,88,'plane'),(57,88,77,'plane'),(58,27,55,'plane'),(59,55,27,'plane'),(60,23,45,'plane'),(61,45,23,'plane'),(62,109,114,'plane'),(63,114,109,'plane'),(64,28,68,'plane'),(65,68,28,'plane'),(66,109,107,'plane'),(67,23,102,'plane'),(68,102,23,'plane'),(69,28,70,'plane'),(70,70,28,'plane'),(71,107,109,'plane'),(73,24,80,'plane'),(74,80,24,'plane'),(76,29,80,'plane'),(77,80,29,'plane'),(78,24,73,'plane'),(79,73,24,'plane'),(80,29,81,'plane'),(81,81,29,'plane'),(82,60,64,'plane'),(83,64,60,'plane'),(85,60,72,'plane'),(87,31,2,'plane'),(88,2,31,'plane'),(89,72,60,'plane'),(90,35,40,'plane'),(91,40,32,'plane'),(92,31,4,'plane'),(93,4,31,'plane'),(94,18,111,'plane'),(95,113,111,'plane'),(96,113,109,'plane'),(97,35,62,'plane'),(98,62,35,'plane'),(99,111,18,'plane'),(101,63,1,'plane'),(102,1,63,'plane'),(105,63,20,'plane'),(106,20,63,'plane'),(107,58,62,'plane'),(108,62,58,'plane'),(109,111,113,'plane'),(110,109,111,'plane'),(114,64,15,'plane'),(115,15,64,'plane'),(116,57,14,'plane'),(117,14,57,'plane'),(118,65,46,'plane'),(119,46,65,'plane'),(120,34,58,'plane'),(121,58,34,'plane'),(122,65,82,'plane'),(123,82,65,'plane'),(124,57,26,'plane'),(125,26,57,'plane'),(127,34,36,'plane'),(128,36,34,'plane'),(129,72,37,'plane'),(130,37,72,'plane'),(133,72,19,'plane'),(134,19,72,'plane'),(135,73,12,'plane'),(136,12,73,'plane'),(138,73,56,'plane'),(139,56,73,'plane'),(140,88,112,'plane'),(141,112,88,'plane'),(144,33,54,'plane'),(145,15,2,'plane'),(146,74,91,'plane'),(147,54,33,'plane'),(148,91,74,'plane'),(149,2,15,'plane'),(150,33,80,'plane'),(152,80,33,'plane'),(153,74,102,'plane'),(154,102,74,'plane'),(157,56,90,'plane'),(158,90,56,'plane'),(159,76,110,'plane'),(160,110,76,'plane'),(161,88,105,'plane'),(162,105,88,'plane'),(163,76,9,'plane'),(164,9,76,'plane'),(165,16,114,'plane'),(166,114,16,'plane'),(167,80,5,'plane'),(168,5,80,'plane'),(169,102,103,'plane'),(170,103,102,'plane'),(171,102,108,'plane'),(172,108,102,'plane'),(173,18,21,'plane'),(174,21,18,'plane'),(179,19,43,'plane'),(180,43,19,'plane'),(181,37,81,'plane'),(182,41,25,'plane'),(183,25,41,'plane'),(184,41,82,'plane'),(185,82,41,'plane'),(186,81,37,'plane'),(187,80,69,'plane'),(188,69,80,'plane'),(189,38,46,'plane'),(190,46,38,'plane'),(191,86,24,'plane'),(192,24,86,'plane'),(193,39,58,'plane'),(194,58,39,'plane'),(195,86,44,'plane'),(196,44,86,'plane'),(197,43,2,'plane'),(198,2,43,'plane'),(199,61,56,'plane'),(200,40,4,'plane'),(201,43,101,'plane'),(202,101,43,'plane'),(203,4,40,'plane'),(204,56,61,'plane'),(205,98,99,'plane'),(206,99,98,'plane'),(207,61,65,'plane'),(208,44,63,'plane'),(209,63,44,'plane'),(210,65,61,'plane'),(211,42,8,'plane'),(212,97,47,'plane'),(213,47,97,'plane'),(214,8,42,'plane'),(215,45,34,'plane'),(216,34,45,'plane'),(217,98,57,'plane'),(218,57,98,'plane'),(219,97,60,'plane'),(220,60,97,'plane'),(221,44,70,'plane'),(222,70,44,'plane'),(223,45,19,'plane'),(224,19,45,'plane'),(225,48,20,'plane'),(226,20,48,'plane'),(227,13,41,'plane'),(228,41,13,'plane'),(229,46,55,'plane'),(230,55,46,'plane'),(231,103,110,'plane'),(232,104,102,'plane'),(233,1,23,'plane'),(234,23,1,'plane'),(235,82,57,'plane'),(236,57,82,'plane'),(237,46,62,'plane'),(238,62,46,'plane'),(239,51,31,'plane'),(240,2,67,'plane'),(241,67,2,'plane'),(242,47,13,'plane'),(243,13,47,'plane'),(244,21,34,'plane'),(245,34,21,'plane'),(246,31,51,'plane'),(247,3,104,'plane'),(248,104,3,'plane'),(249,104,113,'plane'),(250,4,93,'plane'),(251,93,4,'plane'),(253,5,33,'plane'),(254,33,5,'plane'),(255,47,1,'plane'),(256,1,47,'plane'),(257,54,3,'plane'),(258,3,54,'plane'),(259,47,61,'plane'),(260,61,47,'plane'),(261,113,104,'plane'),(262,49,100,'plane'),(263,100,49,'plane'),(264,49,36,'plane'),(265,36,49,'plane'),(267,14,66,'plane'),(268,66,14,'plane'),(269,53,14,'plane'),(273,12,22,'plane'),(274,22,12,'plane'),(275,14,53,'plane'),(276,14,17,'plane'),(277,53,77,'plane'),(278,77,53,'plane'),(279,31,41,'plane'),(280,41,31,'plane'),(281,17,14,'plane'),(282,55,23,'plane'),(283,23,55,'plane'),(284,105,110,'plane'),(285,37,90,'plane'),(287,90,37,'plane'),(288,55,62,'plane'),(289,62,55,'plane'),(290,110,105,'plane'),(291,29,47,'plane'),(292,72,91,'plane'),(293,91,72,'plane'),(294,47,29,'plane'),(295,106,102,'plane'),(296,59,30,'plane'),(297,30,59,'plane'),(298,102,106,'plane'),(299,107,113,'plane'),(300,94,56,'plane'),(301,56,94,'plane'),(302,54,10,'plane'),(303,113,107,'plane'),(304,62,102,'plane'),(305,102,62,'plane'),(306,87,30,'plane'),(307,30,87,'plane'),(308,10,54,'plane'),(309,62,25,'plane'),(310,25,62,'plane'),(311,90,12,'plane'),(312,12,90,'plane'),(313,25,43,'plane'),(314,43,25,'plane'),(315,108,100,'plane'),(316,100,108,'plane'),(318,69,8,'plane'),(319,8,69,'plane'),(320,109,98,'plane'),(321,69,81,'plane'),(322,81,69,'plane'),(323,32,100,'plane'),(324,100,32,'plane'),(325,98,110,'plane'),(326,111,89,'plane'),(327,89,111,'plane'),(328,30,62,'plane'),(329,62,30,'plane'),(330,112,56,'plane'),(331,70,114,'plane'),(332,114,70,'plane'),(333,56,111,'plane'),(334,56,112,'plane'),(336,70,11,'plane'),(337,11,70,'plane'),(338,36,80,'plane'),(339,80,36,'plane'),(340,111,56,'plane'),(341,17,39,'plane'),(342,39,17,'plane'),(343,66,84,'plane'),(344,84,66,'plane'),(345,68,5,'plane'),(346,5,68,'plane'),(347,15,54,'plane'),(348,54,15,'plane'),(349,71,18,'plane'),(350,18,71,'plane'),(351,51,3,'plane'),(352,75,93,'plane'),(353,93,75,'plane'),(354,3,51,'plane'),(355,48,5,'plane'),(356,5,48,'plane'),(357,112,2,'plane'),(360,1,11,'plane'),(362,8,1,'plane'),(363,20,14,'plane'),(364,14,2,'plane'),(365,20,2,'plane'),(366,14,20,'plane'),(368,19,6,'plane'),(375,18,10,'plane'),(376,14,1,'plane'),(378,5,1,'plane'),(379,14,15,'plane'),(390,1,13,'plane'),(392,13,8,'plane'),(394,18,1,'plane'),(395,2,14,'plane'),(397,8,86,'plane');
/*!40000 ALTER TABLE `routes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedules`
--

DROP TABLE IF EXISTS `schedules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedules` (
  `id` int NOT NULL AUTO_INCREMENT,
  `route_id` int NOT NULL,
  `departure_time` time NOT NULL,
  `arrival_time` time NOT NULL,
  `duration` bigint NOT NULL,
  `company_id` int DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `seats` int DEFAULT '10',
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `route_departure_arrival` (`route_id`,`departure_time`,`arrival_time`),
  KEY `route_id_fk_idx` (`route_id`),
  KEY `company_id_fk_idx` (`company_id`),
  CONSTRAINT `company_id_fk` FOREIGN KEY (`company_id`) REFERENCES `companies` (`id`),
  CONSTRAINT `route_id_fk` FOREIGN KEY (`route_id`) REFERENCES `routes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedules`
--

LOCK TABLES `schedules` WRITE;
/*!40000 ALTER TABLE `schedules` DISABLE KEYS */;
INSERT INTO `schedules` VALUES (2,1,'13:13:00','15:03:00',6600,3,50.00,100,'2023-07-27',NULL),(3,1,'06:30:00','12:30:00',21600,2,140.22,10,'2023-07-27',NULL),(4,2,'06:30:00','10:33:00',14580,2,140.22,10,'2023-07-27',NULL),(5,2,'08:56:00','10:56:00',7200,4,110.22,20,'2023-07-27',NULL),(6,1,'08:30:00','10:30:00',7200,3,120.22,20,'2023-07-27',NULL),(7,1,'14:50:00','16:50:00',7200,4,20.00,20,'2023-05-15',NULL),(8,6,'22:00:00','06:00:00',1152000,6,18.50,20,'2023-08-03','2023-12-27'),(10,7,'20:00:00','23:00:00',10800,2,45.00,20,'2023-08-07',NULL),(11,3,'04:30:00','07:41:00',11460,8,20.80,30,'2023-09-15',NULL),(14,363,'10:00:00','11:00:00',3600,4,30.00,100,'2023-08-04',NULL),(15,364,'13:00:00','15:00:00',7200,6,25.00,100,'2023-08-04',NULL),(16,365,'15:00:00','17:30:00',9000,2,45.00,100,'2023-08-04','2023-12-27'),(17,365,'13:00:00','15:00:00',7200,2,20.00,20,'2023-08-04',NULL),(21,1,'10:30:00','14:00:00',12600,3,50.00,100,'2023-08-15',NULL),(23,1,'20:30:00','00:00:00',12600,4,50.00,100,'2023-08-15',NULL),(25,1,'15:50:00','17:40:00',6600,3,50.00,100,'2023-08-15',NULL),(26,366,'10:00:00','11:00:00',3600,4,30.00,100,'2023-08-04',NULL),(27,8,'19:50:00','23:01:00',11460,5,20.80,12,'2023-09-16',NULL),(28,3,'21:00:00','00:20:00',12000,4,30.60,15,'2023-09-16',NULL),(29,94,'04:23:00','08:23:00',14400,8,52.40,25,'2023-09-15',NULL),(30,48,'10:40:00','14:00:00',12000,5,10.40,15,'2023-09-15',NULL),(31,233,'05:10:00','07:30:00',8400,3,45.25,25,'2023-09-15',NULL),(32,234,'12:40:00','15:05:00',8700,2,28.00,20,'2023-09-15',NULL),(33,237,'17:20:00','21:30:00',15000,6,33.20,20,'2023-09-19',NULL),(34,238,'18:00:00','22:10:00',15000,2,47.00,20,'2023-09-15',NULL),(35,1,'19:20:00','23:20:00',14400,8,20.80,20,'2023-09-19',NULL),(37,1,'12:00:00','15:11:00',11460,5,10.40,15,'2023-09-22',NULL),(38,1,'09:00:00','12:00:00',10800,8,20.80,30,'2023-09-19',NULL),(39,3,'12:30:00','15:50:00',12000,5,12.20,4,'2023-09-20',NULL),(40,50,'17:40:00','19:40:00',7200,3,20.00,30,'2023-10-03',NULL),(41,1,'10:30:00','12:30:00',7200,7,30.40,20,'2023-10-08',NULL),(42,8,'10:00:00','12:00:00',7200,7,20.00,20,'2023-10-09',NULL),(43,10,'14:00:00','16:00:00',7200,6,20.00,100,'2023-09-01','2023-12-31'),(44,14,'17:20:00','19:20:00',7200,3,30.00,20,'2023-10-09',NULL),(45,60,'16:30:00','18:30:00',7200,8,15.00,20,'2023-10-09',NULL),(46,17,'19:10:00','07:10:00',216000,4,40.30,20,'2023-10-09',NULL),(47,22,'12:05:00','00:05:00',216000,8,40.20,20,'2023-10-09',NULL),(48,149,'14:00:00','16:20:00',8400,6,30.00,20,'2023-10-09',NULL),(49,58,'13:30:00','16:10:00',9600,8,15.00,20,'2023-10-09',NULL),(50,90,'16:05:00','19:45:00',13200,5,13.30,20,'2023-10-09',NULL),(51,54,'12:20:00','14:50:00',9000,2,20.00,20,'2023-10-09',NULL),(52,11,'14:30:00','17:10:00',9600,3,20.00,20,'2023-10-09',NULL),(53,9,'14:00:00','16:00:00',7200,4,30.00,100,'2023-09-01',NULL),(54,15,'16:00:00','17:00:00',3600,6,50.00,100,'2023-09-01',NULL),(55,16,'17:00:00','18:00:00',3600,6,30.00,100,'2023-09-01',NULL),(56,28,'06:00:00','07:20:00',4800,7,20.00,100,'2023-09-01',NULL),(57,10,'13:00:00','15:00:00',7200,4,40.00,100,'2023-09-01',NULL),(58,2,'12:30:00','14:30:00',7200,2,25.00,50,'2023-10-17',NULL),(59,2,'12:30:00','14:33:00',7380,2,20.00,20,'2023-10-05',NULL),(60,2,'19:20:00','19:40:00',1200,3,444.00,44,'2023-10-29',NULL),(61,2,'20:30:00','21:30:00',3600,7,69.00,420,'2023-10-16',NULL),(62,2,'21:50:00','23:50:00',7200,4,20.00,30,'2023-10-16',NULL);
/*!40000 ALTER TABLE `schedules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `travels`
--

DROP TABLE IF EXISTS `travels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `travels` (
  `id` int NOT NULL AUTO_INCREMENT,
  `schedule_id` int NOT NULL,
  `route_id` int NOT NULL,
  `departure_date` date NOT NULL,
  `arrival_date` date NOT NULL,
  `empty_seats` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `travel_detail` (`schedule_id`,`route_id`,`departure_date`,`arrival_date`),
  UNIQUE KEY `route_schedule_date_id` (`schedule_id`,`route_id`,`departure_date`,`arrival_date`),
  KEY `route_id_fk_idx` (`route_id`),
  KEY `schedule_id_fk_idx` (`schedule_id`),
  CONSTRAINT `route_id_fk_2` FOREIGN KEY (`route_id`) REFERENCES `routes` (`id`),
  CONSTRAINT `schedule_id_fk` FOREIGN KEY (`schedule_id`) REFERENCES `schedules` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `travels`
--

LOCK TABLES `travels` WRITE;
/*!40000 ALTER TABLE `travels` DISABLE KEYS */;
INSERT INTO `travels` VALUES (26,6,1,'2023-09-20','2023-09-20',13),(29,4,2,'2023-09-20','2023-09-20',5),(30,14,363,'2023-09-25','2023-09-25',99),(31,3,1,'2023-09-20','2023-09-20',3),(32,6,1,'2023-09-27','2023-09-27',19),(33,5,2,'2023-09-20','2023-09-20',18),(34,27,8,'2023-09-19','2023-09-19',11),(35,11,3,'2023-09-19','2023-09-19',1),(36,3,1,'2023-09-27','2023-09-27',7),(37,5,2,'2023-09-27','2023-09-27',16),(38,7,1,'2023-09-27','2023-09-27',15),(39,11,3,'2023-09-27','2023-09-27',28),(40,3,1,'2023-10-18','2023-10-18',0),(41,4,2,'2023-10-18','2023-10-18',7),(42,7,1,'2023-10-11','2023-10-11',18),(43,5,2,'2023-10-11','2023-10-11',17),(44,7,1,'2023-10-18','2023-10-18',13),(45,3,1,'2023-10-11','2023-10-11',3),(46,4,2,'2023-10-11','2023-10-11',6),(47,8,6,'2023-10-09','2023-10-23',18),(48,14,363,'2023-10-23','2023-10-23',99),(49,53,9,'2023-10-12','2023-10-12',98),(50,43,10,'2023-10-12','2023-10-12',98),(51,61,2,'2023-10-25','2023-10-25',412),(53,5,2,'2023-10-18','2023-10-18',18);
/*!40000 ALTER TABLE `travels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `surname` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `creation_date` datetime NOT NULL,
  `disabled_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Giovanni','Bianchi','3360@disabled.com','Pass1234.','2021-06-28 20:57:44','2023-09-19 12:06:26'),(13,'admin','admin','admin@admin','admin','2023-07-28 15:44:48',NULL),(38,'sasdf','as','76934@disabled.com','asd','2023-09-14 11:31:52','2023-09-14 12:09:59'),(40,'Gianni','Morandi','15583@disabled.com','1234','2023-09-14 17:34:30','2023-09-14 17:46:36'),(42,'Sandro','Gialli','91472@disabled.com','12345','2023-09-19 10:12:56','2023-09-19 12:32:50'),(43,'Gino','Gino','38898@disabled.com','12345','2023-09-19 10:17:26','2023-09-19 14:01:42'),(44,'Pippo','Pluto','25853@disabled.com','12345','2023-09-19 10:19:21','2023-10-03 16:39:12'),(46,'Giovanni','Neri','g@gmail.com','12345','2023-09-19 14:45:22','2023-10-13 14:55:53'),(55,'giovanni','rossi','giovanni.rossi@gmail.com','Password123.','2023-09-19 18:47:42','2023-10-13 15:20:15'),(56,'Altra','Prova','11639@disabled.com','00000','2023-09-20 10:07:11','2023-10-03 16:34:02'),(57,'Ciccio','Pasticcio','91195@disabled.com','123321','2023-09-20 10:35:08','2023-09-20 10:37:30'),(58,'Ciao','Ciao','58067@disabled.com','1111','2023-09-20 10:35:08','2023-10-03 16:58:40'),(59,'pippo','pippo','pippo2@gmail.com','pippo','2023-10-03 17:09:29',NULL),(60,'test','test','test@test','test1','2023-10-03 17:46:07',NULL),(61,'Keinny','Ulloa','69450@disabled.com','keinny','2023-10-09 10:18:19','2023-10-09 11:27:31'),(112,'Alice','Ceccarelli','m@gmail.com','prova','2023-10-16 10:41:30',NULL),(113,'Alice','Ceccarelli','a@gmail.com','prova','2023-10-16 12:33:55','0000-00-00 00:00:00'),(117,'aa','aa','3852@disabled.com','aa','2023-10-18 10:08:30','2023-10-18 17:25:45'),(118,'bb','bb','5546@disabled.com','b','2023-10-18 10:10:48','2023-10-18 17:27:57'),(119,'ee','ee','ee','ee','2023-10-18 10:20:06',NULL),(120,'yy','yy','yy','yy','2023-10-18 10:35:32',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_authority`
--

DROP TABLE IF EXISTS `users_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_authority` (
  `user_id` int NOT NULL,
  `authority_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`authority_id`),
  KEY `fk_user_has_authority_authority_idx` (`authority_id`),
  KEY `fk_user_has_authority_user_idx` (`user_id`),
  CONSTRAINT `fk_user_has_authority_authority` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`),
  CONSTRAINT `fk_user_has_authority_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_authority`
--

LOCK TABLES `users_authority` WRITE;
/*!40000 ALTER TABLE `users_authority` DISABLE KEYS */;
INSERT INTO `users_authority` VALUES (13,1),(46,1),(55,1),(59,1),(112,1),(113,1),(117,1),(118,1),(119,1),(120,1),(13,2),(117,2),(119,2),(120,2);
/*!40000 ALTER TABLE `users_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xports`
--

DROP TABLE IF EXISTS `xports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xports` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `city_id` int NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `stop_name_city` (`city_id`,`name`),
  UNIQUE KEY `xport_name_city` (`name`,`city_id`),
  KEY `city_id_fk_idx` (`city_id`),
  CONSTRAINT `city_id_fk` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xports`
--

LOCK TABLES `xports` WRITE;
/*!40000 ALTER TABLE `xports` DISABLE KEYS */;
INSERT INTO `xports` VALUES (1,'Hamburg Airport',309,'plane'),(2,'Berlin Brandenburg Airport',310,'plane'),(3,'Dortmund Airport',314,'plane'),(4,'Frankfurt International Airport',312,'plane'),(5,'Leizpig Airport',320,'plane'),(6,'Minsk International Airport',277,'plane'),(7,'Madrid-Barajas Airport',406,'plane'),(8,'Barcelona Airport',407,'plane'),(9,'El Altet Airport',419,'plane'),(10,'Tenerife South Airport',425,'plane'),(11,'San Pablo Airport',409,'plane'),(12,'Brest Airport',282,'plane'),(13,'Gomel Airport',278,'plane'),(14,'Vienna International Airport',249,'plane'),(15,'Graz Airport',250,'plane'),(16,'Linz Airport',251,'plane'),(17,'Brussels Airport',259,'plane'),(18,'Salzburg Airport',252,'plane'),(19,'Innsbruck Airport',253,'plane'),(20,'Linate Airport',622,'plane'),(21,'Naples International Airport',623,'plane'),(22,'Rome Ciampino Airport',621,'plane'),(23,'Bari Palese Airport',630,'plane'),(24,'Malpensa International Airport',622,'plane'),(25,'Antwerp International Airport',255,'plane'),(26,'Stockholm-Arlanda Airport',1007,'plane'),(27,'Stockholm-Bromma Airport',1007,'plane'),(28,'Sundsvall-Härnösand Airport',1020,'plane'),(29,'Gothenburg-Landvetter Airport',1008,'plane'),(30,'Liege Airport',258,'plane'),(31,'Malmö-Sturup Airport',1009,'plane'),(32,'Brussels South Charleroi Airport',257,'plane'),(33,'Euro Airport Basel-Mulhouse-Freiburg',295,'plane'),(34,'Belp Airport',296,'plane'),(35,'M. R. Štefánik Airport',1002,'plane'),(36,'Ostend-Bruges International Airport',260,'plane'),(37,'Lennart Meri Tallinn Airport',465,'plane'),(38,'Helsinki Airport',467,'plane'),(39,'Turku Airport',471,'plane'),(40,'Oulu Airport',472,'plane'),(41,'Birmingham Airport',516,'plane'),(42,'Tampere-Pirkkala Airport',469,'plane'),(43,'London Heathrow Airport',515,'plane'),(44,'Glasgow International Airport',517,'plane'),(45,'Edinburgh Airport',519,'plane'),(46,'Manchester Airport',521,'plane'),(47,'Banja Luka International Airport',275,'plane'),(48,'Chisinau International Airport',692,'plane'),(49,'Sarajevo International Airport',274,'plane'),(51,'Skopje Airport',696,'plane'),(52,'Ljubljana Airport',1005,'plane'),(53,'Portela Airport',776,'plane'),(54,'Malta International Airport',698,'plane'),(55,'Francisco Sa Carneiro Airport',777,'plane'),(56,'Cointrin International Airport',294,'plane'),(57,'Košice Airport',1003,'plane'),(58,'Zurich Airport',293,'plane'),(59,'Frederic Chopin International Airport',734,'plane'),(60,'Athens International Airport',597,'plane'),(61,'Araxos Airport',600,'plane'),(62,'John Paul II International Airport',732,'plane'),(63,'Boryspil International Airport',1022,'plane'),(64,'Odesa International Airport',1026,'plane'),(65,'Mariupol International Airport',1031,'plane'),(66,'Paris-Charles de Gaulle Airport',474,'plane'),(67,'Thessaloniki International Airport',598,'plane'),(68,'Marseille Provence Airport',475,'plane'),(69,'Gdansk Lech Walesa Airport',737,'plane'),(70,'Copernicus Airport Wroclaw',735,'plane'),(71,'Strasbourg Entzheim International Airport',480,'plane'),(72,'Kaunas International Airport',682,'plane'),(73,'Vilnius International Airport',681,'plane'),(74,'Luxemburg-Findel International Airport',686,'plane'),(75,'Toulouse Blagnac International Airport',477,'plane'),(76,'Granada Jaén Airport',422,'plane'),(77,'Osijek Airport',608,'plane'),(78,'Split Airport',606,'plane'),(79,'Zagreb Airport',605,'plane'),(80,'Valencia Airport',408,'plane'),(81,'Oslo Airport Gardermoen',727,'plane'),(82,'Stavanger Airport Sola',730,'plane'),(83,'Bacau International Airport',792,'plane'),(84,'Bergen Airport Flesland',728,'plane'),(85,'Baia Mare Airport',797,'plane'),(86,'Riga International Airport',687,'plane'),(87,'Trondheim Airport Vaernes',729,'plane'),(88,'Eindhoven Airport',703,'plane'),(89,'Bucharest Aurel Vlaicu Airport',781,'plane'),(90,'Groningen Airport Eelde',705,'plane'),(91,'Cluj-Napoca International Airport',784,'plane'),(93,'Mihail Kolniceanu International Airport',783,'plane'),(94,'Rotterdam The Hague Airport',700,'plane'),(95,'Cagliari Airport',642,'plane'),(96,'Traian Vuia International Airport',786,'plane'),(97,'Maastricht Aachen Airport',716,'plane'),(98,'Stockholm-Skavsta Airport',1007,'plane'),(99,'Domodedovo International Airport',810,'plane'),(100,'Sheremetyevo International Airport',810,'plane'),(101,'Pulkovo International Airport',811,'plane'),(102,'Copenhagen Airport',401,'plane'),(103,'Aarhus Airport',402,'plane'),(104,'Aalborg Airport',404,'plane'),(105,'Prague Ruzyne Airport',298,'plane'),(106,'Brno-Turany Airport',299,'plane'),(107,'Ostrava-Mosnov International Airport',300,'plane'),(108,'Pardubice Airport',307,'plane'),(109,'Budapest Ferihegy International Airport',609,'plane'),(110,'Debrecen Airport',610,'plane'),(111,'Dublin Airport',618,'plane'),(112,'Cork Airport',619,'plane'),(113,'Keflavík International Airport',620,'plane'),(114,'Schiphol Airport',699,'plane'),(115,'La Seu d\'Urgell Airport',248,'plane'),(121,'Prova Xport',260,'plane'),(122,'Helloaaa',247,'plane'),(123,'Prova2',247,'plane'),(124,'Euro Airport Freiburg',295,'plane'),(127,'Provissima',1101,'plane'),(128,'Prova Xport',1101,'plane');
/*!40000 ALTER TABLE `xports` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-17 10:20:38
CREATE DATABASE  IF NOT EXISTS `bookstore1` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bookstore1`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: bookstore.cvzu4xrxvkdz.eu-south-1.rds.amazonaws.com    Database: bookstore1
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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1,'J.R.R.','Tolkien',''),(2,'Andrzej','Sapkowski',NULL),(3,'Douglas','Adams',NULL),(4,'Arthur Conan','Doyle',''),(5,'Eowin','Colfer','Irlandese'),(6,'Gosho','Aoyama','Creatore di Detective Conan'),(7,'nuovo autore','cognome',''),(8,'AUTORE','cognome','Autore americano famoso per Il Grande Gatsby'),(9,'WOW','PROVA',NULL),(11,'aaa','bbb','ccc');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `author_id` int NOT NULL,
  `editor` varchar(100) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `author_id_fk_idx` (`author_id`),
  CONSTRAINT `author_id_fk` FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'The Lord of the Rings : The fellowship of the ring',NULL,1,'Mondadori',20.00,96),(2,'The Lord of the Rings : The two towers',NULL,1,'Mondadori',20.00,146),(3,'The Witcher',NULL,2,'Feltrinelli',10.00,60),(4,'Guida Galattica per Autostoppisti',NULL,3,'Feltrinelli',15.00,79),(5,'Sherlock Holmes',NULL,4,'Mondadori',25.00,42),(8,'prova','prova',7,'Mondadori',20.00,3),(9,'The Lord of the Rings: The Return of the King','',1,'Feltrinelli',18.50,17),(10,'aa','aaa',1,'aa',11.00,1111),(11,'libro1','libro',8,'Mondadori',20.00,3),(12,'lalala','lalala',1,'lalala',5.00,4),(15,'letsgo','prova',7,'Mondadori',20.00,3),(16,'Il Grande Gatsby','Un romanzo ambientato nell\'era del jazz',8,'Mondadori',25.99,100),(17,'Il Grande Gatsby','Un romanzo ambientato nell\'era del jazz',8,'Mondadori',25.99,100),(18,'PROVA','Un romanzo ambientato nell\'era del jazz',8,'Mondadori',25.99,100),(20,'Libro','Libro',1,'Editor',12.00,100),(22,'Libro','Libro',1,'Editor',12.00,100),(24,'Prova','Ciao',2,'Hello',13.00,44);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_id` int NOT NULL,
  `quantity` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_cart_idx` (`user_id`),
  KEY `fk_book_cart_idx` (`book_id`),
  CONSTRAINT `fk_book_cart` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_cart` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `book_id` int NOT NULL,
  `quantity` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id_fk_idx` (`order_id`),
  KEY `book_id_fk_idx` (`book_id`),
  CONSTRAINT `book_id_fk` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  CONSTRAINT `order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (1,4,2,1,20.00),(2,4,1,1,20.00),(3,5,1,1,20.00),(4,6,4,1,15.00),(5,7,4,1,15.00),(6,8,1,1,20.00),(7,9,2,1,20.00),(8,10,2,1,20.00),(9,11,2,1,20.00),(10,17,9,1,18.50),(11,18,4,4,15.00),(12,19,12,1,5.00),(14,21,1,2,20.00);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `user_id` int NOT NULL,
  `status` char(1) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `shipping_address` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_fk_idx` (`user_id`),
  CONSTRAINT `user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (4,'2023-07-17 12:10:19',2,'P',40.00,'Via qualcosa 3'),(5,'2023-07-17 14:35:12',2,'C',20.00,'triq il-nofs 12'),(6,'2023-07-17 14:39:23',2,'C',15.00,'via bella 1'),(7,'2023-07-17 14:39:23',1,'P',15.00,'triq il-nofs 12'),(8,'2023-07-19 15:00:02',5,'P',20.00,'triq il-nofs 12'),(9,'2023-07-19 16:35:40',5,'P',20.00,'triq il-nofs 12'),(10,'2023-07-19 16:36:47',5,'C',20.00,'triq il-nofs 12'),(11,'2023-07-19 16:37:47',5,'C',20.00,'triq il-nofs 12'),(17,'2023-07-21 10:09:07',2,'I',18.50,'Via qualcosa 3'),(18,'2023-07-24 14:39:57',5,'P',60.00,'triq il-nofs 12'),(19,'2023-07-24 17:41:43',5,'I',5.00,'via lala'),(21,'2023-09-22 15:05:25',1,'I',20.00,'hgfdgfd'),(22,'2023-09-22 15:09:27',1,'I',0.00,'fsddfs');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'anna@gmail.com','111',NULL,NULL,'2023-07-14 00:00:00'),(2,'lara@gmail.com','222',NULL,NULL,'2023-07-14 00:00:00'),(3,'lisa@gmail.com','333','lisa','sala','2023-07-17 15:37:31'),(4,'chris@gmail.com','444','chris','galea','2023-07-19 09:21:33'),(5,'annalisa.b.sala@gmail.com','555','annalisa','sala','2023-07-19 14:06:58'),(6,'ciao@gmail.com','888','d','e','2023-07-24 15:16:55'),(7,'esempio@email.com','password123','Nome','Cognome','2023-08-07 15:30:46'),(8,'secondoEsempio@email.com','password123','Nome','Cognome','2023-08-07 15:30:46');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-17 10:20:39
CREATE DATABASE  IF NOT EXISTS `bookstore2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bookstore2`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: bookstore.cvzu4xrxvkdz.eu-south-1.rds.amazonaws.com    Database: bookstore2
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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1,'Minnie','pluto',''),(2,'Pluto','topolino',''),(3,'Walt','Disney',''),(4,'Daisy','Papera','');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `author_id` int NOT NULL,
  `editor` varchar(100) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `author_id_fk_idx` (`author_id`),
  CONSTRAINT `author_id_fk` FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'il mondo incantato','fantasy',1,'bo',30.00,81),(2,'piccoli brividi','fantasy',2,'jjj',20.00,81),(7,'La divina commedia','',2,'Pippo',40.00,396),(8,'Paradiso','',2,'Fluffy',30.00,3),(9,'Inferno','',1,'Gino',20.00,5),(10,'Pinocchio','',3,'Stella',15.00,48),(11,'ciao','',4,'ciao',40.00,28);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `book_id` int NOT NULL,
  `quantity` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id_fk_idx` (`order_id`),
  KEY `book_id_fk_idx` (`book_id`),
  CONSTRAINT `book_id_fk` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  CONSTRAINT `order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (30,36,1,2,30.00),(31,36,2,3,20.00),(32,36,7,1,40.00),(33,37,2,1,20.00),(34,37,7,1,40.00),(35,39,1,2,30.00),(36,39,2,2,20.00),(37,42,10,2,15.00),(38,42,11,2,40.00);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `user_id` int NOT NULL,
  `status` varchar(45) NOT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `shipping_address` varchar(500) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_fk_idx` (`user_id`),
  CONSTRAINT `user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (36,'2023-07-24 17:37:41',6,'I',160.00,'Viale Giacomo Matteotti, 304 Sesto San Giovanni 20099 Italy'),(37,'2023-07-24 17:42:45',6,'I',60.00,'Viale Giacomo Matteotti, 304 Sesto San Giovanni 20099 Italy'),(39,'2023-07-24 17:49:38',6,'I',100.00,'Viale Giacomo Matteotti, 304 Sesto San Giovanni 20099 Italy'),(42,'2023-07-24 17:57:46',6,'I',110.00,'Viale Giacomo Matteotti, 304 Sesto San Giovanni 20099 Italy');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'pippo@gmail.com','12345','Pippo','Parerino','2023-07-14 17:27:01'),(6,'flaviana@gmail.com','flaviana','Flaviana','Caroselli','2023-07-15 10:14:02');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-17 10:20:39
CREATE DATABASE  IF NOT EXISTS `bookstore3` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bookstore3`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: bookstore.cvzu4xrxvkdz.eu-south-1.rds.amazonaws.com    Database: bookstore3
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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1,'Dante','Alighieri','Poeta italiano, definito padre della lingua italiana, autore de La Divina Commedia '),(2,'George','Orwell','George Orwell, pseudonimo di Eric Arthur Blair, è stato uno scrittore e saggista britannico. È noto per il suo romanzo distopico \"1984\" e per il suo impegno politico e sociale che ha influenzato profondamente il panorama letterario del XX secolo.'),(3,'Jane','Austen','Jane Austen è stata una scrittrice inglese nota per i suoi romanzi che ritraggono la vita della classe media inglese del periodo regency. I suoi romanzi più famosi includono \"Orgoglio e pregiudizio\" e \"Ragione e sentimento'),(4,'J.R.R.','Tolkien','John Ronald Reuel Tolkien è stato uno scrittore e professore britannico. È famoso per il suo lavoro nel campo della fantasy, in particolare per il suo romanzo epico \"Il signore degli anelli\" e il suo prequel \"Lo Hobbit\".'),(5,'George R.R.','Martin','George Raymond Richard Martin è uno scrittore americano di fantasy e fantascienza. È noto per la sua serie di romanzi \"Cronache del ghiaccio e del fuoco\", che ha ispirato la popolare serie televisiva \"Game of Thrones\".'),(6,'F. Scott','Fitzgerald','Francis Scott Fitzgerald è stato uno scrittore statunitense noto per i suoi romanzi che rappresentano l\'era del jazz negli anni \'20. Il suo romanzo più famoso è \"Il grande Gatsby\", considerato un classico della letteratura americana.'),(7,'J.K.','Rowling','Joanne Rowling, conosciuta come J.K. Rowling, è una scrittrice britannica famosa per la serie di libri su Harry Potter. I suoi romanzi hanno conquistato un\'enorme popolarità e hanno ispirato una serie di film di successo.'),(8,'Herman','Melville','Herman Melville è stato uno scrittore e poeta americano del XIX secolo. È famoso per il suo romanzo \"Moby Dick\", considerato uno dei capolavori della letteratura americana.'),(9,'Antoine','de Saint-Exupéry','Antoine de Saint-Exupéry è stato uno scrittore e aviatore francese. Il suo libro più celebre, \"Il Piccolo Principe\", è una fiaba filosofica amata da lettori di tutte le età.'),(10,'Harper','Lee','Nelle Harper Lee, è stata una scrittrice statunitense. È conosciuta principalmente per il suo unico romanzo pubblicato, \"To Kill a Mockingbird\" (in italiano \"Il buio oltre la siepe\"), pubblicato nel 1960. Il libro, ambientato nel profondo sud degli Stati Uniti durante la Grande Depressione, affronta temi importanti come il razzismo, l\'ingiustizia sociale e l\'infanzia.'),(14,'Alessandro','Baricco','Scrittore, saggista e drammaturgo italiano contemporaneo. Nato il 25 gennaio 1958 a Torino, è uno degli autori più celebri e amati della letteratura italiana contemporanea. Noto per il suo stile di scrittura elegante e poetico, che spazia tra vari generi letterari. Ha scritto romanzi, racconti, opere teatrali e saggi, dimostrando una grande versatilità e creatività nel suo lavoro.'),(17,'nome','cognome',NULL),(18,'nome','cognome',NULL);
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `author_id` int NOT NULL,
  `editor` varchar(100) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `author_id_fk_idx` (`author_id`),
  CONSTRAINT `author_id_fk` FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Divina Commedia','Nel poema, Dante racconta il suo viaggio attraverso l\'aldilà guidato dal poeta romano Virgilio. Dante rappresenta se stesso come protagonista e narra il suo viaggio attraverso gli inferi, il monte del Purgatorio e i cieli celesti. Lungo il percorso, egli incontra anime di peccatori e santi, ognuno dei quali ha una storia da raccontare e un destino diverso.',1,'Mondadori',13.34,4),(7,'1984','Ambientato in un futuro totalitario, il libro descrive la vita di Winston Smith, un uomo che vive in un regime oppressivo noto come Oceania. Il governo controlla ogni aspetto della vita delle persone, manipolando la storia, limitando le libertà personali e sorvegliando costantemente i cittadini attraverso il Grande Fratello. Il romanzo esplora temi come la manipolazione della verità, la perdita dell\'individualità e la resistenza contro l\'oppressione.',10,'Secker & Warburg',12.70,12),(8,'Il signore degli anelli','Ambientata in un mondo immaginario chiamato Terra di Mezzo, la storia segue un gruppo di personaggi, tra cui hobbit, elfi, nani, uomini e altre creature fantastiche, nella loro lotta contro il Signore Oscuro Sauron. Il romanzo segue il percorso di Frodo Baggins, un hobbit incaricato di distruggere l\'Unico Anello, un potente artefatto che può dare a Sauron il controllo assoluto sul mondo.',3,'Allen & Unwin',14.54,12),(9,'Orgoglio e pregiudizio','Ambientato nella società inglese del XIX secolo, il libro racconta la storia di Elizabeth Bennet, una giovane donna intelligente e vivace, e del suo rapporto con il ricco e orgoglioso signor Darcy. Il romanzo esplora temi come l\'amore, l\'orgoglio, i pregiudizi sociali e la ricerca della felicità personale.',3,'Thomas Egerton',9.99,22),(10,'Cronache del ghiaccio e del fuoco','Ambientato in un mondo immaginario medievale, il ciclo narra la storia di potenti famiglie nobiliari che lottano per il controllo del Trono di Spade. La serie è caratterizzata da una trama intricata, personaggi complessi e un\'ambientazione dettagliata. La serie è composta da diversi libri, il primo dei quali è \"Il gioco del trono\".',5,'Bantam Books',14.80,28),(11,'Il grande Gatsby','Ambientato negli anni \'20, il libro racconta la storia di Jay Gatsby, un misterioso e affascinante milionario che organizza sontuose feste nella speranza di riconquistare il cuore della donna che ama. Il romanzo esplora temi come il sogno americano, la ricchezza, l\'amore e la corruzione della società dell\'epoca.',6,'Charles Scribner\'s Sons',9.50,19),(12,'Harry Potter e la pietra filosofale','Primo libro della serie di Harry Potter scritta da J.K. Rowling. Il libro introduce il giovane mago Harry Potter e il suo ingresso nella Scuola di Magia e Stregoneria di Hogwarts. La storia segue le avventure di Harry mentre scopre il suo retaggio magico, affronta il malevolo Signore Oscuro Voldemort e impara il valore dell\'amicizia e del coraggio.',7,'Bloomsbury',13.50,11),(13,'To Kill a Mockingbird','Ambientato nel profondo sud degli Stati Uniti negli anni \'30, il libro racconta la storia di Scout Finch, una giovane ragazza che osserva suo padre, l\'avvocato Atticus Finch, difendere un uomo di colore ingiustamente accusato di stupro. Il romanzo affronta temi di razzismo, giustizia e crescita personale.',10,'J.B. Lippincott & Co',11.99,12),(14,'Lo Hobbit','Il libro narra le avventure di Bilbo Baggins, un hobbit tranquillo che si ritrova coinvolto in una missione epica per recuperare un tesoro custodito da un drago. Ambientato nello stesso mondo della trilogia de \"Il Signore degli Anelli\", \"Lo Hobbit\" è una storia avventurosa, ricca di creature fantasy, incontri pericolosi e la scoperta del coraggio nascosto.',4,'Allen & Unwin',24.99,10),(15,'Moby Dick','Il libro racconta la storia del capitano Ahab e la sua ossessione per la caccia a un enorme capodoglio bianco chiamato Moby Dick. Il romanzo esplora temi come la follia, la vendetta e la lotta dell\'uomo contro le forze incontrollabili della natura.',8,'Richard Bentley',9.99,14),(16,'Il Piccolo Principe',' È una fiaba poetica che racconta l\'incontro di un aviatore bloccato nel deserto con un misterioso principe proveniente da un altro pianeta. Il libro affronta temi come l\'amicizia, l\'innocenza, l\'amore e il significato della vita attraverso le lezioni impartite dal piccolo principe.',9,'Éditions Gallimard',7.00,3),(27,'Novecento','Monologo teatrale ambientato su una nave transatlantica nel primo Novecento e viene raccontata dal protagonista e narratore, Tim Tooney. Il protagonista racconta la straordinaria storia di un uomo di nome Novecento, un musicista dotato di un talento eccezionale per il pianoforte.',14,'Feltrinelli',6.00,13),(28,'titolo','desc',1,'editor',24.00,0),(29,'titolo2','desc',2,'editor',24.00,4);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `book_id` int NOT NULL,
  `quantity` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id_fk_idx` (`order_id`),
  KEY `book_id_fk_idx` (`book_id`),
  CONSTRAINT `book_id_fk` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  CONSTRAINT `order_id_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=579 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (123,75,1,3,40.02),(124,75,7,2,25.40),(125,75,8,1,23.50),(126,75,12,4,54.00),(127,76,1,3,40.02),(128,76,7,2,25.40),(129,76,8,1,23.50),(130,76,12,4,54.00),(131,76,12,4,54.00),(132,77,1,1,13.34),(133,77,7,2,25.40),(134,77,1,1,13.34),(135,77,7,2,25.40),(136,79,8,3,70.50),(137,80,1,3,40.02),(138,80,7,2,25.40),(139,80,8,1,23.50),(140,80,12,4,54.00),(141,80,12,4,54.00),(146,84,1,1,13.34),(147,85,1,2,26.68),(148,85,7,1,12.70),(149,85,14,10,249.90),(150,85,14,10,249.90),(151,86,1,3,40.02),(152,86,7,2,25.40),(153,86,12,4,54.00),(154,86,12,4,54.00),(155,87,1,3,40.02),(156,87,7,2,25.40),(157,87,12,6,81.00),(158,87,12,6,81.00),(159,88,11,1,9.50),(160,89,12,5,67.50),(161,90,1,3,40.02),(162,90,7,2,25.40),(163,90,10,1,14.80),(164,91,1,3,40.02),(165,91,7,2,25.40),(166,92,1,2,26.68),(167,92,7,3,38.10),(168,93,1,2,26.68),(169,93,7,1,12.70),(170,93,8,3,70.50),(171,94,1,3,40.02),(172,94,7,2,25.40),(173,94,14,1,24.99),(174,95,1,3,40.02),(175,95,7,2,25.40),(177,97,1,3,40.02),(178,97,7,2,25.40),(183,102,8,3,70.50),(184,103,1,3,40.02),(185,104,1,3,40.02),(190,119,1,2,26.68),(191,119,7,1,12.70),(192,119,9,5,49.95),(193,120,1,3,40.02),(194,120,7,2,25.40),(195,122,8,2,47.00),(196,122,7,2,25.40),(197,139,7,3,38.10),(198,139,8,2,47.00),(199,139,7,3,38.10),(200,139,8,2,47.00),(201,139,8,2,47.00),(202,140,7,3,38.10),(203,140,8,3,70.50),(204,141,7,4,50.80),(205,141,9,4,39.96),(206,142,7,3,38.10),(207,142,10,3,44.40),(208,146,1,1,13.34),(209,147,10,1,14.80),(210,147,10,1,14.80),(211,147,10,1,14.80),(212,147,10,1,14.80),(213,147,11,2,19.00),(214,147,10,1,14.80),(215,147,10,1,14.80),(216,147,11,2,19.00),(217,148,1,1,13.34),(218,149,1,2,26.68),(219,149,16,2,14.00),(221,152,11,3,28.50),(222,152,14,1,24.99),(223,153,1,1,13.34),(224,153,8,1,23.50),(225,155,12,3,40.50),(226,155,12,3,40.50),(227,154,1,1,13.34),(231,158,1,3,40.02),(233,158,8,2,47.00),(234,158,8,2,47.00),(235,159,1,2,26.68),(236,159,7,1,12.70),(237,159,8,1,23.50),(238,159,8,1,23.50),(240,159,8,1,23.50),(242,159,8,1,23.50),(245,161,7,2,25.40),(247,162,10,2,29.60),(248,164,1,1,13.34),(249,165,11,2,19.00),(252,167,1,2,26.68),(253,168,1,1,13.34),(254,168,7,1,12.70),(255,171,11,1,9.50),(256,172,1,2,26.68),(266,178,1,1,13.34),(267,179,1,1,13.34),(268,180,1,1,13.34),(269,181,12,2,27.00),(270,182,9,1,9.99),(271,183,1,1,13.34),(272,184,1,1,13.34),(275,186,1,1,13.34),(276,187,1,1,13.34),(277,188,1,1,13.34),(279,190,1,1,13.34),(280,191,1,1,13.34),(281,191,7,1,12.70),(282,191,8,1,23.50),(283,192,1,1,13.34),(284,192,1,1,13.34),(285,192,7,1,12.70),(286,193,1,1,13.34),(287,194,1,1,13.34),(288,196,1,1,13.34),(289,197,1,1,13.34),(296,202,1,1,13.34),(298,203,1,1,13.34),(299,203,7,1,12.70),(300,203,11,1,9.50),(301,204,1,1,13.34),(302,204,7,1,12.70),(303,206,1,1,13.34),(304,206,7,1,12.70),(305,208,1,1,13.34),(306,208,7,1,12.70),(307,209,1,1,13.34),(308,209,7,1,12.70),(310,211,1,1,13.34),(311,212,1,1,13.34),(312,213,1,1,13.34),(313,214,1,1,13.34),(314,215,1,1,13.34),(318,218,8,1,14.54),(319,218,8,1,14.54),(320,218,8,1,14.54),(321,218,8,1,14.54),(322,218,8,1,14.54),(334,227,9,1,9.99),(335,229,9,2,19.98),(353,243,1,2,26.68),(355,245,1,2,26.68),(356,246,1,2,26.68),(371,260,1,1,13.34),(372,260,7,10,127.00),(379,265,1,2,26.68),(380,265,1,2,26.68),(381,265,1,2,26.68),(382,265,1,2,26.68),(383,265,1,2,26.68),(384,265,1,2,26.68),(396,274,7,2,25.40),(409,286,7,1,12.70),(410,286,16,1,7.00),(411,286,8,1,14.54),(433,306,1,1,13.34),(448,316,1,2,26.68),(450,317,7,1,12.70),(453,319,1,2,26.68),(454,319,7,1,12.70),(455,320,1,2,26.68),(457,321,1,2,26.68),(459,322,1,2,26.68),(461,323,1,1,13.34),(463,324,1,1,13.34),(472,328,7,1,12.70),(475,331,8,1,14.54),(476,331,10,1,14.80),(477,333,7,1,12.70),(478,332,8,2,29.08),(481,334,16,1,7.00),(482,336,1,1,13.34),(483,336,1,1,13.34),(484,336,1,1,13.34),(485,342,16,1,7.00),(486,342,16,1,7.00),(488,342,16,1,7.00),(489,344,7,2,25.40),(493,342,16,1,7.00),(494,342,16,1,7.00),(495,342,16,1,7.00),(496,346,7,1,12.70),(497,346,7,1,12.70),(498,346,7,1,12.70),(499,346,7,1,12.70),(500,346,7,1,12.70),(505,350,15,1,9.99),(507,353,1,2,26.68),(514,355,7,2,25.40),(516,356,8,1,14.54),(517,359,1,1,13.34),(518,359,7,2,25.40),(519,359,8,2,29.08),(520,363,1,43,573.62),(521,363,1,43,573.62),(522,363,1,1,13.34),(523,365,7,1,12.70),(524,365,7,2,25.40),(526,365,9,1,9.99),(527,367,8,1,14.54),(528,368,8,2,29.08),(529,368,9,1,9.99),(530,368,12,1,13.50),(531,368,15,1,9.99),(532,370,11,1,9.50),(533,367,7,1,12.70),(534,372,16,1,7.00),(537,374,8,1,14.54),(538,374,10,2,29.60),(539,374,11,2,19.00),(540,375,1,1,13.34),(541,375,7,1,12.70),(544,366,1,2,26.68),(545,377,15,1,9.99),(546,378,1,2,26.68),(547,379,1,2,26.68),(548,382,1,1,13.34),(550,382,8,1,14.54),(553,384,1,2,26.68),(554,384,7,2,25.40),(556,385,1,2,26.68),(557,386,8,1,14.54),(558,386,1,2,26.68),(559,387,1,2,26.68),(560,387,7,3,38.10),(561,387,1,2,26.68),(562,387,7,3,38.10),(563,387,1,10,133.40),(573,391,7,1,12.70),(574,391,7,1,12.70),(575,393,7,1,12.70);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `user_id` int NOT NULL,
  `status` char(1) NOT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `shipping_address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_fk_idx` (`user_id`),
  CONSTRAINT `user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=395 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (28,'2023-07-17 09:39:18',1,'I',0.00,NULL),(29,'2023-07-17 10:21:45',1,'I',294.30,NULL),(30,'2023-07-17 10:32:19',1,'I',0.00,NULL),(31,'2023-07-17 10:36:45',1,'I',0.00,NULL),(32,'2023-07-17 10:37:12',1,'I',26.04,NULL),(33,'2023-07-17 10:38:31',1,'I',0.00,NULL),(34,'2023-07-17 10:39:36',1,'I',0.00,NULL),(35,'2023-07-17 10:39:49',1,'I',0.00,NULL),(36,'2023-07-17 10:40:19',1,'I',0.00,NULL),(37,'2023-07-17 10:43:52',1,'I',123.66,NULL),(39,'2023-07-17 10:57:27',1,'I',0.00,NULL),(41,'2023-07-17 11:00:33',1,'I',0.00,NULL),(46,'2023-07-17 11:39:59',2,'P',71.42,'via teodoro 2'),(49,'2023-07-17 12:16:12',2,'P',38.74,'via torino 1'),(50,'2023-07-17 12:22:16',2,'P',38.74,NULL),(51,'2023-07-17 12:34:12',1,'I',39.38,NULL),(52,'2023-07-17 12:45:42',1,'I',65.42,NULL),(75,'2023-07-17 15:10:21',1,'I',54.00,NULL),(76,'2023-07-17 15:13:30',1,'I',54.00,NULL),(77,'2023-07-17 15:15:36',2,'P',77.48,'via battisti 2'),(78,'2023-07-17 15:18:23',1,'I',0.00,NULL),(79,'2023-07-17 15:19:34',2,'P',70.50,'via luigi VI 3'),(80,'2023-07-17 15:21:15',1,'I',54.00,NULL),(81,'2023-07-17 15:28:16',1,'I',65.42,NULL),(83,'2023-07-17 15:32:56',1,'I',0.00,NULL),(84,'2023-07-17 15:33:13',1,'I',13.34,NULL),(85,'2023-07-17 15:36:25',1,'I',249.90,NULL),(86,'2023-07-17 15:42:24',1,'I',54.00,NULL),(87,'2023-07-17 15:44:50',1,'I',81.00,NULL),(88,'2023-07-17 15:45:59',2,'P',9.50,'via prova'),(89,'2023-07-17 15:47:49',2,'P',67.50,'via torino 4'),(90,'2023-07-17 15:48:26',1,'I',14.80,NULL),(91,'2023-07-17 16:32:54',1,'I',65.42,NULL),(92,'2023-07-17 16:34:53',1,'I',64.78,NULL),(93,'2023-07-17 16:36:07',1,'P',70.50,'a'),(94,'2023-07-17 16:37:16',1,'P',24.99,'a'),(95,'2023-07-17 16:37:41',1,'I',65.42,NULL),(97,'2023-07-17 16:44:44',1,'I',65.42,NULL),(100,'2023-07-17 16:53:52',1,'I',0.00,NULL),(102,'2023-07-17 17:14:14',2,'P',70.50,'via como 1'),(103,'2023-07-17 17:36:17',2,'P',40.02,'via torino 1'),(104,'2023-07-17 17:45:49',2,'P',40.02,'via casa 12'),(119,'2023-07-18 17:31:23',1,'P',49.95,NULL),(120,'2023-07-18 17:32:35',1,'P',65.42,'Via cane 2'),(121,'2023-07-18 17:34:35',1,'I',0.00,NULL),(122,'2023-07-18 17:35:27',1,'P',72.40,'vvv'),(123,'2023-07-18 17:36:13',1,'I',0.00,NULL),(124,'2023-07-19 14:08:38',1,'I',0.00,NULL),(125,'2023-07-19 14:09:06',1,'I',0.00,NULL),(126,'2023-07-19 14:09:07',1,'I',0.00,NULL),(127,'2023-07-19 14:09:08',1,'I',0.00,NULL),(128,'2023-07-19 14:09:23',1,'I',0.00,NULL),(129,'2023-07-19 14:09:46',1,'I',0.00,NULL),(130,'2023-07-19 14:10:30',1,'I',0.00,NULL),(131,'2023-07-19 14:12:57',1,'I',0.00,NULL),(132,'2023-07-19 14:13:37',1,'I',0.00,NULL),(133,'2023-07-19 14:23:09',1,'I',0.00,NULL),(134,'2023-07-19 14:23:26',1,'I',0.00,NULL),(135,'2023-07-19 14:26:55',1,'I',0.00,NULL),(136,'2023-07-19 14:28:31',1,'I',0.00,NULL),(137,'2023-07-19 14:29:59',1,'I',0.00,NULL),(138,'2023-07-19 14:42:20',1,'I',0.00,NULL),(139,'2023-07-19 15:04:45',1,'I',132.10,NULL),(140,'2023-07-19 15:08:50',1,'I',108.60,NULL),(141,'2023-07-19 15:12:10',1,'I',90.76,NULL),(142,'2023-07-19 15:16:12',1,'I',82.50,NULL),(143,'2023-07-19 15:38:09',1,'I',0.00,NULL),(144,'2023-07-19 15:39:22',1,'I',0.00,NULL),(145,'2023-07-19 15:41:50',1,'I',0.00,NULL),(146,'2023-07-19 15:42:48',1,'I',13.34,NULL),(147,'2023-07-19 15:48:50',1,'I',48.60,NULL),(148,'2023-07-19 15:56:09',1,'I',13.34,NULL),(149,'2023-07-19 15:56:33',1,'I',40.68,NULL),(150,'2023-07-19 15:57:07',1,'I',0.00,NULL),(152,'2023-07-19 16:43:59',1,'I',0.00,NULL),(153,'2023-07-19 16:46:08',1,'I',36.84,NULL),(154,'2023-07-19 16:49:11',1,'I',22.84,NULL),(155,'2023-07-19 16:49:51',2,'P',40.50,'via pluto'),(156,'2023-07-19 16:55:35',1,'I',0.00,NULL),(158,'2023-07-19 16:56:30',1,'I',47.00,NULL),(159,'2023-07-19 17:08:50',1,'P',23.50,'null'),(161,'2023-07-19 17:20:31',2,'P',25.40,'via casa 1'),(162,'2023-07-19 17:23:01',2,'P',29.60,'address'),(163,'2023-07-19 17:24:05',2,'P',0.00,'address'),(164,'2023-07-19 17:29:43',1,'I',13.34,NULL),(165,'2023-07-19 17:30:45',2,'P',19.00,'null'),(167,'2023-07-19 17:50:14',2,'P',26.68,'via pippo 2'),(168,'2023-07-20 15:16:00',1,'P',12.70,'Via roma 2'),(171,'2023-07-20 16:39:42',2,'P',9.50,'via casa mia'),(172,'2023-07-20 17:40:06',2,'P',26.68,'via casa2'),(178,'2023-07-21 09:44:57',1,'I',13.34,NULL),(179,'2023-07-21 09:46:00',1,'I',13.34,NULL),(180,'2023-07-21 09:50:30',1,'I',13.34,NULL),(181,'2023-07-21 09:50:59',2,'P',27.00,'via casa 1'),(182,'2023-07-21 09:53:00',1,'I',9.99,NULL),(183,'2023-07-21 09:55:27',1,'I',13.34,NULL),(184,'2023-07-21 10:01:37',1,'I',13.34,NULL),(186,'2023-07-21 10:12:19',1,'I',13.34,NULL),(187,'2023-07-21 10:13:47',1,'I',13.34,''),(188,'2023-07-21 10:14:32',1,'P',13.34,'via roma 2'),(190,'2023-07-21 10:16:38',1,'P',13.34,'s'),(191,'2023-07-21 10:16:46',1,'P',23.50,'dsa'),(192,'2023-07-21 10:17:20',1,'P',12.70,'{\r\n    \"address\": \"via prova 2\"\r\n}'),(193,'2023-07-21 10:33:27',1,'P',13.34,'via prova 2'),(194,'2023-07-21 10:33:40',1,'I',13.34,NULL),(195,'2023-07-21 10:36:10',1,'I',NULL,NULL),(196,'2023-07-21 10:37:16',1,'I',13.34,NULL),(197,'2023-07-21 10:39:34',1,'I',13.34,NULL),(198,'2023-07-21 10:42:11',1,'I',NULL,NULL),(199,'2023-07-21 10:42:11',1,'I',49.37,NULL),(200,'2023-07-21 10:43:39',1,'I',9.99,NULL),(202,'2023-07-21 10:46:34',1,'I',0.00,NULL),(203,'2023-07-21 10:54:29',1,'I',26.04,NULL),(204,'2023-07-21 10:58:11',1,'I',13.34,NULL),(205,'2023-07-21 11:01:13',1,'I',0.00,NULL),(206,'2023-07-21 11:01:44',1,'I',12.70,NULL),(207,'2023-07-21 11:02:46',1,'I',NULL,NULL),(208,'2023-07-21 11:04:49',1,'I',26.04,NULL),(209,'2023-07-21 11:05:45',1,'I',26.04,NULL),(211,'2023-07-21 11:47:38',1,'I',13.34,NULL),(212,'2023-07-21 11:49:19',1,'I',13.34,NULL),(213,'2023-07-21 11:54:15',1,'I',13.34,NULL),(214,'2023-07-21 11:57:10',1,'I',13.34,NULL),(215,'2023-07-21 12:09:11',1,'I',13.34,NULL),(216,'2023-07-21 12:13:39',1,'I',13.34,NULL),(217,'2023-07-21 12:15:11',1,'I',12.70,NULL),(218,'2023-07-21 12:16:00',1,'I',72.70,NULL),(224,'2023-07-21 13:05:18',1,'I',12.70,NULL),(225,'2023-07-21 13:11:23',1,'I',9.99,NULL),(227,'2023-07-21 14:26:08',1,'I',9.99,NULL),(229,'2023-07-21 14:27:12',1,'I',9.99,NULL),(230,'2023-07-21 14:29:19',1,'I',59.36,NULL),(232,'2023-07-21 14:37:52',1,'I',26.68,NULL),(234,'2023-07-21 14:41:57',1,'I',NULL,NULL),(236,'2023-07-21 14:42:35',1,'I',13.34,NULL),(237,'2023-07-21 14:43:59',1,'I',13.34,NULL),(239,'2023-07-21 14:48:05',1,'I',26.68,NULL),(242,'2023-07-21 14:51:51',1,'I',26.68,NULL),(243,'2023-07-21 14:54:36',1,'I',26.68,NULL),(245,'2023-07-21 14:56:26',1,'I',26.68,NULL),(246,'2023-07-21 15:00:03',1,'I',26.68,NULL),(247,'2023-07-21 15:02:05',1,'I',26.68,NULL),(260,'2023-07-21 15:41:20',1,'P',140.34,'via roma 2'),(261,'2023-07-21 15:42:23',1,'I',NULL,NULL),(265,'2023-07-21 16:26:46',1,'I',160.08,NULL),(268,'2023-07-21 16:35:12',1,'I',29.08,NULL),(269,'2023-07-21 16:37:33',1,'I',12.70,NULL),(271,'2023-07-21 16:40:28',1,'I',25.40,NULL),(273,'2023-07-21 16:42:23',1,'I',12.70,NULL),(274,'2023-07-21 16:43:18',1,'P',25.40,'v'),(286,'2023-07-21 17:16:48',2,'P',34.24,'via pinna'),(287,'2023-07-21 17:16:56',1,'I',NULL,NULL),(289,'2023-07-21 17:17:31',1,'I',NULL,NULL),(290,'2023-07-21 17:17:54',1,'I',12.70,NULL),(292,'2023-07-21 17:18:26',1,'I',7.00,NULL),(294,'2023-07-21 17:21:40',1,'I',12.70,NULL),(295,'2023-07-21 17:22:26',1,'I',7.00,NULL),(300,'2023-07-21 17:25:35',1,'I',NULL,NULL),(306,'2023-07-21 17:44:32',1,'I',26.04,NULL),(316,'2023-07-22 16:07:07',1,'I',52.08,NULL),(317,'2023-07-22 16:14:15',1,'I',12.70,NULL),(318,'2023-07-22 16:14:32',1,'I',26.04,NULL),(319,'2023-07-22 16:23:45',1,'I',39.38,NULL),(320,'2023-07-22 16:24:02',1,'I',39.38,NULL),(321,'2023-07-22 16:24:41',1,'I',39.38,NULL),(322,'2023-07-22 16:28:24',1,'I',39.38,NULL),(323,'2023-07-24 09:07:27',1,'I',26.04,NULL),(324,'2023-07-24 09:08:51',1,'I',26.04,NULL),(325,'2023-07-24 09:11:08',1,'I',0.00,NULL),(328,'2023-07-24 09:16:12',1,'I',12.70,NULL),(331,'2023-07-24 09:20:35',1,'P',29.34,'via roma 2'),(332,'2023-07-24 09:21:05',2,'P',29.08,'via casa mia'),(333,'2023-07-24 09:21:08',1,'P',12.70,'via a'),(334,'2023-07-24 09:21:21',1,'I',7.00,NULL),(335,'2023-07-24 09:21:42',1,'I',0.00,NULL),(336,'2023-07-24 09:25:41',1,'I',40.02,NULL),(337,'2023-07-24 09:39:08',1,'I',NULL,NULL),(338,'2023-07-24 09:39:16',1,'I',NULL,NULL),(339,'2023-07-24 09:39:40',1,'I',NULL,NULL),(340,'2023-07-24 09:39:44',1,'I',NULL,NULL),(341,'2023-07-24 09:40:21',1,'I',NULL,NULL),(342,'2023-07-24 09:53:34',1,'I',42.00,NULL),(344,'2023-07-24 09:55:34',2,'P',25.40,'via pippo 2'),(346,'2023-07-24 09:57:54',1,'P',63.50,'roma'),(347,'2023-07-24 10:00:12',1,'I',NULL,NULL),(350,'2023-07-24 10:07:19',2,'I',9.99,NULL),(351,'2023-07-24 10:11:05',1,'I',NULL,NULL),(353,'2023-07-24 10:12:14',1,'I',26.68,NULL),(355,'2023-07-24 10:14:03',1,'I',25.40,NULL),(356,'2023-07-24 10:17:09',1,'I',14.54,NULL),(357,'2023-07-24 10:17:30',1,'I',NULL,NULL),(359,'2023-07-24 10:18:03',1,'I',67.82,NULL),(360,'2023-07-24 10:19:40',1,'I',NULL,NULL),(363,'2023-07-24 10:21:53',1,'I',1160.58,NULL),(364,'2023-07-24 10:22:20',1,'I',NULL,NULL),(365,'2023-07-24 10:22:36',1,'I',48.09,NULL),(366,'2023-07-24 10:22:53',2,'P',26.68,'via susa'),(367,'2023-07-24 10:24:53',2,'P',27.24,'via pippo 2'),(368,'2023-07-24 10:25:59',1,'I',62.56,NULL),(369,'2023-07-24 10:36:21',1,'I',NULL,NULL),(370,'2023-07-24 10:36:44',1,'I',9.50,NULL),(371,'2023-07-24 10:57:38',2,'I',NULL,NULL),(372,'2023-07-24 10:58:57',1,'I',7.00,NULL),(373,'2023-07-24 11:27:21',1,'I',NULL,NULL),(374,'2023-07-24 11:27:24',1,'P',63.14,'Via Roma 3'),(375,'2023-07-24 11:28:21',1,'P',26.04,'d'),(377,'2023-07-24 11:30:44',1,'I',9.99,NULL),(378,'2023-07-24 11:34:25',2,'I',26.68,NULL),(379,'2023-07-24 11:39:02',2,'P',26.68,'via casa'),(380,'2023-07-24 11:43:23',1,'I',NULL,NULL),(381,'2023-07-24 11:57:08',2,'I',NULL,NULL),(382,'2023-07-24 12:24:04',1,'P',27.88,'a'),(384,'2023-07-24 12:50:24',1,'P',52.08,'via roma'),(385,'2023-07-24 14:30:44',6,'P',26.68,'via pippo 2'),(386,'2023-07-24 14:31:45',6,'I',41.22,NULL),(387,'2023-07-24 17:09:10',1,'P',262.96,'via roma'),(391,'2023-07-24 17:48:46',2,'I',25.40,NULL),(392,'2023-07-24 17:49:53',1,'I',NULL,NULL),(393,'2023-07-24 17:51:25',1,'I',12.70,NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'luca@gmail.com','12345','Luca','Verdi','2023-07-14 15:42:47'),(2,'pippo.rossi@gmail.com','123456','pippo','rossi','2023-07-17 11:25:13'),(3,'paolo@gmail.com','12345','Paolo','Rossi','2023-07-18 17:26:17'),(4,'anto@gmail.com','123','Antonio','Rossi','2023-07-20 15:25:07'),(5,'dsa','21','sd','sd','2023-07-24 10:50:25'),(6,'pino.rossi@gmail.com','123456','pino','rossi','2023-07-24 14:30:37'),(7,'antonio@gmail.com','12345','antonio','Gialli','2023-08-09 09:54:32'),(9,'antonio3@gmail.com','12345','antonio3','Gialli3','2023-08-09 10:21:16'),(10,'pippo@pluto.com','4567','Pippo','Pluto','2023-08-09 10:30:30'),(11,'pippo@prova.com','45678','Pippo','Prova','2023-08-09 10:40:09'),(12,'gigioUpdate@prova.com','45678Update','gigioUpdate','ProvaUpdate','2023-07-14 15:42:47'),(13,'gigio2@prova.com','456782','gigioUpdate22','ProvaUpdate22','2023-07-14 15:42:47');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-17 10:20:40
CREATE DATABASE  IF NOT EXISTS `gruppo2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gruppo2`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: bookstore.cvzu4xrxvkdz.eu-south-1.rds.amazonaws.com    Database: gruppo2
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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `attempts`
--

DROP TABLE IF EXISTS `attempts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attempts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `attempts` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attempts`
--

LOCK TABLES `attempts` WRITE;
/*!40000 ALTER TABLE `attempts` DISABLE KEYS */;
/*!40000 ALTER TABLE `attempts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `account_non_locked` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`username`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('user1','user1',0),('user2','user2',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-17 10:20:40
CREATE DATABASE  IF NOT EXISTS `gruppo1` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gruppo1`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: bookstore.cvzu4xrxvkdz.eu-south-1.rds.amazonaws.com    Database: gruppo1
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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `attempts`
--

DROP TABLE IF EXISTS `attempts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attempts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `attempts` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attempts`
--

LOCK TABLES `attempts` WRITE;
/*!40000 ALTER TABLE `attempts` DISABLE KEYS */;
INSERT INTO `attempts` VALUES (3,'ale123',4),(4,'Keinny',5),(5,'Giovanni',2),(6,'John',4);
/*!40000 ALTER TABLE `attempts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `account_non_locked` tinyint DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Giovanni','Pass1234',1),(2,'Sofia','Password123',NULL),(3,'Martina','SecurePwd456',NULL),(4,'Alessandro','MySecretPwd',NULL),(5,'Giorgia','P@ssw0rd!',NULL),(6,'Leonardo','RandomPwd789',NULL),(7,'Valentina','SecurePwd2023',NULL),(8,'John','john77',0),(10,'Ilaria','lilypuppy123:)',NULL),(11,'Keinny','keinny',0),(12,'Don Jose','josesito',NULL),(13,'ale123','ale',0),(14,'paolo','$2a$10$vyKqcXm72Kdo8tq7myG7KO/d7P2xBQ3kGfr4Wjy.nToaodbX72eHW',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-17 10:20:41
