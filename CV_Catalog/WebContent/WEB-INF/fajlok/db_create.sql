-- --------------------------------------------------------
-- Host:                         79.172.252.29
-- Server version:               5.6.22 - MySQL Community Server (GPL)
-- Server OS:                    Linux
-- HeidiSQL Verzi�:              8.1.0.4545
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for gabtihu1_cv_catalog
DROP DATABASE IF EXISTS `gabtihu1_cv_catalog`;
CREATE DATABASE IF NOT EXISTS `gabtihu1_cv_catalog` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `gabtihu1_cv_catalog`;


-- Dumping structure for t�bla gabtihu1_cv_catalog.dokumentumok
DROP TABLE IF EXISTS `dokumentumok`;
CREATE TABLE IF NOT EXISTS `dokumentumok` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fk_oneletrajz` int(11) unsigned NOT NULL DEFAULT '0',
  `fk_dokumentum_tipus` int(11) unsigned NOT NULL DEFAULT '0',
  `fajl_neve` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table gabtihu1_cv_catalog.dokumentumok: ~0 rows (approximately)
/*!40000 ALTER TABLE `dokumentumok` DISABLE KEYS */;
/*!40000 ALTER TABLE `dokumentumok` ENABLE KEYS */;


-- Dumping structure for t�bla gabtihu1_cv_catalog.dokumentum_tipus
DROP TABLE IF EXISTS `dokumentum_tipus`;
CREATE TABLE IF NOT EXISTS `dokumentum_tipus` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `megnevezes` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table gabtihu1_cv_catalog.dokumentum_tipus: ~0 rows (approximately)
/*!40000 ALTER TABLE `dokumentum_tipus` DISABLE KEYS */;
INSERT INTO `dokumentum_tipus` (`id`, `megnevezes`) VALUES
	(1, 'F�nyk�p'),
	(2, '�n�letrajz'),
	(3, 'Motiv�ci�s lev�l'),
	(4, 'Referencia');
/*!40000 ALTER TABLE `dokumentum_tipus` ENABLE KEYS */;


-- Dumping structure for t�bla gabtihu1_cv_catalog.egyeb_keszsegek
DROP TABLE IF EXISTS `egyeb_keszsegek`;
CREATE TABLE IF NOT EXISTS `egyeb_keszsegek` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fk_oneletrajz` int(11) unsigned NOT NULL DEFAULT '0',
  `leiras` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table gabtihu1_cv_catalog.egyeb_keszsegek: ~0 rows (approximately)
/*!40000 ALTER TABLE `egyeb_keszsegek` DISABLE KEYS */;
/*!40000 ALTER TABLE `egyeb_keszsegek` ENABLE KEYS */;


-- Dumping structure for t�bla gabtihu1_cv_catalog.felhasznalok
DROP TABLE IF EXISTS `felhasznalok`;
CREATE TABLE IF NOT EXISTS `felhasznalok` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nev` varchar(50) NOT NULL DEFAULT '''''',
  `jelszo` tinytext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table gabtihu1_cv_catalog.felhasznalok: ~0 rows (approximately)
/*!40000 ALTER TABLE `felhasznalok` DISABLE KEYS */;
/*!40000 ALTER TABLE `felhasznalok` ENABLE KEYS */;


-- Dumping structure for t�bla gabtihu1_cv_catalog.kepzes_szint
DROP TABLE IF EXISTS `kepzes_szint`;
CREATE TABLE IF NOT EXISTS `kepzes_szint` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `megnvezes` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table gabtihu1_cv_catalog.kepzes_szint: ~6 rows (approximately)
/*!40000 ALTER TABLE `kepzes_szint` DISABLE KEYS */;
INSERT INTO `kepzes_szint` (`id`, `megnvezes`) VALUES
	(1, '�ltal�nos iskola'),
	(2, 'Szakiskola / Szakmunk�s k�pz�'),
	(3, 'K�z�piskola'),
	(4, 'F�iskola'),
	(5, 'Egyetem'),
	(6, 'OKJ-rendszer� tanfolyam');
/*!40000 ALTER TABLE `kepzes_szint` ENABLE KEYS */;


-- Dumping structure for t�bla gabtihu1_cv_catalog.nyelvek
DROP TABLE IF EXISTS `nyelvek`;
CREATE TABLE IF NOT EXISTS `nyelvek` (
  `NYELVKOD` varchar(4) NOT NULL DEFAULT '',
  `NYELV` varchar(15) NOT NULL DEFAULT '',
  `KARAKTER_KESZLET` varchar(15) NOT NULL DEFAULT '',
  PRIMARY KEY (`NYELVKOD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2;

-- Dumping data for table gabtihu1_cv_catalog.nyelvek: ~22 rows (approximately)
/*!40000 ALTER TABLE `nyelvek` DISABLE KEYS */;
INSERT INTO `nyelvek` (`NYELVKOD`, `NYELV`, `KARAKTER_KESZLET`) VALUES
	('BUL', 'Bulg�r', 'CE'),
	('CZE', 'Cseh', 'CE'),
	('ENG', 'Angol', 'CE'),
	('FRE', 'Francia', 'CE'),
	('GER', 'N�met', 'CE'),
	('GRE', 'G�r�g', 'Greek'),
	('HUN', 'Magyar', 'CE'),
	('ITA', 'Olasz', 'CE'),
	('MAC', 'Maced�n', 'CYR'),
	('NOR', 'Norv�g', 'CE'),
	('POL', 'Lengyel', 'CE'),
	('POR', 'Portug�l', 'CE'),
	('ROM', 'Rom�n', 'CYR'),
	('RUS', 'Orosz', 'CYR'),
	('SCC', 'Szerb', 'CE'),
	('SCR', 'Horv�t', 'CE'),
	('SLO', 'Szlov�k', 'CE'),
	('SLV', 'Szlov�n', 'CE'),
	('SPA', 'Spanyol', 'CE'),
	('SWE', 'Sv�d', 'CE'),
	('TUR', 'T�r�k', 'TUR'),
	('UKR', 'Ukr�n', 'CYR');
/*!40000 ALTER TABLE `nyelvek` ENABLE KEYS */;


-- Dumping structure for t�bla gabtihu1_cv_catalog.nyelvismeret
DROP TABLE IF EXISTS `nyelvismeret`;
CREATE TABLE IF NOT EXISTS `nyelvismeret` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fk_oneletrajz` int(11) unsigned NOT NULL DEFAULT '0',
  `fk_nyelvek` varchar(4) NOT NULL,
  `fk_nyelv_szint` int(11) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table gabtihu1_cv_catalog.nyelvismeret: ~0 rows (approximately)
/*!40000 ALTER TABLE `nyelvismeret` DISABLE KEYS */;
/*!40000 ALTER TABLE `nyelvismeret` ENABLE KEYS */;


-- Dumping structure for t�bla gabtihu1_cv_catalog.nyelv_szint
DROP TABLE IF EXISTS `nyelv_szint`;
CREATE TABLE IF NOT EXISTS `nyelv_szint` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `megnevezes` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table gabtihu1_cv_catalog.nyelv_szint: ~4 rows (approximately)
/*!40000 ALTER TABLE `nyelv_szint` DISABLE KEYS */;
INSERT INTO `nyelv_szint` (`id`, `megnevezes`) VALUES
	(1, 'Alapfok'),
	(2, 'K�z�pfok'),
	(3, 'Fels�fok'),
	(4, 'Anyanyelvi szint');
/*!40000 ALTER TABLE `nyelv_szint` ENABLE KEYS */;


-- Dumping structure for t�bla gabtihu1_cv_catalog.oneletrajz
DROP TABLE IF EXISTS `oneletrajz`;
CREATE TABLE IF NOT EXISTS `oneletrajz` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fk_felhasznalok` int(11) unsigned NOT NULL DEFAULT '0',
  `hozzaadva` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table gabtihu1_cv_catalog.oneletrajz: ~0 rows (approximately)
/*!40000 ALTER TABLE `oneletrajz` DISABLE KEYS */;
/*!40000 ALTER TABLE `oneletrajz` ENABLE KEYS */;


-- Dumping structure for t�bla gabtihu1_cv_catalog.orszagok
DROP TABLE IF EXISTS `orszagok`;
CREATE TABLE IF NOT EXISTS `orszagok` (
  `ORSZAG` varchar(4) NOT NULL DEFAULT '',
  `MEGNEVEZES` varchar(20) NOT NULL DEFAULT '',
  `TIPUS` varchar(1) NOT NULL DEFAULT '',
  `PENZNEM` varchar(4) NOT NULL DEFAULT '',
  `NYELVKOD` varchar(4) NOT NULL DEFAULT '',
  PRIMARY KEY (`ORSZAG`)
) ENGINE=InnoDB DEFAULT CHARSET=latin2;

-- Dumping data for table gabtihu1_cv_catalog.orszagok: ~51 rows (approximately)
/*!40000 ALTER TABLE `orszagok` DISABLE KEYS */;
INSERT INTO `orszagok` (`ORSZAG`, `MEGNEVEZES`, `TIPUS`, `PENZNEM`, `NYELVKOD`) VALUES
	('AT', 'Ausztria', 'U', 'EUR', 'GER'),
	('AU', 'Ausztr�lia', 'K', '', 'ENG'),
	('BE', 'Belgium', 'U', 'EUR', 'FRE'),
	('BG', 'Bulg�ria', 'U', 'BGN', 'BUL'),
	('BR', 'Braz�lia', 'K', '', 'POR'),
	('BU', 'Bulg�ria', 'K', '', 'ENG'),
	('BY', 'Belorusszia', 'K', '', 'ENG'),
	('CA', 'Kanada', 'K', '', 'ENG'),
	('CH', 'Sv�jc', 'K', 'CHF', 'GER'),
	('COL', 'Kolumbia', 'K', '', 'ENG'),
	('CY', 'Ciprus', 'U', 'EUR', 'ENG'),
	('CZ', 'Cseh-K�zt�rsas�g', 'U', 'CZK', 'CZE'),
	('DE', 'N�metorsz�g', 'U', 'EUR', 'GER'),
	('DK', 'D�nia', 'U', 'DKK', 'ENG'),
	('EE', '�sztorsz�g', 'U', 'EUR', 'ENG'),
	('EL', 'G�r�gorsz�g', 'U', 'EUR', 'GRE'),
	('ES', 'Spanyolorsz�g', 'U', 'EUR', 'SPA'),
	('FI', 'Finnorsz�g', 'U', 'EUR', 'ENG'),
	('FR', 'Franciaorsz�g', 'U', 'EUR', 'FRE'),
	('GB', 'Anglia', 'U', 'GBP', 'ENG'),
	('GE', 'Gr�zia', 'K', '', 'ENG'),
	('HR', 'Horv�torsz�g', 'K', 'HRK', 'SCR'),
	('HU', 'Magyarorsz�g', 'B', 'HUF', 'HUN'),
	('IE', '�rorsz�g', 'U', 'EUR', 'ENG'),
	('IL', 'Izrael', 'K', '', 'ENG'),
	('IS', 'Islands', 'K', '', 'ENG'),
	('IT', 'Olaszorsz�g', 'U', 'EUR', 'ITA'),
	('KND', 'Koreai NDK', 'K', '', 'ENG'),
	('LT', 'Litv�nia', 'U', 'LTL', 'ENG'),
	('LU', 'Luxemburg', 'U', 'EUR', 'ENG'),
	('LV', 'Lettorsz�g', 'U', 'LVL', 'ENG'),
	('MK', 'Maced�nia', 'K', 'EUR', 'MAC'),
	('MT', 'M�lta', 'U', 'EUR', 'ENG'),
	('NL', 'Hollandia', 'U', 'EUR', 'ENG'),
	('NO', 'Norv�gia', 'K', 'NOK', 'NOR'),
	('NZ', '�jz�land', 'K', 'EUR', 'ENG'),
	('PA', 'Panama', 'K', '', 'ENG'),
	('PL', 'Lengyelorsz�g', 'U', 'PLN', 'POL'),
	('PT', 'Portug�lia', 'U', 'EUR', 'POR'),
	('RO', 'Rom�nia', 'U', 'LEI', 'ROM'),
	('RU', 'Oroszorsz�g', 'K', 'RUB', 'RUS'),
	('SC', 'Seychelle-Szgk', 'K', '', 'ENG'),
	('SE', 'Sv�dorsz�g', 'U', 'SEK', 'SWE'),
	('SI', 'Szlov�nia', 'U', 'EUR', 'SLV'),
	('SK', 'Szlov�kia', 'U', 'EUR', 'SLO'),
	('SM', 'Szerbia', 'K', '', 'ENG'),
	('TR', 'T�r�korsz�g', 'K', 'TRL', 'TUR'),
	('UA', 'Ukrajna', 'K', 'UAH', 'UKR'),
	('UK', 'United Kingdom', 'K', '', 'ENG'),
	('US', 'USA', 'K', 'USD', 'ENG'),
	('ZA', 'D�l-Afrika', 'K', '', 'ENG');
/*!40000 ALTER TABLE `orszagok` ENABLE KEYS */;


-- Dumping structure for t�bla gabtihu1_cv_catalog.szakmai_tapasztalat
DROP TABLE IF EXISTS `szakmai_tapasztalat`;
CREATE TABLE IF NOT EXISTS `szakmai_tapasztalat` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fk_oneletrajz` int(11) unsigned NOT NULL DEFAULT '0',
  `munkaado_neve` text NOT NULL,
  `pozicio_neve` text NOT NULL,
  `leiras` text NOT NULL,
  `kezdete` datetime NOT NULL,
  `vege` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table gabtihu1_cv_catalog.szakmai_tapasztalat: ~0 rows (approximately)
/*!40000 ALTER TABLE `szakmai_tapasztalat` DISABLE KEYS */;
/*!40000 ALTER TABLE `szakmai_tapasztalat` ENABLE KEYS */;


-- Dumping structure for t�bla gabtihu1_cv_catalog.szemelyes_adatok
DROP TABLE IF EXISTS `szemelyes_adatok`;
CREATE TABLE IF NOT EXISTS `szemelyes_adatok` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fk_oneletrajz` int(11) unsigned NOT NULL DEFAULT '0',
  `vezetek_nev` varchar(50) NOT NULL DEFAULT '''''',
  `kereszt_nev` varchar(50) NOT NULL DEFAULT '''''',
  `szul_ido` datetime NOT NULL,
  `orszag` varchar(4) NOT NULL DEFAULT '''''' COMMENT 'fk_orszag',
  `anyanyelv` varchar(4) NOT NULL DEFAULT '''''' COMMENT 'fk_nyelvek',
  `telefonszam` varchar(50) NOT NULL DEFAULT '''''',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table gabtihu1_cv_catalog.szemelyes_adatok: ~0 rows (approximately)
/*!40000 ALTER TABLE `szemelyes_adatok` DISABLE KEYS */;
/*!40000 ALTER TABLE `szemelyes_adatok` ENABLE KEYS */;


-- Dumping structure for t�bla gabtihu1_cv_catalog.tanulmanyok
DROP TABLE IF EXISTS `tanulmanyok`;
CREATE TABLE IF NOT EXISTS `tanulmanyok` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fk_oneletrajz` int(11) unsigned NOT NULL DEFAULT '0',
  `fk_kepzes_szint` int(11) unsigned NOT NULL DEFAULT '0',
  `neve` text NOT NULL,
  `kezdete` datetime NOT NULL,
  `vege` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table gabtihu1_cv_catalog.tanulmanyok: ~0 rows (approximately)
/*!40000 ALTER TABLE `tanulmanyok` DISABLE KEYS */;
/*!40000 ALTER TABLE `tanulmanyok` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
