-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ElBilleton` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ElBilleton`;
USE `ElBilleton` ;

-- -----------------------------------------------------
-- Table `mydb`.`Cajero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CAJERO(
  `codigo` BIGINT(19) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(200) NOT NULL,
  `turno` VARCHAR(45) NOT NULL,
  `dpi` VARCHAR(30) NOT NULL,
  `direccion` VARCHAR(200) NOT NULL,
  `sexo` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`codigo`));


-- -----------------------------------------------------
-- Table `mydb`.`Gerente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS GERENTE (
  `codigo` BIGINT(19) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(200) NOT NULL,
  `turno` VARCHAR(45) NOT NULL,
  `dpi` VARCHAR(30) NOT NULL,
  `direccion` VARCHAR(200) NOT NULL,
  `sexo` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`codigo`)
);


-- -----------------------------------------------------
-- Table `mydb`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CLIENTE (
  `codigo` BIGINT(19) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(200) NOT NULL,
  `dpi` VARCHAR(30) NOT NULL,
  `direccion` VARCHAR(200) NOT NULL,
  `sexo` VARCHAR(45) NOT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `pdfDPI` MEDIUMBLOB NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`codigo`));


-- -----------------------------------------------------
-- Table `mydb`.`Cuenta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS CUENTA (
  `codigo` BIGINT(19) NOT NULL AUTO_INCREMENT,
  `fecha_creacion` DATE NOT NULL,
  `monto` DOUBLE NOT NULL,
  `cliente_codigo` BIGINT(19) NOT NULL,
  PRIMARY KEY (`codigo`),
	FOREIGN KEY (`cliente_codigo`) REFERENCES CLIENTE(`codigo`)
);


-- -----------------------------------------------------
-- Table `mydb`.`Solicitud`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS SOLICITUD (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `cuenta_codigo_envia` BIGINT(19) NOT NULL,
  `cuenta_codigo_recibe` BIGINT(19) NOT NULL,
  PRIMARY KEY (`codigo`),
    FOREIGN KEY (`cuenta_codigo_envia`) REFERENCES CUENTA (`codigo`),
    FOREIGN KEY (`cuenta_codigo_recibe`) REFERENCES CUENTA (`codigo`)
);


-- -----------------------------------------------------
-- Table `mydb`.`Asociacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ASOCIACION(
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `solicitud_codigo` INT NOT NULL,
  PRIMARY KEY (`codigo`),
    FOREIGN KEY (`solicitud_codigo`) REFERENCES SOLICITUD (`codigo`)
);


-- -----------------------------------------------------
-- Table `mydb`.`Transaccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS TRANSACCION (
  `codigo` BIGINT(19) NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `monto` DOUBLE NOT NULL,
  `cuenta_codigo` BIGINT(19) NOT NULL,
  `cajero_codigo` BIGINT(19) NOT NULL,
  PRIMARY KEY (`codigo`),
    FOREIGN KEY (`cuenta_codigo`) REFERENCES CUENTA (`codigo`),
    FOREIGN KEY (`cajero_codigo`) REFERENCES CAJERO (`codigo`)
);



-- -----------------------------------------------------
-- Table `mydb`.`Historial_Gerente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS HISTORIAL_GERENTE (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(200) NOT NULL,
  `turno` VARCHAR(45) NOT NULL,
  `dpi` VARCHAR(30) NOT NULL,
  `direccion` VARCHAR(200) NOT NULL,
  `sexo` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `gerente_codigo` BIGINT(19) NOT NULL,
  PRIMARY KEY (`codigo`),
    FOREIGN KEY (`gerente_codigo`) REFERENCES GERENTE (`codigo`)
);


-- -----------------------------------------------------
-- Table `mydb`.`HIstorial_Cajero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS HISTORIAL_CAJERO (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(200) NOT NULL,
  `turno` VARCHAR(45) NOT NULL,
  `dpi` VARCHAR(30) NOT NULL,
  `direccion` VARCHAR(200) NOT NULL,
  `sexo` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `cajero_codigo` BIGINT(19) NOT NULL,
  PRIMARY KEY (`codigo`),
    FOREIGN KEY (`cajero_codigo`) REFERENCES CAJERO (`codigo`)
);


-- -----------------------------------------------------
-- Table `mydb`.`Historial_Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS HISTORIAL_CLIENTE(
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(200) NOT NULL,
  `dpi` VARCHAR(30) NOT NULL,
  `direccion` VARCHAR(200) NOT NULL,
  `sexo` VARCHAR(45) NOT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `pdfDPI` MEDIUMBLOB NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `cliente_codigo` BIGINT(19) NOT NULL,
  PRIMARY KEY (`codigo`),
    FOREIGN KEY (`cliente_codigo`) REFERENCES CLIENTE (`codigo`)
);

CREATE TABLE IF NOT EXISTS LIMITES_GERENTE(
	`limite_reporte2` DOUBLE NOT NULL,
	`limite_reporte3` DOUBLE NOT NULL,
    PRIMARY KEY (`limite_reporte2`,`limite_reporte3`)
);

INSERT INTO CAJERO VALUES(101,'Banca virtual','Toda hora',101,'','','8cX7%%tedj4!yJm4');
INSERT INTO LIMITES_GERENTE VALUES(0,0);

