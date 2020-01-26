-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema GamEvaluate
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema GamEvaluate
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `GamEvaluate` DEFAULT CHARACTER SET utf8 ;
USE `GamEvaluate` ;

-- -----------------------------------------------------
-- Table `GamEvaluate`.`GeneralUser`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GamEvaluate`.`GeneralUser` (
  `Username` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(300) NOT NULL,
  `Password` VARCHAR(100) NOT NULL,
  `Role` INT NOT NULL DEFAULT 0,
  `Banned` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`Username`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamEvaluate`.`Valutazione`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GamEvaluate`.`Valutazione` (
  `ID_Valutazione` INT NOT NULL AUTO_INCREMENT,
  `Gameplay` INT NOT NULL DEFAULT 0,
  `Trama` INT NOT NULL DEFAULT 0,
  `Grafica` INT NOT NULL DEFAULT 0,
  `Creativita` INT NOT NULL DEFAULT 0,
  `Innovazione` INT NOT NULL DEFAULT 0,
  `Coinvolgimento` INT NOT NULL DEFAULT 0,
  `Realismo` INT NOT NULL DEFAULT 0,
  `Rigiocabilita` INT NOT NULL DEFAULT 0,
  `Difficolta` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`ID_Valutazione`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamEvaluate`.`Genere`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GamEvaluate`.`Genere` (
  `Nome` VARCHAR(45) NOT NULL,
  `Descrizione` VARCHAR(400) NOT NULL,
  PRIMARY KEY (`Nome`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamEvaluate`.`Piattaforma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GamEvaluate`.`Piattaforma` (
  `Nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Nome`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamEvaluate`.`Gioco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GamEvaluate`.`Gioco` (
  `ID_Gioco` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `Descrizione` VARCHAR(2000) NOT NULL,
  `Immagine` BLOB NULL,
  `Genere` VARCHAR(45) NOT NULL,
  `Piattaforma` VARCHAR(45) NOT NULL,
  `ID_Valutazione` INT NOT NULL,
  PRIMARY KEY (`ID_Gioco`),
  INDEX `ID_Valutazione_idx` (`ID_Valutazione` ASC) VISIBLE,
  INDEX `Genere_idx` (`Genere` ASC) VISIBLE,
  INDEX `Piattaforma_idx` (`Piattaforma` ASC) VISIBLE,
  CONSTRAINT `ID_Valutazione`
    FOREIGN KEY (`ID_Valutazione`)
    REFERENCES `GamEvaluate`.`Valutazione` (`ID_Valutazione`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `Genere`
    FOREIGN KEY (`Genere`)
    REFERENCES `GamEvaluate`.`Genere` (`Nome`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `Piattaforma`
    FOREIGN KEY (`Piattaforma`)
    REFERENCES `GamEvaluate`.`Piattaforma` (`Nome`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GamEvaluate`.`Recensione`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GamEvaluate`.`Recensione` (
  `Testo` VARCHAR(2000) NOT NULL,
  `Data` DATETIME NOT NULL,
  `ID_Gioco` INT NOT NULL,
  `Username` VARCHAR(45) NOT NULL,
  INDEX `ID_Gioco_idx` (`ID_Gioco` ASC) VISIBLE,
  INDEX `Username_idx` (`Username` ASC) VISIBLE,
  PRIMARY KEY (`Username`, `ID_Gioco`),
  CONSTRAINT `ID_Gioco`
    FOREIGN KEY (`ID_Gioco`)
    REFERENCES `GamEvaluate`.`Gioco` (`ID_Gioco`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `Username`
    FOREIGN KEY (`Username`)
    REFERENCES `GamEvaluate`.`GeneralUser` (`Username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


ALTER TABLE `gamevaluate`.`recensione` 
DROP PRIMARY KEY,
ADD PRIMARY KEY (`Username`, `ID_Gioco`, `Data`);
;


ALTER TABLE `gamevaluate`.`gioco` 
CHANGE COLUMN `Immagine` `Immagine` VARCHAR(2048) NULL DEFAULT NULL ;

