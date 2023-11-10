package org.example.controller;

import org.example.model.Veiculo;
import org.example.services.CRUD;

import java.util.List;
import java.util.Scanner;

public class VeiculoController implements CRUD<Veiculo> {
    private List<Veiculo> listaDeVeiculos;
    private Scanner scanner;

    public VeiculoController(List<Veiculo> listaDeVeiculos, Scanner scanner) {
        this.listaDeVeiculos = listaDeVeiculos;
        this.scanner = scanner;
    }

    @Override
    public void criar(Veiculo veiculo) {
        // Lógica para criar um veículo
        listaDeVeiculos.add(veiculo);
        System.out.println("Veículo criado com sucesso.");
    }

    @Override
    public Veiculo ler(int id) {
        // Lógica para ler um veículo com o ID especificado
        for (Veiculo veiculo : listaDeVeiculos) {
            if (veiculo.getId() == id) {
                return veiculo;
            }
        }
        System.out.println("Veículo não encontrado com o ID especificado.");
        return null;
    }

    @Override
    public void atualizar(Veiculo veiculo) {
        // Lógica para atualizar um veículo
        for (Veiculo v : listaDeVeiculos) {
            if (v.getId() == veiculo.getId()) {
                v.setMarca(veiculo.getMarca());
                v.setModelo(veiculo.getModelo());
                v.setAno(veiculo.getAno());
                System.out.println("Veículo atualizado com sucesso.");
                return;
            }
        }
        System.out.println("Veículo não encontrado para atualização.");
    }

    @Override
    public void deletar(int id) {
        // Lógica para deletar um veículo com o ID especificado
        Veiculo veiculo = ler(id);
        if (veiculo != null) {
            listaDeVeiculos.remove(veiculo);
            System.out.println("Veículo removido com sucesso.");
        } else {
            System.out.println("Veículo não encontrado para exclusão.");
        }
    }

    public void gerenciarVeiculos() {
        while (true) {
            System.out.println("Gerenciar Veículos");
            System.out.println("1. Listar Veículos");
            System.out.println("2. Criar Veículo");
            System.out.println("3. Atualizar Veículo");
            System.out.println("4. Excluir Veículo");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar a nova linha após a leitura do número

            switch (opcao) {
                case 1:
                    listarVeiculos();
                    break;
                case 2:
                    criarVeiculo();
                    break;
                case 3:
                    atualizarVeiculo();
                    break;
                case 4:
                    excluirVeiculo();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void listarVeiculos() {
        // Lógica para listar veículos
        for (Veiculo veiculo : listaDeVeiculos) {
            System.out.println(veiculo);
        }
    }

    private void criarVeiculo() {
        // Lógica para criar veículo
        System.out.print("ID do Veículo: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar a nova linha após a leitura do ID

        System.out.print("Marca do Veículo: ");
        String marca = scanner.nextLine();

        System.out.print("Modelo do Veículo: ");
        String modelo = scanner.nextLine();

        System.out.print("Ano do Veículo: ");
        int ano = scanner.nextInt();
        scanner.nextLine(); // Limpar a nova linha após a leitura do ano

        Veiculo novoVeiculo = new Veiculo(id, marca, modelo, ano);
        criar(novoVeiculo);
    }

    private void atualizarVeiculo() {
        // Lógica para atualizar veículo
        System.out.print("ID do Veículo para atualização: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar a nova linha após a leitura do ID

        Veiculo veiculoExistente = ler(id);
        if (veiculoExistente != null) {
            System.out.print("Nova Marca: ");
            String marca = scanner.nextLine();

            System.out.print("Novo Modelo: ");
            String modelo = scanner.nextLine();

            System.out.print("Novo Ano: ");
            int ano = scanner.nextInt();
            scanner.nextLine(); // Limpar a nova linha após a leitura do ano

            Veiculo veiculoAtualizado = new Veiculo(id, marca, modelo, ano);
            atualizar(veiculoAtualizado);
        }
    }

    private void excluirVeiculo() {
        // Lógica para excluir veículo
        System.out.print("ID do Veículo para exclusão: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar a nova linha após a leitura do ID

        deletar(id);
    }
}
