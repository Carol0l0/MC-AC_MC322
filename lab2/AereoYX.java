public class AereoYX extends Aereo{//anda na direção Y depois na direção X

    public AereoYX(String nome, int posicaoX, int posicaoY, int posicaoZ, int altitude, int altitudeMax){
        super(nome, posicaoX, posicaoY, posicaoZ, altitude, altitudeMax);
    }

    @Override
    public boolean mover(int deltaX, int deltaY, Ambiente a) {
        if(super.mover(deltaX, deltaY, a)){
            for(int i=this.posicaoY; i!=this.posicaoY+deltaY; i+= (deltaY>0) ? 1:-1){
                if(!identificarObstaculo(a, this.posicaoX, i, this.posicaoZ)){
                    System.out.println("Movimentacao cancelada!");
                    return false;
                }
            }
            this.posicaoY+=deltaY;
            for(int i=this.posicaoX; i!=this.posicaoX+deltaX; i+= (deltaX>0) ? 1:-1){
                if(!identificarObstaculo(a, i, this.posicaoY, this.posicaoZ)){
                    System.out.println("Movimentacao cancelada!");
                    return false;
                }
            }
            this.posicaoX+=deltaX;
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