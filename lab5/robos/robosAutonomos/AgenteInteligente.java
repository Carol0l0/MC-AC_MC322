package robos.robosAutonomos;

import robos.robosAutonomos.missoes.*;
import exception.RoboDesligadoException;
import robos.Robo;

public abstract class AgenteInteligente extends Robo {

    protected Missao missao;

    public AgenteInteligente(String id, int posicaoX, int posicaoY, int posicaoZ) {
        super(id, posicaoX, posicaoY, posicaoZ);
    }

    public void definirMissao ( Missao m) {
        this.missao=m;
    }

    public boolean temMissao () {
        return missao!=null;
    }

    public abstract void executarMissao() throws RoboDesligadoException;

}