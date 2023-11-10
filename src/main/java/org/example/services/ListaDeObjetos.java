package org.example.services;

import java.util.ArrayList;
import java.util.List;

public class ListaDeObjetos<T> {
    private List<T> lista = new ArrayList<>();

    public void adicionar(T objeto) {
        lista.add(objeto);
    }

    public List<T> listar() {
        return lista;
    }
}
