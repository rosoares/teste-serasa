CREATE TABLE afinidade (
  id BIGINT NOT NULL,
   regiao VARCHAR(255) NOT NULL,
   estados VARCHAR(255) NOT NULL,
   CONSTRAINT pk_afinidade PRIMARY KEY (id)
);