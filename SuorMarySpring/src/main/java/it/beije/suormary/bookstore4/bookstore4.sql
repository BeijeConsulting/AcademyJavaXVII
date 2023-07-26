-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: bookstore.cvzu4xrxvkdz.eu-south-1.rds.amazonaws.com    Database: bookstore4
-- ------------------------------------------------------
-- Server version	8.0.33

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1,'J.K.','Rowling','British author and philanthropist'),(2,'Isabel','Allende','The world\'s most widely read Spanish-language author'),(3,'George','Orwell','English novelist, essayist, journalist, and critic'),(4,'Oscar','Wilde','Flamboyant and sparklingly witty Anglo-Irish playwright, poet and critic'),(5,'Lev','Tolstoy','Master of realistic fiction and one of the world\'s greatest novelists'),(6,'Jane','Austen','English writer who first gave the novel its distinctly modern character through her treatment of ordinary people in everyday life'),(10,'Alessandro','Manzoni','Italian poet, novelist and philosopher'),(11,'Gianluca','Gotto','Ispirazione di vita!!');
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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `baskets`
--

LOCK TABLES `baskets` WRITE;
/*!40000 ALTER TABLE `baskets` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Harry Potter and the Philosopher\'s Stone','Fantasy novel',1,'Bloomsbury',20.00,5),(2,'Harry Potter and the Chamber of Secrets','Fantasy novel',1,'Bloomsbury',15.00,13),(3,'Harry Potter and the Prisoner of Azkaban','Fantasy novel',1,'Bloomsbury',18.00,13),(4,'Harry Potter and the Goblet of Fire','Fantasy novel',1,'Bloomsbury',21.00,18),(5,'Harry Potter and the Order of the Phoenix','Fantasy novel',1,'Bloomsbury',22.00,5),(6,'Harry Potter and the Half-Blood Prince','Fantasy novel',1,'Bloomsbury',27.00,20),(7,'Harry Potter and the Deathly Hallows','Fantasy novel',1,'Bloomsbury',28.00,29),(8,'The House of the Spirits','Clara del Valle, has paranormal powers and keeps a detailed diary of her life',2,'Plaza & Janés',18.00,15),(9,'City of the Beasts','Story of Alexander Cold, who is 15 years old and going through a family crisis',2,'Sudamericana',17.00,14),(10,'Eva Luna','Eva Luna takes us into the life of the eponymous protagonist, an orphan who grows up in an unidentified country in South America',2,'Editorial Oveja Negra',12.00,7),(11,'\rIsland Beneath the Sea','Zarité (known as Tété) is the daughter of an African mother she never knew and one of the white sailors who brought her into bondage',2,'Sudamericana',19.00,20),(23,'Animal farm','A group of anthropomorphic farm animals who rebel against their human farmer',3,'Secker & Warburg',50.00,2),(24,'1984','Dystopic novel',3,'HarperCollins',16.00,2),(25,'The picture of Dorian Grey',' A young man named Dorian Gray who has a portrait painted of himself',4,'Simon & Schuster',12.50,19),(26,'War and Peace','broadly focuses on Napoleon\'s invasion of Russia in 1812 and follows three of the most well-known characters in literature',5,'Simon & Schuster',30.00,2),(29,'Pride and Prejudice','The turbulent relationship between Elizabeth Bennet, the daughter of a country gentleman, and Fitzwilliam Darcy, a rich aristocratic landowner',6,'T. Egerton, Whitehall',10.50,0),(30,'Promessi sposi','Italian historical novel',10,'Antonio Fortunato Stella',19.00,2),(31,'L\'importanza di chiamarsi Ernesto','opera teatrale',4,'Rusconi',25.50,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (7,14,24,1,16.00),(8,14,11,2,19.00),(9,14,23,1,50.00),(10,15,24,1,16.00),(11,16,11,2,19.00),(12,16,30,1,20.50),(13,16,29,1,10.50),(14,17,7,1,28.00),(15,17,11,1,19.00),(16,17,24,1,16.00),(17,18,7,1,28.00),(18,18,1,1,15.00),(19,18,9,1,17.00),(20,19,1,1,15.00),(22,21,1,1,15.00),(23,21,26,1,30.00),(24,22,1,1,20.00);
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (13,'2023-07-19 15:35:37',1,'C',104.00,'via sconosciuta'),(14,'2023-07-19 15:37:51',1,'I',104.00,'via sconosciuta'),(15,'2023-07-19 15:57:28',1,'C',104.00,'Via saraàgiustononloso'),(16,'2023-07-19 16:04:04',1,'I',69.00,'Via saraàgiustononloso'),(17,'2023-07-19 16:38:15',1,'P',63.00,'Via prova delle 16:38'),(18,'2023-07-19 17:04:57',5,'C',60.00,'via della disperazione 3'),(19,'2023-07-24 11:11:07',1,'C',45.00,'prova delle 11:11'),(21,'2023-07-24 11:34:16',1,'C',45.00,'prova delle 11:34'),(22,'2023-07-25 09:14:14',1,'C',20.00,'prova delle 09:13');
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'alice.ceccarelli@gmail.com','00000','Alice','Ceccarelli','2023-07-14 15:23:00'),(5,'chiara.iannetta@gmail.com','11111','Chiara','Iannetta','2023-07-17 15:32:12'),(7,'prova@gmail.com','prova','Prova','Provaprova','2023-07-24 14:08:07');
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

-- Dump completed on 2023-07-26 12:46:54
