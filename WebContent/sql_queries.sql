CREATE TABLE `dataverse_database`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fullName` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `phoneNo` VARCHAR(45) NULL,
  `company` VARCHAR(45) NULL,
  `profilePic` LONGBLOB NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Email_UNIQUE` (`email` ASC));
  