public class TerrestreDeCarga extends Terrestre {

    private int cargaMaxima; 
    private int cargaAtual; 
    private int nivelBateria; 

    public TerrestreDeCarga(String nome, int posicaoX, int posicaoY, int posicaoZ, int v_max, int cargaMaxima, int bateria) {
        super(nome, posicaoX, posicaoY, posicaoZ, v_max);
        this.cargaMaxima = cargaMaxima;
        this.cargaAtual = 0;
        this.nivelBateria = 100;
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

    @Override
    public boolean mover(int deltaX, int deltaY, Ambiente a) {
        boolean conseguiuMover = super.mover(deltaX, deltaY, a);
    
        if (!conseguiuMover) {
            System.out.println(getNome() + " nao conseguiu se mover!");
            return false;
        }
    
        if (nivelBateria >= 10) {
            nivelBateria -= 10;
        } else {
            nivelBateria = 0;
            System.out.println(getNome() + " esta sem bateria! Precisa recarregar.");
        }
    
        System.out.println(getNome() + " Posicao: (" + posicaoX + ", " + posicaoY + "). Nivel de bateria: " + nivelBateria + "%");
    
        return true;
    }
    

}