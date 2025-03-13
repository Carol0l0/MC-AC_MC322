public class Ambiente{
    
    private int alturaY;
    private int larguraX;

    public Ambiente(int alturaY, int larguraX){

        this.alturaY = alturaY;
        this.larguraX = larguraX;
    }


    public boolean XdentroDosLimites (int alturaY, int posicaoY){
        return (posicaoY <= alturaY);

    }

    public boolean YdentroDosLimites (int larguraX, int posicaoX){
        return (larguraX <= posicaoX);
    }

}