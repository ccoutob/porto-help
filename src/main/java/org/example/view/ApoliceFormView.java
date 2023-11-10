package org.example.view;

import java.util.Scanner;

public class ApoliceFormView {
    private Scanner scanner;

    public ApoliceFormView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int lerNumero() {
        System.out.print("Número da Apólice: ");
        return scanner.nextInt();
    }

    public String lerSeguradora() {
        scanner.nextLine(); // Limpar a nova linha após a leitura do número
        System.out.print("Seguradora: ");
        return scanner.nextLine();
    }

    public double lerValor() {
        System.out.print("Valor: ");
        return scanner.nextDouble();
    }
}
