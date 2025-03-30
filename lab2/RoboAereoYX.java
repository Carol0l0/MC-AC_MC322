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
        if (nivelBateria >= 10) {
            nivelBateria -= 10;
        } else {
            nivelBateria = 0;
            System.out.println(getNome() + " está sem bateria! Precisa recarregar!");
            return;
        }

        super.subir(metros, a);
    }

    @Override
    public void descer(int metros, Ambiente a){
        //Reduz a bateria a cada movimento
        if (nivelBateria >= 10) {
            nivelBateria -= 10;
        } else {
            nivelBateria = 0;
            System.out.println(getNome() + " está sem bateria! Precisa recarregar!");
            return;
        }

        super.descer(metros, a);
    }

    //O robô se move na direção Y depois da direção X, checando se têm obstáculos no meio do caminho
    @Override
    public boolean mover(int deltaX, int deltaY, Ambiente a) {
        if (nivelBateria >= 10) {
            nivelBateria -= 10;
        } else {
            nivelBateria = 0;
            System.out.println(getNome() + " está sem bateria! Precisa recarregar!");
            return false;
        }

        if(a.dentroDosLimites(this.posicaoX+deltaX, this.posicaoY+deltaY, this.posicaoZ)){
            for(int i=this.posicaoY; i!=this.posicaoY+deltaY; i+= (deltaY>0) ? 1:-1){
                if(this.identificarObstaculo(a, this.posicaoX, i, this.posicaoZ)){
                    System.out.println("Movimentacao cancelada!");
                    return false;
                }
            }
            if(!this.identificarObstaculo(a, this.posicaoX, this.posicaoY+deltaY, this.posicaoZ)){
                this.posicaoY+=deltaY;
            }
            else{
                return false;
            }

            for(int i=this.posicaoX; i!=this.posicaoX+deltaX; i+= (deltaX>0) ? 1:-1){
                if(this.identificarObstaculo(a, i, this.posicaoY, this.posicaoZ)){
                    System.out.println("Movimentacao cancelada!");
                    return false;
                }
            }
            if(!this.identificarObstaculo(a, this.posicaoX+deltaX, this.posicaoY, this.posicaoZ)){
                this.posicaoX+=deltaX;
            }
            else{
                System.out.println("Movimentacao cancelada!");
                return false;
            }
            
            if(deltaX>0){
                this.direcao="Leste";
            }
            else{
                this.direcao="Oeste";
            }
            return true;
        }

        return false;
    }
}