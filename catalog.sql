-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.5.9-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for catalog
CREATE DATABASE IF NOT EXISTS `catalog` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `catalog`;

-- Dumping structure for table catalog.clase
CREATE TABLE IF NOT EXISTS `clase` (
  `Nume` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `DiriginteId` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`Nume`),
  KEY `FK_clase_profesori` (`DiriginteId`),
  CONSTRAINT `FK_clase_profesori` FOREIGN KEY (`DiriginteId`) REFERENCES `profesori` (`Nume`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.

-- Dumping structure for table catalog.materii
CREATE TABLE IF NOT EXISTS `materii` (
  `Nume` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`Nume`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.

-- Dumping structure for table catalog.profesori
CREATE TABLE IF NOT EXISTS `profesori` (
  `Nume` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaterieId` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`Nume`),
  KEY `FK_profesori_materii` (`MaterieId`),
  CONSTRAINT `FK_profesori_materii` FOREIGN KEY (`MaterieId`) REFERENCES `materii` (`Nume`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.

-- Dumping structure for table catalog.situatie
CREATE TABLE IF NOT EXISTS `situatie` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `StudentId` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Valoare` int(11) NOT NULL DEFAULT 0,
  `Data` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `MaterieId` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_situatie_studenti` (`StudentId`),
  KEY `FK_situatie_materii` (`MaterieId`) USING BTREE,
  CONSTRAINT `FK_situatie_materii` FOREIGN KEY (`MaterieId`) REFERENCES `materii` (`Nume`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_situatie_studenti` FOREIGN KEY (`StudentId`) REFERENCES `studenti` (`Nume`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.

-- Dumping structure for table catalog.studenti
CREATE TABLE IF NOT EXISTS `studenti` (
  `Nume` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `ClasaId` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`Nume`),
  KEY `FK_studenti_clase` (`ClasaId`),
  CONSTRAINT `FK_studenti_clase` FOREIGN KEY (`ClasaId`) REFERENCES `clase` (`Nume`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
