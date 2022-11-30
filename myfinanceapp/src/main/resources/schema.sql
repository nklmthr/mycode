DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int NOT NULL,
  `name` varchar(6) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `parent` int DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  KEY `parent_category_fk_idx` (`parent`),
  CONSTRAINT `parent_category_fk` FOREIGN KEY (`parent`) REFERENCES `category` (`category_id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `institution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `institution` (
  `institution_id` int NOT NULL,
  `institution_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`institution_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `transaction_id` int NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `account_id` int DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `is_debit` tinyint DEFAULT NULL,
  `amount` decimal(10,2) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`),
  KEY `category_transaction_fk_idx` (`category_id`),
  KEY `account_transaction_fk_idx` (`account_id`),
  CONSTRAINT `account_transaction_fk` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`),
  CONSTRAINT `category_transaction_fk` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=896 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `account_id` int NOT NULL,
  `account_type_id` int NOT NULL,
  `account_category_id` int DEFAULT NULL,
  `account_name` varchar(7) DEFAULT NULL,
  `account_description` varchar(100) DEFAULT NULL,
  `institution_id` int DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  KEY `account_bank_foreign_key_idx` (`institution_id`),
  KEY `account_account_type_foreign_key_idx` (`account_type_id`),
  KEY `account_account_category_foreignkey_idx` (`account_category_id`),
  CONSTRAINT `account_account_category_foreignkey` FOREIGN KEY (`account_category_id`) REFERENCES `account_category` (`account_category_id`),
  CONSTRAINT `account_account_type_foreign_key` FOREIGN KEY (`account_type_id`) REFERENCES `account_type` (`account_type_id`),
  CONSTRAINT `account_institution_foreign_key` FOREIGN KEY (`institution_id`) REFERENCES `institution` (`institution_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `account_bal_hist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;

CREATE TABLE `account_bal_hist` (
  `account_bal_hist` int NOT NULL,
  `account_id` int DEFAULT NULL,
  `date_of_balance` datetime DEFAULT NULL,
  `balance` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`account_bal_hist`),
  KEY `account_bal_hist_account_id_fk_idx` (`account_id`),
  CONSTRAINT `account_bal_hist_account_id_fk` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `account_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_category` (
  `account_category_id` int NOT NULL,
  `account_category_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`account_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `account_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_type` (
  `account_type_id` int NOT NULL,
  `account_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`account_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


