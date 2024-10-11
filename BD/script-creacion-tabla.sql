-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema gymwebBD
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gymwebBD
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gymwebBD` DEFAULT CHARACTER SET utf8 ;
USE `gymwebBD` ;

-- -----------------------------------------------------
-- Table `gymwebBD`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gymwebBD`.`usuario` (
  `usuarioID` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `contraseña` VARCHAR(45) NOT NULL,
  `rol` ENUM('administrador', 'final') NOT NULL,
  PRIMARY KEY (`usuarioID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gymwebBD`.`entrenamiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gymwebBD`.`entrenamiento` (
  `entrenamientoID` INT NOT NULL,
  `usuarioID` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `cantidadEjercicios` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`entrenamientoID`, `usuarioID`),
  INDEX `fk_entrenamiento_usuario_idx` (`usuarioID` ASC) VISIBLE,
  CONSTRAINT `fk_entrenamiento_usuario`
    FOREIGN KEY (`usuarioID`)
    REFERENCES `gymwebBD`.`usuario` (`usuarioID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gymwebBD`.`equipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gymwebBD`.`equipo` (
  `equipoID` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `imagen` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`equipoID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gymwebBD`.`parteDelCuerpo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gymwebBD`.`parteDelCuerpo` (
  `parteDelCuerpoID` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `imagen` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`parteDelCuerpoID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gymwebBD`.`ejercicio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gymwebBD`.`ejercicio` (
  `ejercicioID` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `imagen` VARCHAR(45) NOT NULL,
  `parteDelCuerpoID` INT NOT NULL,
  `musculosQueTrabaja` VARCHAR(45) NOT NULL,
  `equipoID` INT NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  `preparacion` VARCHAR(100) NOT NULL,
  `ejecucion` VARCHAR(45) NOT NULL,
  `consejosClave` VARCHAR(45) NOT NULL,
  `cargadoPor` ENUM('sistema''usuario') NOT NULL,
  `usuario_usuarioID` INT NOT NULL,
  PRIMARY KEY (`ejercicioID`, `parteDelCuerpoID`, `equipoID`, `usuario_usuarioID`),
  INDEX `fk_ejercicio_Equipo1_idx` (`equipoID` ASC) VISIBLE,
  INDEX `fk_ejercicio_parteDelCuerpo1_idx` (`parteDelCuerpoID` ASC) VISIBLE,
  INDEX `fk_ejercicio_usuario1_idx` (`usuario_usuarioID` ASC) VISIBLE,
  CONSTRAINT `fk_ejercicio_Equipo1`
    FOREIGN KEY (`equipoID`)
    REFERENCES `gymwebBD`.`equipo` (`equipoID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ejercicio_parteDelCuerpo1`
    FOREIGN KEY (`parteDelCuerpoID`)
    REFERENCES `gymwebBD`.`parteDelCuerpo` (`parteDelCuerpoID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ejercicio_usuario1`
    FOREIGN KEY (`usuario_usuarioID`)
    REFERENCES `gymwebBD`.`usuario` (`usuarioID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gymwebBD`.`entrenamiento_has_ejercicio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gymwebBD`.`entrenamiento_has_ejercicio` (
  `EjercicioEntrenamientoID` INT NOT NULL,
  `EntrenamientoID` INT NOT NULL,
  `usuarioID` INT NOT NULL,
  `ejercicioID` INT NOT NULL,
  `numeroSerie` INT NOT NULL,
  `repeticiones` INT NOT NULL,
  `peso` DECIMAL NOT NULL,
  PRIMARY KEY (`EjercicioEntrenamientoID`, `EntrenamientoID`, `usuarioID`, `ejercicioID`),
  INDEX `fk_entrenamiento_has_ejercicio_ejercicio1_idx` (`ejercicioID` ASC) VISIBLE,
  INDEX `fk_entrenamiento_has_ejercicio_entrenamiento1_idx` (`EntrenamientoID` ASC, `usuarioID` ASC) VISIBLE,
  CONSTRAINT `fk_entrenamiento_has_ejercicio_entrenamiento1`
    FOREIGN KEY (`EntrenamientoID` , `usuarioID`)
    REFERENCES `gymwebBD`.`entrenamiento` (`entrenamientoID` , `usuarioID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_entrenamiento_has_ejercicio_ejercicio1`
    FOREIGN KEY (`ejercicioID`)
    REFERENCES `gymwebBD`.`ejercicio` (`ejercicioID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
