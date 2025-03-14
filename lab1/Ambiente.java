public class Ambiente{
    
    private int alturaY;
    private int larguraX;

    public Ambiente(int alturaY, int larguraX){
        this.alturaY = alturaY;
        this.larguraX = larguraX;
    }


    public boolean dentroDosLimites (int y, int x){
        return x>=0 && x<this.larguraX && y>=0 && y<this.alturaY;
    }

}