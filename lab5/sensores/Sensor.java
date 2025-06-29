package sensores;
import robos.Robo;

/**
 * Classe base para todos os tipos de sensores.
 * Define propriedades e comportamentos comuns para sensores de robôs.
 */
public class Sensor {
    
    // O raio de alcance do sensor.
    protected int raio;
    // O robô ao qual este sensor está associado.
    protected Robo robo;

    /**
     * Construtor para criar uma instância de Sensor.
     * @param raio O raio de detecção do sensor.
     * @param robo O robô ao qual o sensor pertence.
     */
    public Sensor(int raio, Robo robo) {
        this.raio = raio;
        this.robo = robo;
    }

    /**
     * Retorna o raio de alcance do sensor.
     * @return O valor do raio.
     */
    public int getRaio() {
        return raio;
    }

    /**
     * Método a ser sobrescrito pelas subclasses para realizar a monitorização específica do sensor.
     * Por padrão, retorna "0".
     * @return Uma string representando o resultado da monitorização.
     */
    public String monitorar() {
        return "0"; 
    }
}