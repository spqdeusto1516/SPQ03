/* DELETE 'transactionsDB' database*/
DROP SCHEMA IF EXISTS transactionsDB;
/* DELETE USER 'spq' AT LOCAL SERVER*/
DROP USER IF EXISTS 'spq'@'%';

/* CREATE 'transactionsDB' DATABASE */
CREATE SCHEMA transactionsDB;
/* CREATE THE USER 'spq' AT LOCAL SERVER WITH PASSWORD 'spq' */
CREATE USER IF NOT EXISTS 'spq'@'%' IDENTIFIED BY 'spq';

GRANT ALL ON transactionsDB.* TO 'spq'@'%';