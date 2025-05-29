public class Obstaculo implements Entidade{
    private int posicaoX1;
    private int posicaoY1;
    private int posicaoX2;
    private int posicaoY2;
    private int altura;
    private TipoObstaculo tipo_o;
    protected TipoEntidade tipo_e;

    public Obstaculo(int x1, int y1, int x2, int y2, TipoObstaculo tipo_o) {

        //garantindo que x1<x2 e y1<y2
        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);
        int minY = Math.min(y1, y2);
        int maxY = Math.max(y1, y2);

        this.posicaoX1 = minX;
        this.posicaoY1 = minY;
        this.posicaoX2 = maxX;
        this.posicaoY2 = maxY;
        this.tipo_o = tipo_o;
        this.tipo_e = TipoEntidade.OBSTACULO;

    }

    public Boolean podeAdicionar(Ambiente a) {
        // Percorre todos os obstáculos no ambiente
        for (Obstaculo o : a.listadeObstaculos) {

            // *CHECAR* Verifica se a posição final está dentro da área e da altura do obstáculo
            if (this.posicaoX2 >= o.getX1() && this.posicaoX1 <= o.getX2() &&
               this.posicaoY2 >= o.getY1() && this.posicaoY1 <= o.getY2()) {

                return false;
            }
        }

       //verifica se não tem robô no caminho
       for (Robo robo : a.listadeRobos) {
            int x=robo.getX1();
            int y=robo.getY1();
            int z=robo.getZ();
           if (x>=this.posicaoX1 && x<=this.posicaoX2 && y>=this.posicaoY1 
           && y<=this.posicaoY2 && z<=this.tipo_o.getAlturaPadrao()) {
               System.out.println("Obstáculo detectado! Robô: " + robo.getNome());
               return false;
           }
       }
   
       return true;
   }

    public TipoObstaculo getTipoObstaculo() {
        return tipo_o;
    }

    public TipoEntidade getTipoEntidade(){
        return tipo_e;
    }

    public String getDescricao(){
        return this.tipo_e.descricao;
    }

    public char getRepresentacao(){
        return this.tipo_e.representacao;
    }

    public int getX1() {
        return posicaoX1;
    }

    public int getY1() {
        return posicaoY1;
    }

    public int getX2() {
        return posicaoX2;
    }

    public int getY2() {
        return posicaoY2;
    }

    public int getZ() { //retorna a altura
        return altura;
    }

    public void setTipo_o(TipoObstaculo tipo) {
        this.tipo_o = tipo;
    }

    
}