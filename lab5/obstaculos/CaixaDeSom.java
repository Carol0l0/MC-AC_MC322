package obstaculos;
import entidades.TipoEntidade;

/**
 * Representa um obstáculo do tipo Caixa de Som no ambiente.
 * Emite um som com uma intensidade específica.
 */
public class CaixaDeSom extends Obstaculo{
    
    // Nível de intensidade sonora emitida pela caixa.
    private int intensidade;


    public CaixaDeSom(int x1, int y1, int x2, int y2, int intensidade){
        // Chama o construtor da classe pai (Obstaculo), definindo o tipo como CAIXADESOM.
        super(x1, y1, x1, y1, TipoObstaculo.CAIXADESOM);
        this.intensidade=intensidade;
        // Define o tipo de entidade específica para esta caixa de som.
        this.tipo_e=TipoEntidade.CAIXADESOM;
    }

    /**
     * Retorna a intensidade do som emitida pela caixa.
     * @return A intensidade sonora.
     */
    public int getIntensidade(){
        return intensidade;
    }   
    
}