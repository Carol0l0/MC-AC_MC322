package sensores;
import robos.Robo;

public class Sensor {
    
    protected int raio;
    protected Robo robo;

    public Sensor(int raio, Robo robo) {
        this.raio = raio;
        this.robo = robo;
    }

    public int getRaio() {
        return raio;
    }

    public String monitorar() {
        return "0"; 
    }
}
