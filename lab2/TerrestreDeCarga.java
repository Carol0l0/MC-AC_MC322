public class TerrestreDeCarga extends Terrestre {

    private int cargaMaxima; 
    private int cargaAtual;  

    public TerrestreDeCarga(String nome, int posicaoX, int posicaoY, int posicaoZ, int v_max, int cargaMaxima) {
        super(nome, posicaoX, posicaoY, posicaoZ, v_max);
        this.cargaMaxima = cargaMaxima;
        this.cargaAtual = 0;
    }

    public boolean carregar(int peso) {
        if (cargaAtual + peso <= cargaMaxima) {
            cargaAtual += peso;
            System.out.println(getNome() + " carregou " + peso + " kg. Carga atual: " + cargaAtual + " kg.");
            return true;
        } else {
            System.out.println(getNome() + " não pode carregar tanto peso! " + peso + " kg. Esta carga excede o limite!");
            return false;
        }
    }

    public int getCargaAtual() {
        return cargaAtual;
    }
    
    public int getCargaMaxima() {
        return cargaMaxima;
    }
}
