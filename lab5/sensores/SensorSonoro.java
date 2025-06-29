package sensores;
import robos.Robo;

/**
 * Sensor que detecta a intensidade sonora na posição atual do robô.
 */
public class SensorSonoro extends Sensor{


    public SensorSonoro(int raio, Robo robo){
        super(0, robo); // Sensor sonoro sempre tem raio 0.
    }

    /**
     * Monitora a intensidade sonora na posição do robô e retorna como String para registro no log.
     * @return Uma string com a intensidade do som detectado.
     */
    @Override
    public String monitorar(){
        // Obtém a intensidade do som do ambiente na posição do robô.
        int barulho=this.robo.getAmbiente().som[this.robo.getX1()][this.robo.getY1()][this.robo.getZ()];
        System.out.println("A intensidade do som é "+ barulho);
        return "A intensidade do som é "+ barulho;
    }

    /**
     * Monitora a intensidade sonora na posição do robô e retorna como int para uso em missões.
     * @return A intensidade do som detectado como um inteiro.
     */
    public int monitorarInt(){
        // Obtém a intensidade do som do ambiente na posição do robô.
        int barulho=this.robo.getAmbiente().som[this.robo.getX1()][this.robo.getY1()][this.robo.getZ()];
        System.out.println("A intensidade do som é "+ barulho);
        return barulho;
    }
    
}