-- MySQL dump 10.13  Distrib 5.5.57, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: sina
-- ------------------------------------------------------
-- Server version	5.5.57-0ubuntu0.14.04.1

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
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_persian_ci NOT NULL,
  `parent` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent` (`parent`),
  CONSTRAINT `city_ibfk_1` FOREIGN KEY (`parent`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'tehran',NULL);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm`
--

DROP TABLE IF EXISTS `cm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `a_fname` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `a_lname` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `a_birthday` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `a_code` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `fixed_phone` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `mobile` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `username` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `password` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm`
--

LOCK TABLES `cm` WRITE;
/*!40000 ALTER TABLE `cm` DISABLE KEYS */;
INSERT INTO `cm` VALUES (1,'gwdwrg','wgwg','wgweg','krwbkrw','mrgmw','gmem','gomeromeg','eqgmemqg','qeqopem','2017-09-01 16:11:01',0);
/*!40000 ALTER TABLE `cm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_city`
--

DROP TABLE IF EXISTS `cm_city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cmid` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cmid` (`cmid`),
  KEY `cid` (`cid`),
  CONSTRAINT `cm_city_ibfk_1` FOREIGN KEY (`cmid`) REFERENCES `cm` (`id`),
  CONSTRAINT `cm_city_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `city` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_city`
--

LOCK TABLES `cm_city` WRITE;
/*!40000 ALTER TABLE `cm_city` DISABLE KEYS */;
INSERT INTO `cm_city` VALUES (1,1,1);
/*!40000 ALTER TABLE `cm_city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `docs`
--

DROP TABLE IF EXISTS `docs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `docs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `desc` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `type` int(11) NOT NULL,
  `oid` int(11) DEFAULT NULL,
  `vid` int(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `did` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `oid` (`oid`),
  KEY `vid` (`vid`),
  KEY `cid` (`cid`),
  KEY `did` (`did`),
  KEY `rid` (`rid`),
  CONSTRAINT `docs_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `cm` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docs`
--

LOCK TABLES `docs` WRITE;
/*!40000 ALTER TABLE `docs` DISABLE KEYS */;
INSERT INTO `docs` VALUES (1,'2beaa08e-2e73-413c-a33e-3830e18a895b_a1tdh2dvsw00000_examples.desktop',NULL,6,NULL,NULL,1,NULL,NULL);
INSERT INTO `docs` VALUES (2,'65a55aaf-445d-4f4c-ba83-21cba1204f7d_b4yq0dfrpc00000_examples.desktop',NULL,2,NULL,1,NULL,NULL,NULL);
INSERT INTO `docs` VALUES (3,'78a8a150-7924-4be3-91a1-b1ecdea9455b_jznaoo6uv400000_examples.desktop',NULL,3,NULL,1,NULL,NULL,NULL);
/*!40000 ALTER TABLE `docs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dr`
--

DROP TABLE IF EXISTS `dr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `expert` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `best_visit_time1` int(11) DEFAULT NULL,
  `fixed_phone` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `mobile` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `mkdb` varchar(300) COLLATE utf8_persian_ci DEFAULT NULL,
  `place` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `address` varchar(300) COLLATE utf8_persian_ci DEFAULT NULL,
  `email` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `pezeshk` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `company_products_ack` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `company_products_pop` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `suggestion` varchar(300) COLLATE utf8_persian_ci DEFAULT NULL,
  `city` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `city` (`city`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dr`
--

LOCK TABLES `dr` WRITE;
/*!40000 ALTER TABLE `dr` DISABLE KEYS */;
/*!40000 ALTER TABLE `dr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drugs`
--

DROP TABLE IF EXISTS `drugs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `drugs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drugs`
--

LOCK TABLES `drugs` WRITE;
/*!40000 ALTER TABLE `drugs` DISABLE KEYS */;
/*!40000 ALTER TABLE `drugs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ds`
--

DROP TABLE IF EXISTS `ds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ds` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `phone` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `clerk_name` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `company_products_ack` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `address` varchar(300) COLLATE utf8_persian_ci NOT NULL,
  `best_time` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `company_name_ack_reason` int(11) DEFAULT NULL,
  `username` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `password` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `city` int(11) DEFAULT NULL,
  `verified` tinyint(1) NOT NULL,
  `verified_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ds`
--

LOCK TABLES `ds` WRITE;
/*!40000 ALTER TABLE `ds` DISABLE KEYS */;
INSERT INTO `ds` VALUES (1,'ng','wegkkeqg','null','wg','gm',2,3,2,NULL,NULL,1,0,NULL,NULL,0);
/*!40000 ALTER TABLE `ds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vid` int(11) DEFAULT NULL,
  `cmid` int(11) DEFAULT NULL,
  `dsid` int(11) DEFAULT NULL,
  `drid` int(11) DEFAULT NULL,
  `ds_visited_name` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `ds_visited_job` int(11) DEFAULT NULL,
  `ds_visited_phone` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `ds_idea` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `ds_pop_cm` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `ds_rival` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `ds_index_dr` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `dr_visit_place` int(11) DEFAULT NULL,
  `dr_visit_place_name` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `dr_suggestion` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_at_p` int(11) NOT NULL,
  `next_session` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `prev_session_id` int(11) DEFAULT NULL,
  `content` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `result` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `desc` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `given_document` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `needed_document` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `forward_to_vid` int(11) DEFAULT NULL,
  `from_id` int(11) DEFAULT NULL,
  `submited` int(11) NOT NULL,
  `submit_time` int(11) DEFAULT NULL,
  `viewed_at` int(11) DEFAULT NULL,
  `urgency` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_drugs`
--

DROP TABLE IF EXISTS `order_drugs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_drugs` (
  `id` int(11) NOT NULL,
  `oid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  `drug_id` int(11) DEFAULT NULL,
  `drug_name` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `visit_desc` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `reason` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_drugs`
--

LOCK TABLES `order_drugs` WRITE;
/*!40000 ALTER TABLE `order_drugs` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_drugs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `r`
--

DROP TABLE IF EXISTS `r`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `r` (
  `id` int(11) NOT NULL,
  `cmid` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(11) NOT NULL,
  `desc` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `urgency` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `r`
--

LOCK TABLES `r` WRITE;
/*!40000 ALTER TABLE `r` DISABLE KEYS */;
/*!40000 ALTER TABLE `r` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visitor`
--

DROP TABLE IF EXISTS `visitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visitor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `lname` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `birthday` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `code` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `fixed_phone` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `mobile` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `ack` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `grade` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `username` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `password` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `address` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `grade_exp` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `work_exp` varchar(200) COLLATE utf8_persian_ci DEFAULT NULL,
  `desc` varchar(200) COLLATE utf8_persian_ci NOT NULL,
  `type` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visitor`
--

LOCK TABLES `visitor` WRITE;
/*!40000 ALTER TABLE `visitor` DISABLE KEYS */;
INSERT INTO `visitor` VALUES (1,'mohsen','yazzdani','afagfi','gijwiw','gj','ijwgi','wgji','jggij','wgi','jiwg','ji','wg','wgij','iwjg',0,'2017-09-01 12:11:45',0);
/*!40000 ALTER TABLE `visitor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visitor_city`
--

DROP TABLE IF EXISTS `visitor_city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visitor_city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vid` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visitor_city`
--

LOCK TABLES `visitor_city` WRITE;
/*!40000 ALTER TABLE `visitor_city` DISABLE KEYS */;
INSERT INTO `visitor_city` VALUES (1,1,1);
/*!40000 ALTER TABLE `visitor_city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visitor_cm`
--

DROP TABLE IF EXISTS `visitor_cm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visitor_cm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vid` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visitor_cm`
--

LOCK TABLES `visitor_cm` WRITE;
/*!40000 ALTER TABLE `visitor_cm` DISABLE KEYS */;
/*!40000 ALTER TABLE `visitor_cm` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-01  9:30:48
