CREATE TABLE tb_veiculo (
    cd_veiculo  NUMBER(10, 2) NOT NULL,
    nm_marca    VARCHAR2(50) NOT NULL,
    nm_modelo   VARCHAR2(50) NOT NULL,
    nr_ano      NUMBER(5, 2) 
);

ALTER TABLE tb_veiculo ADD CONSTRAINT tb_veiculo_pk PRIMARY KEY ( cd_veiculo );

CREATE SEQUENCE SQ_TB_VEICULO START WITH 1 INCREMENT BY 1; 