package robos.robosAutonomos.missoes;

import exception.RoboDesligadoException;
import robos.robosAutonomos.*;

public interface Missao {

    public TipoMissao getTipoMissao();

    public String getDescricao();

    public boolean executar (AgenteInteligente a) throws RoboDesligadoException;
}
