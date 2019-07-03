-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jul 03, 2019 at 06:19 AM
-- Server version: 5.7.21
-- PHP Version: 5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbmsfinals`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking_log`
--

DROP TABLE IF EXISTS `booking_log`;
CREATE TABLE IF NOT EXISTS `booking_log` (
  `BL_id` int(11) NOT NULL AUTO_INCREMENT,
  `Check_in` datetime DEFAULT NULL,
  `check_out` datetime DEFAULT NULL,
  `clientid` int(11) NOT NULL,
  `roomno` int(11) NOT NULL,
  PRIMARY KEY (`BL_id`),
  KEY `clientid_idx` (`clientid`),
  KEY `roomno_idx` (`roomno`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `check_out`
--

DROP TABLE IF EXISTS `check_out`;
CREATE TABLE IF NOT EXISTS `check_out` (
  `co_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_checkout` datetime DEFAULT NULL,
  `clientid` int(11) NOT NULL,
  `BL_id` int(11) NOT NULL,
  PRIMARY KEY (`co_id`),
  KEY `clientid_idx` (`clientid`),
  KEY `BL_id_idx` (`BL_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `check_out`
--

INSERT INTO `check_out` (`co_id`, `customer_checkout`, `clientid`, `BL_id`) VALUES
(1, '2019-07-15 04:08:30', 1, 1),
(2, '2019-08-13 05:10:20', 2, 2),
(3, '2019-09-12 04:07:40', 3, 3),
(4, '2019-09-10 02:00:00', 4, 4),
(5, '2019-06-08 03:09:30', 5, 5),
(6, '2019-05-07 06:08:50', 6, 6),
(7, '2019-04-06 04:00:00', 7, 7),
(8, '2019-03-05 09:00:00', 8, 8),
(9, '2019-03-04 06:06:00', 9, 9),
(10, '2019-02-03 01:10:30', 10, 10);

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `clientid` int(5) NOT NULL AUTO_INCREMENT,
  `fname` varchar(50) DEFAULT NULL,
  `lname` varchar(50) DEFAULT NULL,
  `phoneno` varchar(50) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`clientid`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
CREATE TABLE IF NOT EXISTS `reservations` (
  `reservation_id` int(11) NOT NULL,
  `reservation_date` date NOT NULL,
  `fname` varchar(15) NOT NULL,
  `lname` varchar(15) NOT NULL,
  `PrfrdRoom_size` varchar(15) NOT NULL,
  PRIMARY KEY (`reservation_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`reservation_id`, `reservation_date`, `fname`, `lname`, `PrfrdRoom_size`) VALUES
(1, '2019-07-01', 'Miguel', 'Gallardo', '1-2 persons'),
(2, '2019-07-02', 'El', 'Chapo', '2-3 persons'),
(3, '2019-07-03', 'Sheldon', 'Maxwell', '4-5 persons'),
(4, '2019-07-04', 'Katrina', 'Singleton', '5-6 persons'),
(5, '2019-07-05', 'Richard', 'Hammond', '7-8 persons'),
(6, '2019-07-06', 'Victor', 'Soto', '9+ people'),
(7, '2019-07-07', 'Mister', 'Stanley', '1 person'),
(8, '2019-07-08', 'Agent', 'Pena', '2 persons'),
(9, '2019-07-09', 'Agent', 'Murphy', '3-4 persons'),
(10, '2019-07-10', 'Pablo', 'Escobar', '5 persons');

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
CREATE TABLE IF NOT EXISTS `rooms` (
  `roomno` int(11) NOT NULL,
  `Accomodation` varchar(15) DEFAULT NULL,
  `Availablity` varchar(45) NOT NULL DEFAULT '"Available"',
  PRIMARY KEY (`roomno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`roomno`, `Accomodation`, `Availablity`) VALUES
(1, '1-2 persons', '1'),
(2, '2-3 persons', '1'),
(3, '4-5 persons', '1'),
(4, '5-6 persons', '1'),
(5, '7-8 persons', '1'),
(6, '9+ people', '1'),
(7, '1 person', '1'),
(8, '2 persons', '1'),
(9, '3-4 persons', '1'),
(10, '5 persons', '1');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
