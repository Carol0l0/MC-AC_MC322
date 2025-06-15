package robos;
// RoboPatrulheiro.java

import entidades.TipoEntidade;

public class RoboPatrulheiro extends AgenteInteligente{

    private int v_max;       

    // Construtor
    public RoboPatrulheiro(String id, int x1, int y1, int z, int v_max) {
        super(id, x1, y1, z); 
        this.v_max = v_max;
        this.setTipoEntidade(TipoEntidade.ROBO);
    }

    @Override
    public void executarMissao() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executarMissao'");
    }

}