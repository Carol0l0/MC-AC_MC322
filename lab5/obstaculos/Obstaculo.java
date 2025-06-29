package obstaculos;
import entidades.Entidade;
import entidades.TipoEntidade;
import ambientes.Ambiente;

/**
 * Representa um obstáculo genérico no ambiente do simulador.
 * Implementa a interface Entidade.
 */
public class Obstaculo implements Entidade{
    // Coordenadas X e Y que definem a área ocupada pelo obstáculo.
    private int posicaoX1;
    private int posicaoY1;
    private int posicaoX2;
    private int posicaoY2;
    // Altura do obstáculo.
    private int altura;
    // O tipo específico deste obstáculo (ex: CAIXADESOM, ARVOREMISTICA).
    private TipoObstaculo tipo_o;
    // O tipo geral de entidade (sempre OBSTACULO para esta classe).
    protected TipoEntidade tipo_e;

    
    public Obstaculo(int x1, int y1, int x2, int y2, TipoObstaculo tipo_o) {

        // Garantindo que x1<x2 e y1<y2
        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);
        int minY = Math.min(y1, y2);
        int maxY = Math.max(y1, y2);

        this.posicaoX1 = minX;
        this.posicaoY1 = minY;
        this.posicaoX2 = maxX;
        this.posicaoY2 = maxY;
        this.tipo_o = tipo_o;
        this.altura = tipo_o.getAlturaPadrao(); // Altura padrão definida pelo tipo.
        this.tipo_e = TipoEntidade.OBSTACULO; // Define como tipo geral OBSTACULO.

    }

    /**
     * Verifica se o obstáculo pode ser adicionado ao ambiente sem colidir.
     * Considera robôs e outros obstáculos já existentes.
     * @param a O ambiente onde se tenta adicionar o obstáculo.
     * @return Verdadeiro se puder ser adicionado, falso caso contrário.
     */
    public Boolean podeAdicionar(Ambiente a) {
        for(Entidade e : a.listaEntidades){
            // Verifica se um robô impede a adição do obstáculo.
            if(e.getTipoEntidade()==TipoEntidade.ROBO){ 
                int x=e.getX1();
                int y=e.getY1();
                int z=e.getZ();
                if (x>=this.posicaoX1 && x<=this.posicaoX2 && y>=this.posicaoY1 
                && y<=this.posicaoY2 && z<=this.tipo_o.getAlturaPadrao()) {
                    System.out.println("Obstáculo detectado! Robô: " + e.getId());
                    return false;
                }
            }
            else{ // Verifica se outro obstáculo impede a adição.
                if(this.posicaoX2 >= e.getX1() && this.posicaoX1 <= e.getX2() &&
                this.posicaoY2 >= e.getY1() && this.posicaoY1 <= e.getY2()) {
                    System.out.println("Obstáculo detectado! " + e.getId());
                    return false;
                }
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

    public String getId(){
        return tipo_o.getNome();
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

    public int getZ() { //retorna a altura padrão
        return altura;
    }

    public void setX1(int x1){
        this.posicaoX1=x1;
    }

    public void setX2(int x2){
        this.posicaoX2=x2;
    }

    public void setY1(int y1){
        this.posicaoY1=y1;
    }

    public void setY2(int y2){
        this.posicaoY2=y2;
    }

    public void setTipo_o(TipoObstaculo tipo) {
        this.tipo_o = tipo;
    }

    
}