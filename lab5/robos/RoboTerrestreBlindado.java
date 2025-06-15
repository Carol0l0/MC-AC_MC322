package robos;
// Classe que representa um robô terrestre blindado

import entidades.Entidade;
import entidades.TipoEntidade;

public class RoboTerrestreBlindado extends RoboTerrestre implements Atacante {

    private int resistencia;        // Define a resistência do robô antes de ser destruído
    private boolean funcionando;    // Indica se o robô ainda pode operar

    //Construtor da classe RoboTerrestreBlindado
    public RoboTerrestreBlindado(String nome, int posicaoX, int posicaoY, int posicaoZ, int v_max) {
        super(nome, posicaoX, posicaoY, posicaoZ, v_max); // Chama o construtor da classe base (RoboTerrestre)
        this.resistencia = 5; // Define a resistência inicial do robô
        this.funcionando = true; // Define que o robô inicia funcionando normalmente
    }

    //Método para mover o robô em uma determinada direção (X ou Y)
    public boolean mover(int delta, String direcao) {
        if (!funcionando) {
            System.out.println(this.getId() + " está destruído! Ele não pode mais se mover.");
            return false; // Se o robô foi destruído, ele não pode se mover
        }

        boolean conseguiuMover = false;

        if (delta < this.v_max) {
            //Verifica a direção do movimento e chama a função auxiliar correta
            if (direcao.equalsIgnoreCase("Y")) {
                conseguiuMover = moverEmDirecao(0, delta);
                this.direcao = (delta > 0) ? "Norte" : "Sul";
            } else if (direcao.equalsIgnoreCase("X")) {
                conseguiuMover = moverEmDirecao(delta, 0);
                this.direcao = (delta > 0) ? "Leste" : "Oeste";
            } else {
                System.out.println("Direção inválida! Escolha 'X' ou 'Y'.");
                return false;
            }
        } else {
            System.out.println("Velocidade máxima excedida! " + getId() + " não conseguiu se mover!");
        }

        return conseguiuMover;
    }

    //Método auxiliar para mover o robô na direção desejada, verificando obstáculos e aplicando danos
    private boolean moverEmDirecao(int deltaX, int deltaY) {
        int novoX = posicaoX + deltaX;
        int novoY = posicaoY + deltaY;

        if (!this.ambiente.dentroDosLimites(novoX, novoY, posicaoZ)) {
            System.out.println("Movimento inválido! " + getId() + " tentou sair dos limites do ambiente.");
            return false;
        }

        int dano = contarObstaculos(posicaoX, posicaoY, novoX, novoY);

        if (this.resistencia - dano < 0) {
            System.out.println("Movimentação cancelada! Resistência abaixo do necessário p/ execução do movimento");
            return false;
        }

        if (dano > 0) {
            sofreDano(dano);
        }

        if(identificarObstaculo(novoX, novoY, 0)) {

            System.out.println("Movimento inválido! " + getId() + " obstáculo detectado no destino");
            return false;
        }

        else{
            this.posicaoX = novoX;
            this.posicaoY = novoY;
            return true;
        }
}

    //Método para contar quantos obstáculos estão no caminho e calcular o dano causado ao robô
    private int contarObstaculos(int inicioX, int inicioY, int destinoX, int destinoY) {
        int totalDano = 0;

        //Se o movimento for apenas na direção X
        if (inicioY == destinoY) {
            int passo = (destinoX > inicioX) ? 1 : -1;
            for (int x = inicioX + passo; x != destinoX + passo; x += passo) {
                if (identificarObstaculoSemSabio(x, inicioY, posicaoZ)) {
                    System.out.println("Obstáculo detectado em (" + x + ", " + inicioY + ")");
                    totalDano++; //Aumenta o dano sofrido pelo robô
                }
            }
        }

        //Se o movimento for apenas na direção Y
        if (inicioX == destinoX) {
            int passo = (destinoY > inicioY) ? 1 : -1;
            for (int y = inicioY + passo; y != destinoY + passo; y += passo) {
                if (identificarObstaculoSemSabio(inicioX, y, posicaoZ)) {
                    System.out.println("Obstáculo detectado em (" + inicioX + ", " + y + ")");
                    totalDano++;
                }
            }
        }

        return totalDano;
    }

    //Método para aplicar dano ao robô quando ele colide com obstáculos
    public void sofreDano(int dano) {
        resistencia -= dano; //Reduz a resistência do robô
        System.out.println(getId() + " sofreu " + dano + " dano(s)! Resistência atual: " + resistencia);

        //Se a resistência chegar a zero ou menos, o robô é destruído
        if (resistencia <= 0) {
            resistencia = 0;
            funcionando = false;
            System.out.println(getId() + " foi destruído após múltiplas colisões!");
        }
    }

    //Método para recuperar dano (reparar o robô)
    public void recuperaDano(int dano) {
        resistencia += dano;
        System.out.println(getId() + " consertou " + dano + " dano(s)! Resistência atual: " + resistencia);

        if (resistencia > 0) {
            funcionando = true;
        }
    }

    //Método para obter a resistência atual do robô
    public int getResistencia() {
        return resistencia;
    }

    //Método para verificar se o robô ainda está funcionando
    public boolean estaFuncionando() {
        return funcionando;
    }

    //herdado da interface atacar, pode remover uma entidade
    public void atacar(String alvo) {
        if (!funcionando) { //adicionar exception que joga pra opção de recarregar ele
            System.out.println(this.getId() + " está destruído e não pode atacar.");
            return;
        }
    
        for (Entidade e : ambiente.listaEntidades) { 
            if (e.getId().equals(alvo)) {
                ambiente.removerEntidade(e);
                System.out.println(this.getId() + " atacou e destruiu o alvo: " + alvo);
                return;
            }
        }
        //da pra colocar exception aqui tbm
        System.out.println("Alvo " + alvo + " não encontrado no ambiente.");
    }
    
    @Override
    public void executarTarefa() {
        if (!funcionando) {
            System.out.println(getId() + " está destruído e não pode executar tarefas.");
            return;
        }
    
        for (int i = 0; i < 5; i++) {
            int destinoX = posicaoX + 1;
            int destinoY = posicaoY;
    
            Entidade entidade = ambiente.qualObstaculo(destinoX, destinoY, posicaoZ);
    
            if (entidade != null 
                && entidade.getTipoEntidade() != TipoEntidade.VAZIO
                && !entidade.getId().equals(this.getId())) {
    
                atacar(entidade.getId());
                System.out.println(getId() + " eliminou um(a) " + entidade.getTipoEntidade());
                return;
            }
    
            if (!mover(1, "X")) {
                System.out.println(getId() + " encontrou obstáculo. Tarefa interrompida.");
                return;
            }
        }
    
        System.out.println("Fim da varredura. Nada foi encontrado.");
    }
    
    

}
