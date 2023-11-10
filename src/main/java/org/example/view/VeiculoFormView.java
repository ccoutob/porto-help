package org.example.view;

import java.util.Scanner;

public class VeiculoFormView {
    private Scanner scanner;

    public VeiculoFormView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int lerId() {
        System.out.print("ID do Veículo: ");
        return scanner.nextInt();
    }

    public String lerMarca() {
        scanner.nextLine(); // Limpar a nova linha após a leitura do ID
        System.out.print("Marca do Veículo: ");
        return scanner.nextLine();
    }

    public String lerModelo() {
        System.out.print("Modelo do Veículo: ");
        return scanner.nextLine();
    }

    public int lerAno() {
        System.out.print("Ano do Veículo: ");
        return scanner.nextInt();
    }
}
