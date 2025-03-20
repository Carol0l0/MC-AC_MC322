public class Terrestre extends Robo {
    
    int v_max;
    
    public Terrestre (String nome, int posicaoX, int posicaoY, int v_max){
        super(nome, posicaoX, posicaoY);
        this.v_max=v_max;
    }

    @Override
    public boolean mover(int deltaX, int deltaY, Ambiente a) {
        if(deltaX+deltaY>this.v_max){
            return false;
        }

        return super.mover(deltaX, deltaY, a);
    }
    
}
