package org.example.view;

import java.util.Scanner;

public class CargaFormView {
    private Scanner scanner;

    public CargaFormView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int lerId() {
        System.out.print("ID da Carga: ");
        return scanner.nextInt();
    }

    public String lerDescricao() {
        scanner.nextLine(); // Limpar a nova linha após a leitura do ID
        System.out.print("Descrição da Carga: ");
        return scanner.nextLine();
    }

    public double lerPeso() {
        System.out.print("Peso da Carga: ");
        return scanner.nextDouble();
    }
}
