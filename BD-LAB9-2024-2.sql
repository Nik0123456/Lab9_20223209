CREATE DATABASE  IF NOT EXISTS `lab9` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `lab9`;
-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: localhost    Database: lab9
-- ------------------------------------------------------
-- Server version	8.0.24

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
-- Table structure for table `arbitro`
--

DROP TABLE IF EXISTS `arbitro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `arbitro` (
  `idArbitro` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `pais` varchar(45) NOT NULL,
  PRIMARY KEY (`idArbitro`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arbitro`
--

LOCK TABLES `arbitro` WRITE;
/*!40000 ALTER TABLE `arbitro` DISABLE KEYS */;
INSERT INTO `arbitro` VALUES (1,'Néstor Pitano','Argentina'),(2,'Eber Aquino','Paraguay'),(3,'Guillermo Guerrero','Ecuador'),(4,'Diego Haro','Peru');
/*!40000 ALTER TABLE `arbitro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadio`
--

DROP TABLE IF EXISTS `estadio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estadio` (
  `idEstadio` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `provincia` varchar(45) NOT NULL,
  `club` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idEstadio`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadio`
--

LOCK TABLES `estadio` WRITE;
/*!40000 ALTER TABLE `estadio` DISABLE KEYS */;
INSERT INTO `estadio` VALUES (1,'Estadio Nacional','Lima Metropolitana',NULL),(2,'Estadio Nacional Juan  Martínez Prádamo','Santiago',NULL),(3,'Estadio Alberto J. Armando \"La Bombonera\"','Buenos Aires','Boca Juniors');
/*!40000 ALTER TABLE `estadio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jugador`
--

DROP TABLE IF EXISTS `jugador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jugador` (
  `idJugador` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `edad` int NOT NULL,
  `posicion` varchar(45) NOT NULL,
  `club` varchar(45) NOT NULL,
  `sn_idSeleccion` int NOT NULL,
  PRIMARY KEY (`idJugador`),
  KEY `fk_jugadores_seleccionesnacionales_idx` (`sn_idSeleccion`),
  CONSTRAINT `fk_jugadores_seleccionesnacionales` FOREIGN KEY (`sn_idSeleccion`) REFERENCES `seleccion` (`idSeleccion`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugador`
--

LOCK TABLES `jugador` WRITE;
/*!40000 ALTER TABLE `jugador` DISABLE KEYS */;
INSERT INTO `jugador` VALUES (1,'Gianluca Lapadula',30,'Delantero','Benevento Calcio',1),(2,'Paolo Guerrero',36,'Delantero','Sporting Club Internacional',1),(3,'Alexis Sánchez',31,'Delantero ','Inter de Milán',2),(4,'Mauricio Isla',32,'Defensa','Clube de Regatas do Flamengo',2),(5,'Lionel Andres Messi Cuccittini',33,'Delantero','Futbol Club Barcelona',3),(6,'Franco Armani',34,'Portero','Club Atlético River Plate',3);
/*!40000 ALTER TABLE `jugador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partido`
--

DROP TABLE IF EXISTS `partido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partido` (
  `idPartido` int NOT NULL AUTO_INCREMENT,
  `seleccionLocal` int NOT NULL,
  `seleccionVisitante` int NOT NULL,
  `arbitro` int NOT NULL,
  `fecha` date NOT NULL,
  `numeroJornada` int NOT NULL,
  PRIMARY KEY (`idPartido`),
  KEY `fk_partidos_seleccionesnacionales1_idx` (`seleccionLocal`),
  KEY `fk_partidos_seleccionesnacionales2_idx` (`seleccionVisitante`),
  KEY `fk_partidos_arbitros1_idx` (`arbitro`),
  CONSTRAINT `fk_partidos_arbitros1` FOREIGN KEY (`arbitro`) REFERENCES `arbitro` (`idArbitro`),
  CONSTRAINT `fk_partidos_seleccionesnacionales1` FOREIGN KEY (`seleccionLocal`) REFERENCES `seleccion` (`idSeleccion`),
  CONSTRAINT `fk_partidos_seleccionesnacionales2` FOREIGN KEY (`seleccionVisitante`) REFERENCES `seleccion` (`idSeleccion`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partido`
--

LOCK TABLES `partido` WRITE;
/*!40000 ALTER TABLE `partido` DISABLE KEYS */;
INSERT INTO `partido` VALUES (1,2,1,1,'2020-11-13',3),(2,1,3,2,'2020-11-17',4);
/*!40000 ALTER TABLE `partido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seleccion`
--

DROP TABLE IF EXISTS `seleccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seleccion` (
  `idSeleccion` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `tecnico` varchar(45) NOT NULL,
  `estadio_idEstadio` int NOT NULL,
  PRIMARY KEY (`idSeleccion`),
  KEY `fk_seleccionesnacionales_estadios_idx` (`estadio_idEstadio`),
  CONSTRAINT `fk_seleccionesnacionales_estadio1` FOREIGN KEY (`estadio_idEstadio`) REFERENCES `estadio` (`idEstadio`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seleccion`
--

LOCK TABLES `seleccion` WRITE;
/*!40000 ALTER TABLE `seleccion` DISABLE KEYS */;
INSERT INTO `seleccion` VALUES (1,'Peru','Ricardo Gareca',1),(2,'Chile','Reinaldo Rueda',2),(3,'Argentina','Lieonel Scaloni',3);
/*!40000 ALTER TABLE `seleccion` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-07 21:22:47
