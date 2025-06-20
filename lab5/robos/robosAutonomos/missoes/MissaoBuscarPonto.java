package robos.robosAutonomos.missoes;
// MissaoBuscarPonto.java

import exception.RoboDesligadoException;
import robos.Robo;
import robos.robosAutonomos.*;

public class MissaoBuscarPonto implements Missao {
    private String descricao;
    private int targetX, targetY;
    private TipoMissao tipoM;
    private Log log;
    private Robo robo;

    public MissaoBuscarPonto(int targetX, int targetY, Robo robo) {
        this.targetX = targetX;
        this.targetY = targetY;
        this.robo = robo;
        this.tipoM = TipoMissao.BUSCAPORPONTO;
        this.descricao = "O robô irá mover-se até a coordenada (" + targetX + ", " + targetY + ").";
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
        System.out.println("--- Iniciando Missão de Busca de Ponto: " + "(" + targetX + ", " + targetY + ")" + " ---");
        System.out.println("Agente: " + agente.getId() + " - " + getDescricao());

        try {
            // Calcular deltaX e deltaY para chegar ao ponto alvo
            int deltaX = targetX - agente.getX1();
            int deltaY = targetY - agente.getY1();

            System.out.println(agente.getId() + " movendo para (" + targetX + ", " + targetY + ")");
            boolean moveu = agente.mover(deltaX, deltaY);
             //FAZER O RESTO DO LOG

            if (moveu) {
                log.adicionarPosicao(targetX, targetY, this);
                System.out.println(agente.getId() + " chegou ao ponto alvo. Posição atual: (" + agente.getX1() + ", " + agente.getY1() + ", " + agente.getZ() + ")");
                // Aciona sensores ao chegar
                log.adicionarLinha(agente.gerenciadorSens.acionarSensores());
                System.out.println("--- Missão de Busca de Ponto concluída com sucesso por " + agente.getId() + " ---");
                return true;
            } else {
                log.adicionarLinha(this.robo.getId()+"não conseguiu chegar ao ponto (" + targetX + ", " + targetY + "). Missão falhou.");
                System.out.println(agente.getId() + " não conseguiu chegar ao ponto (" + targetX + ", " + targetY + "). Missão falhou.");
                return false;
            }
        } catch (RoboDesligadoException e) {
            log.adicionarLinha("Erro na Missão de Busca de Ponto "+this.robo.getId()+" estava desligado!");
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