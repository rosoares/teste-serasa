CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE pessoa (
  id BIGINT NOT NULL,
   nome VARCHAR(255) NOT NULL,
   telefone VARCHAR(255) NOT NULL,
   idade INT NOT NULL,
   cidade VARCHAR(255) NOT NULL,
   estado VARCHAR(255) NOT NULL,
   score INT NOT NULL,
   regiao VARCHAR(255) NOT NULL,
   data_inclusao date,
   CONSTRAINT pk_pessoa PRIMARY KEY (id)
);