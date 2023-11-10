CREATE TABLE tb_apolice (
    cd_apolice NUMBER(10,2) NOT NULL,
    nr_numero   NUMBER (10,2) NOT NULL,
    nm_seguradora VARCHAR2(100) NOT NULL,
    nr_valor NUMBER(10,2) NOT NULL,
    dt_inicio DATE NOT NULL,
    dt_fim DATE NOT NULL
);

ALTER TABLE tb_apolice ADD CONSTRAINT tb_apolice_pk PRIMARY KEY ( cd_apolice ); 

CREATE SEQUENCE SQ_TB_APOLICE
  MINVALUE 1
  START WITH 1
  INCREMENT BY 1
  CACHE 10;