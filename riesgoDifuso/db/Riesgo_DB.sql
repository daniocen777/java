/*
SQLyog Job Agent v11.11 (64 bit) Copyright(c) Webyog Inc. All Rights Reserved.


MySQL - 5.7.14 : Database - riesgodb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`riesgodb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `riesgodb`;

/*Table structure for table `actespec` */

DROP TABLE IF EXISTS `actespec`;

CREATE TABLE `actespec` (
  `idactespec` int(11) NOT NULL AUTO_INCREMENT,
  `idactivo` int(11) NOT NULL,
  `nombactespec` varchar(45) NOT NULL,
  PRIMARY KEY (`idactespec`),
  KEY `fk_idacttivo_idx` (`idactivo`),
  CONSTRAINT `fk_idacttivo` FOREIGN KEY (`idactivo`) REFERENCES `activo` (`idactivo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `actespec` */

insert  into `actespec` values (1,31,'LG 3010 XS'),(3,34,'XEROX MINI'),(4,35,'WORD, POINT, EXCEL, ACCES'),(5,36,'VENTAS Y COMPRAS DE GASEOSAS'),(6,37,'HP 3000'),(7,37,'CPU GAMER HP'),(8,35,'VIZAGI');

/*Table structure for table `actespecamen` */

DROP TABLE IF EXISTS `actespecamen`;

CREATE TABLE `actespecamen` (
  `idactespecamen` int(11) NOT NULL AUTO_INCREMENT,
  `idactespec` int(11) NOT NULL,
  `idamenaza` int(11) NOT NULL,
  PRIMARY KEY (`idactespecamen`),
  KEY `fk_idactespec_idx` (`idactespec`),
  KEY `fk_idamenaza_idx` (`idamenaza`),
  CONSTRAINT `fk_idactespec` FOREIGN KEY (`idactespec`) REFERENCES `actespec` (`idactespec`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_idamenaza` FOREIGN KEY (`idamenaza`) REFERENCES `amenaza` (`idamenaza`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `actespecamen` */

insert  into `actespecamen` values (1,1,3),(2,3,3),(4,8,1),(5,7,3),(6,5,2);

/*Table structure for table `activo` */

DROP TABLE IF EXISTS `activo`;

CREATE TABLE `activo` (
  `idactivo` int(11) NOT NULL AUTO_INCREMENT,
  `nombact` varchar(45) NOT NULL,
  `tipoact` varchar(45) NOT NULL,
  PRIMARY KEY (`idactivo`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;

/*Data for the table `activo` */

insert  into `activo` values (31,'MONITOR','HARDWARE'),(34,'MOUSE','HARDWARE'),(35,'SOFTWARE DE ESCRITORIO','SOFTWARE'),(36,'SISTEMA DE VENTAS','SOFTWARE'),(37,'CPU','HARDWARE'),(38,'ACTIVO DE PRUEBA','SOFTWARE');

/*Table structure for table `amenaza` */

DROP TABLE IF EXISTS `amenaza`;

CREATE TABLE `amenaza` (
  `idamenaza` int(11) NOT NULL AUTO_INCREMENT,
  `nombamen` varchar(45) NOT NULL,
  PRIMARY KEY (`idamenaza`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `amenaza` */

insert  into `amenaza` values (1,'INFECCIÃ“N CON CÃ“DIGO MALICIOSO'),(2,'ROBO DE INFORMACIÃ“N'),(3,'ROBO DE EQUIPO');

/*Table structure for table `cuantriesgo` */

DROP TABLE IF EXISTS `cuantriesgo`;

CREATE TABLE `cuantriesgo` (
  `idcuantriesgo` int(11) NOT NULL AUTO_INCREMENT,
  `idactespecamen` int(11) NOT NULL,
  `amenaza` varchar(45) NOT NULL,
  `vulnerabilidad` varchar(45) NOT NULL,
  `impacto` varchar(45) NOT NULL,
  PRIMARY KEY (`idcuantriesgo`),
  KEY `fk_idactespecamen_idx` (`idactespecamen`),
  CONSTRAINT `fk_idactespecamen` FOREIGN KEY (`idactespecamen`) REFERENCES `actespecamen` (`idactespecamen`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `cuantriesgo` */

/*Table structure for table `riesgo` */

DROP TABLE IF EXISTS `riesgo`;

CREATE TABLE `riesgo` (
  `idriesgo` int(11) NOT NULL AUTO_INCREMENT,
  `idcuantriesgo` int(11) NOT NULL,
  `valriesgo` varchar(45) NOT NULL,
  PRIMARY KEY (`idriesgo`),
  KEY `fk_cuantriesgo_idx` (`idcuantriesgo`),
  CONSTRAINT `fk_cuantriesgo` FOREIGN KEY (`idcuantriesgo`) REFERENCES `cuantriesgo` (`idcuantriesgo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `riesgo` */

/*Table structure for table `usuarios` */

DROP TABLE IF EXISTS `usuarios`;

CREATE TABLE `usuarios` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(50) NOT NULL,
  `nombres` varchar(50) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `password` tinyblob NOT NULL,
  `autorizacion` varchar(10) NOT NULL COMMENT 'ADMIN / CLIENT',
  PRIMARY KEY (`idusuario`),
  UNIQUE KEY `IDX_usuarios_2` (`apellidos`,`nombres`),
  UNIQUE KEY `IDX_usuarios_3` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `usuarios` */

--insert  into `usuarios` values (1,'Lozano','Daniel','dani','þ\0\ZÖaP+ûü\'Æ’ˆ™Z','ADMIN');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
