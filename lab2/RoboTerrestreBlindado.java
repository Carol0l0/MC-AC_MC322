// Classe que representa um robô terrestre blindado
public class RoboTerrestreBlindado extends Robo {

    private int resistencia;        // Define a resistência do robô antes de ser destruído
    private boolean funcionando;    // Indica se o robô ainda pode operar
    
    //Construtor da classe RoboTerrestreBlindado
    public RoboTerrestreBlindado(String nome, int posicaoX, int posicaoY, int posicaoZ) {
        super(nome, posicaoX, posicaoY, posicaoZ); // Chama o construtor da classe base (Robo)
        this.resistencia = 5; // Define a resistência inicial do robô
        this.funcionando = true; // Define que o robô inicia funcionando normalmente
    }

    //Método para mover o robô em uma determinada direção (X ou Y)
    public boolean mover(int delta, String direcao, Ambiente a) {
        if (!funcionando) {
            System.out.println(getNome() + " está destruído! Ele não pode mais se mover.");
            return false; // Se o robô foi destruído, ele não pode se mover
        }

        boolean conseguiuMover = false;

        //Verifica a direção do movimento e chama a função auxiliar correta
        if (direcao.equalsIgnoreCase("Y")) {
            conseguiuMover = moverEmDirecao(a, 0, delta);
        } else if (direcao.equalsIgnoreCase("X")) {
            conseguiuMover = moverEmDirecao(a, delta, 0);
        } else {
            System.out.println("Direção inválida! Escolha 'X' ou 'Y'.");
            return false;
        }

        //Exibe a nova posição se o movimento for bem-sucedido
        if (conseguiuMover) {
            System.out.println(getNome() + " se moveu para (" + posicaoX + ", " + posicaoY + ")");
        }

        return conseguiuMover;
    }

    //Método auxiliar para mover o robô na direção desejada, verificando obstáculos e aplicando danos
    private boolean moverEmDirecao(Ambiente a, int deltaX, int deltaY) {
        int novoX = posicaoX + deltaX;
        int novoY = posicaoY + deltaY;

        //Verifica se a nova posição está dentro dos limites do ambiente
        if (!a.dentroDosLimites(novoX, novoY, posicaoZ)) {
            System.out.println("Movimento inválido! " + getNome() + " tentou sair dos limites do ambiente.");
            return false;
        }

        //Verifica se há obstáculos no caminho e calcula o dano sofrido
        int dano = contarObstaculos(a, posicaoX, posicaoY, novoX, novoY);

        //Se houver dano, reduz a resistência do robô
        if (dano > 0) {
            sofreDano(dano);
        }

        //Tenta mover o robô para a nova posição chamando a função de movimento da classe base
        return super.mover(deltaX, deltaY, a);
    }

    //Método para contar quantos obstáculos estão no caminho e calcular o dano causado ao robô
    private int contarObstaculos(Ambiente a, int inicioX, int inicioY, int destinoX, int destinoY) {
        int totalDano = 0;

        //Se o movimento for apenas na direção X
        if (inicioY == destinoY) {
            int passo = (destinoX > inicioX) ? 1 : -1;
            for (int x = inicioX + passo; x != destinoX + passo; x += passo) {
                if (identificarObstaculo(a, x, inicioY, posicaoZ)) {
                    System.out.println("Obstáculo detectado em (" + x + ", " + inicioY + ")");
                    totalDano++; //Aumenta o dano sofrido pelo robô
                }
            }
        }

        //Se o movimento for apenas na direção Y
        if (inicioX == destinoX) {
            int passo = (destinoY > inicioY) ? 1 : -1;
            for (int y = inicioY + passo; y != destinoY + passo; y += passo) {
                if (identificarObstaculo(a, inicioX, y, posicaoZ)) {
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
        System.out.println(getNome() + " sofreu " + dano + " dano(s)! Resistência atual: " + resistencia);

        //Se a resistência chegar a zero ou menos, o robô é destruído
        if (resistencia <= 0) {
            funcionando = false;
            System.out.println(getNome() + " foi destruído após múltiplas colisões!");
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

    //Método para imprimir e retornar a posição atual do robô no eixo X
    public int getPosicao() {
        System.out.println("(" + posicaoX + "," + posicaoY + ")");
        return posicaoX;
    }
}