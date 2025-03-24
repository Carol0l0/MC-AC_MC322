import java.util.ArrayList;

public class Robo{
    
    private String nome;
    public int posicaoX;
    public int posicaoY;
    public int posicaoZ;

    public String getNome(Robo r){
        return r.nome;
    }

    public Robo(String nome, int posicaoX, int posicaoY, int posicaoZ){
        this.nome = nome;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.posicaoZ = posicaoZ;
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

    public Boolean identificarObstaculo(Ambiente a, int x, int y, int z){
        for (int i=0; i<listadeRobos.size(); i++) {
            if (){
                return true;
            }
        }
        return false;

    }

    public String exibirPosicao(){
        System.out.println(this.nome+" esta na posicao ("+this.posicaoX+", "+this.posicaoY+").");
        return this.posicaoX+" "+this.posicaoY;
    }

}