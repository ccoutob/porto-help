package org.example.controller;

import org.example.model.Carga;
import org.example.services.CRUD;

import java.util.List;
import java.util.Scanner;

public class CargaController implements CRUD<Carga> {
    private List<Carga> listaDeCargas;
    private Scanner scanner;

    public CargaController(List<Carga> listaDeCargas, Scanner scanner) {
        this.listaDeCargas = listaDeCargas;
        this.scanner = scanner;
    }

    @Override
    public void criar(Carga carga) {
        // Lógica para criar uma carga
        listaDeCargas.add(carga);
        System.out.println("Carga criada com sucesso.");
    }

    @Override
    public Carga ler(int id) {
        // Lógica para ler uma carga com o ID especificado
        for (Carga carga : listaDeCargas) {
            if (carga.getId() == id) {
                return carga;
            }
        }
        System.out.println("Carga não encontrada com o ID especificado.");
        return null;
    }

    @Override
    public void atualizar(Carga carga) {
        // Lógica para atualizar uma carga
        for (Carga cg : listaDeCargas) {
            if (cg.getId() == carga.getId()) {
                cg.setDescricao(carga.getDescricao());
                cg.setPeso(carga.getPeso());
                System.out.println("Carga atualizada com sucesso.");
                return;
            }
        }
        System.out.println("Carga não encontrada para atualização.");
    }

    @Override
    public void deletar(int id) {
        // Lógica para deletar uma carga com o ID especificado
        Carga carga = ler(id);
        if (carga != null) {
            listaDeCargas.remove(carga);
            System.out.println("Carga removida com sucesso.");
        } else {
            System.out.println("Carga não encontrada para exclusão.");
        }
    }

    public void gerenciarCargas() {
        while (true) {
            System.out.println("Gerenciar Cargas");
            System.out.println("1. Listar Cargas");
            System.out.println("2. Criar Carga");
            System.out.println("3. Atualizar Carga");
            System.out.println("4. Excluir Carga");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar a nova linha após a leitura do número

            switch (opcao) {
                case 1:
                    listarCargas();
                    break;
                case 2:
                    criarCarga();
                    break;
                case 3:
                    atualizarCarga();
                    break;
                case 4:
                    excluirCarga();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void listarCargas() {
        // Lógica para listar cargas
        for (Carga carga : listaDeCargas) {
            System.out.println(carga);
        }
    }

    private void criarCarga() {
        // Lógica para criar carga
        System.out.print("ID da Carga: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar a nova linha após a leitura do ID

        System.out.print("Descrição da Carga: ");
        String descricao = scanner.nextLine();

        System.out.print("Peso da Carga: ");
        double peso = scanner.nextDouble();
        scanner.nextLine(); // Limpar a nova linha após a leitura do peso

        Carga novaCarga = new Carga(id, descricao, peso);
        criar(novaCarga);
    }

    private void atualizarCarga() {
        // Lógica para atualizar carga
        System.out.print("ID da Carga para atualização: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar a nova linha após a leitura do ID

        Carga cargaExistente = ler(id);
        if (cargaExistente != null) {
            System.out.print("Nova Descrição: ");
            String descricao = scanner.nextLine();

            System.out.print("Novo Peso: ");
            double peso = scanner.nextDouble();
            scanner.nextLine(); // Limpar a nova linha após a leitura do peso

            Carga cargaAtualizada = new Carga(id, descricao, peso);
            atualizar(cargaAtualizada);
        }
    }

    private void excluirCarga() {
        // Lógica para excluir carga
        System.out.print("ID da Carga para exclusão: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar a nova linha após a leitura do ID

        deletar(id);
    }
}
