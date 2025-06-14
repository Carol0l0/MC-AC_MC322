// MissaoBuscarPonto.java

public class MissaoBuscarPonto implements Missao {
    private String nome;
    private String descricao;
    private int targetX, targetY;

    public MissaoBuscarPonto(int targetX, int targetY) {
        this.nome = "Missão de Busca de Ponto Específico";
        this.targetX = targetX;
        this.targetY = targetY;
        this.descricao = "O robô irá mover-se até a coordenada (" + targetX + ", " + targetY + ").";
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
        System.out.println("--- Iniciando Missão de Busca de Ponto: " + getNome() + " ---");
        System.out.println("Agente: " + agente.getId() + " - " + getDescricao());

        try {
            // Calcular deltaX e deltaY para chegar ao ponto alvo
            int deltaX = targetX - agente.getX1();
            int deltaY = targetY - agente.getY1();

            System.out.println(agente.getId() + " movendo para (" + targetX + ", " + targetY + ")");
            boolean moveu = agente.mover(deltaX, deltaY);

            if (moveu) {
                System.out.println(agente.getId() + " chegou ao ponto alvo. Posição atual: (" + agente.getX1() + ", " + agente.getY1() + ", " + agente.getZ() + ")");
                agente.acionarSensores(); // Aciona sensores ao chegar
                System.out.println("--- Missão de Busca de Ponto concluída com sucesso por " + agente.getId() + " ---");
                return true;
            } else {
                System.out.println(agente.getId() + " não conseguiu chegar ao ponto (" + targetX + ", " + targetY + "). Missão falhou.");
                return false;
            }
        } catch (RoboDesligadoException e) {
            System.out.println("Erro na Missão de Busca de Ponto para " + agente.getId() + ": " + e.getMessage());
            return false;
        } catch (Exception e) { // Captura outras exceções como ForaDosLimitesException, ObstaculoException
            System.out.println("Erro inesperado durante a Missão de Busca de Ponto para " + agente.getId() + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}