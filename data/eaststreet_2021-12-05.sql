# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 192.168.1.4 (MySQL 5.7.22-22)
# Database: eaststreet
# Generation Time: 2021-12-05 15:56:39 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table cart
# ------------------------------------------------------------

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `itemid` int(11) NOT NULL,
  `itemname` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `itemprice` decimal(11,2) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# Dump of table item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `storeid` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `quantity` int(11) DEFAULT NULL,
  `price` decimal(11,2) DEFAULT NULL,
  `image` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;

INSERT INTO `item` (`id`, `storeid`, `name`, `quantity`, `price`, `image`)
VALUES
	(1,1,'name1',100,12.30,NULL),
	(2,1,'name2',101,23.40,NULL),
	(3,2,'name3',200,34.50,NULL);

/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table order_detail
# ------------------------------------------------------------

DROP TABLE IF EXISTS `order_detail`;

CREATE TABLE `order_detail` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `orderlistid` int(11) DEFAULT NULL,
  `itemname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `itemprice` decimal(11,2) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;

INSERT INTO `order_detail` (`id`, `orderlistid`, `itemname`, `itemprice`, `quantity`)
VALUES
	(12,10,'name1',12.30,1),
	(13,10,'name2',23.40,1),
	(14,11,'name1',12.30,1),
	(15,11,'name2',23.40,1),
	(16,12,'name1',12.30,1),
	(17,12,'name2',23.40,1);

/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table order_list
# ------------------------------------------------------------

DROP TABLE IF EXISTS `order_list`;

CREATE TABLE `order_list` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `storeid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `driverid` int(11) NOT NULL DEFAULT '0',
  `storename` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `drivername` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT ' ',
  `usercontact` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `drivercontact` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `pickup` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `terminal` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `status` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `order_list` WRITE;
/*!40000 ALTER TABLE `order_list` DISABLE KEYS */;

INSERT INTO `order_list` (`id`, `storeid`, `userid`, `driverid`, `storename`, `drivername`, `usercontact`, `drivercontact`, `pickup`, `terminal`, `status`)
VALUES
	(1,1,1,3,'store1','aaa','aa','fff','addfrom1','addto1','DELIVERING'),
	(2,1,3,0,'store1','','bb','dd2','addfrom1','addto3','PICKED'),
	(11,1,3,0,'aa','','cc','dd3','bb','eee','INIT'),
	(12,1,3,0,'aa','','fff','','bb','eee','INIT');

/*!40000 ALTER TABLE `order_list` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table product
# ------------------------------------------------------------

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;

INSERT INTO `product` (`id`, `name`, `price`)
VALUES
	(1,'Kindle Fire',199),
	(2,'Kindle Fire',199);

/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table store
# ------------------------------------------------------------

DROP TABLE IF EXISTS `store`;

CREATE TABLE `store` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ownerid` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;

INSERT INTO `store` (`id`, `ownerid`, `name`, `address`, `phone`)
VALUES
	(1,1,'aa','bb','cc'),
	(2,3,'aaa','bbb','ccc');

/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table todo
# ------------------------------------------------------------

DROP TABLE IF EXISTS `todo`;

CREATE TABLE `todo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `todo_status` int(11) DEFAULT NULL,
  `date_created` timestamp NULL DEFAULT NULL,
  `last_modified` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `realname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mobilephone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `name`, `password`, `realname`, `email`, `address`, `mobilephone`)
VALUES
	(1,'test01','password01','test 01','email01','address01','mobilephone01'),
	(3,'aaa','bbb','ccc','ddd','eee','fff');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
