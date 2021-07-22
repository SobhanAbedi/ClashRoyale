CREATE DATABASE  IF NOT EXISTS `clash_royale` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `clash_royale`;
-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: clash_royale
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `building_constant_attributes`
--

DROP TABLE IF EXISTS `building_constant_attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `building_constant_attributes` (
  `name` varchar(45) NOT NULL,
  `hit_speed` float NOT NULL DEFAULT '1',
  `target` varchar(45) NOT NULL DEFAULT 'all',
  `range` float NOT NULL DEFAULT '0.5',
  `lifetime` float NOT NULL DEFAULT '0',
  `projectile` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`name`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `building_constant_attributes`
--

LOCK TABLES `building_constant_attributes` WRITE;
/*!40000 ALTER TABLE `building_constant_attributes` DISABLE KEYS */;
INSERT INTO `building_constant_attributes` VALUES ('Cannon',0.8,'ground',5.5,30,3),('Inferno Tower',0.4,'all',6,40,4),('King Tower',1,'all',7,0,3),('Princess Tower',0.8,'all',7.5,0,3);
/*!40000 ALTER TABLE `building_constant_attributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card_forces`
--

DROP TABLE IF EXISTS `card_forces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card_forces` (
  `id` int NOT NULL AUTO_INCREMENT,
  `card_name` varchar(45) NOT NULL,
  `force_name` varchar(45) NOT NULL,
  `force_kind` int NOT NULL DEFAULT '1',
  `round` int NOT NULL DEFAULT '1',
  `rel_position` point NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card_forces`
--

LOCK TABLES `card_forces` WRITE;
/*!40000 ALTER TABLE `card_forces` DISABLE KEYS */;
INSERT INTO `card_forces` VALUES (1,'Barbarians','Barbarian',1,1,_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0'),(2,'Barbarians','Barbarian',1,1,_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0?\0\0\0\0\0\0\0\0'),(3,'Barbarians','Barbarian',1,1,_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0ø\0\0\0\0\0\0\0\0'),(4,'Barbarians','Barbarian',1,1,_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0?'),(5,'Archers','Archer',1,1,_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0?\0\0\0\0\0\0\0\0'),(6,'Archers','Archer',1,1,_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0ø\0\0\0\0\0\0\0\0'),(7,'Baby Dragon','Baby Dragon',1,1,_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0'),(8,'Wizard','Wizard',1,1,_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0'),(9,'Mini P.E.K.K.A.','Mini P.E.K.K.A.',1,1,_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0'),(10,'Giant','Giant',1,1,_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0'),(11,'Valkyrie','Valkyrie',1,1,_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0'),(12,'Rage','Rage',3,1,_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0'),(13,'Fireball','Fireball',3,1,_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0'),(14,'Arrows','Arrows',3,1,_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0'),(15,'Cannon','Cannon',2,1,_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0'),(16,'Inferno Tower','Inferno Tower',2,1,_binary '\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0');
/*!40000 ALTER TABLE `card_forces` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cards`
--

DROP TABLE IF EXISTS `cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cards` (
  `name` varchar(45) NOT NULL,
  `cost` int NOT NULL DEFAULT '2',
  PRIMARY KEY (`name`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cards`
--

LOCK TABLES `cards` WRITE;
/*!40000 ALTER TABLE `cards` DISABLE KEYS */;
INSERT INTO `cards` VALUES ('Archers',3),('Arrows',3),('Baby Dragon',4),('Barbarians',5),('Cannon',6),('Fireball',4),('Giant',5),('Inferno Tower',5),('Mini P.E.K.K.A.',4),('Rage',3),('Valkyrie',4),('Wizard',5);
/*!40000 ALTER TABLE `cards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deck`
--

DROP TABLE IF EXISTS `deck`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deck` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userid` int NOT NULL,
  `place` int NOT NULL,
  `card_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deck`
--

LOCK TABLES `deck` WRITE;
/*!40000 ALTER TABLE `deck` DISABLE KEYS */;
INSERT INTO `deck` VALUES (1,6,1,NULL),(2,6,2,'Barbarians'),(3,6,3,NULL),(4,6,4,NULL),(5,6,5,NULL),(6,6,6,NULL),(7,6,7,NULL),(8,6,8,NULL);
/*!40000 ALTER TABLE `deck` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `force_kind`
--

DROP TABLE IF EXISTS `force_kind`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `force_kind` (
  `name` varchar(45) NOT NULL,
  `kind` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`name`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `force_kind`
--

LOCK TABLES `force_kind` WRITE;
/*!40000 ALTER TABLE `force_kind` DISABLE KEYS */;
INSERT INTO `force_kind` VALUES ('Archer',1),('Arrows',3),('Baby Dragon',1),('Barbarian',1),('Cannon',2),('Fireball',3),('Giant',1),('Inferno Tower',2),('King Tower',2),('Mini P.E.K.K.A.',1),('Princess Tower',2),('Rage',3),('Valkyrie',1),('Wizard',1);
/*!40000 ALTER TABLE `force_kind` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `level_score`
--

DROP TABLE IF EXISTS `level_score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `level_score` (
  `level` int NOT NULL,
  `score` int NOT NULL,
  PRIMARY KEY (`level`),
  UNIQUE KEY `level_UNIQUE` (`level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `level_score`
--

LOCK TABLES `level_score` WRITE;
/*!40000 ALTER TABLE `level_score` DISABLE KEYS */;
INSERT INTO `level_score` VALUES (1,300),(2,500),(3,900),(4,1700),(5,2500);
/*!40000 ALTER TABLE `level_score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `soldiers_constant_attributes`
--

DROP TABLE IF EXISTS `soldiers_constant_attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `soldiers_constant_attributes` (
  `name` varchar(45) NOT NULL,
  `hit_speed` float NOT NULL DEFAULT '1',
  `speed_tier` int NOT NULL DEFAULT '1',
  `target` varchar(45) NOT NULL DEFAULT 'all',
  `range` float NOT NULL DEFAULT '0.5',
  `area_splash` tinyint(1) NOT NULL DEFAULT '0',
  `flies` tinyint(1) NOT NULL DEFAULT '0',
  `projectile` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`name`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `soldiers_constant_attributes`
--

LOCK TABLES `soldiers_constant_attributes` WRITE;
/*!40000 ALTER TABLE `soldiers_constant_attributes` DISABLE KEYS */;
INSERT INTO `soldiers_constant_attributes` VALUES ('Archer',1.2,2,'all',5,0,0,1),('Baby Dragon',1.8,3,'all',3,1,1,2),('Barbarian',1.5,2,'ground',0.5,0,0,0),('Giant',1.5,1,'buildings',0.5,0,0,0),('Mini P.E.K.K.A.',1.8,3,'ground',0.5,0,0,0),('Valkyrie',1.5,2,'ground',0.5,1,0,0),('Wizard',1.7,2,'all',5,1,0,2);
/*!40000 ALTER TABLE `soldiers_constant_attributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spell_constant_attributes`
--

DROP TABLE IF EXISTS `spell_constant_attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spell_constant_attributes` (
  `name` varchar(45) NOT NULL,
  `radius` float NOT NULL DEFAULT '1',
  PRIMARY KEY (`name`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spell_constant_attributes`
--

LOCK TABLES `spell_constant_attributes` WRITE;
/*!40000 ALTER TABLE `spell_constant_attributes` DISABLE KEYS */;
INSERT INTO `spell_constant_attributes` VALUES ('Arrows',3),('Fireball',2.5),('Rage',5);
/*!40000 ALTER TABLE `spell_constant_attributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spell_variable_attributes`
--

DROP TABLE IF EXISTS `spell_variable_attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spell_variable_attributes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `level` int NOT NULL DEFAULT '1',
  `damage` int DEFAULT NULL,
  `duration` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spell_variable_attributes`
--

LOCK TABLES `spell_variable_attributes` WRITE;
/*!40000 ALTER TABLE `spell_variable_attributes` DISABLE KEYS */;
INSERT INTO `spell_variable_attributes` VALUES (1,'Rage',1,NULL,6),(2,'Rage',2,NULL,6.5),(3,'Rage',3,NULL,7),(4,'Rage',4,NULL,7.5),(5,'Rage',5,NULL,8),(6,'Fireball',1,325,NULL),(7,'Fireball',2,357,NULL),(8,'Fireball',3,393,NULL),(9,'Fireball',4,432,NULL),(10,'Fireball',5,474,NULL),(11,'Arrows',1,144,NULL),(12,'Arrows',2,156,NULL),(13,'Arrows',3,174,NULL),(14,'Arrows',4,189,NULL),(15,'Arrows',5,210,NULL);
/*!40000 ALTER TABLE `spell_variable_attributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `salt` binary(16) NOT NULL,
  `password` binary(128) NOT NULL,
  `score` int unsigned NOT NULL DEFAULT '0',
  `coins` int unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`userid`),
  UNIQUE KEY `userid_UNIQUE` (`userid`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (6,'SobhanAbedi',_binary 'â\„\¬\ÔIöîØ\Œ,îX3_ûß',_binary '$™\'\À=îò~úÒñ1\‡ÄÙG*2≥òµ;0üa\÷Fl˜HTã≈∑öî,\◊i“ä\ÎtÉ5 ¶ÛF|†vYNÚ\⁄~>]Cã¸\"YW¯LHJCö\‡Àôv%0õ∞Cg\nÇ\ƒøWG˚çbOJ~öC≈É\Ã\∆ÒÒé1',200,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `variable_attributes`
--

DROP TABLE IF EXISTS `variable_attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `variable_attributes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `level` int NOT NULL DEFAULT '1',
  `hp` int NOT NULL DEFAULT '100',
  `damage` int NOT NULL DEFAULT '100',
  `starting_damage` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `variable_attributes`
--

LOCK TABLES `variable_attributes` WRITE;
/*!40000 ALTER TABLE `variable_attributes` DISABLE KEYS */;
INSERT INTO `variable_attributes` VALUES (1,'Barbarian',1,300,75,NULL),(2,'Barbarian',2,330,82,NULL),(3,'Barbarian',3,363,90,NULL),(4,'Barbarian',4,438,99,NULL),(5,'Barbarian',5,480,109,NULL),(6,'Archer',1,125,33,NULL),(7,'Archer',2,137,44,NULL),(8,'Archer',3,151,48,NULL),(9,'Archer',4,166,53,NULL),(10,'Archer',5,182,58,NULL),(11,'Baby Dragon',1,800,100,NULL),(12,'Baby Dragon',2,880,110,NULL),(13,'Baby Dragon',3,968,121,NULL),(14,'Baby Dragon',4,1064,133,NULL),(27,'Baby Dragon',5,1168,146,NULL),(28,'Wizard',1,340,130,NULL),(29,'Wizard',2,374,143,NULL),(30,'Wizard',3,411,157,NULL),(31,'Wizard',4,452,172,NULL),(32,'Wizard',5,496,189,NULL),(33,'Mini P.E.K.K.A.',1,600,325,NULL),(34,'Mini P.E.K.K.A.',2,660,357,NULL),(35,'Mini P.E.K.K.A.',3,726,393,NULL),(36,'Mini P.E.K.K.A.',4,798,432,NULL),(37,'Mini P.E.K.K.A.',5,879,474,NULL),(38,'Giant',1,2000,126,NULL),(39,'Giant',2,2200,138,NULL),(40,'Giant',3,2420,152,NULL),(41,'Giant',4,2660,167,NULL),(42,'Giant',5,2920,183,NULL),(43,'Valkyrie',1,880,120,NULL),(44,'Valkyrie',2,968,132,NULL),(45,'Valkyrie',3,1064,145,NULL),(46,'Valkyrie',4,1170,159,NULL),(47,'Valkyrie',5,1284,175,NULL),(48,'King Tower',1,2400,50,NULL),(49,'King Tower',2,2568,53,NULL),(50,'King Tower',3,2736,57,NULL),(51,'King Tower',4,2904,60,NULL),(52,'King Tower',5,3096,64,NULL),(53,'Princess Tower',1,1400,50,NULL),(54,'Princess Tower',2,1512,54,NULL),(55,'Princess Tower',3,1624,58,NULL),(56,'Princess Tower',4,1750,62,NULL),(57,'Princess Tower',5,1890,69,NULL),(58,'Cannon',1,380,60,NULL),(59,'Cannon',2,418,66,NULL),(60,'Cannon',3,459,72,NULL),(61,'Cannon',4,505,79,NULL),(62,'Cannon',5,554,87,NULL),(63,'Inferno Tower',1,800,400,20),(64,'Inferno Tower',2,880,440,22),(65,'Inferno Tower',3,968,484,24),(66,'Inferno Tower',4,1064,532,26),(67,'Inferno Tower',5,1168,584,29);
/*!40000 ALTER TABLE `variable_attributes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-20 17:30:52
