package missoes;
// MissaoPatrulhar.java
import java.util.List;

import exception.RoboDesligadoException;
import robos.AgenteInteligente;

import java.util.ArrayList; // Para o exemplo do Main
import java.util.Arrays;    // Para o exemplo do Main

public class MissaoPatrulhar implements Missao {
    private String nome;
    private String descricao;
    private List<int[]> caminho; // Lista de coordenadas [x, y] a serem patrulhadas

    public MissaoPatrulhar(List<int[]> caminho) {
        this.nome = "Missão de Patrulha em Caminho Definido";
        this.caminho = caminho;
        this.descricao = "O robô seguirá o caminho predefinido de " + caminho.size() + " pontos.";
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean executar(AgenteInteligente agente) {
        System.out.println("--- Iniciando Missão de Patrulha: " + getNome() + " ---");
        System.out.println("Agente: " + agente.getId() + " - " + getDescricao());

        if (caminho == null || caminho.isEmpty()) {
            System.out.println(agente.getId() + ": O caminho de patrulha está vazio ou não foi definido.");
            return false;
        }

        try {
            for (int i = 0; i < caminho.size(); i++) {
                int[] pontoAlvo = caminho.get(i);
                int destinoX = pontoAlvo[0];
                int destinoY = pontoAlvo[1];

                System.out.println(agente.getId() + " patrulhando para ponto " + (i + 1) + ": (" + destinoX + ", " + destinoY + ")");

                // Calcular deltaX e deltaY para chegar ao ponto alvo
                int deltaX = destinoX - agente.getX1();
                int deltaY = destinoY - agente.getY1();

                // Mover o robô em direção ao ponto alvo
                // Para uma movimentação mais complexa (ex: passo a passo),
                // você precisaria de um algoritmo de pathfinding aqui.
                // Por simplicidade, faremos um movimento direto.
                boolean moveu = agente.mover(deltaX, deltaY);

                if (moveu) {
                    System.out.println(agente.getId() + " chegou ao ponto (" + agente.getX1() + ", " + agente.getY1() + ", " + agente.getZ() + ").");
                    agente.acionarSensores(); // Aciona sensores no ponto de patrulha
                } else {
                    System.out.println(agente.getId() + " não conseguiu chegar ao ponto (" + destinoX + ", " + destinoY + "). Missão interrompida.");
                    return false; // Missão falhou se não conseguiu alcançar um ponto
                }

                // Pequena pausa para simular o tempo
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Missão de Patrulha interrompida.");
                    return false;
                }
            }
            System.out.println("--- Missão de Patrulha concluída com sucesso por " + agente.getId() + " ---");
            return true;
        } catch (RoboDesligadoException e) {
            System.out.println("Erro na Missão de Patrulha para " + agente.getId() + ": " + e.getMessage());
            return false;
        } catch (Exception e) { // Captura outras exceções como ForaDosLimitesException, ObstaculoException
            System.out.println("Erro inesperado durante a Missão de Patrulha para " + agente.getId() + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}