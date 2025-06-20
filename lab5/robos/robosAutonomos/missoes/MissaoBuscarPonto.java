package robos.robosAutonomos.missoes;
// MissaoBuscarPonto.java

import exception.RoboDesligadoException;
import robos.Robo;
import robos.robosAutonomos.*;

public class MissaoBuscarPonto implements Missao {
    private String descricao;
    private int targetX, targetY;
    private TipoMissao tipoM;
    private Log log; // Atributo para o log
    private Robo robo;

    public MissaoBuscarPonto(int targetX, int targetY, Robo robo, Log log) { // Adicionar Log ao construtor
        this.targetX = targetX;
        this.targetY = targetY;
        this.robo = robo;
        this.tipoM = TipoMissao.BUSCAPORPONTO;
        this.descricao = "O robô irá mover-se até a coordenada (" + targetX + ", " + targetY + ").";
        this.log = log; // Inicializar o atributo log
    }

    @Override
    public TipoMissao getTipoMissao() {
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
        log.adicionarLinha("--- Iniciando Missão de Busca de Ponto: (" + targetX + ", " + targetY + ") --- por " + agente.getId());
        System.out.println("--- Iniciando Missão de Busca de Ponto: " + "(" + targetX + ", " + targetY + ")" + " ---");
        System.out.println("Agente: " + agente.getId() + " - " + getDescricao());

        try {
            // Calcular deltaX e deltaY para chegar ao ponto alvo
            int deltaX = targetX - agente.getX1();
            int deltaY = targetY - agente.getY1();
            
            // Mover o robô. A exceção RoboDesligadoException já é tratada por moverPara.
            boolean moveu = agente.mover(deltaX, deltaY);

            if (moveu) {
                log.adicionarPosicao(agente.getX1(), agente.getY1(), this); // Registrar a nova posição
                System.out.println(agente.getId() + " chegou ao ponto alvo. Posição atual: (" + agente.getX1() + ", " + agente.getY1() + ", " + agente.getZ() + ")");
                // Aciona sensores ao chegar
                String sensorLog = agente.gerenciadorSens.acionarSensores();
                if (sensorLog != null && !sensorLog.isEmpty()) {
                    log.adicionarLinha(sensorLog);
                }
                System.out.println("--- Missão de Busca de Ponto concluída com sucesso por " + agente.getId() + " ---");
                log.adicionarLinha("--- Missão de Busca de Ponto concluída com sucesso por " + agente.getId() + " ---");
                return true;
            } else {
                log.adicionarLinha(this.robo.getId() + " não conseguiu chegar ao ponto (" + targetX + ", " + targetY + "). Missão falhou.");
                System.out.println(agente.getId() + " não conseguiu chegar ao ponto (" + targetX + ", " + targetY + "). Missão falhou.");
                log.adicionarLinha("--- Missão de Busca de Ponto falhou por " + agente.getId() + " ---");
                return false;
            }
        } catch (RoboDesligadoException e) {
            log.adicionarLinha("Erro na Missão de Busca de Ponto para " + this.robo.getId() + ": " + e.getMessage());
            System.out.println("Erro na Missão de Busca de Ponto para " + agente.getId() + ": " + e.getMessage());
            return false;
        } catch (Exception e) { // Captura outras exceções como ForaDosLimitesException, ObstaculoException
            log.adicionarLinha("Erro inesperado durante a Missão de Busca de Ponto para " + this.robo.getId() + ": " + e.getMessage());
            System.out.println("Erro inesperado durante a Missão de Busca de Ponto para " + agente.getId() + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}