-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: onlinestoremanager
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `bank_account`
--

DROP TABLE IF EXISTS `bank_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank_account` (
  `id` char(36) NOT NULL,
  `bank_name` varchar(255) NOT NULL,
  `number` varchar(255) DEFAULT NULL,
  `client_id` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr0ixjvuu73hil8v85idoi9h6c` (`client_id`),
  CONSTRAINT `FKr0ixjvuu73hil8v85idoi9h6c` FOREIGN KEY (`client_id`) REFERENCES `base_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_account`
--

LOCK TABLES `bank_account` WRITE;
/*!40000 ALTER TABLE `bank_account` DISABLE KEYS */;
INSERT INTO `bank_account` VALUES ('bffa15ef-4276-47b5-b712-14659f786305','RAIFAISEN',NULL,'55e1d923-065f-4e8a-9bcc-8b4388f488db');
/*!40000 ALTER TABLE `bank_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `base_user`
--

DROP TABLE IF EXISTS `base_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `base_user` (
  `dtype` varchar(31) NOT NULL,
  `id` char(36) NOT NULL,
  `active` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `role` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_b4hwa8i589s1em8fh6wn5gw4a` (`email`),
  UNIQUE KEY `UK_nt92p9abadrya41y29v851m51` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base_user`
--

LOCK TABLES `base_user` WRITE;
/*!40000 ALTER TABLE `base_user` DISABLE KEYS */;
INSERT INTO `base_user` VALUES ('Client','55e1d923-065f-4e8a-9bcc-8b4388f488db',_binary '','test@abv.bg','$2a$12$V3KURuO.D0CfD85Vu1TWu.y17tPaoMBaFRdbfeU0hq4ohLc5Y6vjO','client2',NULL),('User','56181df0-8919-4094-9642-40c9817fa4ea',_binary '','viktor@abv.bg','$2a$12$b6Wv/ust9v28ULtd1ZteH.AdHeU4NWnpUQ5NIGp3BJqGloMtE9jEy','viktor',0),('Client','58751fae-492a-4c0e-a23e-47217a8c9790',_binary '\0','jombloxx1@abv.bg','$2a$12$JYlqvQ0i15RqjDJOdNVPsO9fYB8inTtzlqE9smVNyTk.sEbQJOCXi','client',NULL),('Client','7addceb3-f24c-4f6c-9b43-0b4d1c9d54a0',_binary '','test1@abv.bg','$2a$12$/cH1A7c0cwDT1n1nM4h94.SmI7V/VeJiBK1PKRg43IRwLNgyDxS26','client3',NULL);
/*!40000 ALTER TABLE `base_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `base_user_cart`
--

DROP TABLE IF EXISTS `base_user_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `base_user_cart` (
  `client_id` char(36) NOT NULL,
  `cart_id` char(36) NOT NULL,
  PRIMARY KEY (`client_id`,`cart_id`),
  UNIQUE KEY `UK_daqx04e4gdf1ts2w0cacc19lx` (`cart_id`),
  CONSTRAINT `FK5onmfjv9wlqmn1ggclnwrcndy` FOREIGN KEY (`client_id`) REFERENCES `base_user` (`id`),
  CONSTRAINT `FKbda51l56medlmghyuevkw1ues` FOREIGN KEY (`cart_id`) REFERENCES `xref_clients_items` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base_user_cart`
--

LOCK TABLES `base_user_cart` WRITE;
/*!40000 ALTER TABLE `base_user_cart` DISABLE KEYS */;
INSERT INTO `base_user_cart` VALUES ('55e1d923-065f-4e8a-9bcc-8b4388f488db','b69271fd-5077-4af2-9980-243433648e6f');
/*!40000 ALTER TABLE `base_user_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `base_user_orders`
--

DROP TABLE IF EXISTS `base_user_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `base_user_orders` (
  `client_id` char(36) NOT NULL,
  `orders_id` char(36) NOT NULL,
  PRIMARY KEY (`client_id`,`orders_id`),
  UNIQUE KEY `UK_94ej2ia6wdxfxk4yf78rbamo7` (`orders_id`),
  CONSTRAINT `FK2qb425qemldjtja18ctq34jf0` FOREIGN KEY (`orders_id`) REFERENCES `order_user` (`id`),
  CONSTRAINT `FKqsk4143yka6f94fk03xtxn3rv` FOREIGN KEY (`client_id`) REFERENCES `base_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base_user_orders`
--

LOCK TABLES `base_user_orders` WRITE;
/*!40000 ALTER TABLE `base_user_orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_user_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `base_user_stores`
--

DROP TABLE IF EXISTS `base_user_stores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `base_user_stores` (
  `user_id` char(36) NOT NULL,
  `stores_id` char(36) NOT NULL,
  PRIMARY KEY (`user_id`,`stores_id`),
  UNIQUE KEY `UK_hdrowly3fyc3tdlag5gp8h24r` (`stores_id`),
  CONSTRAINT `FKc83ijayyncu71vrirwcs15mdw` FOREIGN KEY (`user_id`) REFERENCES `base_user` (`id`),
  CONSTRAINT `FKn8n1a5kti1bc4jl4dfxt2kh9o` FOREIGN KEY (`stores_id`) REFERENCES `store` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `base_user_stores`
--

LOCK TABLES `base_user_stores` WRITE;
/*!40000 ALTER TABLE `base_user_stores` DISABLE KEYS */;
/*!40000 ALTER TABLE `base_user_stores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_company`
--

DROP TABLE IF EXISTS `delivery_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery_company` (
  `id` char(36) NOT NULL,
  `delivery_fee` decimal(19,2) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_company`
--

LOCK TABLES `delivery_company` WRITE;
/*!40000 ALTER TABLE `delivery_company` DISABLE KEYS */;
INSERT INTO `delivery_company` VALUES ('28fb78cb-bbc9-41f0-87dd-74a9c96f7f97',5.00,'Speed');
/*!40000 ALTER TABLE `delivery_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `id` char(36) NOT NULL,
  `url` varchar(255) NOT NULL,
  `item_id` char(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKscew1f5bnpn1nuaokhjg89u58` (`item_id`),
  CONSTRAINT `FKscew1f5bnpn1nuaokhjg89u58` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES ('3f482b76-1480-41a0-b7ba-d1bfd3c13278','https://www.maxled-bg.com/Files/Products/702437DOUBLE-HEAD-DESK-LAPM-PANAN-ok.jpg','11e1658b-9284-4f6f-9f68-4dc577b2fa2d');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `id` char(36) NOT NULL,
  `category` int NOT NULL,
  `change_date` date DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `price` decimal(19,2) NOT NULL,
  `title` varchar(255) NOT NULL,
  `sale_id` char(36) DEFAULT NULL,
  `store_id` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKop40p6ufpw5ya6artpaf5sqjj` (`sale_id`),
  KEY `FKi0c87m5jy5qxw8orrf2pugs6h` (`store_id`),
  CONSTRAINT `FKi0c87m5jy5qxw8orrf2pugs6h` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`),
  CONSTRAINT `FKop40p6ufpw5ya6artpaf5sqjj` FOREIGN KEY (`sale_id`) REFERENCES `sale` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES ('11e1658b-9284-4f6f-9f68-4dc577b2fa2d',1,'2022-05-24','2022-05-24','very nice lamp 2',60.00,'Lamp 0',NULL,'192ba0e2-69e4-4ec0-be25-5e1430dfee55'),('7e974c64-8560-49b2-a68c-4fd055600144',1,'2022-05-24','2022-05-24','very nice lamp 2',60.00,'Lamp 2',NULL,'192ba0e2-69e4-4ec0-be25-5e1430dfee55');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_client_carts`
--

DROP TABLE IF EXISTS `item_client_carts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_client_carts` (
  `item_id` char(36) NOT NULL,
  `client_carts_id` char(36) NOT NULL,
  PRIMARY KEY (`item_id`,`client_carts_id`),
  UNIQUE KEY `UK_b2232tui7yiorp2dpvwphum2o` (`client_carts_id`),
  CONSTRAINT `FKaivkhyot7niu2oc664vcxyi30` FOREIGN KEY (`client_carts_id`) REFERENCES `xref_clients_items` (`id`),
  CONSTRAINT `FKjgbcb6y6135q3qk1885fx6he7` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_client_carts`
--

LOCK TABLES `item_client_carts` WRITE;
/*!40000 ALTER TABLE `item_client_carts` DISABLE KEYS */;
INSERT INTO `item_client_carts` VALUES ('7e974c64-8560-49b2-a68c-4fd055600144','b69271fd-5077-4af2-9980-243433648e6f');
/*!40000 ALTER TABLE `item_client_carts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_orders`
--

DROP TABLE IF EXISTS `item_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_orders` (
  `item_id` char(36) NOT NULL,
  `orders_id` char(36) NOT NULL,
  PRIMARY KEY (`item_id`,`orders_id`),
  UNIQUE KEY `UK_8c48vegir9son7ghjc1rie0fw` (`orders_id`),
  CONSTRAINT `FKad5bvldmj5m0rry9ps0oj4d5y` FOREIGN KEY (`orders_id`) REFERENCES `xref_orders_items` (`id`),
  CONSTRAINT `FKkslir0p8t9cl5eqm87ep9erc4` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_orders`
--

LOCK TABLES `item_orders` WRITE;
/*!40000 ALTER TABLE `item_orders` DISABLE KEYS */;
INSERT INTO `item_orders` VALUES ('7e974c64-8560-49b2-a68c-4fd055600144','1dedfa44-66f9-4667-9bbd-7de64aa79fe9'),('7e974c64-8560-49b2-a68c-4fd055600144','349bdbc5-ca3b-433b-886e-6a5bfe21154b'),('7e974c64-8560-49b2-a68c-4fd055600144','63a61574-9f8e-4d18-afde-ad948a7b5c37'),('7e974c64-8560-49b2-a68c-4fd055600144','896aa431-2fad-47ab-9ebf-caf3b967daa2'),('7e974c64-8560-49b2-a68c-4fd055600144','8c68a235-f3c6-407c-8546-ff3483da718f'),('7e974c64-8560-49b2-a68c-4fd055600144','bfc9426c-53b4-43b8-a9a8-fa540fd22216'),('7e974c64-8560-49b2-a68c-4fd055600144','c5e6a272-5a80-484d-83d8-19a0e9ffac1b');
/*!40000 ALTER TABLE `item_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `order_id` char(36) NOT NULL,
  `items_id` char(36) NOT NULL,
  PRIMARY KEY (`order_id`,`items_id`),
  UNIQUE KEY `UK_d0vxbj4gqklaa3ro4kja2h72x` (`items_id`),
  CONSTRAINT `FK1ni651yc5b69bkywkou7wa4xv` FOREIGN KEY (`items_id`) REFERENCES `xref_orders_items` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_user`
--

DROP TABLE IF EXISTS `order_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_user` (
  `id` char(36) NOT NULL,
  `change_date` date DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `status` int NOT NULL,
  `total_price` decimal(19,2) NOT NULL,
  `client_id` char(36) NOT NULL,
  `delivery_company_id` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsrkspqry09a67rueff9ius647` (`client_id`),
  KEY `FKqok14rggw6xpkgpge22huoa2r` (`delivery_company_id`),
  CONSTRAINT `FKqok14rggw6xpkgpge22huoa2r` FOREIGN KEY (`delivery_company_id`) REFERENCES `delivery_company` (`id`),
  CONSTRAINT `FKsrkspqry09a67rueff9ius647` FOREIGN KEY (`client_id`) REFERENCES `base_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_user`
--

LOCK TABLES `order_user` WRITE;
/*!40000 ALTER TABLE `order_user` DISABLE KEYS */;
INSERT INTO `order_user` VALUES ('4b3f1adf-d5d2-4634-8001-4a9b6a12d3fd','2022-05-23','2022-05-23',0,0.00,'55e1d923-065f-4e8a-9bcc-8b4388f488db','28fb78cb-bbc9-41f0-87dd-74a9c96f7f97'),('4d424335-314a-4bfa-9b2c-358318db345e','2022-05-23','2022-05-23',0,60.00,'7addceb3-f24c-4f6c-9b43-0b4d1c9d54a0','28fb78cb-bbc9-41f0-87dd-74a9c96f7f97'),('51cad893-0da6-48d5-b45b-43a144ea3dce','2022-05-23','2022-05-23',0,60.00,'7addceb3-f24c-4f6c-9b43-0b4d1c9d54a0','28fb78cb-bbc9-41f0-87dd-74a9c96f7f97'),('5be2c640-f9e0-469a-9380-f0f5d221af82','2022-05-24','2022-05-24',0,125.00,'7addceb3-f24c-4f6c-9b43-0b4d1c9d54a0','28fb78cb-bbc9-41f0-87dd-74a9c96f7f97'),('65b05192-be56-4bf5-bc94-1e92c2e0b2e2','2022-05-23','2022-05-23',0,60.00,'7addceb3-f24c-4f6c-9b43-0b4d1c9d54a0','28fb78cb-bbc9-41f0-87dd-74a9c96f7f97'),('904f96ac-e44b-4206-a415-6d98bd4a170f','2022-05-24','2022-05-24',0,185.00,'7addceb3-f24c-4f6c-9b43-0b4d1c9d54a0','28fb78cb-bbc9-41f0-87dd-74a9c96f7f97'),('a3b4625e-673a-430a-a27b-d22e81ebd436','2022-05-23','2022-05-23',0,60.00,'7addceb3-f24c-4f6c-9b43-0b4d1c9d54a0','28fb78cb-bbc9-41f0-87dd-74a9c96f7f97'),('a6d9f827-6cc2-431a-8cfb-cb0061f2661f','2022-05-24','2022-05-24',0,65.00,'7addceb3-f24c-4f6c-9b43-0b4d1c9d54a0','28fb78cb-bbc9-41f0-87dd-74a9c96f7f97'),('b42db65a-6b08-4a6e-a71f-f20f48719e3d','2022-05-23','2022-05-23',0,60.00,'7addceb3-f24c-4f6c-9b43-0b4d1c9d54a0','28fb78cb-bbc9-41f0-87dd-74a9c96f7f97'),('b55d9037-890e-44a1-8c3e-fd51a4b88c33','2022-05-23','2022-05-23',0,120.00,'7addceb3-f24c-4f6c-9b43-0b4d1c9d54a0','28fb78cb-bbc9-41f0-87dd-74a9c96f7f97'),('e7009a7a-6c61-4100-ab7b-b0f9c165c760','2022-05-25','2022-05-23',4,240.00,'7addceb3-f24c-4f6c-9b43-0b4d1c9d54a0','28fb78cb-bbc9-41f0-87dd-74a9c96f7f97');
/*!40000 ALTER TABLE `order_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale`
--

DROP TABLE IF EXISTS `sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale` (
  `id` char(36) NOT NULL,
  `end_date` date DEFAULT NULL,
  `sale_percentage` int NOT NULL,
  `start_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale`
--

LOCK TABLES `sale` WRITE;
/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
INSERT INTO `sale` VALUES ('a79cbcff-b925-4716-a1fb-fe25aefd9df2','2024-03-03',7,'2023-03-03'),('eceb9201-3d83-4b34-8da0-a13df7699f85','2023-03-03',6,'2022-03-03');
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store` (
  `id` char(36) NOT NULL,
  `active` bit(1) NOT NULL,
  `address` varchar(255) NOT NULL,
  `user_id` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKryfs3fgbjxsldo468pfd2cnlw` (`user_id`),
  CONSTRAINT `FKryfs3fgbjxsldo468pfd2cnlw` FOREIGN KEY (`user_id`) REFERENCES `base_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES ('192ba0e2-69e4-4ec0-be25-5e1430dfee55',_binary '','dummy','56181df0-8919-4094-9642-40c9817fa4ea');
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_items`
--

DROP TABLE IF EXISTS `store_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store_items` (
  `store_id` char(36) NOT NULL,
  `items_id` char(36) NOT NULL,
  PRIMARY KEY (`store_id`,`items_id`),
  UNIQUE KEY `UK_5i87vhpn6ks3bc3gi66c5yjbb` (`items_id`),
  CONSTRAINT `FKegcguslxih1tg7dv1i2jgyded` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`),
  CONSTRAINT `FKnxxhxds8qw061wkjmdrc5clka` FOREIGN KEY (`items_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_items`
--

LOCK TABLES `store_items` WRITE;
/*!40000 ALTER TABLE `store_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `store_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xref_clients_items`
--

DROP TABLE IF EXISTS `xref_clients_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xref_clients_items` (
  `id` char(36) NOT NULL,
  `create_date` date DEFAULT NULL,
  `item_quantity` int NOT NULL,
  `client_id` char(36) NOT NULL,
  `item_id` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm8t8vjfotfsdqsrl4vdfiq6h0` (`client_id`),
  KEY `FK8kq9hn5dxltxp2kraswtoy6yw` (`item_id`),
  CONSTRAINT `FK8kq9hn5dxltxp2kraswtoy6yw` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
  CONSTRAINT `FKm8t8vjfotfsdqsrl4vdfiq6h0` FOREIGN KEY (`client_id`) REFERENCES `base_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xref_clients_items`
--

LOCK TABLES `xref_clients_items` WRITE;
/*!40000 ALTER TABLE `xref_clients_items` DISABLE KEYS */;
INSERT INTO `xref_clients_items` VALUES ('b69271fd-5077-4af2-9980-243433648e6f','2022-05-24',1,'55e1d923-065f-4e8a-9bcc-8b4388f488db','7e974c64-8560-49b2-a68c-4fd055600144');
/*!40000 ALTER TABLE `xref_clients_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xref_orders_items`
--

DROP TABLE IF EXISTS `xref_orders_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xref_orders_items` (
  `id` char(36) NOT NULL,
  `item_quantity` int NOT NULL,
  `item_id` char(36) DEFAULT NULL,
  `order_id` char(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK107ngbvoe3epk2t2lckjq3xom` (`order_id`),
  KEY `FKv0va8l8k18gtvj94w90xk4hj` (`item_id`),
  CONSTRAINT `FK107ngbvoe3epk2t2lckjq3xom` FOREIGN KEY (`order_id`) REFERENCES `order_user` (`id`),
  CONSTRAINT `FKv0va8l8k18gtvj94w90xk4hj` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xref_orders_items`
--

LOCK TABLES `xref_orders_items` WRITE;
/*!40000 ALTER TABLE `xref_orders_items` DISABLE KEYS */;
INSERT INTO `xref_orders_items` VALUES ('1ade846e-a5db-4935-937b-0df02d810f6a',2,'7e974c64-8560-49b2-a68c-4fd055600144','904f96ac-e44b-4206-a415-6d98bd4a170f'),('1dedfa44-66f9-4667-9bbd-7de64aa79fe9',1,'7e974c64-8560-49b2-a68c-4fd055600144','65b05192-be56-4bf5-bc94-1e92c2e0b2e2'),('349bdbc5-ca3b-433b-886e-6a5bfe21154b',1,'7e974c64-8560-49b2-a68c-4fd055600144','a3b4625e-673a-430a-a27b-d22e81ebd436'),('63a61574-9f8e-4d18-afde-ad948a7b5c37',1,'7e974c64-8560-49b2-a68c-4fd055600144','51cad893-0da6-48d5-b45b-43a144ea3dce'),('689c4d5d-9d33-4aa1-bfe2-8dc5f7e766f8',2,'7e974c64-8560-49b2-a68c-4fd055600144','5be2c640-f9e0-469a-9380-f0f5d221af82'),('742e67ce-ba50-4a14-9f5b-a385b95726b2',1,'11e1658b-9284-4f6f-9f68-4dc577b2fa2d','904f96ac-e44b-4206-a415-6d98bd4a170f'),('896aa431-2fad-47ab-9ebf-caf3b967daa2',1,'7e974c64-8560-49b2-a68c-4fd055600144','4d424335-314a-4bfa-9b2c-358318db345e'),('8c68a235-f3c6-407c-8546-ff3483da718f',2,'7e974c64-8560-49b2-a68c-4fd055600144','b55d9037-890e-44a1-8c3e-fd51a4b88c33'),('a34bf851-3cc7-4140-84fe-1a0a87e27466',1,'7e974c64-8560-49b2-a68c-4fd055600144','a6d9f827-6cc2-431a-8cfb-cb0061f2661f'),('bfc9426c-53b4-43b8-a9a8-fa540fd22216',1,'7e974c64-8560-49b2-a68c-4fd055600144','b42db65a-6b08-4a6e-a71f-f20f48719e3d'),('c5e6a272-5a80-484d-83d8-19a0e9ffac1b',4,'7e974c64-8560-49b2-a68c-4fd055600144','e7009a7a-6c61-4100-ab7b-b0f9c165c760');
/*!40000 ALTER TABLE `xref_orders_items` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-04 16:21:27
