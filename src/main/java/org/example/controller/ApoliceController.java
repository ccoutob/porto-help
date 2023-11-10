package org.example.controller;

import org.example.model.Apolice;
import org.example.services.CRUD;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ApoliceController implements CRUD<Apolice> {
    private List<Apolice> listaDeApolices;
    private Scanner scanner;

    public ApoliceController(List<Apolice> listaDeApolices, Scanner scanner) {
        this.listaDeApolices = listaDeApolices;
        this.scanner = scanner;
    }

    public List<Apolice> getListaDeApolices() {
        return listaDeApolices;
    }

    @Override
    public void criar(Apolice apolice) {
        listaDeApolices.add(apolice);
        System.out.println("Apólice criada com sucesso.");
    }

    @Override
    public Apolice ler(int id) {
        for (Apolice apolice : listaDeApolices) {
            if (apolice.getNumero() == id) {
                return apolice;
            }
        }
        System.out.println("Apólice não encontrada com o ID especificado.");
        return null;
    }

    @Override
    public void atualizar(Apolice apolice) {
        for (Apolice ap : listaDeApolices) {
            if (ap.getNumero() == apolice.getNumero()) {
                ap.setSeguradora(apolice.getSeguradora());
                ap.setDataInicio(apolice.getDataInicio());
                ap.setDataFim(apolice.getDataFim());
                ap.setValor(apolice.getValor());
                System.out.println("Apólice atualizada com sucesso.");
                return;
            }
        }
        System.out.println("Apólice não encontrada para atualização.");
    }

    @Override
    public void deletar(int id) {
        Apolice apolice = ler(id);
        if (apolice != null) {
            listaDeApolices.remove(apolice);
            System.out.println("Apólice removida com sucesso.");
        } else {
            System.out.println("Apólice não encontrada para exclusão.");
        }
    }

    public void gerenciarApolices() {
        while (true) {
            System.out.println("Gerenciar Apólices");
            System.out.println("1. Listar Apólices");
            System.out.println("2. Criar Apólice");
            System.out.println("3. Atualizar Apólice");
            System.out.println("4. Excluir Apólice");
            System.out.println("5. Solicitar Recomendação de Modal de Guincho");
            System.out.println("6. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    listarApolices();
                    break;
                case 2:
                    criarApolice();
                    break;
                case 3:
                    atualizarApolice();
                    break;
                case 4:
                    excluirApolice();
                    break;
                case 5:
                    solicitarRecomendacao();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void listarApolices() {
        for (Apolice apolice : listaDeApolices) {
            System.out.println(apolice);
        }
    }

    private void criarApolice() {
        System.out.print("Número da Apólice: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Seguradora: ");
        String seguradora = scanner.nextLine();

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            System.out.print("Data de Início (yyyy-MM-dd): ");
            Date dataInicio = dateFormat.parse(scanner.nextLine());

            System.out.print("Data de Fim (yyyy-MM-dd): ");
            Date dataFim = dateFormat.parse(scanner.nextLine());

            System.out.print("Valor: ");
            double valor = scanner.nextDouble();
            scanner.nextLine();

            Apolice novaApolice = new Apolice(numero, seguradora, dataInicio, dataFim, valor);
            criar(novaApolice);
        } catch (ParseException e) {
            System.out.println("Formato de data inválido. Certifique-se de usar o formato yyyy-MM-dd.");
        }
    }

    private void atualizarApolice() {
        System.out.print("Número da Apólice para atualização: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        Apolice apoliceExistente = ler(numero);
        if (apoliceExistente != null) {
            System.out.print("Nova Seguradora: ");
            String seguradora = scanner.nextLine();

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                System.out.print("Nova Data de Início (yyyy-MM-dd): ");
                Date dataInicio = dateFormat.parse(scanner.nextLine());

                System.out.print("Nova Data de Fim (yyyy-MM-dd): ");
                Date dataFim = dateFormat.parse(scanner.nextLine());

                System.out.print("Novo Valor: ");
                double valor = scanner.nextDouble();
                scanner.nextLine();

                Apolice apoliceAtualizada = new Apolice(numero, seguradora, dataInicio, dataFim, valor);
                atualizar(apoliceAtualizada);
            } catch (ParseException e) {
                System.out.println("Formato de data inválido. Certifique-se de usar o formato yyyy-MM-dd.");
            }
        }
    }

    private void excluirApolice() {
        System.out.print("Número da Apólice para exclusão: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        deletar(numero);
    }

    private void solicitarRecomendacao() {
        // Lógica para solicitar recomendação de modal de guincho com base nas apólices
        // Suponha que você gere uma recomendação fictícia para demonstração, por exemplo:
        String recomendacao = "Recomendação de modal de guincho: Modal A é mais econômico e atende às suas necessidades.";
        System.out.println(recomendacao);
    }
}
