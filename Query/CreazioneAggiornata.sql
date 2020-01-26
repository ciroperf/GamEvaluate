-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema gamevaluate
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gamevaluate
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gamevaluate` DEFAULT CHARACTER SET utf8 ;
USE `gamevaluate` ;

-- -----------------------------------------------------
-- Table `gamevaluate`.`generaluser`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gamevaluate`.`generaluser` (
  `Username` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(300) NOT NULL,
  `Password` VARCHAR(100) NOT NULL,
  `Role` INT(11) NOT NULL DEFAULT '0',
  `Banned` TINYINT(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gamevaluate`.`genere`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gamevaluate`.`genere` (
  `Nome` VARCHAR(45) NOT NULL,
  `Descrizione` VARCHAR(400) NOT NULL,
  PRIMARY KEY (`Nome`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gamevaluate`.`piattaforma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gamevaluate`.`piattaforma` (
  `Nome` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Nome`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gamevaluate`.`valutazione`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gamevaluate`.`valutazione` (
  `ID_Valutazione` INT(11) NOT NULL,
  `Gameplay` INT(11) NOT NULL DEFAULT '0',
  `Trama` INT(11) NOT NULL DEFAULT '0',
  `Grafica` INT(11) NOT NULL DEFAULT '0',
  `Creativita` INT(11) NOT NULL DEFAULT '0',
  `Innovazione` INT(11) NOT NULL DEFAULT '0',
  `Coinvolgimento` INT(11) NOT NULL DEFAULT '0',
  `Realismo` INT(11) NOT NULL DEFAULT '0',
  `Rigiocabilita` INT(11) NOT NULL DEFAULT '0',
  `Difficolta` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID_Valutazione`),
  CONSTRAINT `ID_Valutazione`
    FOREIGN KEY (`ID_Valutazione`)
    REFERENCES `gamevaluate`.`gioco` (`ID_Gioco`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gamevaluate`.`gioco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gamevaluate`.`gioco` (
  `ID_Gioco` INT(11) NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `Descrizione` VARCHAR(2000) NOT NULL,
  `Immagine` VARCHAR(2048) NULL DEFAULT NULL,
  `Genere` VARCHAR(45) NOT NULL,
  `Piattaforma` VARCHAR(45) NOT NULL,
  `ID_Valutazione` INT(11) NOT NULL,
  PRIMARY KEY (`ID_Gioco`),
  INDEX `Genere_idx` (`Genere` ASC) VISIBLE,
  INDEX `Piattaforma_idx` (`Piattaforma` ASC) VISIBLE,
  INDEX `Valutazione_idx` (`ID_Valutazione` ASC) VISIBLE,
  CONSTRAINT `Genere`
    FOREIGN KEY (`Genere`)
    REFERENCES `gamevaluate`.`genere` (`Nome`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `Piattaforma`
    FOREIGN KEY (`Piattaforma`)
    REFERENCES `gamevaluate`.`piattaforma` (`Nome`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `Valutazione`
    FOREIGN KEY (`ID_Valutazione`)
    REFERENCES `gamevaluate`.`valutazione` (`ID_Valutazione`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `gamevaluate`.`recensione`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gamevaluate`.`recensione` (
  `Testo` VARCHAR(2000) NOT NULL,
  `Data` DATETIME NOT NULL,
  `ID_Gioco` INT(11) NOT NULL,
  `Username` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Username`, `ID_Gioco`, `Data`),
  INDEX `ID_Gioco_idx` (`ID_Gioco` ASC) VISIBLE,
  INDEX `Username_idx` (`Username` ASC) VISIBLE,
  CONSTRAINT `ID_Gioco`
    FOREIGN KEY (`ID_Gioco`)
    REFERENCES `gamevaluate`.`gioco` (`ID_Gioco`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `Username`
    FOREIGN KEY (`Username`)
    REFERENCES `gamevaluate`.`generaluser` (`Username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
