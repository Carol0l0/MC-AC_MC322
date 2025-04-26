public class Obstaculo {
    public int posicaoX1;
    public int posicaoY1;
    public int posicaoX2;
    public int posicaoY2;
    private int altura;
    private TipoObstaculo tipo;

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

    public int getPosicaoX1() {
        return posicaoX1;
    }

    public void setPosicaoX1(int posicaoX1) {
        this.posicaoX1 = posicaoX1;
    }

    public int getPosicaoY1() {
        return posicaoY1;
    }

    public void setPosicaoY1(int posicaoY1) {
        this.posicaoY1 = posicaoY1;
    }

    public int getPosicaoX2() {
        return posicaoX2;
    }

    public void setPosicaoX2(int posicaoX2) {
        this.posicaoX2 = posicaoX2;
    }

    public int getPosicaoY2() {
        return posicaoY2;
    }

    public void setPosicaoY2(int posicaoY2) {
        this.posicaoY2 = posicaoY2;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setTipo(TipoObstaculo tipo) {
        this.tipo = tipo;
    }

    
}



