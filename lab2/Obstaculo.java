public class Obstaculo {
    private int posicaoX1;
    private int posicaoY1;
    private int posicaoX2;
    private int posicaoY2;
    private int altura;
    private TipoObstaculo tipo;

    //criar a posição que os obstáculos irão ocupar
    public Obstaculo(int x1, int y1, int x2, int y2, TipoObstaculo tipo) {
        this.posicaoX1 = x1;
        this.posicaoY1 = y1;
        this.posicaoX2 = x2;
        this.posicaoY2 = y2;
        this.tipo = tipo;

    }

    public TipoObstaculo getTipo() {
        return tipo;
    }

    
}



