-- Dumping structure for tábla gabtihu1_cv_catalog.dokumentumok
CREATE TABLE IF NOT EXISTS `dokumentumok` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fk_oneletrajz` int(11) unsigned NOT NULL DEFAULT '0',
  `fk_dokumentum_tipus` int(11) unsigned DEFAULT '0',
  `fajl_neve` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Dumping structure for tábla gabtihu1_cv_catalog.dokumentum_tipus
CREATE TABLE IF NOT EXISTS `dokumentum_tipus` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `megnevezes` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table gabtihu1_cv_catalog.dokumentum_tipus: ~3 rows (approximately)
/*!40000 ALTER TABLE `dokumentum_tipus` DISABLE KEYS */;
INSERT INTO `dokumentum_tipus` (`id`, `megnevezes`) VALUES
	(1, 'Fénykép'),
	(2, 'Önéletrajz'),
	(3, 'Motivációs levél'),
	(4, 'Referencia');
/*!40000 ALTER TABLE `dokumentum_tipus` ENABLE KEYS */;

-- Dumping structure for tábla gabtihu1_cv_catalog.egyeb_keszsegek
CREATE TABLE IF NOT EXISTS `egyeb_keszsegek` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fk_oneletrajz` int(11) unsigned NOT NULL DEFAULT '0',
  `leiras` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table gabtihu1_cv_catalog.egyeb_keszsegek: ~2 rows (approximately)
/*!40000 ALTER TABLE `egyeb_keszsegek` DISABLE KEYS */;
INSERT INTO `egyeb_keszsegek` (`id`, `fk_oneletrajz`, `leiras`) VALUES
	(1, 1, '"B" katagóriás jogosítvány'),
	(3, 1, 'Fúvószenekari tag több, mint 10 éve.');
/*!40000 ALTER TABLE `egyeb_keszsegek` ENABLE KEYS */;


-- Dumping structure for tábla gabtihu1_cv_catalog.felhasznalok
CREATE TABLE IF NOT EXISTS `felhasznalok` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nev` varchar(50) NOT NULL DEFAULT '''''',
  `jelszo` tinytext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table gabtihu1_cv_catalog.felhasznalok: ~2 rows (approximately)
/*!40000 ALTER TABLE `felhasznalok` DISABLE KEYS */;
INSERT INTO `felhasznalok` (`id`, `nev`, `jelszo`) VALUES
	(1, 'Mikeux', 'motnaf87'),
	(2, 'teszt', 'teszt');
/*!40000 ALTER TABLE `felhasznalok` ENABLE KEYS */;


-- Dumping structure for tábla gabtihu1_cv_catalog.kepzes_szint
CREATE TABLE IF NOT EXISTS `kepzes_szint` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `megnvezes` varchar(100) NOT NULL DEFAULT '''''',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table gabtihu1_cv_catalog.kepzes_szint: ~5 rows (approximately)
/*!40000 ALTER TABLE `kepzes_szint` DISABLE KEYS */;
INSERT INTO `kepzes_szint` (`id`, `megnvezes`) VALUES
	(1, 'Gimnázium'),
	(2, 'Szakiskola / Szakmunkás képző'),
	(3, 'Középiskola'),
	(4, 'Főiskola'),
	(5, 'Egyetem'),
	(6, 'OKJ-rendszerű tanfolyam');
/*!40000 ALTER TABLE `kepzes_szint` ENABLE KEYS */;


-- Dumping structure for tábla gabtihu1_cv_catalog.nyelvek
CREATE TABLE IF NOT EXISTS `nyelvek` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `NYELVKOD` varchar(4) NOT NULL DEFAULT '',
  `NYELV` varchar(15) NOT NULL DEFAULT '',
  `KARAKTER_KESZLET` varchar(15) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin2;

-- Dumping data for table gabtihu1_cv_catalog.nyelvek: ~22 rows (approximately)
/*!40000 ALTER TABLE `nyelvek` DISABLE KEYS */;
INSERT INTO `nyelvek` (`id`, `NYELVKOD`, `NYELV`, `KARAKTER_KESZLET`) VALUES
	(1, 'BUL', 'Bulgár', 'CE'),
	(2, 'CZE', 'Cseh', 'CE'),
	(3, 'ENG', 'Angol', 'CE'),
	(4, 'FRE', 'Francia', 'CE'),
	(5, 'GER', 'Német', 'CE'),
	(6, 'GRE', 'Görög', 'Greek'),
	(7, 'HUN', 'Magyar', 'CE'),
	(8, 'ITA', 'Olasz', 'CE'),
	(9, 'MAC', 'Macedón', 'CYR'),
	(10, 'NOR', 'Norvég', 'CE'),
	(11, 'POL', 'Lengyel', 'CE'),
	(12, 'POR', 'Portugál', 'CE'),
	(13, 'ROM', 'Román', 'CYR'),
	(14, 'RUS', 'Orosz', 'CYR'),
	(15, 'SCC', 'Szerb', 'CE'),
	(16, 'SCR', 'Horvát', 'CE'),
	(17, 'SLO', 'Szlovák', 'CE'),
	(18, 'SLV', 'Szlovén', 'CE'),
	(19, 'SPA', 'Spanyol', 'CE'),
	(20, 'SWE', 'Svéd', 'CE'),
	(21, 'TUR', 'Török', 'TUR'),
	(22, 'UKR', 'Ukrán', 'CYR');
/*!40000 ALTER TABLE `nyelvek` ENABLE KEYS */;


-- Dumping structure for tábla gabtihu1_cv_catalog.nyelvismeret
CREATE TABLE IF NOT EXISTS `nyelvismeret` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fk_oneletrajz` int(11) unsigned NOT NULL DEFAULT '0',
  `fk_nyelvek` varchar(4) DEFAULT NULL,
  `fk_nyelv_szint` int(11) unsigned DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table gabtihu1_cv_catalog.nyelvismeret: ~4 rows (approximately)
/*!40000 ALTER TABLE `nyelvismeret` DISABLE KEYS */;
INSERT INTO `nyelvismeret` (`id`, `fk_oneletrajz`, `fk_nyelvek`, `fk_nyelv_szint`) VALUES
	(1, 1, '5', 3),
	(2, 1, '3', 2),
	(3, 1, '7', 4);
/*!40000 ALTER TABLE `nyelvismeret` ENABLE KEYS */;


-- Dumping structure for tábla gabtihu1_cv_catalog.nyelv_szint
CREATE TABLE IF NOT EXISTS `nyelv_szint` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `megnevezes` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table gabtihu1_cv_catalog.nyelv_szint: ~4 rows (approximately)
/*!40000 ALTER TABLE `nyelv_szint` DISABLE KEYS */;
INSERT INTO `nyelv_szint` (`id`, `megnevezes`) VALUES
	(1, 'Alapfok'),
	(2, 'Középfok'),
	(3, 'Felsőfok'),
	(4, 'Anyanyelvi szint'),
	(5, 'Nagyon kezdő');
/*!40000 ALTER TABLE `nyelv_szint` ENABLE KEYS */;


-- Dumping structure for tábla gabtihu1_cv_catalog.oneletrajz
CREATE TABLE IF NOT EXISTS `oneletrajz` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fk_felhasznalok` int(11) unsigned NOT NULL DEFAULT '0',
  `hozzaadva` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table gabtihu1_cv_catalog.oneletrajz: ~3 rows (approximately)
/*!40000 ALTER TABLE `oneletrajz` DISABLE KEYS */;
INSERT INTO `oneletrajz` (`id`, `fk_felhasznalok`, `hozzaadva`) VALUES
	(1, 1, '2015-02-18 20:44:45'),
	(2, 2, '2015-02-20 13:59:57'),
	(3, 2, '2015-02-20 14:01:38');
/*!40000 ALTER TABLE `oneletrajz` ENABLE KEYS */;


-- Dumping structure for tábla gabtihu1_cv_catalog.orszagok
CREATE TABLE IF NOT EXISTS `orszagok` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ORSZAG` varchar(4) NOT NULL DEFAULT '',
  `MEGNEVEZES` varchar(20) NOT NULL DEFAULT '',
  `TIPUS` varchar(1) NOT NULL DEFAULT '',
  `PENZNEM` varchar(4) NOT NULL DEFAULT '',
  `NYELVKOD` varchar(4) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=latin2;

-- Dumping data for table gabtihu1_cv_catalog.orszagok: ~51 rows (approximately)
/*!40000 ALTER TABLE `orszagok` DISABLE KEYS */;
INSERT INTO `orszagok` (`id`, `ORSZAG`, `MEGNEVEZES`, `TIPUS`, `PENZNEM`, `NYELVKOD`) VALUES
	(1, 'AT', 'Ausztria', 'U', 'EUR', 'GER'),
	(2, 'AU', 'Ausztrália', 'K', '', 'ENG'),
	(3, 'BE', 'Belgium', 'U', 'EUR', 'FRE'),
	(4, 'BG', 'Bulgária', 'U', 'BGN', 'BUL'),
	(5, 'BR', 'Brazília', 'K', '', 'POR'),
	(6, 'BUss', 'Bulgária', 'K', '', 'ENG'),
	(7, 'BY', 'Belorusszia', 'K', '', 'ENG'),
	(8, 'CA', 'Kanada', 'K', '', 'ENG'),
	(9, 'CH', 'Svájc', 'K', 'CHF', 'GER'),
	(10, 'COL', 'Kolumbia', 'K', '', 'ENG'),
	(11, 'CY', 'Ciprus', 'U', 'EUR', 'ENG'),
	(12, 'CZ', 'Cseh-Köztársaság', 'U', 'CZK', 'CZE'),
	(13, 'DE', 'Németország', 'U', 'EUR', 'GER'),
	(14, 'DK', 'Dánia', 'U', 'DKK', 'ENG'),
	(15, 'EE', 'Észtország', 'U', 'EUR', 'ENG'),
	(16, 'EL', 'Görögország', 'U', 'EUR', 'GRE'),
	(17, 'ES', 'Spanyolország', 'U', 'EUR', 'SPA'),
	(18, 'FI', 'Finnország', 'U', 'EUR', 'ENG'),
	(19, 'FR', 'Franciaország', 'U', 'EUR', 'FRE'),
	(20, 'GB', 'Anglia', 'U', 'GBP', 'ENG'),
	(21, 'GE', 'Grúzia', 'K', '', 'ENG'),
	(22, 'HR', 'Horvátország', 'K', 'HRK', 'SCR'),
	(23, 'HU', 'Magyarország', 'B', 'HUF', 'HUN'),
	(24, 'IE', 'Írország', 'U', 'EUR', 'ENG'),
	(25, 'IL', 'Izrael', 'K', '', 'ENG'),
	(26, 'IS', 'Islands', 'K', '', 'ENG'),
	(27, 'IT', 'Olaszország', 'U', 'EUR', 'ITA'),
	(28, 'KND', 'Koreai NDK', 'K', '', 'ENG'),
	(29, 'LT', 'Litvánia', 'U', 'LTL', 'ENG'),
	(30, 'LU', 'Luxemburg', 'U', 'EUR', 'ENG'),
	(31, 'LV', 'Lettország', 'U', 'LVL', 'ENG'),
	(32, 'MK', 'Macedónia', 'K', 'EUR', 'MAC'),
	(33, 'MT', 'Málta', 'U', 'EUR', 'ENG'),
	(34, 'NL', 'Hollandia', 'U', 'EUR', 'ENG'),
	(35, 'NO', 'Norvégia', 'K', 'NOK', 'NOR'),
	(36, 'NZ', 'Újzéland', 'K', 'EUR', 'ENG'),
	(37, 'PA', 'Panama', 'K', '', 'ENG'),
	(38, 'PL', 'Lengyelország', 'U', 'PLN', 'POL'),
	(39, 'PT', 'Portugália', 'U', 'EUR', 'POR'),
	(40, 'RO', 'Románia', 'U', 'LEI', 'ROM'),
	(41, 'RU', 'Oroszország', 'K', 'RUB', 'RUS'),
	(42, 'SC', 'Seychelle-Szgk', 'K', '', 'ENG'),
	(43, 'SE', 'Svédország', 'U', 'SEK', 'SWE'),
	(44, 'SI', 'Szlovénia', 'U', 'EUR', 'SLV'),
	(45, 'SK', 'Szlovákia', 'U', 'EUR', 'SLO'),
	(46, 'SM', 'Szerbia', 'K', '', 'ENG'),
	(47, 'TR', 'Törökország', 'K', 'TRL', 'TUR'),
	(48, 'UA', 'Ukrajna', 'K', 'UAH', 'UKR'),
	(49, 'UK', 'United Kingdom', 'K', '', 'ENG'),
	(50, 'US', 'USA', 'K', 'USD', 'ENG'),
	(51, 'ZA', 'Dél-Afrika', 'K', '', 'ENG');
/*!40000 ALTER TABLE `orszagok` ENABLE KEYS */;


-- Dumping structure for tábla gabtihu1_cv_catalog.szakmai_tapasztalat
CREATE TABLE IF NOT EXISTS `szakmai_tapasztalat` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fk_oneletrajz` int(11) unsigned NOT NULL DEFAULT '0',
  `munkaado_neve` text NOT NULL,
  `pozicio_neve` text NOT NULL,
  `leiras` text NOT NULL,
  `kezdete` datetime NOT NULL,
  `vege` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table gabtihu1_cv_catalog.szakmai_tapasztalat: ~0 rows (approximately)
/*!40000 ALTER TABLE `szakmai_tapasztalat` DISABLE KEYS */;
INSERT INTO `szakmai_tapasztalat` (`id`, `fk_oneletrajz`, `munkaado_neve`, `pozicio_neve`, `leiras`, `kezdete`, `vege`) VALUES
	(1, 1, 'Szolex KFT Nyíregyháza', 'Számítástechnikai programozó', 'Programozó', '2011-08-01 00:00:00', '2015-08-01 00:00:00');
/*!40000 ALTER TABLE `szakmai_tapasztalat` ENABLE KEYS */;


-- Dumping structure for tábla gabtihu1_cv_catalog.szemelyes_adatok
CREATE TABLE IF NOT EXISTS `szemelyes_adatok` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fk_oneletrajz` int(11) unsigned DEFAULT '0',
  `vezetek_nev` varchar(50) NOT NULL DEFAULT '''''',
  `kereszt_nev` varchar(50) NOT NULL DEFAULT '''''',
  `szul_ido` date DEFAULT NULL,
  `fk_orszagok` int(11) unsigned DEFAULT '0' COMMENT 'fk_orszag',
  `fk_nyelvek` int(11) unsigned DEFAULT '0' COMMENT 'fk_nyelvek',
  `telefonszam` varchar(50) NOT NULL DEFAULT '''''',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table gabtihu1_cv_catalog.szemelyes_adatok: ~2 rows (approximately)
/*!40000 ALTER TABLE `szemelyes_adatok` DISABLE KEYS */;
INSERT INTO `szemelyes_adatok` (`id`, `fk_oneletrajz`, `vezetek_nev`, `kereszt_nev`, `szul_ido`, `fk_orszagok`, `fk_nyelvek`, `telefonszam`) VALUES
	(1, 1, 'Mikó', 'Sándor ', '1987-01-02', 23, 7, '06-30-54-72-567');
/*!40000 ALTER TABLE `szemelyes_adatok` ENABLE KEYS */;


-- Dumping structure for tábla gabtihu1_cv_catalog.tanulmanyok
CREATE TABLE IF NOT EXISTS `tanulmanyok` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fk_oneletrajz` int(11) unsigned DEFAULT '0',
  `fk_kepzes_szint` int(11) unsigned DEFAULT '0',
  `neve` text NOT NULL,
  `kezdete` date NOT NULL,
  `vege` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table gabtihu1_cv_catalog.tanulmanyok: ~2 rows (approximately)
INSERT INTO `tanulmanyok` (`id`, `fk_oneletrajz`, `fk_kepzes_szint`, `neve`, `kezdete`, `vege`) VALUES
	(1, 1, 4, 'Debreceni Egyetem Programtervező Informatikus szak (BSC) ', '2006-08-01', '2011-02-01'),
	(2, 1, 7, 'Korányi Frigyes Gimnázium Nagykálló ', '2001-08-01', '2006-06-01');

