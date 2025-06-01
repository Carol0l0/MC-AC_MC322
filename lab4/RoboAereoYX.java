public class RoboAereoYX extends RoboAereo{

    private int nivelBateria; //Nível atual da bateria do robô

    //Método construtor do robô aéreo yx.
    public RoboAereoYX(String nome, int posicaoX, int posicaoY, int posicaoZ, int altitudeMax){
        super(nome, posicaoX, posicaoY, posicaoZ, altitudeMax);
        this.nivelBateria=50;
    }

    //Método para recarregar completamente a bateria do robô.
    @Override
    public void executarTarefa() {//Carrega o Robô
        this.nivelBateria = 50;     //Define o nível da bateria de volta para 50%
        System.out.println(getId() + " foi recarregado. Nível de bateria: " + nivelBateria + "%.");
    }

    //Método que retorna o nível atual da bateria do robô.
    public int getNivelBateria() {
        System.out.println("Bateria de " + getId() + " igual a: " + nivelBateria + "%");
        return nivelBateria;
    }

    @Override
    public void subir(int metros, Ambiente a){
        //Reduz a bateria a cada movimento
        if(metros!=0){
            if (nivelBateria >= 10 && metros>0) {
                nivelBateria -= 10;
            } else {
                nivelBateria = 0;
                System.out.println(getId() + " está sem bateria! Precisa recarregar!");
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
                System.out.println(getId() + " está sem bateria! Precisa recarregar!");
                return;
            }
        }
        else{
            System.out.println("Movimento nulo!");//não gasta bateria se o movimento é delta 0
        }

        super.descer(metros, a);
    }

    //O robô se move na direção Y depois da direção X, checando se têm obstáculos no meio do caminho
    @Override
    public boolean mover(int deltaX, int deltaY) {
        if(Math.abs(deltaX)+Math.abs(deltaY)!=0){
            if (nivelBateria >= 10) {
                nivelBateria -= 10;
            } else {
                nivelBateria = 0;
                System.out.println(getId() + " está sem bateria! Precisa recarregar!");
                return false;
            }

            if(this.ambiente.dentroDosLimites(this.posicaoX+deltaX, this.posicaoY+deltaY, this.posicaoZ)){
                //Mobimentaçãp em Y
                for(int i=this.posicaoY; i!=this.posicaoY+deltaY; i+= (deltaY>0) ? 1:-1){//conferindo se tem obstáculos no meio do caminho
                    if(this.identificarObstaculo(this.posicaoX, i, this.posicaoZ)){
                        System.out.println("Movimentacao cancelada!");
                        return false;
                    }
                }
                if(!this.identificarObstaculo(this.posicaoX, this.posicaoY+deltaY, this.posicaoZ)){
                    this.posicaoY+=deltaY;
                }
                else{
                    return false;
                }

                //Movimentação em X
                for(int i=this.posicaoX; i!=this.posicaoX+deltaX; i+= (deltaX>0) ? 1:-1){//conferindo se tem obstáculos no meio do caminho
                    if(this.identificarObstaculo(i, this.posicaoY, this.posicaoZ)){
                        System.out.println("Movimentacao cancelada!");
                        return false;
                    }
                }
                if(!this.identificarObstaculo(this.posicaoX+deltaX, this.posicaoY, this.posicaoZ)){
                    this.posicaoX+=deltaX;
                }
                else{
                    System.out.println("Movimentacao cancelada!");
                    return false;
                }
                
                // Atualiza a direção com base no último eixo percorrido
                if (deltaY != 0) {
                    this.direcao = (deltaY > 0) ? "Norte" : "Sul";
                }
                else if (deltaX != 0) {
                    this.direcao = (deltaX > 0) ? "Leste" : "Oeste";
                }

                return true;
            }
        }
        else{
            System.out.println("Movimento nulo!");//não gasta bateria se o movimento é delta 0
        }

        return false;
    }
}