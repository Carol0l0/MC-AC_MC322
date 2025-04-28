public class Obstaculo {
    private int posicaoX1;
    private int posicaoY1;
    private int posicaoX2;
    private int posicaoY2;
    private int altura;
    private TipoObstaculo tipo;

    public Obstaculo(int x1, int y1, int x2, int y2, TipoObstaculo tipo) {

        //garantindo que x1<x2 e y1<y2
        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);
        int minY = Math.min(y1, y2);
        int maxY = Math.max(y1, y2);

        this.posicaoX1 = minX;
        this.posicaoY1 = maxX;
        this.posicaoX2 = minY;
        this.posicaoY2 = maxY;
        this.tipo = tipo;

    }

    public Boolean podeAdicionar(Ambiente a) {
        // Percorre todos os obstáculos no ambiente
        for (Obstaculo o : a.listadeObstaculos) {

            // *CHECAR* Verifica se a posição final está dentro da área e da altura do obstáculo
            if (this.posicaoX2 >= o.getPosicaoX1() && this.posicaoX1 <= o.getPosicaoX2() &&
               this.posicaoY2 >= o.getPosicaoY1() && this.posicaoY1 <= o.getPosicaoY2()) {

                return true;
            }
        }

       //verifica se não tem robô no caminho
       for (Robo robo : a.listadeRobos) {
            int x=robo.getPosicaoX();
            int y=robo.getPosicaoY();
            int z=robo.getPosicaoZ();
           if (x>=this.posicaoX1 && x<=this.posicaoX2 && y>=this.posicaoY1 
           && y<=this.posicaoY2 && z<=this.tipo.getAlturaPadrao()) {
               System.out.println("Obstáculo detectado! Robô: " + robo.getNome());
               return true;
           }
       }
   
       return false;
   }

    public TipoObstaculo getTipo() {
        return tipo;
    }

    public int getPosicaoX1() {
        return posicaoX1;
    }

    public int getPosicaoY1() {
        return posicaoY1;
    }

    public int getPosicaoX2() {
        return posicaoX2;
    }

    public int getPosicaoY2() {
        return posicaoY2;
    }

    public int getAltura() {
        return altura;
    }

    public void setTipo(TipoObstaculo tipo) {
        this.tipo = tipo;
    }

    
}