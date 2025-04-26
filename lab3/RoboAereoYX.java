public class RoboAereoYX extends RoboAereo{

    private int nivelBateria; //Nível atual da bateria do robô

    //Método para recarregar completamente a bateria do robô.
    public void carregarBateria() {
        this.nivelBateria = 50;     //Define o nível da bateria de volta para 50%
        System.out.println(getNome() + " foi recarregado. Nível de bateria: " + nivelBateria + "%.");
    }

    //Método que retorna o nível atual da bateria do robô.
    public int getNivelBateria() {
        System.out.println("Bateria de " + getNome() + " igual a: " + nivelBateria + "%");
        return nivelBateria;
    }

    //Método construtor do robô aéreo yx.
    public RoboAereoYX(String nome, int posicaoX, int posicaoY, int posicaoZ, int altitudeMax){
        super(nome, posicaoX, posicaoY, posicaoZ, altitudeMax);
        this.nivelBateria=50;
    }

    @Override
    public void subir(int metros, Ambiente a){
        //Reduz a bateria a cada movimento
        if(metros!=0){
            if (nivelBateria >= 10 && metros>0) {
                nivelBateria -= 10;
            } else {
                nivelBateria = 0;
                System.out.println(getNome() + " está sem bateria! Precisa recarregar!");
                return;
            }
        }
        else{
            System.out.println("Movimento nulo!");//não gasta bateria se o movimento é delta 0
        }

        super.subir(metros, a);
    }

    @Override
    public void descer(int metros, Ambiente a){
        //Reduz a bateria a cada movimento
        if(metros!=0){
            if (nivelBateria >= 10) {
                nivelBateria -= 10;
            } else {
                nivelBateria = 0;
                System.out.println(getNome() + " está sem bateria! Precisa recarregar!");
                return;
            }
        }
        else{
            System.out.println("Movimento nulo!");//não gasta bateria se o movimento é delta 0
        }

        super.descer(metros, a);
    }

    @Override
    public boolean mover(int deltaX, int deltaY, Ambiente a) {
        if (Math.abs(deltaX) + Math.abs(deltaY) != 0) {
            if (nivelBateria >= 10) {
                nivelBateria -= 10;
            } else {
                nivelBateria = 0;
                System.out.println(getNome() + " está sem bateria! Precisa recarregar!");
                return false;
            }
    
            if (a.dentroDosLimites(this.posicaoX + deltaX, this.posicaoY + deltaY, this.posicaoZ)) {
                // Movimentação no eixo Y
                for (int i = this.posicaoY; i != this.posicaoY + deltaY; i += (deltaY > 0) ? 1 : -1) {
                    if (this.identificarObstaculo(a, this.posicaoX, i, this.posicaoZ) ||
                        a.verificarSeTemObstaculoNoDestino(this, 0, (deltaY > 0) ? 1 : -1, 0)) {
                        System.out.println("Movimentacao cancelada!");
                        return false;
                    }
                }
                if (!this.identificarObstaculo(a, this.posicaoX, this.posicaoY + deltaY, this.posicaoZ) &&
                    !a.verificarSeTemObstaculoNoDestino(this, 0, (deltaY > 0) ? 1 : -1, 0)) {
                    this.posicaoY += deltaY;
                } else {
                    System.out.println("Movimentacao cancelada!");
                    return false;
                }
    
                // Movimentação no eixo X
                for (int i = this.posicaoX; i != this.posicaoX + deltaX; i += (deltaX > 0) ? 1 : -1) {
                    if (this.identificarObstaculo(a, i, this.posicaoY, this.posicaoZ) ||
                        a.verificarSeTemObstaculoNoDestino(this, (deltaX > 0) ? 1 : -1, 0, 0)) {
                        System.out.println("Movimentacao cancelada!");
                        return false;
                    }
                }
                if (!this.identificarObstaculo(a, this.posicaoX + deltaX, this.posicaoY, this.posicaoZ) &&
                    !a.verificarSeTemObstaculoNoDestino(this, (deltaX > 0) ? 1 : -1, 0, 0)) {
                    this.posicaoX += deltaX;
                } else {
                    System.out.println("Movimentacao cancelada!");
                    return false;
                }
    
                if (deltaX > 0) {
                    this.direcao = "Leste";
                } else {
                    this.direcao = "Oeste";
                }
                return true;
            }
        } else {
            System.out.println("Movimento nulo!"); // não gasta bateria se o movimento é delta 0
        }
    
        return false;
    }
    
    
    
    
    
}




