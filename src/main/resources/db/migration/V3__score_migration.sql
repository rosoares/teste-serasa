CREATE TABLE score (
  id BIGINT NOT NULL,
   score_descricao VARCHAR(255) NOT NULL,
   inicial INT NOT NULL,
   final INT NOT NULL,
   CONSTRAINT pk_score PRIMARY KEY (id)
);