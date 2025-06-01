//Classe que representa um robô terrestre e a velocidade máxima
public class RoboTerrestre extends Robo {
    
    protected int v_max;
    
    public RoboTerrestre (String nome, int posicaoX, int posicaoY, int posicaoZ, int v_max){
        super(nome, posicaoX, posicaoY, posicaoZ);
        this.v_max=v_max;
    }

    //verificar se a quantidade de movimento excede a velocidade posta
    @Override
    public boolean mover(int deltaX, int deltaY) {
        if(Math.abs(deltaX) + Math.abs(deltaY) > v_max){
            System.out.println("Velocidade máxima excedida! " + getId() + " não conseguiu se mover!");
            return false;
        }

        return super.mover(deltaX, deltaY);
    }

}