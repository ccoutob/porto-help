package org.example.services;

public interface CRUD<T> {
    void criar(T objeto);
    T ler(int id);
    void atualizar(T objeto);
    void deletar(int id);
}
