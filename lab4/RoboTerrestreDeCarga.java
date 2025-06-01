//Classe que representa um robô terrestre de carga, capaz de transportar peso
public class RoboTerrestreDeCarga extends RoboTerrestre implements Colorido {

    private int cargaMaxima;    //Capacidade máxima de carga que o robô pode transportar
    private int cargaAtual;     //Peso atual que o robô está carregando
    private TipoColorido cor;   //atributo cor

    public void setCor(TipoColorido cor){ //define a cor
        this.cor=cor;
    }

    public TipoColorido getCor(){ //retorna a cor
        return this.cor;
    }
    
    //Construtor da classe RoboTerrestreDeCarga
    public RoboTerrestreDeCarga(String nome, int posicaoX, int posicaoY, int posicaoZ, int v_max, int cargaMaxima, TipoColorido cor) {
        super(nome, posicaoX, posicaoY, posicaoZ, v_max);
        this.cargaMaxima = cargaMaxima;
        this.cargaAtual = 0;
        this.cor = cor;
    }

    //Método para carregar peso no robô.
    public boolean carregarPeso(int peso) {
        if (cargaAtual + peso <= cargaMaxima) {
            cargaAtual += peso;
            System.out.println(getId() + " carregou " + peso + " kg. Carga atual: " + cargaAtual + " kg.");
            return true;
        } else {
            System.out.println(getId() + " não pode carregar tanto peso! " + peso + " kg. Esta carga excede o limite!");
            return false;
        }
    }

    @Override
    public void executarTarefa() {
        // TODO Auto-generated method stub
        super.executarTarefa();
    }

}
