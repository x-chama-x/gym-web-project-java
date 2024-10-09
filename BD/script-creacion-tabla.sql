-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`usuario` (
  `usuarioID` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `contraseña` VARCHAR(45) NOT NULL,
  `rol` ENUM('administrador', 'final') NOT NULL,
  PRIMARY KEY (`usuarioID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`entrenamiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`entrenamiento` (
  `entrenamientoID` INT NOT NULL,
  `usuarioID` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `cantidadEjercicios` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`entrenamientoID`, `usuarioID`),
  INDEX `fk_entrenamiento_usuario_idx` (`usuarioID` ASC) VISIBLE,
  CONSTRAINT `fk_entrenamiento_usuario`
    FOREIGN KEY (`usuarioID`)
    REFERENCES `mydb`.`usuario` (`usuarioID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`equipo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`equipo` (
  `equipoID` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `imagen` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`equipoID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`parteDelCuerpo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`parteDelCuerpo` (
  `parteDelCuerpoID` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `imagen` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`parteDelCuerpoID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ejercicio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ejercicio` (
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
    REFERENCES `mydb`.`equipo` (`equipoID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ejercicio_parteDelCuerpo1`
    FOREIGN KEY (`parteDelCuerpoID`)
    REFERENCES `mydb`.`parteDelCuerpo` (`parteDelCuerpoID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ejercicio_usuario1`
    FOREIGN KEY (`usuario_usuarioID`)
    REFERENCES `mydb`.`usuario` (`usuarioID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`entrenamiento_has_ejercicio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`entrenamiento_has_ejercicio` (
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
    REFERENCES `mydb`.`entrenamiento` (`entrenamientoID` , `usuarioID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_entrenamiento_has_ejercicio_ejercicio1`
    FOREIGN KEY (`ejercicioID`)
    REFERENCES `mydb`.`ejercicio` (`ejercicioID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
