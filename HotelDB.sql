-- MySQL dump 10.13  Distrib 5.7.26, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel
-- ------------------------------------------------------
-- Server version	5.7.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `check_out`
--

DROP TABLE IF EXISTS `check_out`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `check_out` (
  `co_id` int(11) NOT NULL AUTO_INCREMENT,
  `cust_chkout_date` date NOT NULL,
  `cust_chkout_time` time NOT NULL,
  `additional_fees` double DEFAULT NULL,
  `reserve_id` int(11) NOT NULL,
  PRIMARY KEY (`co_id`),
  KEY `BL_id_idx` (`reserve_id`),
  CONSTRAINT `chkout_reserve` FOREIGN KEY (`reserve_id`) REFERENCES `reservation` (`reserve_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check_out`
--

LOCK TABLES `check_out` WRITE;
/*!40000 ALTER TABLE `check_out` DISABLE KEYS */;
INSERT INTO `check_out` VALUES (1,'2019-07-15','04:08:30',1,1),(2,'2019-08-13','05:10:20',2,2),(3,'2019-09-12','04:07:40',3,3),(4,'2019-09-10','02:00:00',4,4),(5,'2019-06-08','03:09:30',5,5),(6,'2019-05-07','06:08:50',6,6),(7,'2019-04-06','04:00:00',7,7),(8,'2019-03-05','09:00:00',8,8),(9,'2019-03-04','06:06:00',9,9),(10,'2019-02-03','01:10:30',10,10);
/*!40000 ALTER TABLE `check_out` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `client_id` int(5) NOT NULL AUTO_INCREMENT,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `phoneno` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Kuya','Vred','+63-928-555-8913','Turon_@yahoo.com','Émü¿Ap•ò…Jò…Í'),(2,'Tito','Fran','+63-933-555-9077','Valencia_yahoo.com','sjS‡¼ßVúÑãéZÈîõ'),(3,'Jedediah','Sussman','+63-932-555-5456','Jroa_@yahoo.com','ã·Œx]DKç?ºÒÏ'),(4,'Chad','Chua','+63-921-555-1605','Chado_@yahoo.com','×ê1S©¢ÎÆ[CÚZî(-é'),(5,'Svedrofsky','Stavro ','+63-933-555-9322','Svedo_@yahoo.com','yØÓPÎŠ	b•¾jò¶`'),(6,'Vancil','Eduino','+63-280-555-6229','Edwin_@yahoo.com','„Ú¨‰¶ì\r*ºME ¨¥|r'),(7,'Wenonah','Mosher','+63-933-555-8319','Weno_@yahoo.com','‡ò0Ó™:!ƒýÖ~Âþîx'),(8,'Bertoli','Aili','+63-907-555-8548','Ali_@yahoo.com','ÃÙ0ÐÉÄŽ¢ÿ	&JÊ³y'),(9,'Alonzo','Elisabet','+63-283-555-4791','Eli_@yahoo.com','`ý™Ú²#ŒæÞ0\\.Ç{S'),(10,'Bingel','Norrie','+63-932-555-7285','Bingo_@yahoo.com','RŽ	ú&*bÂNsÐŸÐ'),(18,'Turon_@yahoo.com','pass1','zxczx','czxc','zxc'),(19,'123','123','123','123','Mw	™´þÆK¹°w');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL DEFAULT '0',
  `Bill` double NOT NULL DEFAULT '0',
  `reserve_id` int(5) NOT NULL DEFAULT '0',
  `credit_no` int(11) NOT NULL DEFAULT '0',
  `credit_cvv` int(11) NOT NULL DEFAULT '0',
  `debit_no` int(11) NOT NULL DEFAULT '0',
  `debit_cvv` int(11) NOT NULL DEFAULT '0',
  `paymentcol` varchar(45) DEFAULT '0',
  PRIMARY KEY (`payment_id`),
  KEY `reserve_id_idx` (`reserve_id`),
  CONSTRAINT `payment_reserve` FOREIGN KEY (`reserve_id`) REFERENCES `reservation` (`reserve_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,0,1,123456789,124,0,0,'0'),(2,0,2,123234354,234,0,0,'0'),(3,0,3,123412334,532,0,0,'0'),(4,0,4,123141233,125,0,0,'0'),(5,0,5,123124123,534,0,0,'0'),(6,0,6,213124132,231,0,0,'0'),(7,0,7,123124123,245,0,0,'0'),(8,0,8,123214112,534,0,0,'0'),(9,0,9,213141233,125,0,0,'0'),(10,0,10,123124123,643,0,0,'0');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `reserve_id` int(11) NOT NULL AUTO_INCREMENT,
  `check_in_date` date NOT NULL,
  `check_in_time` time NOT NULL,
  `check_out_date` date NOT NULL,
  `check_out_time` time NOT NULL,
  `client_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  PRIMARY KEY (`reserve_id`),
  KEY `clientid_idx` (`client_id`),
  KEY `roomno_idx` (`room_id`),
  CONSTRAINT `reserve_client` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `reserve_rooms` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`room_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (1,'2019-06-02','08:12:13','2019-05-04','09:13:14',1,1),(2,'2019-04-03','09:13:14','2019-05-01','10:14:15',2,2),(3,'2019-05-01','10:14:15','2019-07-04','11:15:16',3,3),(4,'2019-07-04','11:15:16','2019-08-05','12:16:17',4,4),(5,'2019-08-05','12:16:17','2019-09-06','13:17:18',5,5),(6,'2019-09-06','13:17:18','2019-10-08','14:18:19',6,6),(7,'2019-10-08','14:18:19','2019-11-09','15:19:20',7,7),(8,'2019-11-09','15:19:20','2019-02-02','12:00:01',8,8),(9,'2019-02-02','12:00:01','2019-06-03','16:00:02',9,9),(10,'2019-06-03','16:00:02','2019-06-02','08:12:13',10,10);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rooms` (
  `room_id` int(11) NOT NULL,
  `Accomodation` varchar(15) NOT NULL,
  `Availablity` varchar(45) NOT NULL DEFAULT '"Available"',
  `price_per_night` double DEFAULT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,'1-2 persons','1',2000),(2,'2-3 persons','1',4000),(3,'4-5 persons','1',6000),(4,'5-6 persons','1',8000),(5,'7-8 persons','1',10000),(6,'9+ people','1',12000),(7,'1 person','1',100),(8,'2 persons','1',200),(9,'3-4 persons','1',500),(10,'5 persons','1',1000);
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-11 20:21:57
