// Classe que representa um robô terrestre de carga, capaz de transportar peso
public class RoboTerrestreDeCarga extends RoboTerrestre implements Colorido {

    private int cargaMaxima;    // Capacidade máxima de carga que o robô pode transportar
    private int cargaAtual;     // Peso atual que o robô está carregando
    private TipoColorido cor;   // Atributo cor

    public void setCor(TipoColorido cor) { // Define a cor
        this.cor = cor;
    }

    public TipoColorido getCor() { // Retorna a cor
        return this.cor;
    }

    // Construtor da classe RoboTerrestreDeCarga
    public RoboTerrestreDeCarga(String nome, int posicaoX, int posicaoY, int posicaoZ, int v_max, int cargaMaxima, TipoColorido cor) {
        super(nome, posicaoX, posicaoY, posicaoZ, v_max);
        this.cargaMaxima = 1000;
        this.cargaAtual = 0;
        this.cor = cor;
    }

    // Método para carregar peso no robô.
    public boolean carregarEntrega(int peso) {
        if (cargaAtual + peso <= cargaMaxima) {
            cargaAtual += peso;
            System.out.println(getId() + " carregou " + peso + " kg. Carga atual: " + cargaAtual + " kg.");
            return true;
        } else {
            System.out.println(getId() + " não pode carregar tanto peso! " + peso + " kg. Esta carga excede o limite!");
            return false;
        }
    }

    // Método para descarregar peso do robô.
    public boolean descarregarEntrega(int peso) {
        if (peso <= cargaAtual) {
            cargaAtual -= peso;
            System.out.println(getId() + " descarregou " + peso + " kg. Carga restante: " + cargaAtual + " kg.");
            return true;
        } else {
            System.out.println(getId() + " não possui " + peso + " kg para descarregar!");
            return false;
        }
    }

    @Override
    public void executarTarefa() {

        if (cargaAtual < 10) {
            carregarEntrega(50);
        }
        
        boolean conseguiuMover = mover(5, 5);

        if (!conseguiuMover) {
            System.out.println(getId() + " não conseguiu chegar ao destino.");
            return;
        }

        boolean descarregado = descarregarEntrega(5);
        if (descarregado) {
            System.out.println(getId() + " chegou ao destino e realizou a entrega.");
        }
    }
}

