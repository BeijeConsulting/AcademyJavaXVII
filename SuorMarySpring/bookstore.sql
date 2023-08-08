-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: bookstore.cvzu4xrxvkdz.eu-south-1.rds.amazonaws.com    Database: bookstore3
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1,'Dante','Alighieri','Poeta, scrittore, filosofo e politico italiano nato a Firenze. È famoso soprattutto per essere l\'autore de \"La Divina Commedia\", un poema epico che è considerato uno dei capolavori della letteratura mondiale. Dante fu una figura di spicco nella letteratura e nella politica del suo tempo, e il suo lavoro ebbe un impatto significativo sulla cultura italiana e oltre.'),(2,'George','Orwell','George Orwell, pseudonimo di Eric Arthur Blair, è stato uno scrittore e saggista britannico. È noto per il suo romanzo distopico \"1984\" e per il suo impegno politico e sociale che ha influenzato profondamente il panorama letterario del XX secolo.'),(3,'Jane','Austen','Jane Austen è stata una scrittrice inglese nota per i suoi romanzi che ritraggono la vita della classe media inglese del periodo regency. I suoi romanzi più famosi includono \"Orgoglio e pregiudizio\" e \"Ragione e sentimento'),(4,'J.R.R.','Tolkien','John Ronald Reuel Tolkien è stato uno scrittore e professore britannico. È famoso per il suo lavoro nel campo della fantasy, in particolare per il suo romanzo epico \"Il signore degli anelli\" e il suo prequel \"Lo Hobbit\".'),(5,'George R.R.','Martin','George Raymond Richard Martin è uno scrittore americano di fantasy e fantascienza. È noto per la sua serie di romanzi \"Cronache del ghiaccio e del fuoco\", che ha ispirato la popolare serie televisiva \"Game of Thrones\".'),(6,'F. Scott','Fitzgerald','Francis Scott Fitzgerald è stato uno scrittore statunitense noto per i suoi romanzi che rappresentano l\'era del jazz negli anni \'20. Il suo romanzo più famoso è \"Il grande Gatsby\", considerato un classico della letteratura americana.'),(7,'J.K.','Rowling','Joanne Rowling, conosciuta come J.K. Rowling, è una scrittrice britannica famosa per la serie di libri su Harry Potter. I suoi romanzi hanno conquistato un\'enorme popolarità e hanno ispirato una serie di film di successo.'),(8,'Herman','Melville','Herman Melville è stato uno scrittore e poeta americano del XIX secolo. È famoso per il suo romanzo \"Moby Dick\", considerato uno dei capolavori della letteratura americana.'),(9,'Antoine','de Saint-Exupéry','Antoine de Saint-Exupéry è stato uno scrittore e aviatore francese. Il suo libro più celebre, \"Il Piccolo Principe\", è una fiaba filosofica amata da lettori di tutte le età.'),(10,'Harper','Lee','Nelle Harper Lee, è stata una scrittrice statunitense. È conosciuta principalmente per il suo unico romanzo pubblicato, \"To Kill a Mockingbird\" (in italiano \"Il buio oltre la siepe\"), pubblicato nel 1960. Il libro, ambientato nel profondo sud degli Stati Uniti durante la Grande Depressione, affronta temi importanti come il razzismo, l\'ingiustizia sociale e l\'infanzia.'),(14,'Alessandro','Baricco','Scrittore, saggista e drammaturgo italiano contemporaneo. Nato il 25 gennaio 1958 a Torino, è uno degli autori più celebri e amati della letteratura italiana contemporanea. Noto per il suo stile di scrittura elegante e poetico, che spazia tra vari generi letterari. Ha scritto romanzi, racconti, opere teatrali e saggi, dimostrando una grande versatilità e creatività nel suo lavoro.');
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Divina Commedia','Nel poema, Dante racconta il suo viaggio attraverso l\'aldilà guidato dal poeta romano Virgilio. Dante rappresenta se stesso come protagonista e narra il suo viaggio attraverso gli inferi, il monte del Purgatorio e i cieli celesti. Lungo il percorso, egli incontra anime di peccatori e santi, ognuno dei quali ha una storia da raccontare e un destino diverso.',1,'Mondadori',13.34,4),(7,'1984','Ambientato in un futuro totalitario, il libro descrive la vita di Winston Smith, un uomo che vive in un regime oppressivo noto come Oceania. Il governo controlla ogni aspetto della vita delle persone, manipolando la storia, limitando le libertà personali e sorvegliando costantemente i cittadini attraverso il Grande Fratello. Il romanzo esplora temi come la manipolazione della verità, la perdita dell\'individualità e la resistenza contro l\'oppressione.',10,'Secker & Warburg',12.70,12),(8,'Il signore degli anelli','Ambientata in un mondo immaginario chiamato Terra di Mezzo, la storia segue un gruppo di personaggi, tra cui hobbit, elfi, nani, uomini e altre creature fantastiche, nella loro lotta contro il Signore Oscuro Sauron. Il romanzo segue il percorso di Frodo Baggins, un hobbit incaricato di distruggere l\'Unico Anello, un potente artefatto che può dare a Sauron il controllo assoluto sul mondo.',3,'Allen & Unwin',14.54,12),(9,'Orgoglio e pregiudizio','Ambientato nella società inglese del XIX secolo, il libro racconta la storia di Elizabeth Bennet, una giovane donna intelligente e vivace, e del suo rapporto con il ricco e orgoglioso signor Darcy. Il romanzo esplora temi come l\'amore, l\'orgoglio, i pregiudizi sociali e la ricerca della felicità personale.',3,'Thomas Egerton',9.99,22),(10,'Cronache del ghiaccio e del fuoco','Ambientato in un mondo immaginario medievale, il ciclo narra la storia di potenti famiglie nobiliari che lottano per il controllo del Trono di Spade. La serie è caratterizzata da una trama intricata, personaggi complessi e un\'ambientazione dettagliata. La serie è composta da diversi libri, il primo dei quali è \"Il gioco del trono\".',5,'Bantam Books',14.80,28),(11,'Il grande Gatsby','Ambientato negli anni \'20, il libro racconta la storia di Jay Gatsby, un misterioso e affascinante milionario che organizza sontuose feste nella speranza di riconquistare il cuore della donna che ama. Il romanzo esplora temi come il sogno americano, la ricchezza, l\'amore e la corruzione della società dell\'epoca.',6,'Charles Scribner\'s Sons',9.50,19),(12,'Harry Potter e la pietra filosofale','Primo libro della serie di Harry Potter scritta da J.K. Rowling. Il libro introduce il giovane mago Harry Potter e il suo ingresso nella Scuola di Magia e Stregoneria di Hogwarts. La storia segue le avventure di Harry mentre scopre il suo retaggio magico, affronta il malevolo Signore Oscuro Voldemort e impara il valore dell\'amicizia e del coraggio.',7,'Bloomsbury',13.50,11),(13,'To Kill a Mockingbird','Ambientato nel profondo sud degli Stati Uniti negli anni \'30, il libro racconta la storia di Scout Finch, una giovane ragazza che osserva suo padre, l\'avvocato Atticus Finch, difendere un uomo di colore ingiustamente accusato di stupro. Il romanzo affronta temi di razzismo, giustizia e crescita personale.',10,'J.B. Lippincott & Co',11.99,12),(14,'Lo Hobbit','Il libro narra le avventure di Bilbo Baggins, un hobbit tranquillo che si ritrova coinvolto in una missione epica per recuperare un tesoro custodito da un drago. Ambientato nello stesso mondo della trilogia de \"Il Signore degli Anelli\", \"Lo Hobbit\" è una storia avventurosa, ricca di creature fantasy, incontri pericolosi e la scoperta del coraggio nascosto.',4,'Allen & Unwin',24.99,10),(15,'Moby Dick','Il libro racconta la storia del capitano Ahab e la sua ossessione per la caccia a un enorme capodoglio bianco chiamato Moby Dick. Il romanzo esplora temi come la follia, la vendetta e la lotta dell\'uomo contro le forze incontrollabili della natura.',8,'Richard Bentley',9.99,14),(16,'Il Piccolo Principe',' È una fiaba poetica che racconta l\'incontro di un aviatore bloccato nel deserto con un misterioso principe proveniente da un altro pianeta. Il libro affronta temi come l\'amicizia, l\'innocenza, l\'amore e il significato della vita attraverso le lezioni impartite dal piccolo principe.',9,'Éditions Gallimard',7.00,3),(27,'Novecento','Monologo teatrale ambientato su una nave transatlantica nel primo Novecento e viene raccontata dal protagonista e narratore, Tim Tooney. Il protagonista racconta la straordinaria storia di un uomo di nome Novecento, un musicista dotato di un talento eccezionale per il pianoforte.',14,'Feltrinelli',6.00,13);
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
INSERT INTO `orders` VALUES (28,'2023-07-17 09:39:18',1,'I',0.00,NULL),(29,'2023-07-17 10:21:45',1,'I',294.30,NULL),(30,'2023-07-17 10:32:19',1,'I',0.00,NULL),(31,'2023-07-17 10:36:45',1,'I',0.00,NULL),(32,'2023-07-17 10:37:12',1,'I',26.04,NULL),(33,'2023-07-17 10:38:31',1,'I',0.00,NULL),(34,'2023-07-17 10:39:36',1,'I',0.00,NULL),(35,'2023-07-17 10:39:49',1,'I',0.00,NULL),(36,'2023-07-17 10:40:19',1,'I',0.00,NULL),(37,'2023-07-17 10:43:52',1,'I',123.66,NULL),(39,'2023-07-17 10:57:27',1,'I',0.00,NULL),(41,'2023-07-17 11:00:33',1,'I',0.00,NULL),(46,'2023-07-17 11:39:59',2,'P',71.42,'via teodoro 2'),(49,'2023-07-17 12:16:12',2,'P',38.74,'via torino 1'),(50,'2023-07-17 12:22:16',2,'P',38.74,NULL),(51,'2023-07-17 12:34:12',1,'I',39.38,NULL),(52,'2023-07-17 12:45:42',1,'I',65.42,NULL),(75,'2023-07-17 15:10:21',1,'I',54.00,NULL),(76,'2023-07-17 15:13:30',1,'I',54.00,NULL),(77,'2023-07-17 15:15:36',2,'P',77.48,'via battisti 2'),(78,'2023-07-17 15:18:23',1,'I',0.00,NULL),(79,'2023-07-17 15:19:34',2,'P',70.50,'via luigi VI 3'),(80,'2023-07-17 15:21:15',1,'I',54.00,NULL),(81,'2023-07-17 15:28:16',1,'I',65.42,NULL),(83,'2023-07-17 15:32:56',1,'I',0.00,NULL),(84,'2023-07-17 15:33:13',1,'I',13.34,NULL),(85,'2023-07-17 15:36:25',1,'I',249.90,NULL),(86,'2023-07-17 15:42:24',1,'I',54.00,NULL),(87,'2023-07-17 15:44:50',1,'I',81.00,NULL),(88,'2023-07-17 15:45:59',2,'P',9.50,'via prova'),(89,'2023-07-17 15:47:49',2,'P',67.50,'via torino 4'),(90,'2023-07-17 15:48:26',1,'I',14.80,NULL),(91,'2023-07-17 16:32:54',1,'I',65.42,NULL),(92,'2023-07-17 16:34:53',1,'I',64.78,NULL),(93,'2023-07-17 16:36:07',1,'P',70.50,'a'),(94,'2023-07-17 16:37:16',1,'P',24.99,'a'),(95,'2023-07-17 16:37:41',1,'I',65.42,NULL),(97,'2023-07-17 16:44:44',1,'I',65.42,NULL),(100,'2023-07-17 16:53:52',1,'I',0.00,NULL),(102,'2023-07-17 17:14:14',2,'P',70.50,'via como 1'),(103,'2023-07-17 17:36:17',2,'P',40.02,'via torino 1'),(104,'2023-07-17 17:45:49',2,'P',40.02,'via casa 12'),(119,'2023-07-18 17:31:23',1,'P',49.95,NULL),(120,'2023-07-18 17:32:35',1,'P',65.42,'Via cane 2'),(121,'2023-07-18 17:34:35',1,'I',0.00,NULL),(122,'2023-07-18 17:35:27',1,'P',72.40,'vvv'),(123,'2023-07-18 17:36:13',1,'I',0.00,NULL),(124,'2023-07-19 14:08:38',1,'I',0.00,NULL),(125,'2023-07-19 14:09:06',1,'I',0.00,NULL),(126,'2023-07-19 14:09:07',1,'I',0.00,NULL),(127,'2023-07-19 14:09:08',1,'I',0.00,NULL),(128,'2023-07-19 14:09:23',1,'I',0.00,NULL),(129,'2023-07-19 14:09:46',1,'I',0.00,NULL),(130,'2023-07-19 14:10:30',1,'I',0.00,NULL),(131,'2023-07-19 14:12:57',1,'I',0.00,NULL),(132,'2023-07-19 14:13:37',1,'I',0.00,NULL),(133,'2023-07-19 14:23:09',1,'I',0.00,NULL),(134,'2023-07-19 14:23:26',1,'I',0.00,NULL),(135,'2023-07-19 14:26:55',1,'I',0.00,NULL),(136,'2023-07-19 14:28:31',1,'I',0.00,NULL),(137,'2023-07-19 14:29:59',1,'I',0.00,NULL),(138,'2023-07-19 14:42:20',1,'I',0.00,NULL),(139,'2023-07-19 15:04:45',1,'I',132.10,NULL),(140,'2023-07-19 15:08:50',1,'I',108.60,NULL),(141,'2023-07-19 15:12:10',1,'I',90.76,NULL),(142,'2023-07-19 15:16:12',1,'I',82.50,NULL),(143,'2023-07-19 15:38:09',1,'I',0.00,NULL),(144,'2023-07-19 15:39:22',1,'I',0.00,NULL),(145,'2023-07-19 15:41:50',1,'I',0.00,NULL),(146,'2023-07-19 15:42:48',1,'I',13.34,NULL),(147,'2023-07-19 15:48:50',1,'I',48.60,NULL),(148,'2023-07-19 15:56:09',1,'I',13.34,NULL),(149,'2023-07-19 15:56:33',1,'I',40.68,NULL),(150,'2023-07-19 15:57:07',1,'I',0.00,NULL),(152,'2023-07-19 16:43:59',1,'I',0.00,NULL),(153,'2023-07-19 16:46:08',1,'I',36.84,NULL),(154,'2023-07-19 16:49:11',1,'I',22.84,NULL),(155,'2023-07-19 16:49:51',2,'P',40.50,'via pluto'),(156,'2023-07-19 16:55:35',1,'I',0.00,NULL),(158,'2023-07-19 16:56:30',1,'I',47.00,NULL),(159,'2023-07-19 17:08:50',1,'P',23.50,'null'),(161,'2023-07-19 17:20:31',2,'P',25.40,'via casa 1'),(162,'2023-07-19 17:23:01',2,'P',29.60,'address'),(163,'2023-07-19 17:24:05',2,'P',0.00,'address'),(164,'2023-07-19 17:29:43',1,'I',13.34,NULL),(165,'2023-07-19 17:30:45',2,'P',19.00,'null'),(167,'2023-07-19 17:50:14',2,'P',26.68,'via pippo 2'),(168,'2023-07-20 15:16:00',1,'P',12.70,'Via roma 2'),(171,'2023-07-20 16:39:42',2,'P',9.50,'via casa mia'),(172,'2023-07-20 17:40:06',2,'P',26.68,'via casa2'),(178,'2023-07-21 09:44:57',1,'I',13.34,NULL),(179,'2023-07-21 09:46:00',1,'I',13.34,NULL),(180,'2023-07-21 09:50:30',1,'I',13.34,NULL),(181,'2023-07-21 09:50:59',2,'P',27.00,'via casa 1'),(182,'2023-07-21 09:53:00',1,'I',9.99,NULL),(183,'2023-07-21 09:55:27',1,'I',13.34,NULL),(184,'2023-07-21 10:01:37',1,'I',13.34,NULL),(186,'2023-07-21 10:12:19',1,'I',13.34,NULL),(187,'2023-07-21 10:13:47',1,'I',13.34,NULL),(188,'2023-07-21 10:14:32',1,'P',13.34,'via roma 2'),(190,'2023-07-21 10:16:38',1,'P',13.34,'s'),(191,'2023-07-21 10:16:46',1,'P',23.50,'dsa'),(192,'2023-07-21 10:17:20',1,'I',12.70,NULL),(193,'2023-07-21 10:33:27',1,'I',13.34,NULL),(194,'2023-07-21 10:33:40',1,'I',13.34,NULL),(195,'2023-07-21 10:36:10',1,'I',NULL,NULL),(196,'2023-07-21 10:37:16',1,'I',13.34,NULL),(197,'2023-07-21 10:39:34',1,'I',13.34,NULL),(198,'2023-07-21 10:42:11',1,'I',NULL,NULL),(199,'2023-07-21 10:42:11',1,'I',49.37,NULL),(200,'2023-07-21 10:43:39',1,'I',9.99,NULL),(202,'2023-07-21 10:46:34',1,'I',0.00,NULL),(203,'2023-07-21 10:54:29',1,'I',26.04,NULL),(204,'2023-07-21 10:58:11',1,'I',13.34,NULL),(205,'2023-07-21 11:01:13',1,'I',0.00,NULL),(206,'2023-07-21 11:01:44',1,'I',12.70,NULL),(207,'2023-07-21 11:02:46',1,'I',NULL,NULL),(208,'2023-07-21 11:04:49',1,'I',26.04,NULL),(209,'2023-07-21 11:05:45',1,'I',26.04,NULL),(211,'2023-07-21 11:47:38',1,'I',13.34,NULL),(212,'2023-07-21 11:49:19',1,'I',13.34,NULL),(213,'2023-07-21 11:54:15',1,'I',13.34,NULL),(214,'2023-07-21 11:57:10',1,'I',13.34,NULL),(215,'2023-07-21 12:09:11',1,'I',13.34,NULL),(216,'2023-07-21 12:13:39',1,'I',13.34,NULL),(217,'2023-07-21 12:15:11',1,'I',12.70,NULL),(218,'2023-07-21 12:16:00',1,'I',72.70,NULL),(224,'2023-07-21 13:05:18',1,'I',12.70,NULL),(225,'2023-07-21 13:11:23',1,'I',9.99,NULL),(227,'2023-07-21 14:26:08',1,'I',9.99,NULL),(229,'2023-07-21 14:27:12',1,'I',9.99,NULL),(230,'2023-07-21 14:29:19',1,'I',59.36,NULL),(232,'2023-07-21 14:37:52',1,'I',26.68,NULL),(234,'2023-07-21 14:41:57',1,'I',NULL,NULL),(236,'2023-07-21 14:42:35',1,'I',13.34,NULL),(237,'2023-07-21 14:43:59',1,'I',13.34,NULL),(239,'2023-07-21 14:48:05',1,'I',26.68,NULL),(242,'2023-07-21 14:51:51',1,'I',26.68,NULL),(243,'2023-07-21 14:54:36',1,'I',26.68,NULL),(245,'2023-07-21 14:56:26',1,'I',26.68,NULL),(246,'2023-07-21 15:00:03',1,'I',26.68,NULL),(247,'2023-07-21 15:02:05',1,'I',26.68,NULL),(260,'2023-07-21 15:41:20',1,'P',140.34,'via roma 2'),(261,'2023-07-21 15:42:23',1,'I',NULL,NULL),(265,'2023-07-21 16:26:46',1,'I',160.08,NULL),(268,'2023-07-21 16:35:12',1,'I',29.08,NULL),(269,'2023-07-21 16:37:33',1,'I',12.70,NULL),(271,'2023-07-21 16:40:28',1,'I',25.40,NULL),(273,'2023-07-21 16:42:23',1,'I',12.70,NULL),(274,'2023-07-21 16:43:18',1,'P',25.40,'v'),(286,'2023-07-21 17:16:48',2,'P',34.24,'via pinna'),(287,'2023-07-21 17:16:56',1,'I',NULL,NULL),(289,'2023-07-21 17:17:31',1,'I',NULL,NULL),(290,'2023-07-21 17:17:54',1,'I',12.70,NULL),(292,'2023-07-21 17:18:26',1,'I',7.00,NULL),(294,'2023-07-21 17:21:40',1,'I',12.70,NULL),(295,'2023-07-21 17:22:26',1,'I',7.00,NULL),(300,'2023-07-21 17:25:35',1,'I',NULL,NULL),(306,'2023-07-21 17:44:32',1,'I',26.04,NULL),(316,'2023-07-22 16:07:07',1,'I',52.08,NULL),(317,'2023-07-22 16:14:15',1,'I',12.70,NULL),(318,'2023-07-22 16:14:32',1,'I',26.04,NULL),(319,'2023-07-22 16:23:45',1,'I',39.38,NULL),(320,'2023-07-22 16:24:02',1,'I',39.38,NULL),(321,'2023-07-22 16:24:41',1,'I',39.38,NULL),(322,'2023-07-22 16:28:24',1,'I',39.38,NULL),(323,'2023-07-24 09:07:27',1,'I',26.04,NULL),(324,'2023-07-24 09:08:51',1,'I',26.04,NULL),(325,'2023-07-24 09:11:08',1,'I',0.00,NULL),(328,'2023-07-24 09:16:12',1,'I',12.70,NULL),(331,'2023-07-24 09:20:35',1,'P',29.34,'via roma 2'),(332,'2023-07-24 09:21:05',2,'P',29.08,'via casa mia'),(333,'2023-07-24 09:21:08',1,'P',12.70,'via a'),(334,'2023-07-24 09:21:21',1,'I',7.00,NULL),(335,'2023-07-24 09:21:42',1,'I',0.00,NULL),(336,'2023-07-24 09:25:41',1,'I',40.02,NULL),(337,'2023-07-24 09:39:08',1,'I',NULL,NULL),(338,'2023-07-24 09:39:16',1,'I',NULL,NULL),(339,'2023-07-24 09:39:40',1,'I',NULL,NULL),(340,'2023-07-24 09:39:44',1,'I',NULL,NULL),(341,'2023-07-24 09:40:21',1,'I',NULL,NULL),(342,'2023-07-24 09:53:34',1,'I',42.00,NULL),(344,'2023-07-24 09:55:34',2,'P',25.40,'via pippo 2'),(346,'2023-07-24 09:57:54',1,'P',63.50,'roma'),(347,'2023-07-24 10:00:12',1,'I',NULL,NULL),(350,'2023-07-24 10:07:19',2,'I',9.99,NULL),(351,'2023-07-24 10:11:05',1,'I',NULL,NULL),(353,'2023-07-24 10:12:14',1,'I',26.68,NULL),(355,'2023-07-24 10:14:03',1,'I',25.40,NULL),(356,'2023-07-24 10:17:09',1,'I',14.54,NULL),(357,'2023-07-24 10:17:30',1,'I',NULL,NULL),(359,'2023-07-24 10:18:03',1,'I',67.82,NULL),(360,'2023-07-24 10:19:40',1,'I',NULL,NULL),(363,'2023-07-24 10:21:53',1,'I',1160.58,NULL),(364,'2023-07-24 10:22:20',1,'I',NULL,NULL),(365,'2023-07-24 10:22:36',1,'I',48.09,NULL),(366,'2023-07-24 10:22:53',2,'P',26.68,'via susa'),(367,'2023-07-24 10:24:53',2,'P',27.24,'via pippo 2'),(368,'2023-07-24 10:25:59',1,'I',62.56,NULL),(369,'2023-07-24 10:36:21',1,'I',NULL,NULL),(370,'2023-07-24 10:36:44',1,'I',9.50,NULL),(371,'2023-07-24 10:57:38',2,'I',NULL,NULL),(372,'2023-07-24 10:58:57',1,'I',7.00,NULL),(373,'2023-07-24 11:27:21',1,'I',NULL,NULL),(374,'2023-07-24 11:27:24',1,'P',63.14,'Via Roma 3'),(375,'2023-07-24 11:28:21',1,'P',26.04,'d'),(377,'2023-07-24 11:30:44',1,'I',9.99,NULL),(378,'2023-07-24 11:34:25',2,'I',26.68,NULL),(379,'2023-07-24 11:39:02',2,'P',26.68,'via casa'),(380,'2023-07-24 11:43:23',1,'I',NULL,NULL),(381,'2023-07-24 11:57:08',2,'I',NULL,NULL),(382,'2023-07-24 12:24:04',1,'P',27.88,'a'),(384,'2023-07-24 12:50:24',1,'P',52.08,'via roma'),(385,'2023-07-24 14:30:44',6,'P',26.68,'via pippo 2'),(386,'2023-07-24 14:31:45',6,'I',41.22,NULL),(387,'2023-07-24 17:09:10',1,'P',262.96,'via roma'),(391,'2023-07-24 17:48:46',2,'I',25.40,NULL),(392,'2023-07-24 17:49:53',1,'I',NULL,NULL),(393,'2023-07-24 17:51:25',1,'I',12.70,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'luca@gmail.com','12345','Luca','Verdi','2023-07-14 15:42:47'),(2,'pippo.rossi@gmail.com','123456','pippo','rossi','2023-07-17 11:25:13'),(3,'paolo@gmail.com','12345','Paolo','Rossi','2023-07-18 17:26:17'),(4,'anto@gmail.com','123','Antonio','Rossi','2023-07-20 15:25:07'),(5,'dsa','21','sd','sd','2023-07-24 10:50:25'),(6,'pino.rossi@gmail.com','123456','pino','rossi','2023-07-24 14:30:37');
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

-- Dump completed on 2023-07-26 12:45:13
