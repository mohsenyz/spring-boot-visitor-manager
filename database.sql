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
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `parent` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk00ylup7bsikbna9whybw9mmq` (`parent_id`),
  CONSTRAINT `FKk00ylup7bsikbna9whybw9mmq` FOREIGN KEY (`parent_id`) REFERENCES `city` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'tehran',NULL,NULL);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm`
--

DROP TABLE IF EXISTS `cm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm` (
  `id` int(11) NOT NULL,
  `a_birthday` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `a_code` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `a_fname` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `a_lname` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `fixed_phone` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `mobile` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm`
--

LOCK TABLES `cm` WRITE;
/*!40000 ALTER TABLE `cm` DISABLE KEYS */;
INSERT INTO `cm` VALUES (1,'mohsen','mohsen','mohsen','mohsen','2017-09-01 14:34:24','\0','mohsen','mohsen','mohsen','mohsen','mohsen');
/*!40000 ALTER TABLE `cm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cm_city`
--

DROP TABLE IF EXISTS `cm_city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cm_city` (
  `id` int(11) NOT NULL,
  `cid` int(11) DEFAULT NULL,
  `cmid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cm_city`
--

LOCK TABLES `cm_city` WRITE;
/*!40000 ALTER TABLE `cm_city` DISABLE KEYS */;
INSERT INTO `cm_city` VALUES (2,1,1);
/*!40000 ALTER TABLE `cm_city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `docs`
--

DROP TABLE IF EXISTS `docs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `docs` (
  `id` int(11) NOT NULL,
  `cid` int(11) DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `did` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `oid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `vid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docs`
--

LOCK TABLES `docs` WRITE;
/*!40000 ALTER TABLE `docs` DISABLE KEYS */;
INSERT INTO `docs` VALUES (3,1,NULL,NULL,'b7c4d7f1-63fb-41e0-9bf6-4b74718119aa_4oa5f5rjww00000_examples.desktop',NULL,NULL,6,NULL);
INSERT INTO `docs` VALUES (6,NULL,NULL,NULL,'e2b529e3-b1bb-46a8-b711-b6d4bbb20a65_c70ep0sim800000_examples.desktop',NULL,NULL,2,4);
INSERT INTO `docs` VALUES (7,NULL,NULL,NULL,'c4dbef65-397f-4fb4-943d-3760b708c9b6_mremu6d6hs00000_examples.desktop',NULL,NULL,3,4);
INSERT INTO `docs` VALUES (14,1,NULL,NULL,'8cd9de30-089d-4f05-86c3-56ccb3f0afb2_2cjtu1t0nk00000_examples.desktop',NULL,NULL,2,11);
INSERT INTO `docs` VALUES (15,1,NULL,NULL,'c39188b2-0035-4497-9737-8af93ce19a9d_0wtelndao000000_examples.desktop',NULL,NULL,3,11);
INSERT INTO `docs` VALUES (19,1,NULL,NULL,'598b0509-462c-4d28-86d4-1d89d31b395e_adtv2w8tjk00000_examples.desktop',NULL,NULL,2,16);
INSERT INTO `docs` VALUES (20,1,NULL,NULL,'6c5dc0d6-073c-4149-a77f-de365a5679b9_18qr2u81ts00000_examples.desktop',NULL,NULL,3,16);
/*!40000 ALTER TABLE `docs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dr`
--

DROP TABLE IF EXISTS `dr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dr` (
  `id` int(11) NOT NULL,
  `address` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `best_visit_time1` int(11) DEFAULT NULL,
  `city` int(11) DEFAULT NULL,
  `company_products_ack` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `company_products_pop` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `expert` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `fixed_phone` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `mkdb` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `mobile` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `pezeshk` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `place` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `suggestion` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
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
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
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
  `id` int(11) NOT NULL,
  `address` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `best_time` int(11) DEFAULT NULL,
  `city` int(11) DEFAULT NULL,
  `clerk_name` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `company_name_ack_reason` int(11) DEFAULT NULL,
  `company_products_ack` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `verified` bit(1) DEFAULT NULL,
  `verified_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ds`
--

LOCK TABLES `ds` WRITE;
/*!40000 ALTER TABLE `ds` DISABLE KEYS */;
INSERT INTO `ds` VALUES (9,'mohseni',3,1,'null',3,'mohseni',NULL,'\0','mohseni',NULL,'mohseni',1,NULL,'\0',NULL);
INSERT INTO `ds` VALUES (21,'hasani',3,1,'null',2,'hasani',NULL,'\0','hasani',NULL,'hasani',1,NULL,'\0',NULL);
INSERT INTO `ds` VALUES (23,'hasani',3,1,'null',2,'hasani',NULL,'\0','hasani',NULL,'hasani',1,NULL,'\0',NULL);
INSERT INTO `ds` VALUES (25,'hasani',3,1,'null',2,'hasani',NULL,'\0','hasani',NULL,'hasani',1,NULL,'\0',NULL);
INSERT INTO `ds` VALUES (27,'hasani',3,1,'null',2,'hasani',NULL,'\0','hasani',NULL,'hasani',1,NULL,'\0',NULL);
INSERT INTO `ds` VALUES (29,'hasani',3,1,'null',2,'hasani',NULL,'\0','hasani',NULL,'hasani',1,NULL,'\0',NULL);
/*!40000 ALTER TABLE `ds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (31);
INSERT INTO `hibernate_sequence` VALUES (31);
INSERT INTO `hibernate_sequence` VALUES (31);
INSERT INTO `hibernate_sequence` VALUES (31);
INSERT INTO `hibernate_sequence` VALUES (31);
INSERT INTO `hibernate_sequence` VALUES (31);
INSERT INTO `hibernate_sequence` VALUES (31);
INSERT INTO `hibernate_sequence` VALUES (31);
INSERT INTO `hibernate_sequence` VALUES (31);
INSERT INTO `hibernate_sequence` VALUES (31);
INSERT INTO `hibernate_sequence` VALUES (31);
INSERT INTO `hibernate_sequence` VALUES (31);
INSERT INTO `hibernate_sequence` VALUES (31);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_drugs`
--

DROP TABLE IF EXISTS `order_drugs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_drugs` (
  `id` int(11) NOT NULL,
  `count` int(11) DEFAULT NULL,
  `drug_id` int(11) DEFAULT NULL,
  `drug_name` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `oid` int(11) DEFAULT NULL,
  `reason` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `visit_desc` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
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
-- Table structure for table `order_list`
--

DROP TABLE IF EXISTS `order_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_list` (
  `id` int(11) NOT NULL,
  `cmid` int(11) DEFAULT NULL,
  `content` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_at_ap` int(11) DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `dr_suggestion` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `dr_visit_place` int(11) DEFAULT NULL,
  `dr_visit_place_name` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `drid` int(11) DEFAULT NULL,
  `ds_idea` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `ds_index_dr` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `ds_pop_cm` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `ds_rival` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `ds_visited_job` int(11) DEFAULT NULL,
  `ds_visited_name` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `ds_visited_phone` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `dsid` int(11) DEFAULT NULL,
  `forward_to_vid` int(11) DEFAULT NULL,
  `from_id` int(11) DEFAULT NULL,
  `given_document` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `needed_document` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `next_session` datetime DEFAULT NULL,
  `prev_session_id` int(11) DEFAULT NULL,
  `result` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `submit_time` datetime DEFAULT NULL,
  `submited` bit(1) DEFAULT NULL,
  `urgency` int(11) DEFAULT NULL,
  `vid` int(11) DEFAULT NULL,
  `viewed_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_list`
--

LOCK TABLES `order_list` WRITE;
/*!40000 ALTER TABLE `order_list` DISABLE KEYS */;
INSERT INTO `order_list` VALUES (8,NULL,'rhrher',NULL,1,';DNGADHPn',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'null',NULL,NULL,NULL,NULL,'jdgjdt','rjejtetj',NULL,NULL,'etjerrwjrt',NULL,NULL,1,4,NULL);
INSERT INTO `order_list` VALUES (10,NULL,'rhrher',NULL,1,';DNGADHPn',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'null',NULL,9,NULL,NULL,'jdgjdt','rjejtetj',NULL,NULL,'etjerrwjrt',NULL,NULL,1,4,NULL);
INSERT INTO `order_list` VALUES (22,1,'knegnewwegw',NULL,1,'lksengekgn',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'null',NULL,21,NULL,NULL,'eg;ernwgn','rwrhrhwrwhrhw',NULL,NULL,'krhnrhheweh',NULL,NULL,1,4,NULL);
INSERT INTO `order_list` VALUES (24,1,'knegnewwegw',NULL,1,'lksengekgn',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'null',NULL,23,NULL,NULL,'eg;ernwgn','rwrhrhwrwhrhw',NULL,NULL,'krhnrhheweh',NULL,NULL,1,4,NULL);
INSERT INTO `order_list` VALUES (26,1,'knegnewwegw',NULL,1,'lksengekgn',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'null',NULL,25,NULL,NULL,'eg;ernwgn','rwrhrhwrwhrhw',NULL,NULL,'krhnrhheweh',NULL,NULL,1,4,NULL);
INSERT INTO `order_list` VALUES (28,1,'knegnewwegw',NULL,1,'lksengekgn',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'null',NULL,27,NULL,NULL,'eg;ernwgn','rwrhrhwrwhrhw',NULL,NULL,'krhnrhheweh',NULL,'',1,16,NULL);
INSERT INTO `order_list` VALUES (30,1,'knegnewwegw',NULL,1,'lksengekgn',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,'null',NULL,29,NULL,NULL,'eg;ernwgn','rwrhrhwrwhrhw',NULL,NULL,'krhnrhheweh',NULL,'',1,11,NULL);
/*!40000 ALTER TABLE `order_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `r`
--

DROP TABLE IF EXISTS `r`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `r` (
  `id` int(11) NOT NULL,
  `cmid` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `urgency` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
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
  `id` int(11) NOT NULL,
  `ack` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `birthday` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `code` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `enabled` bit(1) DEFAULT b'1',
  `fixed_phone` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `fname` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `grade` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `grade_exp` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `lname` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `mobile` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  `work_exp` varchar(255) COLLATE utf8_persian_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visitor`
--

LOCK TABLES `visitor` WRITE;
/*!40000 ALTER TABLE `visitor` DISABLE KEYS */;
INSERT INTO `visitor` VALUES (4,'n=mohseni','ngisgn','mohseni','mohseni','2017-09-01 14:49:22','pogw','','mohseni','mohseni','mpnishe','pgn','mohseni','mohseno','nmoadadni',0,'mohse','ign');
INSERT INTO `visitor` VALUES (11,'jndg','iongiigewiwpng','null','5490118611','2017-09-01 15:26:17','ipndgpiegipeg','','0916288662398','mohammad','qng','ingeipnegpineg','mohammadi','iniknnn','inwgwge',0,'iogww','ipndgpigpinege');
INSERT INTO `visitor` VALUES (16,';ksdng','klknddjld','null','5490118611','2017-09-01 21:16:39','idigegeg','','0515151','asal','kn','kksngkgn','asali','051616','knsg',0,'knngwkkng','ldngoego');
/*!40000 ALTER TABLE `visitor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visitor_city`
--

DROP TABLE IF EXISTS `visitor_city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visitor_city` (
  `id` int(11) NOT NULL,
  `cid` int(11) DEFAULT NULL,
  `vid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visitor_city`
--

LOCK TABLES `visitor_city` WRITE;
/*!40000 ALTER TABLE `visitor_city` DISABLE KEYS */;
INSERT INTO `visitor_city` VALUES (5,1,4);
INSERT INTO `visitor_city` VALUES (12,1,11);
INSERT INTO `visitor_city` VALUES (17,1,16);
/*!40000 ALTER TABLE `visitor_city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visitor_cm`
--

DROP TABLE IF EXISTS `visitor_cm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visitor_cm` (
  `id` int(11) NOT NULL,
  `cid` int(11) DEFAULT NULL,
  `vid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_persian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visitor_cm`
--

LOCK TABLES `visitor_cm` WRITE;
/*!40000 ALTER TABLE `visitor_cm` DISABLE KEYS */;
INSERT INTO `visitor_cm` VALUES (13,1,11);
INSERT INTO `visitor_cm` VALUES (18,1,16);
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

-- Dump completed on 2017-09-10 17:03:42
