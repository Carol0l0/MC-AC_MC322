public class AereoXY extends Aereo{//anda na direção X depois na direção Y

    public AereoXY(String nome, int posicaoX, int posicaoY, int posicaoZ, int altitude, int altitudeMax){
        super(nome, posicaoX, posicaoY, posicaoZ, altitude, altitudeMax);
    }

    @Override
    public boolean mover(int deltaX, int deltaY, Ambiente a) {
        if(super.mover(deltaX, deltaY, a)){
            for(int i=this.posicaoX; i!=this.posicaoX+deltaX; i+= (deltaX>0) ? 1:-1){
                if(!identificarObstaculo(a, i, this.posicaoY, this.posicaoZ)){
                    System.out.println("Movimentacao cancelada!");
                    return false;
                }
            }
            this.posicaoX+=deltaX;
            for(int i=this.posicaoY; i!=this.posicaoY+deltaY; i+= (deltaY>0) ? 1:-1){
                if(!identificarObstaculo(a, this.posicaoX, i, this.posicaoZ)){
                    System.out.println("Movimentacao cancelada!");
                    return false;
                }
            }
            this.posicaoY+=deltaY;
            if(deltaY>0){
                this.direcao="Norte";
            }
            else{
                this.direcao="Sul";
            }
            return true;
        }

        return false;
    }
}