package robos.robosAutonomos;

import robos.robosAutonomos.missoes.*;
import exception.RoboDesligadoException;
import robos.Robo;

public abstract class AgenteInteligente extends Robo {

    protected Missao missao;
    private Log log; 

    public AgenteInteligente(String id, int posicaoX, int posicaoY, int posicaoZ) {
        super(id, posicaoX, posicaoY, posicaoZ);
        this.log = new Log(id + "_log.txt");
    }

    public void definirMissao ( Missao m) {
        this.missao=m;
    }

    public boolean temMissao () {
        return missao!=null;
    }

    public Log getLog() { 
        return this.log;
    }

    public abstract void executarMissao() throws RoboDesligadoException;

}