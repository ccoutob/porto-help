package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Carga {
	@JsonProperty("cd_carga")
    private int id;
	@JsonProperty("ds_carga")
    private String descricao;
	@JsonProperty("nr_peso")
    private double peso;

	public Carga() {
		
	}
	
    public Carga(int id, String descricao, double peso) {
        this.id = id;
        this.descricao = descricao;
        this.peso = peso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Carga [ID=" + id + ", Descrição=" + descricao + ", Peso=" + peso + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Carga carga = (Carga) o;

        if (id != carga.id) return false;
        if (Double.compare(carga.peso, peso) != 0) return false;
        return descricao != null ? descricao.equals(carga.descricao) : carga.descricao == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        temp = Double.doubleToLongBits(peso);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
