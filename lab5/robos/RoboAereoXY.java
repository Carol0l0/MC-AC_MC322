package robos;
public class RoboAereoXY extends RoboAereo implements Colorido{

    private TipoColorido cor; //atributo cor

    //método construtor robô aéreo XY
    public RoboAereoXY(String nome, int posicaoX, int posicaoY, int posicaoZ, int altitudeMax, TipoColorido cor){
        super(nome, posicaoX, posicaoY, posicaoZ, altitudeMax);
        this.cor=cor;
        
    }

    public void setCor(TipoColorido cor){ //define a cor
        this.cor=cor;
    }

    public TipoColorido getCor(){ //retorna a cor
        return this.cor;
    }

    @Override
    public void executarTarefa() { //faz o robô girar no sentido anti-horário
        if(this.direcao=="Norte"){
            this.direcao="Oeste";
            this.exibirPosicao();
            return;
        }
        else if(this.direcao=="Oeste"){
            this.direcao="Sul";
            this.exibirPosicao();
            return;
        }
        else if(this.direcao=="Sul"){
            this.direcao="Leste";
            this.exibirPosicao();
            return;
        }
        this.direcao="Norte";
        this.exibirPosicao();
    }

    //O robô se move na direção X depois da direção Y, checando se têm obstáculos no meio do caminho
    @Override
    public boolean mover(int deltaX, int deltaY) {
        if(this.ambiente.dentroDosLimites(this.posicaoX+deltaX, this.posicaoY+deltaY, this.posicaoZ)){
            //movimentacao em X
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

            //movimentacao em Y
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
                System.out.println("Movimentacao cancelada!");
                return false;
            }
    
            // Atualiza a direção com base no último eixo percorrido
            if (deltaX != 0) {
                this.direcao = (deltaX > 0) ? "Leste" : "Oeste";
            }
            else if (deltaY != 0) {
                this.direcao = (deltaY > 0) ? "Norte" : "Sul";
            }
            return true;
        }
        return false;
    }
}