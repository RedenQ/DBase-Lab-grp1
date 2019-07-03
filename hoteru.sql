-- MySQL dump 10.13  Distrib 5.7.21, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `booking_log`
--

DROP TABLE IF EXISTS `booking_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking_log` (
  `BL_id` int(11) NOT NULL AUTO_INCREMENT,
  `Check_in` datetime DEFAULT NULL,
  `check_out` datetime DEFAULT NULL,
  `clientid` int(11) NOT NULL,
  `roomno` int(11) NOT NULL,
  PRIMARY KEY (`BL_id`),
  KEY `clientid_idx` (`clientid`),
  KEY `roomno_idx` (`roomno`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_log`
--

LOCK TABLES `booking_log` WRITE;
/*!40000 ALTER TABLE `booking_log` DISABLE KEYS */;
INSERT INTO `booking_log` VALUES (1,'2019-06-02 08:12:13','2019-05-04 09:13:14',1,1),(2,'2019-04-03 09:13:14','2019-05-01 10:14:15',2,2),(3,'2019-05-01 10:14:15','2019-07-04 11:15:16',3,3),(4,'2019-07-04 11:15:16','2019-08-05 12:16:17',4,4),(5,'2019-08-05 12:16:17','2019-09-06 13:17:18',5,5),(6,'2019-09-06 13:17:18','2019-10-08 14:18:19',6,6),(7,'2019-10-08 14:18:19','2019-11-09 15:19:20',7,7),(8,'2019-11-09 15:19:20','2019-02-02 12:00:01',8,8),(9,'2019-02-02 12:00:01','2019-06-03 16:00:02',9,9),(10,'2019-06-03 16:00:02','2019-06-02 08:12:13',10,10);
/*!40000 ALTER TABLE `booking_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `check_out`
--

DROP TABLE IF EXISTS `check_out`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `check_out` (
  `co_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_checkout` datetime DEFAULT NULL,
  `clientid` int(11) NOT NULL,
  `BL_id` int(11) NOT NULL,
  PRIMARY KEY (`co_id`),
  KEY `clientid_idx` (`clientid`),
  KEY `BL_id_idx` (`BL_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check_out`
--

LOCK TABLES `check_out` WRITE;
/*!40000 ALTER TABLE `check_out` DISABLE KEYS */;
/*!40000 ALTER TABLE `check_out` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `clientid` int(5) NOT NULL AUTO_INCREMENT,
  `fname` varchar(50) DEFAULT NULL,
  `lname` varchar(50) DEFAULT NULL,
  `phoneno` varchar(50) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`clientid`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Kuya','Vred','+63-928-555-8913','Turon_@yahoo.com','pass1'),(2,'Tito','Fran','+63-933-555-9077','Valencia_yahoo.com','pass2'),(3,'Jedediah','Sussman','+63-932-555-5456','Jroa_@yahoo.com','pass3'),(4,'Chad','Chua','+63-921-555-1605','Chado_@yahoo.com','pass4'),(5,'Svedrofsky','Stavro ','+63-933-555-9322','Svedo_@yahoo.com','pass5'),(6,'Vancil','Eduino','+63-280-555-6229','Edwin_@yahoo.com','pass6'),(7,'Wenonah','Mosher','+63-933-555-8319','Weno_@yahoo.com','pass7'),(8,'Bertoli','Aili','+63-907-555-8548','Ali_@yahoo.com','pass8'),(9,'Alonzo','Elisabet','+63-283-555-4791','Eli_@yahoo.com','pass9'),(10,'Bingel','Norrie','+63-932-555-7285','Bingo_@yahoo.com','pass10');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rooms` (
  `roomno` int(11) NOT NULL,
  `Accomodation` int(11) DEFAULT NULL,
  `Availablity` varchar(45) NOT NULL DEFAULT '"Available"',
  PRIMARY KEY (`roomno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
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

-- Dump completed on 2019-07-03 13:36:12
