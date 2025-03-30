//Classe que representa um robô terrestre de carga, capaz de transportar peso
public class RoboTerrestreDeCarga extends RoboTerrestre {

    private int cargaMaxima;    //Capacidade máxima de carga que o robô pode transportar
    private int cargaAtual;     //Peso atual que o robô está carregando
    
    //Construtor da classe RoboTerrestreDeCarga
    public RoboTerrestreDeCarga(String nome, int posicaoX, int posicaoY, int posicaoZ, int v_max, int cargaMaxima) {
        super(nome, posicaoX, posicaoY, posicaoZ, v_max);
        this.cargaMaxima = cargaMaxima;
        this.cargaAtual = 0;
    }

    //Método para carregar peso no robô.
    public boolean carregarPeso(int peso) {
        if (cargaAtual + peso <= cargaMaxima) {
            cargaAtual += peso;
            System.out.println(getNome() + " carregou " + peso + " kg. Carga atual: " + cargaAtual + " kg.");
            return true;
        } else {
            System.out.println(getNome() + " não pode carregar tanto peso! " + peso + " kg. Esta carga excede o limite!");
            return false;
        }
    }

}
