-- DROP DATABASE shope IF EXISTS;
-- DDL structure of db

CREATE DATABASE shope;

use shope;

CREATE TABLE clients (
  id int NOT NULL UNIQUE AUTO_INCREMENT,
  login VARCHAR(20) NOT NULL UNIQUE,
  pass VARCHAR(20) NOT NULL,
  phone VARCHAR(12) UNIQUE,
  email VARCHAR(30) UNIQUE
);


