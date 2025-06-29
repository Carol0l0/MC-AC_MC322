package robos.robosAutonomos;

import robos.robosAutonomos.missoes.*;
import exception.RoboDesligadoException;
import robos.Robo;

/**
 * Classe abstrata base para robôs que podem executar missões de forma autônoma.
 * Cada agente possui um log próprio.
 */
public abstract class AgenteInteligente extends Robo {

    // A missão atual que o agente deve executar.
    public Missao missao;
    // Objeto Log para registrar as atividades do agente durante a missão.
    public Log log; 

    /**
     * Construtor para criar um Agente Inteligente.
     * @param id O identificador do agente.
     * @param posicaoX A coordenada X inicial.
     * @param posicaoY A coordenada Y inicial.
     * @param posicaoZ A coordenada Z inicial.
     */
    public AgenteInteligente(String id, int posicaoX, int posicaoY, int posicaoZ) {
        super(id, posicaoX, posicaoY, posicaoZ);
        this.log = new Log(id + "_log.txt"); // Inicializa o log com um nome de arquivo baseado no ID.
    }

    /**
     * Define a missão que o agente deve realizar.
     * @param m A missão a ser definida.
     */
    public void definirMissao ( Missao m) {
        this.missao=m;
    }

    /**
     * Verifica se o agente possui uma missão definida.
     * @return Verdadeiro se tiver uma missão, falso caso contrário.
     */
    public boolean temMissao () {
        return missao!=null;
    }

    /**
     * Retorna o objeto de log associado a este agente.
     * @return O objeto Log.
     */
    public Log getLog() { 
        return this.log;
    }

    /**
     * Método abstrato para executar a missão do agente.
     * Deve ser implementado pelas subclasses.
     * @throws RoboDesligadoException Se o robô estiver desligado.
     */
    public abstract void executarMissao() throws RoboDesligadoException;

}