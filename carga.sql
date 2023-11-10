CREATE TABLE tb_carga (
    cd_carga    NUMBER(10, 2) NOT NULL,
    ds_carga    VARCHAR2(200) NOT NULL,
    nr_peso     NUMBER(10,2) NOT NULL
);

ALTER TABLE tb_carga ADD CONSTRAINT tb_carga_pk PRIMARY KEY ( cd_carga ); 

CREATE SEQUENCE SQ_TB_CARGA START WITH 1 INCREMENT BY 1; 