package org.example.controller;

import org.example.model.Apolice;
import org.example.model.Carga;
import org.example.model.Veiculo;
import org.example.view.MenuView;

import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuController {
    private Scanner scanner;
    private ApoliceController apoliceController;
    private CargaController cargaController;
    private VeiculoController veiculoController;
    private List<Apolice> listaDeApolices;
    private List<Carga> listaDeCargas;
    private List<Veiculo> listaDeVeiculos;
    private MenuView menuView;

    // Construtor que aceita Scanner e MenuView
    public MenuController(Scanner scanner, MenuView menuView) {
        this.scanner = scanner;
        this.menuView = menuView;
        this.listaDeApolices = new ArrayList<>();
        this.listaDeCargas = new ArrayList<>();
        this.listaDeVeiculos = new ArrayList<>();
        this.apoliceController = new ApoliceController(listaDeApolices, scanner);
        this.cargaController = new CargaController(listaDeCargas, scanner);
        this.veiculoController = new VeiculoController(listaDeVeiculos, scanner);
    }

    // Construtor que aceita Scanner, MenuView, ApoliceController, CargaController e VeiculoController
    public MenuController(Scanner scanner, MenuView menuView, ApoliceController apoliceController, CargaController cargaController, VeiculoController veiculoController) {
        this(scanner, menuView);
        this.apoliceController = apoliceController;
        this.cargaController = cargaController;
        this.veiculoController = veiculoController;
    }

    public void exibirMenuPrincipal() {
        while (true) {
            System.out.println("Menu Principal");
            System.out.println("1. Gerenciar Apólices");
            System.out.println("2. Gerenciar Cargas");
            System.out.println("3. Gerenciar Veículos");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar a nova linha após a leitura do número

                if (opcao == 4) {
                    System.out.println("Saindo da aplicação.");
                    break;
                }

                processarOpcao(opcao);
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número de opção válido.");
                scanner.nextLine(); // Limpar o buffer do scanner
            }
        }
    }

    public void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                // Lógica para gerenciar Apólices
                apoliceController.gerenciarApolices();
                break;
            case 2:
                // Lógica para gerenciar Cargas
                cargaController.gerenciarCargas();
                break;
            case 3:
                // Lógica para gerenciar Veículos
                veiculoController.gerenciarVeiculos();
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }
}
