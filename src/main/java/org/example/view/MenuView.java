package org.example.view;

import java.util.Scanner;

public class MenuView {
    private Scanner scanner;

    public MenuView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void showMenu() {
        while (true) {
            System.out.println("Menu Principal");
            System.out.println("1. Gerenciar Apólices");
            System.out.println("2. Gerenciar Cargas");
            System.out.println("3. Gerenciar Veículos");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
        }
    }

    public int getEscolhaDoUsuario() {
        return scanner.nextInt();
    }
}
