public class RoboAereoXY extends RoboAereo{

    public String cor;//atributo cor

    //método construtor robô aéreo XY
    public RoboAereoXY(String nome, int posicaoX, int posicaoY, int posicaoZ, int altitudeMax, String cor){
        super(nome, posicaoX, posicaoY, posicaoZ, altitudeMax);
        this.cor=cor;
    }

    public void setCor(String cor){
        this.cor=cor;
    }

    public String getCor(){
        return this.cor;
    }

    @Override
    public boolean mover(int deltaX, int deltaY, Ambiente a) {
        int direcaoX = Integer.compare(deltaX, 0); // 1 para leste, -1 para oeste, 0 para nenhum
        int direcaoY = Integer.compare(deltaY, 0); // 1 para norte, -1 para sul, 0 para nenhum
    
        // Caminho em X
        for (int i = 0; i != deltaX; i += direcaoX) {
            if (!a.dentroDosLimites(posicaoX + direcaoX, posicaoY, posicaoZ)) {
                System.out.println("Movimento fora dos limites no eixo X.");
                return false;
            }
            if (a.verificarSeTemObstaculoNoDestino(this, direcaoX, 0, 0)) {
                System.out.println("Movimentacao cancelada por obstáculo no eixo X!");
                return false;
            }
            posicaoX += direcaoX;
        }
    
        // Caminho em Y
        for (int i = 0; i != deltaY; i += direcaoY) {
            if (!a.dentroDosLimites(posicaoX, posicaoY + direcaoY, posicaoZ)) {
                System.out.println("Movimento fora dos limites no eixo Y.");
                return false;
            }
            if (a.verificarSeTemObstaculoNoDestino(this, 0, direcaoY, 0)) {
                System.out.println("Movimentacao cancelada por obstáculo no eixo Y!");
                return false;
            }
            posicaoY += direcaoY;
        }
    
        // Atualiza a direção com base no último eixo percorrido
        if (deltaY != 0) {
            this.direcao = (deltaY > 0) ? "Norte" : "Sul";
        } else if (deltaX != 0) {
            this.direcao = (deltaX > 0) ? "Leste" : "Oeste";
        }
    
        return true;
    }
    
}