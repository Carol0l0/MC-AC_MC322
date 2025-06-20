package robos.robosAutonomos.missoes;
// MissaoPatrulhar.java
import java.util.List;

import exception.RoboDesligadoException;
import robos.Robo;
import robos.robosAutonomos.*;

public class MissaoPatrulhar implements Missao {
    private String descricao;
    private TipoMissao tipoM;
    private List<int[]> caminho; // Lista de coordenadas [x, y] a serem patrulhadas
    private Log log; // Atributo para o log
    private Robo robo;

    public MissaoPatrulhar(List<int[]> caminho, Robo robo, Log log) { // Adicionar Log ao construtor
        this.caminho = caminho;
        this.tipoM = TipoMissao.PATRULHAR;
        this.robo = robo;
        this.descricao = "O robô seguirá o caminho predefinido de " + caminho.size() + " pontos.";
        this.log = log; // Inicializar o atributo log
    }

    @Override
    public TipoMissao getTipoMissao(){
        return this.tipoM;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public Robo getRobo(){
        return this.robo;
    }

    @Override
    public boolean executar(AgenteInteligente agente) {
        log.adicionarLinha("--- Iniciando Missão de Patrulha por " + agente.getId() + " ---");
        System.out.println("--- Iniciando Missão de Patrulha: ---");
        System.out.println("Agente: " + agente.getId() + " - " + getDescricao());

        if (caminho == null || caminho.isEmpty()) {
            log.adicionarLinha(agente.getId() + ": O caminho de patrulha está vazio ou não foi definido.");
            System.out.println(agente.getId() + ": O caminho de patrulha está vazio ou não foi definido.");
            return false;
        }

        try {
            for (int i = 0; i < caminho.size(); i++) {
                int[] ponto = caminho.get(i);
                int destinoX = ponto[0];
                int destinoY = ponto[1];

                log.adicionarLinha(agente.getId() + " movendo-se para o ponto " + (i + 1) + ": (" + destinoX + ", " + destinoY + ")");
                System.out.println(agente.getId() + " movendo-se para o ponto " + (i + 1) + ": (" + destinoX + ", " + destinoY + ")");

                // Calcular deltaX e deltaY
                int deltaX = destinoX - agente.getX1();
                int deltaY = destinoY - agente.getY1();

                boolean moveu = agente.mover(deltaX, deltaY);

                if (!moveu) {
                    log.adicionarLinha(agente.getId() + " não conseguiu chegar ao ponto (" + destinoX + ", " + destinoY + "). Missão interrompida.");
                    System.out.println(agente.getId() + " não conseguiu chegar ao ponto (" + destinoX + ", " + destinoY + "). Missão interrompida.");
                    log.adicionarLinha("--- Missão de Patrulha falhou por " + agente.getId() + " ---");
                    return false; // Missão falhou se não conseguiu alcançar um ponto
                }

                log.adicionarPosicao(agente.getX1(), agente.getY1(), this); // Registrar a nova posição
                String sensorLog = agente.gerenciadorSens.acionarSensores();
                if (sensorLog != null && !sensorLog.isEmpty()) {
                    log.adicionarLinha(sensorLog);
                }

                // Pequena pausa para simular o tempo
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    log.adicionarLinha("Missão de Patrulha interrompida para " + agente.getId() + ".");
                    System.out.println("Missão de Patrulha interrompida.");
                    log.adicionarLinha("--- Missão de Patrulha falhou por " + agente.getId() + " ---");
                    return false;
                }
            }
            System.out.println("--- Missão de Patrulha concluída com sucesso por " + agente.getId() + " ---");
            log.adicionarLinha("--- Missão de Patrulha concluída com sucesso por " + agente.getId() + " ---");
            return true;
        } catch (RoboDesligadoException e) {
            log.adicionarLinha("Erro na Missão de Patrulha para " + agente.getId() + ": " + e.getMessage());
            System.out.println("Erro na Missão de Patrulha para " + agente.getId() + ": " + e.getMessage());
            return false;
        } catch (Exception e) { // Captura outras exceções como ForaDosLimitesException, ObstaculoException
            log.adicionarLinha("Erro inesperado durante a Missão de Patrulha para " + agente.getId() + ": " + e.getMessage());
            System.out.println("Erro inesperado durante a Missão de Patrulha para " + agente.getId() + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}