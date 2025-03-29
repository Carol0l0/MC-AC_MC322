//Classe que representa um robô terrestre de carga, capaz de transportar peso
public class RoboTerrestreDeCarga extends RoboTerrestre {

    private int cargaMaxima;    //Capacidade máxima de carga que o robô pode transportar
    private int cargaAtual;     //Peso atual que o robô está carregando
    private int nivelBateria;   //Nível atual da bateria do robô

    
    //Construtor da classe RoboTerrestreDeCarga
    public RoboTerrestreDeCarga(String nome, int posicaoX, int posicaoY, int posicaoZ, int v_max, int cargaMaxima, int bateria) {
        super(nome, posicaoX, posicaoY, posicaoZ, v_max);
        this.cargaMaxima = cargaMaxima;
        this.cargaAtual = 0;
        this.nivelBateria = 50;     //Inicializa a bateria com 50%
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

    //Método para recarregar completamente a bateria do robô.
    public void carregarBateria() {
        this.nivelBateria = 50;     //Define o nível da bateria de volta para 50%
        System.out.println(getNome() + " foi recarregado. Nível de bateria: " + nivelBateria + "%.");
    }

    //Método que move o robô para uma nova posição dentro do ambiente
    @Override
    public boolean mover(int deltaX, int deltaY, Ambiente a) {
        boolean conseguiuMover = super.mover(deltaX, deltaY, a); //Chama o método mover da classe pai
    
        if (!conseguiuMover) {
            System.out.println("Velocidade máxima excedida! " + getNome() + " não conseguiu se mover!");
            return false;
        }
    
        //Reduz a bateria a cada movimento
        if (nivelBateria >= 10) {
            nivelBateria -= 10;
        } else {
            nivelBateria = 0;
            super.mover(-deltaX, -deltaY, a); //Move de volta para a posição anterior
            System.out.println(getNome() + " está sem bateria! Precisa recarregar!");
        }
        
        System.out.println(getNome() + " Posição: (" + posicaoX + ", " + posicaoY + "). Nível de bateria: " + nivelBateria + "%");
    
        return true;
    }

    //Método que retorna o nível atual da bateria do robô.
    public int getNivelBateria() {
        System.out.println("Bateria de " + getNome() + " igual a: " + nivelBateria + "%");
        return nivelBateria;
    }
}
