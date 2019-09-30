/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.7.14 : Database - risk
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`risk` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci */;

USE `risk`;

/*Table structure for table `actespec` */

DROP TABLE IF EXISTS `actespec`;

CREATE TABLE `actespec` (
  `idactespec` int(11) NOT NULL AUTO_INCREMENT,
  `activo_idactivo` int(11) NOT NULL,
  `nombactespec` varchar(90) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`idactespec`),
  KEY `fk_actespec_activo_idx` (`activo_idactivo`),
  CONSTRAINT `fk_actespec_activo` FOREIGN KEY (`activo_idactivo`) REFERENCES `activo` (`idactivo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

/*Data for the table `actespec` */

LOCK TABLES `actespec` WRITE;

insert  into `actespec`(`idactespec`,`activo_idactivo`,`nombactespec`) values (25,15,'OFFICE 2016'),(26,18,'MIS FACTURAS'),(27,14,'HP CORE I5'),(29,14,'MAC'),(30,17,'CISCO 2940'),(32,18,'FACTURACI√ìN'),(33,23,'CCCCCCC'),(34,23,'GGGGGG'),(36,16,'TYTTYTYIYTII'),(37,20,'CPU GAMER XXX');

UNLOCK TABLES;

/*Table structure for table `activo` */

DROP TABLE IF EXISTS `activo`;

CREATE TABLE `activo` (
  `idactivo` int(11) NOT NULL AUTO_INCREMENT,
  `nombactivo` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `tipo` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idactivo`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

/*Data for the table `activo` */

LOCK TABLES `activo` WRITE;

insert  into `activo`(`idactivo`,`nombactivo`,`tipo`) values (14,'LAPTOP','HARDWARE'),(15,'OFFICE','SOFTWARE'),(16,'PC','HARDWARE'),(17,'ROUTER','HARDWARE'),(18,'SISTEMA DE FACTURACI√ìN','SOFTWARE'),(20,'CPU xx','HARDWARE'),(23,'CCCCCC XXXX','HARDWARE'),(29,'NOMBRE DE PRUEBA DE ACTIVO','HARDWARE');

UNLOCK TABLES;

/*Table structure for table `amenaza` */

DROP TABLE IF EXISTS `amenaza`;

CREATE TABLE `amenaza` (
  `idamenaza` int(11) NOT NULL AUTO_INCREMENT,
  `catderiesgo_idcatderiesgo` int(11) NOT NULL,
  `amenaza` varchar(300) COLLATE utf8_spanish2_ci NOT NULL,
  `consecuencia` varchar(400) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`idamenaza`),
  KEY `fk_amenaza_catderiesgo1_idx` (`catderiesgo_idcatderiesgo`),
  CONSTRAINT `fk_amenaza_catderiesgo1` FOREIGN KEY (`catderiesgo_idcatderiesgo`) REFERENCES `catderiesgo` (`idcatderiesgo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

/*Data for the table `amenaza` */

LOCK TABLES `amenaza` WRITE;

insert  into `amenaza`(`idamenaza`,`catderiesgo_idcatderiesgo`,`amenaza`,`consecuencia`) values (3,5,'AMENAZA DE RR.HH','CONSECUNCIA DE AMENAZA DE RR.HH'),(5,1,'AMENAZA DE GESTI√ìN','CONSECUENCIAS DE AMENAZA DE GESTI√ìN'),(7,2,'AMENAZA DE OPERACI√ìN','CONSECUENCIA DE AMENAZA DE OPERACI√ìN'),(8,3,'AMENAZA DE INFRAESTRUCTURA','CONSECUENCIAS DE AMENAZA DE INFRAESTRUCTURA'),(9,4,'AMENAZA DE SEGURIDAD','CONSECUENCIAS DE AMENAZA DE SEGURIDAD'),(10,2,'ASDASDSDSAD','DASDASDASDASDSADASDADSADSADAS');

UNLOCK TABLES;

/*Table structure for table `applog` */

DROP TABLE IF EXISTS `applog`;

CREATE TABLE `applog` (
  `USER_ID` varchar(20) NOT NULL,
  `DATED` varchar(50) NOT NULL,
  `LOGGER` varchar(50) NOT NULL,
  `LEVEL` varchar(10) NOT NULL,
  `MESSAGE` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `applog` */

LOCK TABLES `applog` WRITE;

UNLOCK TABLES;

/*Table structure for table `catderiesgo` */

DROP TABLE IF EXISTS `catderiesgo`;

CREATE TABLE `catderiesgo` (
  `idcatderiesgo` int(11) NOT NULL AUTO_INCREMENT,
  `categoria` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `desc` varchar(300) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`idcatderiesgo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

/*Data for the table `catderiesgo` */

LOCK TABLES `catderiesgo` WRITE;

insert  into `catderiesgo`(`idcatderiesgo`,`categoria`,`desc`) values (1,'GESTI√ìN','RIESGOS RELACIONADOS CON LA AUSENCIA O APLICACI√ìNIN CORRECTA DE M√âTODOS DE GESTI√ìN DE LAS TECNOLOG√çAS DE INFORMACI√ìN Y COMUNICACIONES.'),(2,'OPERACI√ìN','INCUMPLIMIENTO DE DIRECTRICES, PROCEDIMIENTOS Y METODOLOG√çAS Y EST√ÅNDARES EN LOS PROCESOS OPERATIVOS DE LA UTI (UNIDAD DE TECNOLOG√çA DE INFORMACI√ìN).'),(3,'INFRAESTRUCTURA','RIESGOS RELACIONADOS CON LAS FALLAS POTENCIALES DE LA INFRAESTRUCTURA TECNOL√ìGICA UTILIZADA EN LA INSTITUCI√ìN.'),(4,'SEGURIDAD','EVENTOS QUE ATENTAN CONTRA LA CONFIDENCIALIDAD, INTEGRIDAD Y DISPONIBILIDAD DE LA INFORMACI√ìN.'),(5,'RECURSO HUMANO','RELACIONADOS CON EL DESEMPE√ëO Y REGULARIDAD DE LOS RECURSOS HUMANOS.');

UNLOCK TABLES;

/*Table structure for table `cuantriesgo` */

DROP TABLE IF EXISTS `cuantriesgo`;

CREATE TABLE `cuantriesgo` (
  `idcuantriesgo` int(11) NOT NULL AUTO_INCREMENT,
  `salvaguarda_idsalvaguarda` int(11) NOT NULL,
  `valoramenaza` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `valorimpacto` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `valorvulner` varchar(20) COLLATE utf8_spanish2_ci NOT NULL,
  `fecreg` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idcuantriesgo`),
  KEY `fk_cuantriesgo_salvaguarda1_idx` (`salvaguarda_idsalvaguarda`),
  CONSTRAINT `fk_cuantriesgo_salvaguarda1` FOREIGN KEY (`salvaguarda_idsalvaguarda`) REFERENCES `salvaguarda` (`idsalvaguarda`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

/*Data for the table `cuantriesgo` */

LOCK TABLES `cuantriesgo` WRITE;

insert  into `cuantriesgo`(`idcuantriesgo`,`salvaguarda_idsalvaguarda`,`valoramenaza`,`valorimpacto`,`valorvulner`,`fecreg`) values (1,78,'MB','B','MA','2018-08-14 15:58:52'),(2,82,'MA','A','A','2018-08-14 15:59:37'),(3,94,'MB','B','M','2018-08-15 17:43:29'),(6,94,'MA','MA','MA','2018-08-21 00:00:00'),(7,82,'MB','M','A','2018-08-22 00:00:00'),(8,91,'B','M','MA','2018-12-03 00:00:00'),(9,92,'MB','MB','MB','2018-12-17 00:00:00');

UNLOCK TABLES;

/*Table structure for table `persona` */

DROP TABLE IF EXISTS `persona`;

CREATE TABLE `persona` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `apellido` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

/*Data for the table `persona` */

LOCK TABLES `persona` WRITE;

insert  into `persona`(`id`,`nombre`,`apellido`,`fecha`) values (4,'MAR√çA XXXX','LLENA ES DE GRACIA','1992-02-13 00:00:00'),(9,'LUIS','CISNEROS XX','1981-06-09 00:00:00'),(16,'YONATAN','OCHOA CHAVARRIA','1989-02-13 00:00:00'),(17,'LORENA xxxx','MERITIANO','1979-03-31 00:00:00'),(19,'EDIT','√ëAND√ö','1988-12-31 00:00:00');

UNLOCK TABLES;

/*Table structure for table `responsable` */

DROP TABLE IF EXISTS `responsable`;

CREATE TABLE `responsable` (
  `idresponsable` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `dni` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  `cargo` varchar(100) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `celular` varchar(10) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `correo` varchar(100) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`idresponsable`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

/*Data for the table `responsable` */

LOCK TABLES `responsable` WRITE;

insert  into `responsable`(`idresponsable`,`nombre`,`dni`,`cargo`,`celular`,`correo`) values (2,'ANTHONI D√çAZ CHINCHAY','44332341','JEFE DE PROYECTO','988776655','tony_stark@gmail.com'),(3,'GRESSLY DIANETH BERCERA ZAMORA','45543344','PRACTICANTE','988544309','gresslysss@gmail.com'),(4,'LOLA PENA ZAN√öN','45433333','GERENTE DE PROYECTOS','555449999','lolitas@hotmail.com');

UNLOCK TABLES;

/*Table structure for table `salvaguarda` */

DROP TABLE IF EXISTS `salvaguarda`;

CREATE TABLE `salvaguarda` (
  `idsalvaguarda` int(11) NOT NULL AUTO_INCREMENT,
  `actespec_idactespec` int(11) NOT NULL,
  `amenaza_idamenaza` int(11) NOT NULL,
  `responsable_idresponsable` int(11) NOT NULL,
  `control` varchar(300) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`idsalvaguarda`),
  KEY `fk_salvaguarda_actespec1_idx` (`actespec_idactespec`),
  KEY `fk_salvaguarda_amenaza1_idx` (`amenaza_idamenaza`),
  KEY `fk_salvaguarda_responsable1_idx` (`responsable_idresponsable`),
  CONSTRAINT `fk_salvaguarda_actespec1` FOREIGN KEY (`actespec_idactespec`) REFERENCES `actespec` (`idactespec`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_salvaguarda_amenaza1` FOREIGN KEY (`amenaza_idamenaza`) REFERENCES `amenaza` (`idamenaza`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_salvaguarda_responsable1` FOREIGN KEY (`responsable_idresponsable`) REFERENCES `responsable` (`idresponsable`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

/*Data for the table `salvaguarda` */

LOCK TABLES `salvaguarda` WRITE;

insert  into `salvaguarda`(`idsalvaguarda`,`actespec_idactespec`,`amenaza_idamenaza`,`responsable_idresponsable`,`control`) values (78,29,7,2,'√ëU√ëIU√ëUI√ëIUIU'),(82,30,7,2,'GWEGEG'),(91,27,5,4,'ILULUILULILUI'),(92,34,5,2,'FWQFFWQF'),(93,27,7,3,'DWQDWQDDWQDWQ'),(94,32,8,3,'DWDWDQDQWQDWDWQDWQDWQWQQDWQDWQDWQDWQDWQ');

UNLOCK TABLES;

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `nombres` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `usuario` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `autorizacion` varchar(10) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

/*Data for the table `usuario` */

LOCK TABLES `usuario` WRITE;

UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `usuarios` */

LOCK TABLES `usuarios` WRITE;

insert  into `usuarios`(`idusuario`,`apellidos`,`nombres`,`usuario`,`password`,`autorizacion`) values (4,'OCHOA CHAVARRIA','YONATAN DANIEL','yochoa','p M∑ôﬂ·ñ†@#0-ú','ADMIN'),(5,'PERALTA','LOLA','lperalta','\0òáÙ¡gpˇc®˜-çZ','CLIENT'),(6,'PEPE','PEPE','ppepe',':Ñ<L˜‚ã;Óa\nÿÆ','ADMIN');

UNLOCK TABLES;

/*Table structure for table `usuarios_perfil` */

DROP TABLE IF EXISTS `usuarios_perfil`;

CREATE TABLE `usuarios_perfil` (
  `idusuarios_perfil` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `perfil` varchar(30) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `idusuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`idusuarios_perfil`),
  KEY `fk_idusuario` (`idusuario`),
  CONSTRAINT `fk_idusuario` FOREIGN KEY (`idusuario`) REFERENCES `usuarios` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

/*Data for the table `usuarios_perfil` */

LOCK TABLES `usuarios_perfil` WRITE;

insert  into `usuarios_perfil`(`idusuarios_perfil`,`username`,`perfil`,`idusuario`) values (1,'dani','ADMIN',4);

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
