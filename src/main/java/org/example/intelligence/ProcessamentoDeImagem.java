package org.example.intelligence;

public class ProcessamentoDeImagem {
    public void processarImagem(String caminhoDaImagem) {
        // Lógica para processar a imagem especificada
        if (caminhoDaImagem != null && !caminhoDaImagem.isEmpty()) {
            // Implemente a lógica de processamento de imagem aqui
            System.out.println("Imagem processada com sucesso.");
        } else {
            System.out.println("Caminho da imagem não especificado. Nenhuma imagem processada.");
        }
    }
}
