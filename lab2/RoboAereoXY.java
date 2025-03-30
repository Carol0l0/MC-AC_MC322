public class RoboAereoXY extends RoboAereo{

    public String cor;

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
        if(a.dentroDosLimites(this.posicaoX+deltaX, this.posicaoY+deltaY, this.posicaoZ)){
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
                System.out.println("Movimentacao cancelada!");
                return false;
            }

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