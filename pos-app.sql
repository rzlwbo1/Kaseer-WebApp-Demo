-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: pos-app
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_t8o6pivur7nn124jehx7cygw5` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'2023-08-25 13:12:47.400067','Brownies','2023-08-25 13:12:47.400067'),(2,'2023-08-25 13:12:51.886744','Bolu','2023-08-25 13:12:51.886744'),(3,'2023-08-25 13:12:57.467938','Cake','2023-08-25 13:12:57.467938'),(4,'2023-09-17 22:18:52.931144','Dessert','2023-09-17 22:18:52.930142'),(9,'2023-10-17 22:13:45.521838','Roti','2023-10-17 22:13:45.521838'),(10,'2023-10-17 22:13:54.710732','Hampers','2023-10-17 22:13:54.710732');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'2023-08-25 13:27:49.533286','aku'),(2,'2023-08-25 13:28:12.819612','dias'),(3,'2023-08-25 21:19:05.686641','kamu'),(4,'2023-08-25 21:21:12.142910','rizal'),(5,'2023-08-25 21:58:33.404483','rizal'),(6,'2023-08-25 22:01:05.328606','rizal'),(10,'2023-08-25 22:09:20.356585','rizal2'),(11,'2023-08-25 22:11:32.216668','rizal2'),(12,'2023-08-25 22:17:54.686106','rizal2222'),(13,'2023-08-25 22:39:00.381040','rizal2222'),(14,'2023-09-02 18:24:38.769435','rizalss'),(15,'2023-09-03 16:16:41.611342','Chindy'),(16,'2023-09-03 17:10:03.999741','Lia'),(17,'2023-09-03 17:36:26.727472','testt'),(18,'2023-09-03 17:48:37.799431','testt'),(19,'2023-09-03 17:52:05.752499','Okta'),(20,'2023-09-03 22:58:15.484352','rizal'),(21,'2023-09-09 23:22:33.117293','Monica'),(22,'2023-10-17 21:41:08.252372','Rapli'),(23,'2023-10-17 22:10:45.292948','Alya'),(24,'2023-10-19 21:51:08.323112','rizal'),(25,'2023-10-19 22:04:07.995955','rizal'),(26,'2023-10-19 22:07:14.611713','alya'),(27,'2023-11-25 16:08:59.271841','test');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menus`
--

DROP TABLE IF EXISTS `menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menus` (
  `id` int NOT NULL AUTO_INCREMENT,
  `is_active` bit(1) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ah4lwmygvwoa5g6f1pnkraksc` (`product_id`),
  CONSTRAINT `FK9fy52rm98bay0xyqw62u0n6n7` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menus`
--

LOCK TABLES `menus` WRITE;
/*!40000 ALTER TABLE `menus` DISABLE KEYS */;
INSERT INTO `menus` VALUES (1,_binary '\0',50000,3),(3,_binary '',35000,2),(4,_binary '',30000,4),(14,_binary '',6000,26),(15,_binary '',25000,1);
/*!40000 ALTER TABLE `menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_date` datetime(6) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `product` varchar(255) DEFAULT NULL,
  `qty` int DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `tax` double DEFAULT NULL,
  `order_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjyu2qbqt8gnvno9oe9j2s2ldk` (`order_id`),
  KEY `FK4q98utpd73imf4yhttm3w0eax` (`product_id`),
  CONSTRAINT `FK4q98utpd73imf4yhttm3w0eax` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKjyu2qbqt8gnvno9oe9j2s2ldk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` VALUES (1,'2023-08-25 13:27:49.611283',20000,'Brownies Kukus',1,20000,500,1,1),(2,'2023-08-25 13:27:49.809651',20000,'Brownies Panggang',1,20000,500,1,2),(3,'2023-08-25 13:28:12.826614',20000,'Brownies Kukus',1,20000,500,2,1),(4,'2023-08-25 13:28:12.843611',20000,'Brownies Panggang',1,20000,500,2,2),(5,'2023-08-25 21:19:05.743346',50000,'Cake Kustom',2,100000,500,3,3),(6,'2023-08-25 21:21:12.151487',50000,'Cake Kustom',1,50000,500,4,3),(12,'2023-08-25 22:09:20.484612',50000,'Cake Kustom',1,50500,500,10,3),(13,'2023-08-25 22:11:32.224664',50000,'Cake Kustom',1,50500,500,11,3),(14,'2023-08-25 22:17:54.741113',50000,'Cake Kustom',1,50500,500,12,3),(15,'2023-08-25 22:39:00.389018',50000,'Cake Kustom',1,50500,500,13,3),(16,'2023-09-02 18:24:38.815688',35000,'Chiffon',1,35500,500,14,4),(17,'2023-09-03 16:16:41.781674',35000,'Chiffon',1,35500,500,15,4),(18,'2023-09-03 17:10:04.067417',20000,'Brownies Kukus',1,20500,500,16,1),(19,'2023-09-03 17:10:04.134235',10000,'Cake Kustom',1,10500,500,16,3),(20,'2023-09-03 17:36:52.536699',20000,'Cake Kustom',2,41000,500,17,3),(21,'2023-09-03 17:48:51.461623',20000,'Cake Kustom',2,21000,500,18,3),(22,'2023-09-03 17:52:29.499325',35000,'Brownies Panggang',1,35500,500,19,2),(23,'2023-09-03 17:52:30.845963',40000,'Brownies Kukus',2,41000,500,19,1),(24,'2023-09-03 22:58:15.536854',105000,'Chiffon',3,106500,500,20,4),(25,'2023-09-09 23:22:33.215621',175000,'Brownies Panggang',5,177500,500,21,2),(26,'2023-09-09 23:22:33.432620',175000,'Chiffon',5,177500,500,21,4),(27,'2023-10-17 21:41:08.365532',12000,'Donat Labu',2,13000,500,22,26),(28,'2023-10-17 22:10:45.376232',35000,'Brownies Panggang',1,35500,500,23,2),(29,'2023-10-17 22:10:45.511143',30000,'Chiffon',1,30500,500,23,4),(30,'2023-10-17 22:10:45.539461',6000,'Donat Labu',1,6500,500,23,26),(31,'2023-10-19 21:51:08.459240',36000,'Donat Labu',6,39000,500,24,26),(32,'2023-10-19 22:04:08.011718',6000,'Donat Labu',1,6500,500,25,26),(33,'2023-10-19 22:07:14.619762',50000,'Cake Kustom',1,50500,500,26,3),(34,'2023-11-25 16:08:59.348726',12000,'Donat Labu',2,13000,500,27,26);
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `order_date` datetime(6) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_astys1dv61mdlp0n0wx0574r2` (`customer_id`),
  CONSTRAINT `FKpxtb8awmi0dk6smoh2vp1litg` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,100000,'2023-08-25 13:27:49.599297',40000,1),(2,100000,'2023-08-25 13:28:12.822611',40000,2),(3,100000,'2023-08-25 21:19:05.735234',50000,3),(4,50000,'2023-08-25 21:21:12.142910',50000,4),(10,50000,'2023-08-25 22:09:20.476609',50000,10),(11,100000,'2023-08-25 22:11:32.224664',50000,11),(12,100000,'2023-08-25 22:17:54.733097',50000,12),(13,10000,'2023-08-25 22:39:00.389018',50000,13),(14,50000,'2023-09-02 18:24:38.812685',35000,14),(15,40000,'2023-09-03 16:16:41.771995',35000,15),(16,32000,'2023-09-03 17:10:04.050699',30000,16),(17,25000,'2023-09-03 17:36:52.502411',21000,17),(18,30000,'2023-09-03 17:48:51.422166',21000,18),(19,80000,'2023-09-03 17:52:29.490322',76500,19),(20,110000,'2023-09-03 22:58:15.530857',106500,20),(21,400000,'2023-09-09 23:22:33.198888',355000,21),(22,15000,'2023-10-17 21:41:08.354950',13000,22),(23,73000,'2023-10-17 22:10:45.361645',72500,23),(24,40000,'2023-10-19 21:51:08.443220',39000,24),(25,7000,'2023-10-19 22:04:08.003694',6500,25),(26,50500,'2023-10-19 22:07:14.611713',50500,26),(27,15000,'2023-11-25 16:08:59.339190',13000,27);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `path_gambar` varchar(255) DEFAULT NULL,
  `stok` int DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o61fmio5yukmmiqgnxf8pnavn` (`name`),
  KEY `FKog2rp4qthbtt2lfyhfo32lsw9` (`category_id`),
  CONSTRAINT `FKog2rp4qthbtt2lfyhfo32lsw9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'2023-08-25 13:13:29.150743','enak dan lezat','Brownies Kukus','brownies.jpg',10,'2023-08-25 13:13:29.150743',1),(2,'2023-08-25 13:13:35.327396','enak dan lezat','Brownies Panggang','brownies.jpg',4,'2023-08-25 13:13:35.327396',1),(3,'2023-08-25 13:13:49.830547','enak dan lezat','Cake Kustom','cake.jpg',9,'2023-08-25 13:13:49.830547',3),(4,'2023-08-27 16:38:44.298190','Chiffon Lembut keju','Chiffon','chiffon.jpg',4,'2023-08-27 16:38:44.298190',2),(26,'2023-09-19 21:47:09.540052','Donat Labu Kuning','Donat Labu','donut_enak.jpg',38,'2023-09-23 21:38:38.017169',4),(28,'2023-10-03 22:09:32.227665','Sample prod','sample','cake.jpg',5,'2023-10-03 22:09:32.227665',2);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'pos-app'
--
/*!50003 DROP PROCEDURE IF EXISTS `spGetAllHistoryOrders` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetAllHistoryOrders`()
begin
	
	select o.id,
	od.product,
	od.qty,
	c.name,
	od.order_date
	from order_details od inner join orders o on od.order_id = o.id
	inner join customers c on o.customer_id = c.id ;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `spGetAllMenus` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetAllMenus`()
begin
	
	select 
	m.id,
	p.name,
	p.description,
	p.path_gambar,
	p.stok,
	m.price,
	m.is_active,
	m.product_id
	from menus m inner join products p on m.product_id = p.id;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `spGetAllMenusActive` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetAllMenusActive`()
begin
	
	select 
	m.id,
	p.name,
	p.description,
	p.path_gambar,
	p.stok,
	m.price,
	m.is_active,
	m.product_id
	from menus m inner join products p on m.product_id = p.id
	where m.is_active = '1'
	;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-25 16:21:04
