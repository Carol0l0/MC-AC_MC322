package sensores;
import exception.RoboDesligadoException;

/**
 * Interface para entidades que possuem a capacidade de acionar sensores.
 */

public interface Sensoreavel {
    
    /**
     * Aciona todos os sensores da entidade.
     * @return Uma string com o resultado do acionamento dos sensores.
     * @throws RoboDesligadoException Se a entidade estiver desligada ao tentar acionar os sensores.
     */
    public String acionarSensores() throws RoboDesligadoException;
    
}