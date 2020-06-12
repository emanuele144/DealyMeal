-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: dbristorante
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `aggiunta`
--

DROP TABLE IF EXISTS `aggiunta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aggiunta` (
  `idaggiunta` int NOT NULL AUTO_INCREMENT,
  `prezzo` float NOT NULL,
  `nomeBibita` varchar(45) NOT NULL,
  `usernameRa` varchar(45) NOT NULL,
  `tipologia` varchar(45) NOT NULL,
  PRIMARY KEY (`idaggiunta`),
  KEY `usernameRa_idx` (`usernameRa`),
  CONSTRAINT `usernameRa` FOREIGN KEY (`usernameRa`) REFERENCES `utente` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aggiunta`
--

LOCK TABLES `aggiunta` WRITE;
/*!40000 ALTER TABLE `aggiunta` DISABLE KEYS */;
INSERT INTO `aggiunta` VALUES (1,2.5,'San Pellegrino','emanuele14','Acqua'),(2,3,'Acqua panna','emanuele14','Acqua'),(4,2,'Vino bho','emanuele14','Vino'),(5,1.5,'Tavernello','emanuele14','Vino'),(6,2.5,'Peroni','emanuele14','Birra'),(7,4,'Moretti','emanuele14','Birra'),(8,3,'Forst','emanuele14','Birra'),(9,3.5,'Tennents','emanuele14','Birra'),(10,4,'Brunello Di Montalcino','emanuele14','Vino'),(11,4.5,'Bolgheri Campo Al Mare','emanuele14','Vino'),(12,3,'Brut Magnum Bellavista','emanuele14','Vino'),(13,5,' Antinori - Tenuta Tignanello','emanuele14','Vino'),(14,3,'Fernet Branca','emanuele14','Digestivo'),(15,2.5,'Chartreuse','emanuele14','Digestivo'),(16,2.6,'Grand Marnier','emanuele14','Digestivo'),(17,3.5,'Cynar','emanuele14','Digestivo'),(18,1.5,'Acqua naturale','ale1926','Acqua'),(19,2.5,'Peroni','ale1926','Birra');
/*!40000 ALTER TABLE `aggiunta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contiene`
--

DROP TABLE IF EXISTS `contiene`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contiene` (
  `idmenu` int NOT NULL,
  `idPiatto` int NOT NULL,
  PRIMARY KEY (`idmenu`,`idPiatto`),
  KEY `idPiatto_idx` (`idPiatto`),
  CONSTRAINT `idmenu` FOREIGN KEY (`idmenu`) REFERENCES `menu` (`idmenu`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idPiatto` FOREIGN KEY (`idPiatto`) REFERENCES `piatto` (`idPiatto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contiene`
--

LOCK TABLES `contiene` WRITE;
/*!40000 ALTER TABLE `contiene` DISABLE KEYS */;
INSERT INTO `contiene` VALUES (34,1),(39,1),(40,1),(41,1),(42,1),(44,1),(46,1),(48,1),(49,1),(50,1),(52,1),(34,3),(39,3),(40,3),(41,3),(46,3),(48,3),(49,3),(50,3),(52,3),(54,3),(42,4),(44,4),(45,4),(51,4),(56,4),(44,13),(45,13),(46,13),(48,13),(56,13),(39,15),(42,15),(34,16),(39,16),(40,16),(41,16),(42,16),(44,16),(45,16),(46,16),(48,16),(49,16),(50,16),(51,16),(52,16),(55,16),(39,17),(40,17),(41,17),(42,17),(44,17),(45,17),(46,17),(48,17),(49,17),(50,17),(51,17),(52,17),(51,24),(54,35),(56,36),(53,37),(55,37),(53,38),(55,38),(53,39),(56,39),(54,40),(55,41),(54,42),(53,43),(55,44),(54,45),(53,47),(56,48),(57,50),(58,50),(59,50),(57,51),(58,51),(59,51),(57,52),(58,52),(59,52),(57,53),(58,53),(59,53),(57,54),(58,54),(59,54);
/*!40000 ALTER TABLE `contiene` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `idmenu` int NOT NULL AUTO_INCREMENT,
  `giorno` varchar(45) NOT NULL,
  `usernameRi` varchar(45) NOT NULL,
  `prezzo` float DEFAULT NULL,
  PRIMARY KEY (`idmenu`),
  KEY `usernameRi` (`usernameRi`),
  CONSTRAINT `usernameRi` FOREIGN KEY (`usernameRi`) REFERENCES `utente` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (34,'2020-05-14','emanuele14',0),(39,'2020-05-06','emanuele14',14),(40,'2020-05-07','emanuele14',22),(41,'2020-05-08','emanuele14',22),(42,'2020-05-15','emanuele14',16),(44,'2020-05-16','emanuele14',15),(45,'2020-05-20','emanuele14',12),(46,'2020-05-25','emanuele14',12),(48,'2020-05-19','emanuele14',13),(49,'2020-05-17','emanuele14',21),(50,'2020-05-27','emanuele14',21),(51,'2020-05-29','emanuele14',20),(52,'2020-05-26','emanuele14',21),(53,'2020-05-28','emanuele14',15),(54,'2020-05-30','emanuele14',14),(55,'2020-05-31','emanuele14',15.5),(56,'2020-06-01','emanuele14',13.5),(57,'2020-05-27','ale1926',20.6),(58,'2020-05-26','ale1926',20.6),(59,'2020-05-28','ale1926',20.6);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamento`
--

DROP TABLE IF EXISTS `pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagamento` (
  `idpagamento` int NOT NULL AUTO_INCREMENT,
  `tipologiaCarta` varchar(45) NOT NULL,
  `importo` float NOT NULL,
  `numCarta` int NOT NULL,
  `numeroPrenotati` int NOT NULL,
  `orarioPrenotazione` int NOT NULL,
  `usernameCl` varchar(45) NOT NULL,
  `idMenuP` int DEFAULT NULL,
  `idAggiunta` int NOT NULL,
  PRIMARY KEY (`idpagamento`),
  KEY `username_idx` (`usernameCl`),
  KEY `idMenuP_idx` (`idMenuP`),
  KEY `idAggiunta_idx` (`idAggiunta`),
  CONSTRAINT `idAggiunta` FOREIGN KEY (`idAggiunta`) REFERENCES `aggiunta` (`idaggiunta`),
  CONSTRAINT `idMenuP` FOREIGN KEY (`idMenuP`) REFERENCES `menu` (`idmenu`) ON UPDATE CASCADE,
  CONSTRAINT `usernameCl` FOREIGN KEY (`usernameCl`) REFERENCES `utente` (`username`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamento`
--

LOCK TABLES `pagamento` WRITE;
/*!40000 ALTER TABLE `pagamento` DISABLE KEYS */;
INSERT INTO `pagamento` VALUES (44,'PayPal',2900,435234,200,17,'paolo1',46,1),(45,'Bancomat',2900,3243,190,16,'vera996',46,1),(48,'PayPal',22,53453,1,13,'paolo1',51,4),(49,'PayPal',22,43532,1,20,'manu996',51,4),(50,'PayPal',315,4523454,18,14,'manu996',53,1),(51,'Bancomat',227.5,5654654,13,19,'vera996',53,6),(54,'PayPal',32,343532,2,13,'paolo1',56,1);
/*!40000 ALTER TABLE `pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `piatto`
--

DROP TABLE IF EXISTS `piatto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `piatto` (
  `tipologia` varchar(45) DEFAULT NULL,
  `nomeP` varchar(45) DEFAULT NULL,
  `descrizione` varchar(90) DEFAULT NULL,
  `prezzo` float DEFAULT NULL,
  `ristoratore` varchar(45) NOT NULL,
  `idPiatto` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idPiatto`),
  KEY `ristoratore` (`ristoratore`),
  CONSTRAINT `ristoratore` FOREIGN KEY (`ristoratore`) REFERENCES `utente` (`username`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `piatto`
--

LOCK TABLES `piatto` WRITE;
/*!40000 ALTER TABLE `piatto` DISABLE KEYS */;
INSERT INTO `piatto` VALUES ('Primo piatto','Carbonara','Piatto tipico romano',3,'emanuele14',1),('Secondo piatto','Pizza','Pizza napoletana',2,'fabio',2),('Contorno','Patatine','Patate al forno',2,'emanuele14',3),('Contorno','Formaggio','Formaggio campano',2,'emanuele14',4),('Antipasto','Fritturina mista di mare','Gamberetti, calamari, triglie, alici e semola',2,'emanuele14',13),('Antipasto','Bruschetta con pomodoro','Pomodoro del piennolo',1,'emanuele14',15),('Secondo piatto','Bistecca con patatine','Bistecca fiorentina',4,'emanuele14',16),('Dolce','Torta kinder bueno','Torta tipica italiana',2,'emanuele14',17),('Primo piatto','Spaghetti al pomodoro','Piatto tipico napoletano',2,'emanuele14',24),('Dolce','Pastiera','Dolce tipico napoletano',4,'emanuele14',35),('Dolce','Cornetto','Cornetto ripeno alla nutella',1.5,'emanuele14',36),('Dolce','Sfogliatella frolla e riccia','Dolce tipico napoletano',3,'emanuele14',37),('Contorno','Melanzane a funghetto','Contorno tipico napoletano',2,'emanuele14',38),('Secondo piatto','Spaghetti alle vongole','Speghetti con vongole verace',4,'emanuele14',39),('Antipasto','Pizza fritta','Pizza ripiena di cicoli e ricotta',2.5,'emanuele14',40),('Antipasto','Fior di latte e provola','Mozzarella di latte e provola affumicata',3.5,'emanuele14',41),('Secondo piatto','Salsiccia e friarielli','Pilastro della cucina napoletana',2.5,'emanuele14',42),('Antipasto','Pizza alla scarole','Pizza con scarole,olive,capperi',2,'emanuele14',43),('Primo piatto','Pasta e patate','Piatto tipico napoletano',3,'emanuele14',44),('Primo piatto','Soffritto','Piatto tipico napoletano',3,'emanuele14',45),('Primo piatto','Genovese di mare','Piatto tipico napoletano',3.5,'emanuele14',46),('Primo piatto','Ziti lardiati','Piatto tipico napoletano',4,'emanuele14',47),('Primo piatto','Lasagna napoletana','Piatto tipico napoletano',4,'emanuele14',48),('Primo piatto','Carbonara','Piatto tipico romano',4.5,'ale1926',49),('Contorno','Patatine','Patatine fritte',2.5,'ale1926',50),('Antipasto','Fritturina di mare','Gamberetti, calamari e triglie',5.5,'ale1926',51),('Dolce','Pastiera','Dolce tipico napoletano',3.5,'ale1926',52),('Secondo piatto','Spaghetti alle vongole','Spaghetti con vongole verace',4.5,'ale1926',53),('Primo piatto','Lasagna napoletana','Piatto tipico napoletano',4.6,'ale1926',54);
/*!40000 ALTER TABLE `piatto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ristorante`
--

DROP TABLE IF EXISTS `ristorante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ristorante` (
  `idristorante` int NOT NULL AUTO_INCREMENT,
  `Nome_ristorante` varchar(45) NOT NULL,
  `Via` varchar(45) NOT NULL,
  `CAP` int NOT NULL,
  `Citta` varchar(45) NOT NULL,
  `capienza` int NOT NULL,
  `orarioApertura` int NOT NULL,
  `orarioChiusura` int NOT NULL,
  `usernameRist` varchar(45) NOT NULL,
  `esitoR` int NOT NULL,
  PRIMARY KEY (`idristorante`),
  KEY `username_idx` (`usernameRist`),
  CONSTRAINT `username` FOREIGN KEY (`usernameRist`) REFERENCES `utente` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ristorante`
--

LOCK TABLES `ristorante` WRITE;
/*!40000 ALTER TABLE `ristorante` DISABLE KEYS */;
INSERT INTO `ristorante` VALUES (2,'AdduFabio','Via degli innamorati',80976,'Napoli',0,0,0,'fabio',0),(8,'DaAle','Via delle rose',80123,'Milano',300,9,21,'ale1926',1),(12,'DaManu','Via Napoli',3456,'Napoli',400,10,23,'emanuele14',1),(13,'Franco1926','Via delle patate',80995,'Napoli',400,10,23,'franco',1),(14,'DaLuigi','Via delle sirene',80123,'Roma',600,8,23,'luigi',1),(15,'Gino','Via della libertà',80125,'Sicilia',60,8,22,'ciro12',0);
/*!40000 ALTER TABLE `ristorante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spedizione`
--

DROP TABLE IF EXISTS `spedizione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spedizione` (
  `idspedizione` int NOT NULL AUTO_INCREMENT,
  `Via` varchar(45) NOT NULL,
  `CAP` int NOT NULL,
  `citta` varchar(45) NOT NULL,
  `giorno` varchar(45) NOT NULL,
  `ora` int NOT NULL,
  `quantitaP` int NOT NULL,
  `utente` varchar(45) NOT NULL,
  `idPiattoS` int NOT NULL,
  `tipologiaCarta` varchar(45) NOT NULL,
  `importo` float NOT NULL,
  `numCarta` int NOT NULL,
  PRIMARY KEY (`idspedizione`),
  KEY `utente_idx` (`utente`),
  KEY `idPiattoS_idx` (`idPiattoS`),
  CONSTRAINT `idPiattoS` FOREIGN KEY (`idPiattoS`) REFERENCES `piatto` (`idPiatto`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `utente` FOREIGN KEY (`utente`) REFERENCES `utente` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spedizione`
--

LOCK TABLES `spedizione` WRITE;
/*!40000 ALTER TABLE `spedizione` DISABLE KEYS */;
INSERT INTO `spedizione` VALUES (1,'Via Napoli',89001,'Napoli','2020-05-18',9,2,'paolo1',16,'Carta di credito',7,45453454),(5,'Via Voli',5672,'Napoli','2020-05-18',20,6,'manu996',1,'PayPal',19,3432231),(6,'viale franco',80123,'Napoli','2020-05-28',11,5,'paolo1',48,'PayPal',21,43543),(7,'Via della rocca',80145,'Napoli','2020-05-28',18,15,'vera996',37,'PayPal',46,5436354),(8,'via dei fiori',8900,'Napoli','2020-05-28',15,8,'manu996',38,'Carta di credito',17,435342),(9,'Via roma',80765,'Napoli','2020-05-28',14,8,'paolo1',41,'PayPal',29,34325),(10,'Via delle brecce',80156,'Napoli','2020-05-28',10,5,'paolo1',51,'Carta di credito',28.5,32432),(11,'Via roma',80123,'Napoli','2020-05-28',14,5,'paolo1',53,'PayPal',23.5,543645),(12,'Via delle ginestre',80154,'Napoli','2020-05-29',14,1,'paolo1',37,'PayPal',4,656758);
/*!40000 ALTER TABLE `spedizione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `username` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `ruolo` int NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES ('ale1926','Alessandro','Gervasio','1234',1),('ciro12','Ciro','Esposito','1234',1),('emanuele14','Emanuele','Mancini','1234',1),('fabio','turbo','ugo','1234',1),('franco','Franco','Brando','1234',1),('franco1926','Gervasio','Franco','1234',1),('luigi','Luigi','Manco','1234',1),('manu996','Manu','Mancini','1234',0),('paolo1','paolo','garo','1234',0),('vera1','Veronica','Filosa','1234',1),('vera996','Veronica','Filosa','1234',0);
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-27 18:38:45
