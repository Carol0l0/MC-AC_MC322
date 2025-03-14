public class Robo{
    
    private String nome;
    public int posicaoX;
    public int posicaoY;


    public Robo(String nome, int posicaoX, int posicaoY){
        this.nome = nome;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
    }

    public boolean mover(int deltaX, int deltaY, Ambiente a){
        if(a.dentroDosLimites(this.posicaoY+deltaY, this.posicaoX+deltaX)){
            this.posicaoX+=deltaX;
            this.posicaoY+=deltaY;
            return false;
        }
        System.out.println("vc excedeu os limites");
        return true;

    }

    public String exibirPosicao(){
        System.out.println(this.nome+" esta na posicao ("+this.posicaoX+", "+this.posicaoY+").");
        return this.posicaoX+" "+this.posicaoY;
    }
    
}
