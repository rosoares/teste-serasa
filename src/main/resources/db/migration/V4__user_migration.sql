CREATE TABLE usuario (
  id BIGINT NOT NULL,
   email VARCHAR(255),
   pass VARCHAR(255),
   CONSTRAINT pk_user PRIMARY KEY (id)
);