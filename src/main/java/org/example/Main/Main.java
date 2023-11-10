package org.example.Main;

import org.example.controller.*;
import org.example.model.*;
import org.example.view.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crie listas para cada tipo de objeto
        List<Apolice> listaDeApolices = new ArrayList<>();
        List<Carga> listaDeCargas = new ArrayList<>();
        List<Veiculo> listaDeVeiculos = new ArrayList<>();

        // Crie instâncias dos controladores, passando as listas e o scanner
        ApoliceController apoliceController = new ApoliceController(listaDeApolices, scanner);
        CargaController cargaController = new CargaController(listaDeCargas, scanner);
        VeiculoController veiculoController = new VeiculoController(listaDeVeiculos, scanner);

        // Crie instâncias das visões de formulário
        ApoliceFormView apoliceFormView = new ApoliceFormView(scanner);
        CargaFormView cargaFormView = new CargaFormView(scanner);
        VeiculoFormView veiculoFormView = new VeiculoFormView(scanner);

        // Crie uma instância da visão do menu
        MenuView menuView = new MenuView(scanner);

// Crie uma instância do controlador do menu
        MenuController menuController = new MenuController(scanner, menuView);

// Inicie a aplicação exibindo o menu
        menuController.exibirMenuPrincipal();

    }
}

