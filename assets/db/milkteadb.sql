-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: milkteadb
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `phoneNumber` varchar(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `address` varchar(250) DEFAULT NULL,
  `birthday` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'0377079042','Nguyễn Lê Nhật Anh','Nghệ An','2000-04-08 17:00:00'),(2,'0911175581','Nguyễn Xuân Chính','Long Biên, Hà Nội',NULL),(3,'0112122133','Nguyễn Quý Đạt','','2000-04-09 22:00:00');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `phoneNumber` varchar(20) DEFAULT NULL,
  `startDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `permissionName` varchar(50) NOT NULL,
  `permissionId` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'admin','admin','Admin',NULL,'2020-11-23 17:00:00','Quản lý','1'),(2,'nhanvien','12345','Nhân Viên 1','0911175581','2020-11-24 05:15:08','Nhân viên','2');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_category`
--

DROP TABLE IF EXISTS `food_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `slug` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_category`
--

LOCK TABLES `food_category` WRITE;
/*!40000 ALTER TABLE `food_category` DISABLE KEYS */;
INSERT INTO `food_category` VALUES (1,'Đồ Ăn','do-an'),(2,'Trà Sữa','tra-sua'),(3,'Cà Phê','ca-phe'),(4,'Topping','topping');
/*!40000 ALTER TABLE `food_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_item`
--

DROP TABLE IF EXISTS `food_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `urlImage` varchar(50) DEFAULT NULL,
  `unitName` varchar(20) NOT NULL,
  `unitPrice` bigint NOT NULL,
  `idCategory` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `fk_item_category` (`idCategory`),
  CONSTRAINT `fk_item_category` FOREIGN KEY (`idCategory`) REFERENCES `food_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_item`
--

LOCK TABLES `food_item` WRITE;
/*!40000 ALTER TABLE `food_item` DISABLE KEYS */;
INSERT INTO `food_item` VALUES (1,'No Topping','','','Phần',0,4),(2,'Trân Châu Tuyết Sợi',NULL,NULL,'Phần',10000,4),(3,'Trân Châu Đen',NULL,NULL,'Phần',10000,4),(4,'Trân Châu Trắng',NULL,NULL,'Phần',10000,4),(5,'Trà Sữa Trân Châu',NULL,NULL,'Ly',50000,2),(6,'Trà Sữa Sương Sáo',NULL,NULL,'Ly',45000,2),(7,'Trà Sữa Matcha(L)','','','Ly',50000,1),(8,'Sữa Tươi Trân Châu Đường Đen',NULL,NULL,'Ly',45000,2),(9,'Bánh Flan','','','Cái',10000,1),(10,'Hướng dương',NULL,NULL,'Túi',10000,1),(11,'Cafe truyền thống',NULL,NULL,'Cốc',35000,3),(12,'Espresso',NULL,NULL,'Cốc',45000,3),(13,'Trà Sữa Matcha(XL)',NULL,NULL,'Ly',25000,2),(14,'Trà Sữa Ô Long','','','Ly',20000,2),(15,'Trà Đào','','tra-ao-2020-12-10-12-15-10.png','Ly',40000,2),(16,'Trà Đào(L)','','tra-ao-l-2020-12-10-12-16-17.png','Ly',50000,1);
/*!40000 ALTER TABLE `food_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idEmployee` int NOT NULL,
  `idTable` int DEFAULT NULL,
  `type` varchar(45) NOT NULL DEFAULT 'local' COMMENT 'local - tại quán\nonline - đặt online',
  `status` varchar(45) NOT NULL DEFAULT 'unpaid' COMMENT 'unpaid - chưa thanh toán\npaid - đã thanh toán',
  `orderDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `payDate` timestamp NULL DEFAULT NULL,
  `paidAmount` bigint DEFAULT '0',
  `totalAmount` bigint NOT NULL DEFAULT '0',
  `discount` bigint DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_employee_order` (`idEmployee`),
  KEY `fk_order_table` (`idTable`),
  CONSTRAINT `fk_employee_order` FOREIGN KEY (`idEmployee`) REFERENCES `employee` (`id`),
  CONSTRAINT `fk_order_table` FOREIGN KEY (`idTable`) REFERENCES `table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,1,1,'local','unpaid','2020-11-24 07:28:41',NULL,0,0,0),(2,1,1,'online','unpaid','2020-11-24 08:05:08','2020-11-23 17:00:00',0,0,0);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `idOrder` int NOT NULL,
  `idFoodItem` int NOT NULL,
  `idTopping` int NOT NULL DEFAULT '0',
  `quantity` int NOT NULL DEFAULT '1',
  `toppingPrice` bigint NOT NULL DEFAULT '0',
  `note` varchar(100) DEFAULT NULL,
  `foodPrice` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`idOrder`,`idFoodItem`,`idTopping`),
  KEY `fk_order_main_item` (`idFoodItem`),
  KEY `fk_order_topping` (`idTopping`),
  CONSTRAINT `fk_order_item` FOREIGN KEY (`idOrder`) REFERENCES `order` (`id`),
  CONSTRAINT `fk_order_main_item` FOREIGN KEY (`idFoodItem`) REFERENCES `food_item` (`id`),
  CONSTRAINT `fk_order_topping` FOREIGN KEY (`idTopping`) REFERENCES `food_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (1,1,1,3,0,NULL,0),(1,3,2,2,0,NULL,0),(1,8,1,2,0,NULL,0),(2,8,1,2,0,NULL,0);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipment`
--

DROP TABLE IF EXISTS `shipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipment` (
  `idOrder` int NOT NULL,
  `idCustomer` int NOT NULL,
  `shipperName` varchar(50) NOT NULL,
  `shipperPhoneNumber` varchar(20) NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'topay' COMMENT 'topay - chờ xác nhận\ntoship - chờ lấy hàng\ntoreceive - đang giao\ncompleted - hoàn thành\ncancelled - đã hủy',
  `notice` varchar(45) DEFAULT NULL,
  `startDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `endDate` timestamp NULL DEFAULT NULL,
  `shipCost` int DEFAULT '0',
  PRIMARY KEY (`idOrder`),
  KEY `fk_ship_customer` (`idCustomer`),
  CONSTRAINT `fk_order_ship` FOREIGN KEY (`idOrder`) REFERENCES `order` (`id`),
  CONSTRAINT `fk_ship_customer` FOREIGN KEY (`idCustomer`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipment`
--

LOCK TABLES `shipment` WRITE;
/*!40000 ALTER TABLE `shipment` DISABLE KEYS */;
INSERT INTO `shipment` VALUES (2,1,'Nguyễn Văn B','09421321323','pending',NULL,'2020-11-23 17:00:00',NULL,20000);
/*!40000 ALTER TABLE `shipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table`
--

DROP TABLE IF EXISTS `table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `table` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'free' COMMENT 'free-Trống\nserving-Đang phục vụ\nreserving-Đặt trước',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table`
--

LOCK TABLES `table` WRITE;
/*!40000 ALTER TABLE `table` DISABLE KEYS */;
INSERT INTO `table` VALUES (1,'Bàn 1','free'),(2,'Bàn 2','free'),(3,'Bàn 3','free');
/*!40000 ALTER TABLE `table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-19 17:58:31
