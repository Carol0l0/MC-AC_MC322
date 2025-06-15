public abstract class AgenteInteligente extends Robo {

    public AgenteInteligente(String id, int posicaoX, int posicaoY, int posicaoZ) {
        super(id, posicaoX, posicaoY, posicaoZ);
    }

    public abstract void executarMissao();

}