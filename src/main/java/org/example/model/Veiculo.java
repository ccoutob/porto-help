package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Veiculo {
	@JsonProperty("cd_veiculo")
    private int id;
    @JsonProperty("nm_marca")
    private String marca;
    @JsonProperty("nm_modelo")
    private String modelo;
    @JsonProperty("nr_ano")
    private int ano;

    public Veiculo() {
    	
    }
    
    public Veiculo(int id, String marca, String modelo, int ano) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    
    @Override
    public String toString() {
        return "Veiculo [ID=" + id + ", Marca=" + marca + ", Modelo=" + modelo + ", Ano=" + ano + "]";
    }
}
