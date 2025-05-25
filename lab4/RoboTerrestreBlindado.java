// Classe que representa um robô terrestre blindado
public class RoboTerrestreBlindado extends RoboTerrestre {

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
            System.out.println(this.getNome() + " está destruído! Ele não pode mais se mover.");
            return false; // Se o robô foi destruído, ele não pode se mover
        }


        boolean conseguiuMover = false;

        if(delta<this.v_max){
            //Verifica a direção do movimento e chama a função auxiliar correta
            if (direcao.equalsIgnoreCase("Y")) {
                conseguiuMover = moverEmDirecao(0, delta);
                if(delta>0){
                    this.direcao="Norte";
                }
                else{
                    this.direcao="Sul";
                }
            } else if (direcao.equalsIgnoreCase("X")) {
                conseguiuMover = moverEmDirecao(delta, 0);
                if(delta>0){
                    this.direcao="Leste";
                }
                else{
                    this.direcao="Oeste";
                }
            } else {
                System.out.println("Direção inválida! Escolha 'X' ou 'Y'.");
                return false;
            }
        }
        else{
            System.out.println("Velocidade máxima excedida! " + getNome() + " não conseguiu se mover!");
        }

        return conseguiuMover;
    }

    //Método auxiliar para mover o robô na direção desejada, verificando obstáculos e aplicando danos
    private boolean moverEmDirecao(int deltaX, int deltaY) {
        int novoX = posicaoX + deltaX;
        int novoY = posicaoY + deltaY;

        //Verifica se a nova posição está dentro dos limites do ambiente
        if (!this.ambiente.dentroDosLimites(novoX, novoY, posicaoZ)) {
            System.out.println("Movimento inválido! " + getNome() + " tentou sair dos limites do ambiente.");
            return false;
        }

        if(identificarObstaculo(novoX, novoY, 0)){
        //Verifica se há obstáculos no caminho e calcula o dano sofrido
        int dano = contarObstaculos(posicaoX, posicaoY, novoX, novoY);

        //Se houver dano, reduz a resistência do robô
        if(this.resistencia-dano<0){//se a resistência final for menor que 0, ele cancela o movimento
            System.out.println("Movimentação cancelada! Resistência abaixo do necessário p/ execução do movimento");
            return false;
        }
        if (dano > 0) {
            sofreDano(dano);
        }
            System.out.println("Movimento inválido! " + getNome() + " obstáculo detectado no destino");
            return false;
        }

        //Tenta mover o robô para a nova posição chamando a função de movimento da classe base
        return super.mover(deltaX, deltaY);
    }

    //Método para contar quantos obstáculos estão no caminho e calcular o dano causado ao robô
    private int contarObstaculos(int inicioX, int inicioY, int destinoX, int destinoY) {
        int totalDano = 0;

        //Se o movimento for apenas na direção X
        if (inicioY == destinoY) {
            int passo = (destinoX > inicioX) ? 1 : -1;
            for (int x = inicioX + passo; x != destinoX + passo; x += passo) {
                if (identificarObstaculo(x, inicioY, posicaoZ)) {
                    System.out.println("Obstáculo detectado em (" + x + ", " + inicioY + ")");
                    totalDano++; //Aumenta o dano sofrido pelo robô
                }
            }
        }

        //Se o movimento for apenas na direção Y
        if (inicioX == destinoX) {
            int passo = (destinoY > inicioY) ? 1 : -1;
            for (int y = inicioY + passo; y != destinoY + passo; y += passo) {
                if (identificarObstaculo(inicioX, y, posicaoZ)) {
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
            resistencia=0;
            
            funcionando = false;
            System.out.println(getNome() + " foi destruído após múltiplas colisões!");
        }
    }

    public void recuperaDano(int dano) {
        resistencia += dano; //Reduz a resistência do robô
        System.out.println(getNome() + " Consertou " + dano + " dano(s)! Resistência atual: " + resistencia);

        if(resistencia>0){
            funcionando=true;
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

}